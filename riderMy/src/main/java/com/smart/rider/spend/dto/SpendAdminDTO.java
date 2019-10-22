package com.smart.rider.spend.dto;

public class SpendAdminDTO {

	private String shopCode;
	private String contractCode;
	private String memberId;
	private String shopName;
	private String shopAddress;
	private String shopPhone;
	private String shopDate;
	
	private String contractUnitCode;
	private String contractStart;
	private String contractFinish;
	private String contractValidDate;
	private String contractDate;

	private String contractManagementState;
	private String contractShopCode;
	
	private String memberBirth;

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

	public String getContractUnitCode() {
		return contractUnitCode;
	}

	public void setContractUnitCode(String contractUnitCode) {
		this.contractUnitCode = contractUnitCode;
	}

	public String getContractStart() {
		return contractStart;
	}

	public void setContractStart(String contractStart) {
		this.contractStart = contractStart;
	}

	public String getContractFinish() {
		return contractFinish;
	}

	public void setContractFinish(String contractFinish) {
		this.contractFinish = contractFinish;
	}

	public String getContractValidDate() {
		return contractValidDate;
	}

	public void setContractValidDate(String contractValidDate) {
		this.contractValidDate = contractValidDate;
	}

	public String getContractDate() {
		return contractDate;
	}

	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}

	public String getContractManagementState() {
		return contractManagementState;
	}

	public void setContractManagementState(String contractManagementState) {
		this.contractManagementState = contractManagementState;
	}

	public String getContractShopCode() {
		return contractShopCode;
	}

	public void setContractShopCode(String contractShopCode) {
		this.contractShopCode = contractShopCode;
	}

	public String getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}

	@Override
	public String toString() {
		return "SpendAdminDTO [shopCode=" + shopCode + ", contractCode=" + contractCode + ", memberId=" + memberId
				+ ", shopName=" + shopName + ", shopAddress=" + shopAddress + ", shopPhone=" + shopPhone + ", shopDate="
				+ shopDate + ", contractUnitCode=" + contractUnitCode + ", contractStart=" + contractStart
				+ ", contractFinish=" + contractFinish + ", contractValidDate=" + contractValidDate + ", contractDate="
				+ contractDate + ", contractManagementState=" + contractManagementState + ", contractShopCode="
				+ contractShopCode + ", memberBirth=" + memberBirth + "]";
	}

}
