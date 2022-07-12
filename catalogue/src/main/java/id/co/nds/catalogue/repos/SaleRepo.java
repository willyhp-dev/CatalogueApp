package id.co.nds.catalogue.repos;
import org.springframework.data.jpa.repository.JpaRepository;

import id.co.nds.catalogue.entities.SaleEntity;

public interface SaleRepo extends JpaRepository<SaleEntity, String> {
	
}
