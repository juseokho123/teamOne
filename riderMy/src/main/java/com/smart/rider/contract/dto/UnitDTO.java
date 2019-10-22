package com.smart.rider.contract.dto;

//계약단가표DTO 
public class UnitDTO {
	
	private String contractUnitCode;
	private String memberId;
	private int  contractUnitMonth;
	private int contractUnitOneyear;
	private int contractUnitTwoyear;
	private String contractUnitDate;
	
	
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
	public int getContractUnitMonth() {
		return contractUnitMonth;
	}
	public void setContractUnitMonth(int contractUnitMonth) {
		this.contractUnitMonth = contractUnitMonth;
	}
	public int getContractUnitOneyear() {
		return contractUnitOneyear;
	}
	public void setContractUnitOneyear(int contractUnitOneyear) {
		this.contractUnitOneyear = contractUnitOneyear;
	}
	public int getContractUnitTwoyear() {
		return contractUnitTwoyear;
	}
	public void setContractUnitTwoyear(int contractUnitTwoyear) {
		this.contractUnitTwoyear = contractUnitTwoyear;
	}
	public String getContractUnitDate() {
		return contractUnitDate;
	}
	public void setContractUnitDate(String contractUnitDate) {
		this.contractUnitDate = contractUnitDate;
	}
	
	@Override
	public String toString() {
		return "UnitDTO [contractUnitCode=" + contractUnitCode + ", memberId=" + memberId + ", contractUnitMonth="
				+ contractUnitMonth + ", contractUnitOneyear=" + contractUnitOneyear + ", contractUnitTwoyear="
				+ contractUnitTwoyear + ", contractUnitDate=" + contractUnitDate + "]";
	}
	
	
	
}
