package com.smart.rider.contract.dto;

//계약금관리 DTO
public class ManagementDTO {
	
	private String contractManagementCode;
	private String contractCode;
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
		return "ManagementDTO [contractManagementCode=" + contractManagementCode + ", contractCode=" + contractCode
				+ ", contractManagementAmout=" + contractManagementAmout + ", contractManagementUnpaid="
				+ contractManagementUnpaid + ", contractManagementState=" + contractManagementState
				+ ", contractManagementContents=" + contractManagementContents + ", contractManagementDate="
				+ contractManagementDate + "]";
	}
	

}


