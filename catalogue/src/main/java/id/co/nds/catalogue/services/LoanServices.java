package id.co.nds.catalogue.services;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.catalogue.entities.LoanEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;
import id.co.nds.catalogue.models.LoanModel;

import id.co.nds.catalogue.repos.LoanRepo;

@Service
public class LoanServices {

	@Autowired
	private LoanRepo loanRepo;

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {
			Exception.class })
	public LoanEntity doLoan(LoanModel loanModel)
			throws ClientException, NotFoundException {

		LoanEntity loan = new LoanEntity();
		
		loan.setId(loanModel.getId());
		loan.setLoanTerm(loanModel.getLoanTerm());
		loan.setUserId(loanModel.getUserId());
		loan.setRoleId(loanModel.getRoleId());
		loan.setLoanAmount(loanModel.getLoanAmount());
		loan.setLoanTerm(loanModel.getLoanTerm());
		loan.setInterestRate(loanModel.getInterestRate() / 100);
		loan.setTotalLoan(loanModel.getLoanAmount() / loanModel.getLoanTerm()
				* loanModel.getInterestRate() / 100);
		loan.setCustomerName(loanModel.getCustomerName());
		loan.setStartDate(new Timestamp(System.currentTimeMillis()));

		return loanRepo.save(loan);

	}
}
