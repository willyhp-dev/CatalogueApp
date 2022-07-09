package id.co.nds.catalogue.repos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.nds.catalogue.entities.ProductInfoEntity;

@Repository
@Transactional
public interface ProductInfoRepo extends JpaRepository<ProductInfoEntity, Integer>  {

    @Query(value = " SELECT p.*, c.name AS category_name  FROM ms_product AS p " +
            " JOIN ms_category AS c ON p.category_id = c.id" + " WHERE p.category_id = ?1", 
             nativeQuery = true)
    List<ProductInfoEntity> findAllByCategory(String categoryId);
}
