package com.smart.rider.main.dto;

public class SearchDTO {
	
	private String searchKey;
	private String searchValue;
	private String beginDate;
	private String endDate;
	
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		return "SearchDTO [searchKey=" + searchKey + ", searchValue=" + searchValue + ", beginDate=" + beginDate
				+ ", endDate=" + endDate + "]";
	}
	
}
