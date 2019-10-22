package com.smart.rider.customer.dto;

public class CustomerDTO {

	private String rentalCustomerCode;
	private String contractShopCode;
	private String goodsRentalCode;
	private String rentalCustomerName;
	private String rentalCustomerAddress;
	private String rentalCustomerPhone;
	private String rentalCustomerStart;
	private String rentalCustomerFinish;
	private char rentalCustomerOverdue;
	private String rentalCustomerDate;
	private String rentalCustomerReturn;
	public String getRentalCustomerCode() {
		return rentalCustomerCode;
	}
	public void setRentalCustomerCode(String rentalCustomerCode) {
		this.rentalCustomerCode = rentalCustomerCode;
	}
	public String getContractShopCode() {
		return contractShopCode;
	}
	public void setContractShopCode(String contractShopCode) {
		this.contractShopCode = contractShopCode;
	}
	public String getGoodsRentalCode() {
		return goodsRentalCode;
	}
	public void setGoodsRentalCode(String goodsRentalCode) {
		this.goodsRentalCode = goodsRentalCode;
	}
	public String getRentalCustomerName() {
		return rentalCustomerName;
	}
	public void setRentalCustomerName(String rentalCustomerName) {
		this.rentalCustomerName = rentalCustomerName;
	}
	public String getRentalCustomerAddress() {
		return rentalCustomerAddress;
	}
	public void setRentalCustomerAddress(String rentalCustomerAddress) {
		this.rentalCustomerAddress = rentalCustomerAddress;
	}
	public String getRentalCustomerPhone() {
		return rentalCustomerPhone;
	}
	public void setRentalCustomerPhone(String rentalCustomerPhone) {
		this.rentalCustomerPhone = rentalCustomerPhone;
	}
	public String getRentalCustomerStart() {
		return rentalCustomerStart;
	}
	public void setRentalCustomerStart(String rentalCustomerStart) {
		this.rentalCustomerStart = rentalCustomerStart;
	}
	public String getRentalCustomerFinish() {
		return rentalCustomerFinish;
	}
	public void setRentalCustomerFinish(String rentalCustomerFinish) {
		this.rentalCustomerFinish = rentalCustomerFinish;
	}
	public char getRentalCustomerOverdue() {
		return rentalCustomerOverdue;
	}
	public void setRentalCustomerOverdue(char rentalCustomerOverdue) {
		this.rentalCustomerOverdue = rentalCustomerOverdue;
	}
	public String getRentalCustomerDate() {
		return rentalCustomerDate;
	}
	public void setRentalCustomerDate(String rentalCustomerDate) {
		this.rentalCustomerDate = rentalCustomerDate;
	}
	public String getRentalCustomerReturn() {
		return rentalCustomerReturn;
	}
	public void setRentalCustomerReturn(String rentalCustomerReturn) {
		this.rentalCustomerReturn = rentalCustomerReturn;
	}
	@Override
	public String toString() {
		return "CustomerDTO [rentalCustomerCode=" + rentalCustomerCode + ", contractShopCode=" + contractShopCode
				+ ", goodsRentalCode=" + goodsRentalCode + ", rentalCustomerName=" + rentalCustomerName
				+ ", rentalCustomerAddress=" + rentalCustomerAddress + ", rentalCustomerPhone=" + rentalCustomerPhone
				+ ", rentalCustomerStart=" + rentalCustomerStart + ", rentalCustomerFinish=" + rentalCustomerFinish
				+ ", rentalCustomerOverdue=" + rentalCustomerOverdue + ", rentalCustomerDate=" + rentalCustomerDate
				+ ", rentalCustomerReturn=" + rentalCustomerReturn + "]";
	}
	
}
