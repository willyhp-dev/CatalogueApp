package id.co.nds.catalogue.models;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import id.co.nds.catalogue.controllers.ControllerGroup.PostingNew;


public class LoanModel {


	private String id;
	@NotNull(message = "User Id cannot be null", groups = {
		PostingNew.class })
	private Integer userId;

	@NotNull(message = "Role id cannot be null", groups = {
			PostingNew.class })
	@Pattern(regexp = "^(R)[0-9]{4}$", message ="Character Invalid (Should Start with R and digit numeric Min 4 digit)")
	private String roleId;

	@NotNull(message = "Loan Amount cannot be null", groups = {
		PostingNew.class })
	private Double loanAmount;

	@NotNull(message = "LoanTerm cannot be null", groups = {
		PostingNew.class })
	private Integer loanTerm;

	@NotNull(message = "Interest Rate cannot be null", groups = {
		PostingNew.class })
	private Double interestRate;

	@NotNull(message = "Total Loan cannot be null", groups = {
		PostingNew.class })
	private Double totalLoan;

	@NotNull(message = "CustomerName cannot be null", groups = {
		PostingNew.class })
	@Pattern(regexp = "^[a-zA-Z0-9]$", message = "Can't WhiteSpace")
	private String customerName;


	private Timestamp startDate;

	public String getId() { return id; }

	public void setId(String id) { this.id = id; }

	public Integer getUserId() { return userId; }

	public void setUserId(Integer userId) { this.userId = userId; }

	public String getRoleId() { return roleId; }

	public void setRoleId(String roleId) { this.roleId = roleId; }

	public Double getLoanAmount() { return loanAmount; }

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Integer getLoanTerm() { return loanTerm; }

	public void setLoanTerm(Integer loanTerm) { this.loanTerm = loanTerm; }

	public Double getInterestRate() { return interestRate; }

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public Double getTotalLoan() { return totalLoan; }

	public void setTotalLoan(Double totalLoan) { this.totalLoan = totalLoan; }

	public String getCustomerName() { return customerName; }

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Timestamp getStartDate() { return startDate; }

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

}
