package com.smart.rider.goods.dto;

public class GoodsPurchaseDTO {
	private String purchaseCode;
	private String accountCode;
	private String contractShopCode;
	private String goodsDbCode;
	private int purchasePay;
	private String purchaseState;
	private String purchaseDate;
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getContractShopCode() {
		return contractShopCode;
	}
	public void setContractShopCode(String contractShopCode) {
		this.contractShopCode = contractShopCode;
	}
	public String getGoodsDbCode() {
		return goodsDbCode;
	}
	public void setGoodsDbCode(String goodsDbCode) {
		this.goodsDbCode = goodsDbCode;
	}
	public int getPurchasePay() {
		return purchasePay;
	}
	public void setPurchasePay(int purchasePay) {
		this.purchasePay = purchasePay;
	}
	public String getPurchaseState() {
		return purchaseState;
	}
	public void setPurchaseState(String purchaseState) {
		this.purchaseState = purchaseState;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	@Override
	public String toString() {
		return "GoodsPurchaseDTO [purchaseCode=" + purchaseCode + ", accountCode=" + accountCode + ", contractShopCode="
				+ contractShopCode + ", goodsDbCode=" + goodsDbCode + ", purchasePay=" + purchasePay
				+ ", purchaseState=" + purchaseState + ", purchaseDate=" + purchaseDate + "]";
	}
	
	
	
}
