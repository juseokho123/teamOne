package com.smart.rider.customer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.smart.rider.customer.dto.CustomerDTO;

@Mapper
public interface CustomerMapper {

	//19.09.23작성 대여고객 목록가져오기
	public List<CustomerDTO> customerList(Map<String, Object> map);
	
	//대여고객 상세보기
	public CustomerDTO getCustomerList(String rentalCustomerCode);
	
	//19.10.02 페이지작업위한 작성
	public int getCustomerAllCount(String contractShopCode);
	
	//19.10.10 자동증가코드
	public String customerCodeCount();
	
	//대여고객 등록처리
	public int customerInsert(CustomerDTO customerdto);
	
	//대여고객 수정처리
	public int customerUpdate(CustomerDTO customerdto);
	
	//대여고객 목록에서 반납버튼 클릭시 반납처리
	public int returnButton(CustomerDTO customerdto);
	
	//대여고객 검색처리 카운팅
	public int getSearchAllCount(String contractShopCode, String returnNo, String overdueYes, String select
								,String searchInput, String selectDate, String beginDate, String endDate);
	
	//대여고객 검색처리
	public List<CustomerDTO> searchCustomer(Map<String, Object> map);
}
