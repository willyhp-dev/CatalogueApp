package id.co.nds.catalogue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.catalogue.entities.LoanEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.models.LoanModel;
import id.co.nds.catalogue.models.ResponseModel;
import id.co.nds.catalogue.services.LoanServices;

@RestController
@RequestMapping("/loan")
public class LoanControler {
	
	@Autowired
	public LoanServices loanServices;

	@PostMapping("/add")
	public ResponseEntity<ResponseModel> postloan(
			@RequestBody LoanModel loanModel) {
		
		try {
			
			LoanEntity loan = loanServices.doLoan(loanModel);

			ResponseModel response = new ResponseModel();
			response.setMsg("New Loan is Successfully added");
			response.setData(loan);

			return ResponseEntity.ok(response);

		} catch (ClientException e) {
			// TODO: handle exception
			ResponseModel response = new ResponseModel();
			response.setMsg(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

		}  catch (Exception e) {

			ResponseModel response = new ResponseModel();
			response.setMsg("Sorry, there is a failure on our server");
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(response);
		}
	}
}
