package com.smart.rider.shop.dto;

//POS관리 DTO
public class PosDTO {

	private String posCode;
	private String contractShopCode;
	private String posName;
	private String posNumber;
	private String posUse;
	
	
	public String getPosCode() {
		return posCode;
	}
	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}
	public String getContractShopCode() {
		return contractShopCode;
	}
	public void setContractShopCode(String contractShopCode) {
		this.contractShopCode = contractShopCode;
	}
	public String getPosName() {
		return posName;
	}
	public void setPosName(String posName) {
		this.posName = posName;
	}
	public String getPosNumber() {
		return posNumber;
	}
	public void setPosNumber(String posNumber) {
		this.posNumber = posNumber;
	}
	public String getPosUse() {
		return posUse;
	}
	public void setPosUse(String posUse) {
		this.posUse = posUse;
	}
	@Override
	public String toString() {
		return "PosDTO [posCode=" + posCode + ", contractShopCode=" + contractShopCode + ", posName=" + posName
				+ ", posNumber=" + posNumber + ", posUse=" + posUse + "]";
	}
	


	
}
