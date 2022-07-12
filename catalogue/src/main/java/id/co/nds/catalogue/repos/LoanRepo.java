package id.co.nds.catalogue.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.co.nds.catalogue.entities.LoanEntity;
import id.co.nds.catalogue.entities.UserEntity;

public interface LoanRepo extends JpaRepository<LoanEntity, String> {
	
   
}
