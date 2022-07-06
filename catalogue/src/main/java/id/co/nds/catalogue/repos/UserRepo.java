package id.co.nds.catalogue.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.catalogue.globals.GlobalConstant;
import id.co.nds.catalogue.entities.UserEntity;

@Repository
@Transactional

public interface UserRepo extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {
    @Query(value = " SELECT COUNT(*)FROM ms_users where rec_status = '" + GlobalConstant.REC_STATUS_ACTIVE
            + "' AND LOWER(call_number) = LOWER(:call_number)", nativeQuery  = true)
    long countByName(@Param("call_number") String callNumber);

    @Modifying
    @Query(value = "UPDATE ms_users SET rec_status = '" + GlobalConstant.REC_STATUS_NON_ACTIVE +"' deleter_id = ?2. deleted_date = NOW()" + "WHERE id = ?1", nativeQuery = true)
    Integer doDelete(Integer id, Integer deleterId);
}

