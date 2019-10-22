package com.smart.rider.goods.dto;

public class GoodsDTO {
	private String goodsCode;
	private String goodsDbCode;
	private String purchaseCode;
	private String contractShopCode;
	private int goodsPay;
	private String goodsPosition;
	private String goodsRental;
	private String goodsState;
	private String goodsCompletionState;
	private String goodsRentalState;
	private String goodsInventoryDate;
	private String goodsDate;
	
	
	
	@Override
	public String toString() {
		return "GoodsDTO [goodsCode=" + goodsCode + ", goodsDbCode=" + goodsDbCode + ", purchaseCode=" + purchaseCode
				+ ", contractShopCode=" + contractShopCode + ", goodsPay=" + goodsPay + ", goodsPosition="
				+ goodsPosition + ", goodsRental=" + goodsRental + ", goodsState=" + goodsState
				+ ", goodsCompletionState=" + goodsCompletionState + ", goodsRentalState=" + goodsRentalState
				+ ", goodsInventoryDate=" + goodsInventoryDate + ", goodsDate=" + goodsDate + "]";
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsDbCode() {
		return goodsDbCode;
	}
	public void setGoodsDbCode(String goodsDbCode) {
		this.goodsDbCode = goodsDbCode;
	}
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public String getContractShopCode() {
		return contractShopCode;
	}
	public void setContractShopCode(String contractShopCode) {
		this.contractShopCode = contractShopCode;
	}
	public int getGoodsPay() {
		return goodsPay;
	}
	public void setGoodsPay(int goodsPay) {
		this.goodsPay = goodsPay;
	}
	public String getGoodsPosition() {
		return goodsPosition;
	}
	public void setGoodsPosition(String goodsPosition) {
		this.goodsPosition = goodsPosition;
	}
	public String getGoodsRental() {
		return goodsRental;
	}
	public void setGoodsRental(String goodsRental) {
		this.goodsRental = goodsRental;
	}
	public String getGoodsState() {
		return goodsState;
	}
	public void setGoodsState(String goodsState) {
		this.goodsState = goodsState;
	}
	public String getGoodsCompletionState() {
		return goodsCompletionState;
	}
	public void setGoodsCompletionState(String goodsCompletionState) {
		this.goodsCompletionState = goodsCompletionState;
	}
	public String getGoodsRentalState() {
		return goodsRentalState;
	}
	public void setGoodsRentalState(String goodsRentalState) {
		this.goodsRentalState = goodsRentalState;
	}
	public String getGoodsInventoryDate() {
		return goodsInventoryDate;
	}
	public void setGoodsInventoryDate(String goodsInventoryDate) {
		this.goodsInventoryDate = goodsInventoryDate;
	}
	public String getGoodsDate() {
		return goodsDate;
	}
	public void setGoodsDate(String goodsDate) {
		this.goodsDate = goodsDate;
	}
	

	
	

}