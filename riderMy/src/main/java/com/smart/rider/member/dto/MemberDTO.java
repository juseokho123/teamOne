package com.smart.rider.member.dto;

public class MemberDTO {
	
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberLevel;
	private String memberBirth;
	private String contractShopCode;
	private String memberAddress;
	private String memberEmail;
	private String memberPhone;
	private String memberInsertDate;
	private String memberUpdateDate;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}
	public String getMemberBirth() {
		return memberBirth;
	}
	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}
	public String getContractShopCode() {
		return contractShopCode;
	}
	public void setContractShopCode(String contractShopCode) {
		this.contractShopCode = contractShopCode;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberInsertDate() {
		return memberInsertDate;
	}
	public void setMemberInsertDate(String memberInsertDate) {
		this.memberInsertDate = memberInsertDate;
	}
	public String getMemberUpdateDate() {
		return memberUpdateDate;
	}
	public void setMemberUpdateDate(String memberUpdateDate) {
		this.memberUpdateDate = memberUpdateDate;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", memberLevel=" + memberLevel + ", memberBirth=" + memberBirth + ", contractShopCode="
				+ contractShopCode + ", memberAddress=" + memberAddress + ", memberEmail=" + memberEmail
				+ ", memberPhone=" + memberPhone + ", memberInsertDate=" + memberInsertDate + ", memberUpdateDate="
				+ memberUpdateDate + "]";
	}
}
