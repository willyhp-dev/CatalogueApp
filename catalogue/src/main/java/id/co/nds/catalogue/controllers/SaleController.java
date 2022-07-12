package id.co.nds.catalogue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.catalogue.entities.SaleEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;
import id.co.nds.catalogue.models.ResponseModel;
import id.co.nds.catalogue.models.SaleModel;
import id.co.nds.catalogue.services.SaleService;

@RestController
@RequestMapping("/sale")
public class SaleController {

	@Autowired
	public SaleService saleService;

	@PostMapping("/add")
	public ResponseEntity<ResponseModel> postSale(
			@RequestBody SaleModel saleModel) {

		try {

			SaleEntity sale = saleService.doSale(saleModel);

			ResponseModel response = new ResponseModel();
			response.setMsg("New Sale is successfully added");
			response.setData(sale);
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
