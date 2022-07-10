package id.co.nds.catalogue.controllers;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.beans.factory.annotation.Autowired;
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
import id.co.nds.catalogue.controllers.ControllerGroup.PostingNew;
import id.co.nds.catalogue.controllers.ControllerGroup.UpdatingById;
import id.co.nds.catalogue.entities.RoleEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;
import id.co.nds.catalogue.models.ResponseModel;
import id.co.nds.catalogue.models.RoleModel;
import id.co.nds.catalogue.services.RoleServices;

@RestController
@Validated
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleServices roleServices;

    @PostMapping(value = "/post")
    public ResponseEntity<ResponseModel> postRoleController(
            @Validated(PostingNew.class) @RequestBody RoleModel roleModel)
            throws ClientException {

        RoleEntity roleEntity = roleServices.add(roleModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("New Role is successfully added");
        response.setData(roleEntity);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseModel> getAllRoleController() {

        List<RoleEntity> roles = roleServices.findAll();

        ResponseModel response = new ResponseModel();
        response.setMsg("Request successfully");
        response.setData(roles);
        return ResponseEntity.ok(response);

    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseModel> getRoleByIdController(
            @NotNull @PositiveOrZero @PathVariable String id)
            throws ClientException, NotFoundException {

        RoleEntity roleEntity = roleServices.findById(id);

        ResponseModel response = new ResponseModel();
        response.setMsg("Request successfully");
        response.setData(roleEntity);
        return ResponseEntity.ok(response);

    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseModel> putRoleController(
            @Validated(UpdatingById.class) @RequestBody RoleModel roleModel)
            throws ClientException, NotFoundException {

        RoleEntity roleEntity = roleServices.edit(roleModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Role is successfully updated");
        response.setData(roleEntity);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseModel> deleteRoleProductController(
            @Validated(DeletingById.class) @RequestBody RoleModel roleModel)
            throws ClientException, NotFoundException {

        RoleEntity roleEntity = roleServices.delete(roleModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Role is successfully deleted");
        response.setData(roleEntity);
        return ResponseEntity.ok(response);

    }
}
