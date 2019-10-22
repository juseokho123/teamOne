package com.smart.rider.spend.dto;

// 지출_공과금 테이블 조인 DTO
public class JoinUtilityDTO {
	
	// spend_utility table
	private String spendUtilityCode;
	private String subjectCode;
	private String contractShopCode;
	private int spendUtilityPay;
	private String spendUtilityContents;
	private String spendUtilityDate;
	
	// subject table
	private String subjectNumber;
	private String subjectName;
	
	// shop_relation table
	private String memberId;
	private String shopCode;
	
	// setters and getters
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
	public String getSubjectNumber() {
		return subjectNumber;
	}
	public void setSubjectNumber(String subjectNumber) {
		this.subjectNumber = subjectNumber;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getShopCode() {
		return shopCode;
	}
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	
	// toString
	@Override
	public String toString() {
		return "JoinUtilityDTO [spendUtilityCode=" + spendUtilityCode + ", subjectCode=" + subjectCode
				+ ", contractShopCode=" + contractShopCode + ", spendUtilityPay=" + spendUtilityPay
				+ ", spendUtilityContents=" + spendUtilityContents + ", spendUtilityDate=" + spendUtilityDate
				+ ", subjectNumber=" + subjectNumber + ", subjectName=" + subjectName + ", memberId=" + memberId
				+ ", shopCode=" + shopCode + "]";
	}
}
