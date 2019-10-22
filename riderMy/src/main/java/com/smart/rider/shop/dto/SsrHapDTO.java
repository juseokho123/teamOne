package com.smart.rider.shop.dto;

//shop+shoprelation 조회 할 값만 골라서 만든 DTO
public class SsrHapDTO {

	private String shopCode;
	private String contractCode;
	private String contractShopCode;
	private String memberId;
	private String shopName;
	
	
	
	public String getShopCode() {
		return shopCode;
	}



	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}



	public String getContractCode() {
		return contractCode;
	}



	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}



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



	public String getShopName() {
		return shopName;
	}



	public void setShopName(String shopName) {
		this.shopName = shopName;
	}



	@Override
	public String toString() {
		return "SsrHapDTO [shopCode=" + shopCode + ", contractCode=" + contractCode + ", contractShopCode="
				+ contractShopCode + ", memberId=" + memberId + ", shopName=" + shopName + "]";
	}
	
	

}
