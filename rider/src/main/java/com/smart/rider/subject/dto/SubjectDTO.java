package com.smart.rider.subject.dto;

public class SubjectDTO {

	private String subjectCode;
	private String memberId;
	private String subjectNumber;
	private String subjectName;
	private String subjectDate;
	
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getSubjectNumber() {
		return subjectNumber;
	}
	public void setSubjectNumber(String subjectNumber) {
		this.subjectNumber = subjectNumber;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectDate() {
		return subjectDate;
	}
	public void setSubjectDate(String subjectDate) {
		this.subjectDate = subjectDate;
	}
	
	@Override
	public String toString() {
		return "SubjectDTO [subjectCode=" + subjectCode + ", memberId=" + memberId + ", subjectNumber=" + subjectNumber
				+ ", subjectName=" + subjectName + ", subjectDate=" + subjectDate + "]";
	}
}
