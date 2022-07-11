package id.co.nds.catalogue.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;
import id.co.nds.catalogue.globals.GlobalConstant;
import id.co.nds.catalogue.entities.UserEntity;
import id.co.nds.catalogue.entities.UserInfoEntity;
import id.co.nds.catalogue.models.Usermodel;
import id.co.nds.catalogue.repos.UserInfoRepo;
import id.co.nds.catalogue.repos.UserRepo;
import id.co.nds.catalogue.repos.specs.UserSpec;
import id.co.nds.catalogue.validators.UserValidator;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserInfoRepo userInfoRepo;

    UserValidator userValidator = new UserValidator();

    public List<UserInfoEntity> findByAllRole(String name)
            throws ClientException, NotFoundException {

        List<UserInfoEntity> userInfoEntities = userInfoRepo
                .findByAllRole(name);
        userValidator.nullCheckObject(userInfoEntities);

        return userInfoEntities;
    }

    public List<UserEntity> findUserByRoleId(String roleName)
            throws ClientException, NotFoundException {

        List<UserEntity> user = userRepo.findUserByRoleId(roleName);
        userValidator.nullCheckObject(user);

        return user;

    }

    public UserEntity add(Usermodel usermodel) throws ClientException {

        // userValidator.notNullCheckUserId(usermodel.getId());
        // userValidator.nullCheckUserFullname(usermodel.getFullname());
        // userValidator.nullCheckUserRoleId(usermodel.getRoleId());

        // userValidator.validateFullname(usermodel.getFullname());
        // userValidator.validateroleId(usermodel.getRoleId());

        long count = userRepo.countByName(usermodel.getCallnumber());
        if (count > 0) {
            throw new ClientException("call Number is already Exited");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setFullname(usermodel.getFullname());
        userEntity.setRole_id(usermodel.getRoleId());

        if (usermodel.getCallnumber().startsWith("+62")) {
            String changes = usermodel.getCallnumber().substring(3);
            String hasil = "0" + changes;
            try {

                userEntity.setCallNumber(hasil);
            } catch (NumberFormatException e) {
                // TODO: handle exception
                throw new ClientException("User call number is already Exists");
            }

        } else {

            try {
                userEntity.setCallNumber(usermodel.getCallnumber());
            } catch (NumberFormatException e) {
                // TODO: handle exception
                throw new ClientException("User call number is already Exists");

            }

        }
        userEntity.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);
        userEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        userEntity.setCreatorId(
                usermodel.getActorId() == null ? 0 : usermodel.getActorId());

        return userRepo.save(userEntity);

    }

    public List<UserEntity> findAll() {

        List<UserEntity> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);

        return users;
    }

    public List<UserEntity> findAllByCriteria(Usermodel usermodel) {

        List<UserEntity> users = new ArrayList<>();
        UserSpec specs = new UserSpec(usermodel);
        userRepo.findAll(specs).forEach(users::add);

        return users;
    }

    public UserEntity findById(Integer id)
            throws ClientException, NotFoundException {

        // userValidator.nullCheckUserId(id);
        // userValidator.validateUserId(id);

        UserEntity userEntity = userRepo.findById(id).orElse(null);
        userValidator.nullCheckObject(userEntity);

        return userEntity;

    }

    public UserEntity edit(Usermodel usermodel)
            throws ClientException, NotFoundException {

        // userValidator.nullCheckUserId(usermodel.getId());
        // userValidator.validateUserId(usermodel.getId());

        if (!userRepo.existsById(usermodel.getId())) {

            throw new NotFoundException(
                    "Cannot find User with id :" + usermodel.getId());
        }

        UserEntity userEntity = new UserEntity();
        userEntity = findById(usermodel.getId());

        if (usermodel.getFullname() != null) {

            // userValidator.validateFullname(usermodel.getFullname());

            userEntity.setFullname(usermodel.getFullname());
        }

        if (usermodel.getRoleId() != null) {

            // userValidator.validateroleId(usermodel.getRoleId());

            userEntity.setRole_id(usermodel.getRoleId());
        }

        if (usermodel.getCallnumber() != null) {
            // userValidator.validateCallNumber(usermodel.getCallnumber());

            Long count = userRepo.countByName(usermodel.getCallnumber());
            if (count > 0) {
                throw new ClientException("User Number is already Exicted");
            }

            userEntity.setCallNumber(usermodel.getCallnumber());
        }

        userEntity.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        userEntity.setUpdaterId(
                usermodel.getActorId() == null ? 0 : usermodel.getActorId());

        return userRepo.save(userEntity);
    }

    public UserEntity delete(Usermodel usermodel)
            throws ClientException, NotFoundException {

        // userValidator.nullCheckUserId(usermodel.getId());
        // userValidator.validateUserId(usermodel.getId());

        if (!userRepo.existsById(usermodel.getId())) {
            throw new NotFoundException(
                    "Cannot find User with id :" + usermodel.getId());
        }

        UserEntity userEntity = new UserEntity();
        userEntity = findById(usermodel.getId());

        if (userEntity.getRecStatus()
                .equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("User id (" + usermodel.getId()
                    + ") is Already been deleted");
        }

        userEntity.setRecStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
        userEntity.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        userEntity.setDeleterId(
                usermodel.getActorId() == null ? 0 : usermodel.getActorId());

        return userRepo.save(userEntity);
    }
}
