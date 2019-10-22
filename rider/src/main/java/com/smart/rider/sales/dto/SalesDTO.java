package com.smart.rider.sales.dto;

public class SalesDTO {
	
	private String salesCode;
	private String goodsCode;
	private String contractShopCode;
	private String goodsRentalCode;
	private String salesGroupCode;
	private String salesPayType;
	private String salesType;
	private String salesAmount;	
	private String salesDate;
	
	
	
	@Override
	public String toString() {
		return "SalesDTO [salesCode=" + salesCode + ", goodsCode=" + goodsCode + ", contractShopCode="
				+ contractShopCode + ", goodsRentalCode=" + goodsRentalCode + ", salesGroupCode=" + salesGroupCode
				+ ", salesPayType=" + salesPayType + ", salesType=" + salesType + ", salesAmount=" + salesAmount
				+ ", salesDate=" + salesDate + "]";
	}
	public String getGoodsRentalCode() {
		return goodsRentalCode;
	}
	public void setGoodsRentalCode(String goodsRentalCode) {
		this.goodsRentalCode = goodsRentalCode;
	}
	public String getSalesCode() {
		return salesCode;
	}
	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getContractShopCode() {
		return contractShopCode;
	}
	public void setContractShopCode(String contractShopCode) {
		this.contractShopCode = contractShopCode;
	}
	public String getSalesGroupCode() {
		return salesGroupCode;
	}
	public void setSalesGroupCode(String salesGroupCode) {
		this.salesGroupCode = salesGroupCode;
	}
	public String getSalesPayType() {
		return salesPayType;
	}
	public void setSalesPayType(String salesPayType) {
		this.salesPayType = salesPayType;
	}
	public String getSalesType() {
		return salesType;
	}
	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}
	public String getSalesAmount() {
		return salesAmount;
	}
	public void setSalesAmount(String salesAmount) {
		this.salesAmount = salesAmount;
	}
	public String getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}
	
	
		
}
