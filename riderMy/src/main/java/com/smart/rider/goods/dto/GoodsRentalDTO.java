package com.smart.rider.goods.dto;

public class GoodsRentalDTO {
	private String goodsRentalCode;
	private String contractShopCode;
	private String goodsCode;
	private String goodsRentalPeriodPay;
	private String goodsRentalDeposit;
	private String goodsRentalPosition;
	private String goodsRentalDate;
	
	
	
	
	@Override
	public String toString() {
		return "GoodsRentalDTO [goodsRentalCode=" + goodsRentalCode + ", contractShopCode=" + contractShopCode
				+ ", goodsCode=" + goodsCode + ", goodsRentalPeriodPay=" + goodsRentalPeriodPay
				+ ", goodsRentalDeposit=" + goodsRentalDeposit + ", goodsRentalPosition=" + goodsRentalPosition
				+ ", goodsRentalDate=" + goodsRentalDate + "]";
	}
	public String getGoodsRentalCode() {
		return goodsRentalCode;
	}
	public void setGoodsRentalCode(String goodsRentalCode) {
		this.goodsRentalCode = goodsRentalCode;
	}
	public String getContractShopCode() {
		return contractShopCode;
	}
	public void setContractShopCode(String contractShopCode) {
		this.contractShopCode = contractShopCode;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsRentalPeriodPay() {
		return goodsRentalPeriodPay;
	}
	public void setGoodsRentalPeriodPay(String goodsRentalPeriodPay) {
		this.goodsRentalPeriodPay = goodsRentalPeriodPay;
	}
	public String getGoodsRentalDeposit() {
		return goodsRentalDeposit;
	}
	public void setGoodsRentalDeposit(String goodsRentalDeposit) {
		this.goodsRentalDeposit = goodsRentalDeposit;
	}
	public String getGoodsRentalPosition() {
		return goodsRentalPosition;
	}
	public void setGoodsRentalPosition(String goodsRentalPosition) {
		this.goodsRentalPosition = goodsRentalPosition;
	}
	public String getGoodsRentalDate() {
		return goodsRentalDate;
	}
	public void setGoodsRentalDate(String goodsRentalDate) {
		this.goodsRentalDate = goodsRentalDate;
	}
	
}
