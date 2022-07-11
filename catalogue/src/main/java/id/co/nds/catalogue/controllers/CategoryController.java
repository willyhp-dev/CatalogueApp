package id.co.nds.catalogue.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.catalogue.entities.CategoryEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;
import id.co.nds.catalogue.models.CategoryModel;
import id.co.nds.catalogue.models.ResponseModel;
import id.co.nds.catalogue.services.CategoryServices;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryServices categoryServices;

    @PostMapping(value = "/post")
    private ResponseEntity<ResponseModel> postCategoryController(
            @RequestBody CategoryModel categoryModel) throws ClientException {

        // try {
        CategoryEntity categoryEntity = categoryServices.add(categoryModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("New Category is successfully added");
        response.setData(categoryEntity);
        return ResponseEntity.ok(response);
        // } catch (ClientException e) {
        // // TODO: handle exception
        // ResponseModel response = new ResponseModel();
        // response.setMsg(e.getMessage());
        // return ResponseEntity.badRequest().body(response);

        // } catch (Exception e) {

        // ResponseModel response = new ResponseModel();
        // response.setMsg("Sorry, there is a failure on our server");
        // e.printStackTrace();
        // return ResponseEntity.internalServerError().body(response);

        // }
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseModel> getAllCategoryController()
            throws ClientException {

        // try {
        List<CategoryEntity> products = categoryServices.findAll();

        ResponseModel response = new ResponseModel();
        response.setMsg("Request successfully");
        response.setData(products);
        return ResponseEntity.ok(response);

        // } catch (Exception e) {
        // // TODO: handle exception
        // ResponseModel response = new ResponseModel();
        // response.setMsg("Sorry, there is failure on our server");
        // e.printStackTrace();
        // return ResponseEntity.internalServerError().body(response);
        // }

    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseModel> getUserByIdController(
            @PathVariable("id") String id)
            throws ClientException, NotFoundException {

        // try {

        CategoryEntity categoryEntity = categoryServices.findById(id);

        ResponseModel response = new ResponseModel();
        response.setMsg("Request successfully");
        response.setData(categoryEntity);
        return ResponseEntity.ok(response);

        // } catch (ClientException e) {
        // // TODO: handle exception
        // ResponseModel response = new ResponseModel();
        // response.setMsg(e.getMessage());
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        // } catch (NotFoundException e) {

        // ResponseModel response = new ResponseModel();
        // response.setMsg("Sorry, there is a failure on our server");
        // e.printStackTrace();
        // return ResponseEntity.internalServerError().body(response);

        // } catch (Exception e) {

        // ResponseModel response = new ResponseModel();
        // response.setMsg("Sorry, there is a failure on our server");
        // e.printStackTrace();
        // return ResponseEntity.internalServerError().body(response);
        // }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseModel> putUserController(
            @RequestBody CategoryModel categoryModel)
            throws ClientException, NotFoundException {

        // try {

        CategoryEntity categoryEntity = categoryServices.edit(categoryModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("User");
        response.setData(categoryEntity);
        return ResponseEntity.ok(response);

        // } catch (ClientException e) {
        // // TODO: handle exception

        // ResponseModel response = new ResponseModel();
        // response.setMsg(e.getMessage());
        // return ResponseEntity.badRequest().body(response);

        // } catch (Exception e) {

        // ResponseModel response = new ResponseModel();
        // response.setMsg("Sorry, there is a failure on our server");
        // e.printStackTrace();
        // return ResponseEntity.internalServerError().body(response);

        // }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseModel> deleteUserController(
            @RequestBody CategoryModel categoryModel)
            throws ClientException, NotFoundException {

        // try {
        CategoryEntity categoryEntity = categoryServices.delete(categoryModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Category is successfully deleted");
        response.setData(categoryEntity);
        return ResponseEntity.ok(response);

        // } catch (ClientException e) {
        // // TODO: handle exception
        // ResponseModel response = new ResponseModel();
        // response.setMsg(e.getMessage());
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        // } catch (NotFoundException e) {

        // ResponseModel response = new ResponseModel();
        // response.setMsg(e.getMessage());
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        // } catch (Exception e) {

        // ResponseModel response = new ResponseModel();
        // response.setMsg("sorry, there is a failure on our server");
        // e.printStackTrace();
        // return ResponseEntity.internalServerError().body(response);

        // }

    }

}
