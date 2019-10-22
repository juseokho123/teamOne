package com.smart.rider.contract.dto;

//계약금관리 DTO
public class ContractManagementDTO {
	private String contractManagementCode;
	private String contractCode;
	private String memberId;
	private String contractMethod;
	private int contractPay;
	private int contractManagementAmout;
	private int contractManagementUnpaid;
	private String contractManagementState;
	private String contractManagementContents;
	private String contractManagementDate;
	
	
	public String getContractManagementCode() {
		return contractManagementCode;
	}
	public void setContractManagementCode(String contractManagementCode) {
		this.contractManagementCode = contractManagementCode;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getContractMethod() {
		return contractMethod;
	}
	public void setContractMethod(String contractMethod) {
		this.contractMethod = contractMethod;
	}
	public int getContractPay() {
		return contractPay;
	}
	public void setContractPay(int contractPay) {
		this.contractPay = contractPay;
	}
	public int getContractManagementAmout() {
		return contractManagementAmout;
	}
	public void setContractManagementAmout(int contractManagementAmout) {
		this.contractManagementAmout = contractManagementAmout;
	}
	public int getContractManagementUnpaid() {
		return contractManagementUnpaid;
	}
	public void setContractManagementUnpaid(int contractManagementUnpaid) {
		this.contractManagementUnpaid = contractManagementUnpaid;
	}
	public String getContractManagementState() {
		return contractManagementState;
	}
	public void setContractManagementState(String contractManagementState) {
		this.contractManagementState = contractManagementState;
	}
	public String getContractManagementContents() {
		return contractManagementContents;
	}
	public void setContractManagementContents(String contractManagementContents) {
		this.contractManagementContents = contractManagementContents;
	}
	public String getContractManagementDate() {
		return contractManagementDate;
	}
	public void setContractManagementDate(String contractManagementDate) {
		this.contractManagementDate = contractManagementDate;
	}
	@Override
	public String toString() {
		return "ContractManagementDTO [contractManagementCode=" + contractManagementCode + ", contractCode="
				+ contractCode + ", memberId=" + memberId + ", contractMethod=" + contractMethod + ", contractPay="
				+ contractPay + ", contractManagementAmout=" + contractManagementAmout + ", contractManagementUnpaid="
				+ contractManagementUnpaid + ", contractManagementState=" + contractManagementState
				+ ", contractManagementContents=" + contractManagementContents + ", contractManagementDate="
				+ contractManagementDate + "]";
	}
	
	
	
	
}
