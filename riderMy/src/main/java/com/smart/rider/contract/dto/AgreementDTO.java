package com.smart.rider.contract.dto;

//계약관리DTO + 회원관리DTO + 계약금관리DTO 필요한 데이터들 
public class AgreementDTO {

	private String contractCode;
	private String contractUnitCode;
	private String memberId;
	private String memberName;
	private String memberPhone;
	private String contractStart;
	private String contractFinish;
	private String contractValidDate;
	private String contractMethod;
	private int contractPay;
	private String contractInformation;
	private String contractManagementCode;
	private int contractManagementAmout;
	private int contractManagementUnpaid;
	private String contractManagementState;
	private String contractManagementContents;
	
	
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getContractUnitCode() {
		return contractUnitCode;
	}
	public void setContractUnitCode(String contractUnitCode) {
		this.contractUnitCode = contractUnitCode;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
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
	public String getContractMethod() {
		return contractMethod;
	}
	public void setContractMethod(String contractMethod) {
		this.contractMethod = contractMethod;
	}
	public int getContractPay() {
		return contractPay;
	}
	public void setContractPay(int contractPay) {
		this.contractPay = contractPay;
	}
	public String getContractInformation() {
		return contractInformation;
	}
	public void setContractInformation(String contractInformation) {
		this.contractInformation = contractInformation;
	}
	public String getContractManagementCode() {
		return contractManagementCode;
	}
	public void setContractManagementCode(String contractManagementCode) {
		this.contractManagementCode = contractManagementCode;
	}
	public int getContractManagementAmout() {
		return contractManagementAmout;
	}
	public void setContractManagementAmout(int contractManagementAmout) {
		this.contractManagementAmout = contractManagementAmout;
	}
	public int getContractManagementUnpaid() {
		return contractManagementUnpaid;
	}
	public void setContractManagementUnpaid(int contractManagementUnpaid) {
		this.contractManagementUnpaid = contractManagementUnpaid;
	}
	public String getContractManagementState() {
		return contractManagementState;
	}
	public void setContractManagementState(String contractManagementState) {
		this.contractManagementState = contractManagementState;
	}
	public String getContractManagementContents() {
		return contractManagementContents;
	}
	public void setContractManagementContents(String contractManagementContents) {
		this.contractManagementContents = contractManagementContents;
	}
	@Override
	public String toString() {
		return "AgreementDTO [contractCode=" + contractCode + ", contractUnitCode=" + contractUnitCode + ", memberId="
				+ memberId + ", memberName=" + memberName + ", memberPhone=" + memberPhone + ", contractStart="
				+ contractStart + ", contractFinish=" + contractFinish + ", contractValidDate=" + contractValidDate
				+ ", contractMethod=" + contractMethod + ", contractPay=" + contractPay + ", contractInformation="
				+ contractInformation + ", contractManagementCode=" + contractManagementCode
				+ ", contractManagementAmout=" + contractManagementAmout + ", contractManagementUnpaid="
				+ contractManagementUnpaid + ", contractManagementState=" + contractManagementState
				+ ", contractManagementContents=" + contractManagementContents + "]";
	}
	
	
	
	
	
	
	

	
	
	
}
