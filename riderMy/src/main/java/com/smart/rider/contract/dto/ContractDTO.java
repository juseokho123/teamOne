package com.smart.rider.contract.dto;

//계약 관리 DTO
public class ContractDTO {
	
	private String contractCode;
	private String contractUnitCode;
	private String memberId;
	private String contractStart;
	private String contractFinish;
	private String contractValidDate;
	private String contractMethod;
	private int contractPay;
	private String contractInformation;
	private String contractDate;
	
	
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getContractUnitCode() {
		return contractUnitCode;
	}
	public void setContractUnitCode(String contractUnitCode) {
		this.contractUnitCode = contractUnitCode;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getContractStart() {
		return contractStart;
	}
	public void setContractStart(String contractStart) {
		this.contractStart = contractStart;
	}
	public String getContractFinish() {
		return contractFinish;
	}
	public void setContractFinish(String contractFinish) {
		this.contractFinish = contractFinish;
	}
	public String getContractValidDate() {
		return contractValidDate;
	}
	public void setContractValidDate(String contractValidDate) {
		this.contractValidDate = contractValidDate;
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
	public String getContractInformation() {
		return contractInformation;
	}
	public void setContractInformation(String contractInformation) {
		this.contractInformation = contractInformation;
	}
	public String getContractDate() {
		return contractDate;
	}
	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}
	@Override
	public String toString() {
		return "ContractDTO [contractCode=" + contractCode + ", contractUnitCode=" + contractUnitCode + ", memberId="
				+ memberId + ", contractStart=" + contractStart + ", contractFinish=" + contractFinish
				+ ", contractValidDate=" + contractValidDate + ", contractMethod=" + contractMethod + ", contractPay="
				+ contractPay + ",contractInformation="+ contractInformation + ", contractDate=" + contractDate + "]";
	}

	

	
	
}
