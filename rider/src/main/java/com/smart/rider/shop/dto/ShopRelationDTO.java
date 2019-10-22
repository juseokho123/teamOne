package com.smart.rider.shop.dto;

//계약매장코드(릴레이션)에 필요한 DTO
public class ShopRelationDTO {
	
	private String contractShopCode;
	private String memberId;
	private String shopCode;
	private String contractShopDate;
	
	public String getContractShopCode() {
		return contractShopCode;
	}
	public void setContractShopCode(String contractShopCode) {
		this.contractShopCode = contractShopCode;
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
	public String getContractShopDate() {
		return contractShopDate;
	}
	public void setContractShopDate(String contractShopDate) {
		this.contractShopDate = contractShopDate;
	}
	@Override
	public String toString() {
		return "ShopRelationDTO [contractShopCode=" + contractShopCode + ", memberId=" + memberId + ", shopCode="
				+ shopCode + ", contractShopDate=" + contractShopDate + "]";
	}
	
	
	
}
