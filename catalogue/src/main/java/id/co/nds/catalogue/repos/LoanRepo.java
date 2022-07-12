package id.co.nds.catalogue.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.nds.catalogue.entities.LoanEntity;

public interface LoanRepo extends JpaRepository<LoanEntity, String> {
	
}
