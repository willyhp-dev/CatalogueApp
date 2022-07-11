package id.co.nds.catalogue.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.stereotype.Service;

import id.co.nds.catalogue.entities.ProductEntity;
import id.co.nds.catalogue.entities.RoleEntity;
import id.co.nds.catalogue.entities.UserEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;
import id.co.nds.catalogue.globals.GlobalConstant;
import id.co.nds.catalogue.models.RoleModel;
import id.co.nds.catalogue.repos.RoleRepo;
import id.co.nds.catalogue.repos.UserRepo;
import id.co.nds.catalogue.validators.RoleValidator;

@Service
public class RoleServices {
    @Autowired
    private RoleRepo roleRepo;
    RoleValidator roleValidator = new RoleValidator();

    public RoleEntity add(RoleModel roleModel) throws ClientException {

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(roleModel.getName());
        roleEntity.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);
        roleEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        roleEntity.setCreatorId(
                roleModel.getActorId() == null ? 0 : roleModel.getActorId());

        return roleRepo.save(roleEntity);
    }

   

    public List<RoleEntity> findAll() {

        List<RoleEntity> roles = new ArrayList<>();
        roleRepo.findAll().forEach(roles::add);

        return roles;
    }

    public RoleEntity findById(String id)
            throws ClientException, NotFoundException {

        RoleEntity roleEntity = roleRepo.findById(id).orElse(null);
        roleValidator.nullCheckObject(roleEntity);

        return roleEntity;
    }

    public RoleEntity edit(RoleModel roleModel)
            throws ClientException, NotFoundException {

        if (!roleRepo.existsById(roleModel.getId())) {
            throw new NotFoundException("Cannot find Role with id" + roleModel);
        }

        RoleEntity roleEntity = new RoleEntity();
        roleEntity = findById(roleModel.getId());

        if (roleModel.getName() != null) {

            Long count = roleRepo.countByName(roleModel.getName());
            if (count > 0) {
                throw new ClientException("Role Name is already Existed");
            }

            roleEntity.setName(roleModel.getName());
        }

        roleEntity.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        roleEntity.setUpdaterId(
                roleModel.getActorId() == null ? 0 : roleModel.getActorId());

        return roleRepo.save(roleEntity);

    }

    public RoleEntity delete(RoleModel roleModel)
            throws ClientException, NotFoundException {

        if (!roleRepo.existsById(roleModel.getId())) {
            throw new NotFoundException(
                    "Cannot find Role with id :" + roleModel.getId());
        }

        RoleEntity roleEntity = new RoleEntity();
        roleEntity = findById(roleModel.getId());

        if (roleEntity.getRecStatus()
                .equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("Role id (" + roleModel.getId()
                    + ") is already been deleted");
        }

        roleEntity.setRecStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
        roleEntity.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        roleEntity.setDeleterId(
                roleModel.getActorId() == null ? 0 : roleModel.getActorId());

        return roleRepo.save(roleEntity);

    }

}
