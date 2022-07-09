package id.co.nds.catalogue.controllers;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import id.co.nds.catalogue.controllers.ControllerGroup.DeletingById;
import id.co.nds.catalogue.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.catalogue.controllers.ControllerGroup.PostingNew;
import id.co.nds.catalogue.controllers.ControllerGroup.UpdatingById;
import id.co.nds.catalogue.entities.ProductEntity;
import id.co.nds.catalogue.entities.ProductInfoEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;
import id.co.nds.catalogue.models.ProductModel;
import id.co.nds.catalogue.models.ResponseModel;
import id.co.nds.catalogue.services.ProductServices;

@RestController
@Validated
@RequestMapping("/product")
public class ProductController {
        @Autowired
        private ProductServices productServices;

        @PostMapping(value = "/add")

        public ResponseEntity<ResponseModel> postProductController(
                        @Validated(PostingNew.class) @RequestBody ProductModel productModel)
                        throws ClientException, InvalidFormatException {

                ProductEntity product = productServices.add(productModel);

                ResponseModel response = new ResponseModel();
                response.setMsg("New product is successfully added");
                response.setData(product);
                return ResponseEntity.ok(response);
        }

        @GetMapping(value = "/get")
        public ResponseEntity<ResponseModel> getAllProductController() throws ClientException {

                List<ProductEntity> products = productServices.findAll();

                ResponseModel response = new ResponseModel();
                response.setMsg("Request successfully");
                response.setData(products);
                return ResponseEntity.ok(response);

        }

        @GetMapping(value = "/get/search")
        public ResponseEntity<ResponseModel> searchProductController(
                        @Validated(GettingAllByCriteria.class) @RequestBody ProductModel productModel)
                        throws ClientException, InvalidFormatException {

                List<ProductEntity> products = productServices.findAllByCriteria(productModel);

                ResponseModel response = new ResponseModel();
                response.setMsg("Request Successfully");
                response.setData(products);
                return ResponseEntity.ok(response);

        }

        @GetMapping(value = "/get/info")
        public ResponseEntity<ResponseModel> getAllByCategoryController
        (@RequestParam String categoryId) {

                try {

                        List<ProductInfoEntity> product = productServices.findAllByCategories(categoryId);

                        ResponseModel response = new ResponseModel();
                        response.setMsg("Request successfully");
                        response.setData(product);
                        return ResponseEntity.ok(response);

                } catch (ClientException e) {
                        // TODO: handle exception
                        ResponseModel response = new ResponseModel();
                        response.setMsg(e.getMessage());
                        return ResponseEntity.badRequest().body(response);

                } catch (NotFoundException e) {

                        ResponseModel response = new ResponseModel();
                        response.setMsg(e.getMessage());
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

                } catch (Exception e) {

                        ResponseModel response = new ResponseModel();
                        response.setMsg("sorry, there is a failure on our server");
                        e.printStackTrace();
                        return ResponseEntity.internalServerError().body(response);

                }

        }

        @GetMapping(value = "/get/{id}")
        public ResponseEntity<ResponseModel> getProductByIdController(
                        @NotNull @PositiveOrZero @PathVariable Integer id)
                        throws ClientException, NotFoundException, InvalidFormatException {

                ProductEntity product = productServices.findById(id);

                ResponseModel response = new ResponseModel();
                response.setMsg("Request successfully");
                response.setData(product);
                return ResponseEntity.ok(response);

        }

        @PutMapping(value = "/update")
        public ResponseEntity<ResponseModel> putProductController(
                        @Validated(UpdatingById.class) @RequestBody ProductModel productModel)
                        throws ClientException, NotFoundException, InvalidFormatException {

                ProductEntity product = productServices.edit(productModel);

                ResponseModel response = new ResponseModel();
                response.setMsg("Product is successfully updated");
                response.setData(product);
                return ResponseEntity.ok(response);

        }

        @DeleteMapping(value = "/delete")
        public ResponseEntity<ResponseModel> deleteProductController(
                        @Validated(DeletingById.class) @RequestBody ProductModel productModel)
                        throws ClientException, NotFoundException, InvalidFormatException {

                ProductEntity product = productServices.delete(productModel);

                ResponseModel response = new ResponseModel();
                response.setMsg("Product is successfully deleted");
                response.setData(product);
                return ResponseEntity.ok(response);

        }

}
