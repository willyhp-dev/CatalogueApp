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
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.catalogue.controllers.ControllerGroup.DeletingById;
import id.co.nds.catalogue.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.catalogue.controllers.ControllerGroup.PostingNew;
import id.co.nds.catalogue.controllers.ControllerGroup.UpdatingById;
import id.co.nds.catalogue.entities.UserEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;

import id.co.nds.catalogue.models.ResponseModel;
import id.co.nds.catalogue.models.Usermodel;
import id.co.nds.catalogue.services.UserService;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/adduser")

    public ResponseEntity<ResponseModel> postUserController
    (@Validated(PostingNew.class)  @RequestBody Usermodel usermodel) throws ClientException {

      
            UserEntity userEntity = userService.add(usermodel);

            ResponseModel response = new ResponseModel();
            response.setMsg("New User is successfully added");
            response.setData(userEntity);
            return ResponseEntity.ok(response);

    }

    @GetMapping(value = "/getuser")
    public ResponseEntity<ResponseModel> getAllProductController() {

        try {

            List<UserEntity> userEntity = userService.findAll();

            ResponseModel response = new ResponseModel();
            response.setMsg("Request successfully");
            response.setData(userEntity);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // TODO: handle exception
            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is failure on our server");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/getuser/search")
    public ResponseEntity<ResponseModel> searchUserController(
            @Validated(GettingAllByCriteria.class) @RequestBody Usermodel usermodel)
        throws ClientException {

            List<UserEntity> users = userService.findAllByCriteria(usermodel);

            ResponseModel response = new ResponseModel();
            response.setMsg("Request Successfully");
            response.setData(users);
            return ResponseEntity.ok(response);

    }

    @GetMapping(value = "/getuser/{id}")
    public ResponseEntity<ResponseModel> getUserByIdController
    (@NotNull @PositiveOrZero @PathVariable Integer id)
    throws ClientException, NotFoundException {

       

            UserEntity userEntity = userService.findById(id);

            ResponseModel response = new ResponseModel();
            response.setMsg("Request successfully");
            response.setData(userEntity);
            return ResponseEntity.ok(response);

       
    }

    @PutMapping(value = "/updateuser")
    public ResponseEntity<ResponseModel> putUserController
    (@Validated(UpdatingById.class) @RequestBody Usermodel usermodel)
    throws ClientException, NotFoundException {

        

            UserEntity userEntity = userService.edit(usermodel);

            ResponseModel response = new ResponseModel();
            response.setMsg("User");
            response.setData(userEntity);
            return ResponseEntity.ok(response);

    }

    @DeleteMapping(value = "/deleteuser")
    public ResponseEntity<ResponseModel> deleteUserController
    (@Validated(DeletingById.class) @RequestBody Usermodel usermodel)
     throws ClientException, NotFoundException  {

       
            UserEntity userEntity = userService.delete(usermodel);

            ResponseModel response = new ResponseModel();
            response.setMsg("User is successfully deleted");
            response.setData(userEntity);
            return ResponseEntity.ok(response);

    }

}
