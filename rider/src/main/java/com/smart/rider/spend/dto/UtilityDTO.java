package com.smart.rider.spend.dto;

public class UtilityDTO {

	private String spendUtilityCode;
	private String subjectCode;
	private String contractShopCode;
	private int spendUtilityPay;
	private String spendUtilityContents;
	private String spendUtilityDate;
	
	public String getSpendUtilityCode() {
		return spendUtilityCode;
	}
	public void setSpendUtilityCode(String spendUtilityCode) {
		this.spendUtilityCode = spendUtilityCode;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getContractShopCode() {
		return contractShopCode;
	}
	public void setContractShopCode(String contractShopCode) {
		this.contractShopCode = contractShopCode;
	}
	public int getSpendUtilityPay() {
		return spendUtilityPay;
	}
	public void setSpendUtilityPay(int spendUtilityPay) {
		this.spendUtilityPay = spendUtilityPay;
	}
	public String getSpendUtilityContents() {
		return spendUtilityContents;
	}
	public void setSpendUtilityContents(String spendUtilityContents) {
		this.spendUtilityContents = spendUtilityContents;
	}
	public String getSpendUtilityDate() {
		return spendUtilityDate;
	}
	public void setSpendUtilityDate(String spendUtilityDate) {
		this.spendUtilityDate = spendUtilityDate;
	}
	
	@Override
	public String toString() {
		return "UtilityDTO [spendUtilityCode=" + spendUtilityCode + ", subjectCode=" + subjectCode
				+ ", contractShopCode=" + contractShopCode + ", spendUtilityPay=" + spendUtilityPay
				+ ", spendUtilityContents=" + spendUtilityContents + ", spendUtilityDate=" + spendUtilityDate + "]";
	}
}
