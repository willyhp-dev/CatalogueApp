package id.co.nds.catalogue.repos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.nds.catalogue.entities.UserInfoEntity;

@Repository
@Transactional
public interface UserInfoRepo extends JpaRepository<UserInfoEntity, Integer>,
                JpaSpecificationExecutor<UserInfoEntity> {

        @Query(value = " SELECT p.*, c.name AS role_name  FROM ms_users AS p "
                        + " JOIN ms_role AS c ON p.role_id = c.id"
                        + " WHERE c.name = ?1", nativeQuery = true)
        List<UserInfoEntity> findByAllRole(String name);
}
