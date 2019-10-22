package com.smart.rider.shop.dto;

//매장관리에 필요한 DTO
public class ShopDTO {

	private String shopCode;
	private String contractCode;
	private String memberId;
	private String shopName;
	private String shopAddress;
	private String shopPhone;
	private String shopDate;
	
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
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public String getShopPhone() {
		return shopPhone;
	}
	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}
	public String getShopDate() {
		return shopDate;
	}
	public void setShopDate(String shopDate) {
		this.shopDate = shopDate;
	}
	@Override
	public String toString() {
		return "ShopDTO [shopCode=" + shopCode + ", contractCode=" + contractCode + ", memberId=" + memberId
				+ ", shopName=" + shopName + ", shopAddress=" + shopAddress + ", shopPhone=" + shopPhone + ", shopDate="
				+ shopDate + "]";
	}
	
	
}
