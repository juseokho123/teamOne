package com.smart.rider.account.dto;

//거래처관리에 필요한 DTO
public class AccountDTO {

	private String accountCode;
	private String contractShopCode;
	private String accountMarket;
	private String accountName;
	private String accountPhone;
	private String accountAddress;
	private String accountCategory;
	private String accountDate;

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
	public String getAccountMarket() {
		return accountMarket;
	}
	public void setAccountMarket(String accountMarket) {
		this.accountMarket = accountMarket;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountPhone() {
		return accountPhone;
	}
	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}
	public String getAccountAddress() {
		return accountAddress;
	}
	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}
	public String getAccountCategory() {
		return accountCategory;
	}
	public void setAccountCategory(String accountCategory) {
		this.accountCategory = accountCategory;
	}
	public String getAccountDate() {
		return accountDate;
	}
	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}
	@Override
	public String toString() {
		return "AccountDTO [accountCode=" + accountCode + ", contractShopCode=" + contractShopCode + ", accountMarket="
				+ accountMarket + ", accountName=" + accountName + ", accountPhone=" + accountPhone
				+ ", accountAddress=" + accountAddress + ", accountCategory=" + accountCategory + ", accountDate="
				+ accountDate + "]";
	}

	
	
}
