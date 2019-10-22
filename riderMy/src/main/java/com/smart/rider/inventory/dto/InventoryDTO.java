package com.smart.rider.inventory.dto;

public class InventoryDTO {
	private String inventoryCode;
	private String goodsCode;
	private String purchaseCode;
	private String contractShopCode;
	private String inventoryState;
	private String inventoryDate;
	public String getInventoryCode() {
		return inventoryCode;
	}
	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
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
	public String getInventoryState() {
		return inventoryState;
	}
	public void setInventoryState(String inventoryState) {
		this.inventoryState = inventoryState;
	}
	public String getInventoryDate() {
		return inventoryDate;
	}
	public void setInventoryDate(String inventoryDate) {
		this.inventoryDate = inventoryDate;
	}
	@Override
	public String toString() {
		return "InventoryDTO [inventoryCode=" + inventoryCode + ", goodsCode=" + goodsCode + ", purchaseCode="
				+ purchaseCode + ", contractShopCode=" + contractShopCode + ", inventoryState=" + inventoryState
				+ ", inventoryDate=" + inventoryDate + "]";
	}
	
	

}
