package com.smart.rider.contract.dto;


//회원관리DTO 와 계약관리DTO 에서 필요한 데이터 조회시 사용 
public class ContractMemberDTO {
	
	private String contractCode;
	private String memberId;
	private String contractStart;
	private String contractFinish;
	private String contractValidDate;
	private String memberName;
	private String memberPhone;
	
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
	@Override
	public String toString() {
		return "ContractMemberDTO [contractCode=" + contractCode + ", memberId=" + memberId + ", contractStart="
				+ contractStart + ", contractFinish=" + contractFinish + ", contractValidDate=" + contractValidDate
				+ ", memberName=" + memberName + ", memberPhone=" + memberPhone + "]";
	}

	






}