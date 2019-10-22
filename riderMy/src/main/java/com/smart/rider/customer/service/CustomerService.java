package com.smart.rider.customer.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.rider.customer.dto.CustomerDTO;
import com.smart.rider.customer.mapper.CustomerMapper;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerMapper customerMapper;
	
	//19.09.23작성 대여고객 상세보기
	public CustomerDTO getCustomerList(String rentalCustomerCode) {
		
		return customerMapper.getCustomerList(rentalCustomerCode);
	}
	
	//19.10.02 페이지 작업
	public Map<String, Object> customerList(int currentPage, String contractShopCode) {
		//페이지 구성 할 행의 갯수
		final int rowPerPage = 10;
		//보여줄 첫번째 페이지번호 초기화
		int startPageNum = 1;
		//보여줄 페이지번호의 갯수 초기화
		int lastPageNum = rowPerPage;
		
		if(currentPage > (rowPerPage/2)) {
			startPageNum = currentPage - ((lastPageNum/2)-1);
			lastPageNum += (startPageNum-1);
		}
		// limit 적용될 값 startRow, 상수 rowPerPage
		Map<String, Object> map = new HashMap<String, Object>();
		
		int startRow = (currentPage-1)*rowPerPage;
		
		map.put("startRow"		  ,startRow);
		map.put("rowPerPage"	  ,rowPerPage);
		map.put("contractShopCode",contractShopCode);
		
		//전체행의 갯수를 가져오는 쿼리
		double customerCount = customerMapper.getCustomerAllCount(contractShopCode);
							//올림함수 소수점이있으면 무조건 올림
		int lastPage = (int)(Math.ceil(customerCount/rowPerPage));
		
		if(currentPage >= (lastPage-4)) {
			lastPageNum = lastPage;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list"		,customerMapper.customerList(map));
		resultMap.put("currentPage" ,currentPage);
		resultMap.put("lastPage"	,lastPage);
		resultMap.put("startPageNum",startPageNum);
		resultMap.put("lastPageNum" ,lastPageNum);
		
		return resultMap;
	}
	
	//19.10.10작성 대여고객 등록처리
	public int customerInsert(CustomerDTO customerdto) {
		String customerCode = "RC" + customerMapper.customerCodeCount();
		
		if(customerCode.equals("RCnull")) {
			customerCode = "RC0001";
		}
		customerdto.setRentalCustomerCode(customerCode);
		
		return customerMapper.customerInsert(customerdto);
	}
	
	//대여고객 수정처리
	public int customerUpdate(CustomerDTO customerdto) {
		
		return customerMapper.customerUpdate(customerdto);
	}
	
	//대여고객목록에서 리턴버튼 클릭시 반납처리
	public int returnButton(CustomerDTO customerdto) {
		
		return customerMapper.returnButton(customerdto);
	}
	
	//대여고객 검색처리
	public Map<String, Object> searchCustomer(int currentPage, String contractShopCode, String returnNo, String overdueYes
											 ,String select, String searchInput, String selectDate, String beginDate, String endDate) {
		//페이지 구성 할 행의 갯수
		final int rowPerPage = 10;
		//보여줄 첫번째 페이지번호 초기화
		int startPageNum = 1;
		//보여줄 페이지번호의 갯수 초기화
		int lastPageNum = rowPerPage;
		
		if(currentPage > (rowPerPage/2)) {
			startPageNum = currentPage - ((lastPageNum/2)-1);
			lastPageNum += (startPageNum-1);
		}
		// limit 적용될 값 startRow, 상수 rowPerPage
		Map<String, Object> map = new HashMap<String, Object>();
		
		int startRow = (currentPage-1)*rowPerPage;
		
		map.put("startRow"		  ,startRow);
		map.put("rowPerPage"	  ,rowPerPage);
		map.put("contractShopCode",contractShopCode);
		map.put("returnNo"		  ,returnNo);
		map.put("overdueYes"	  ,overdueYes);
		map.put("select"		  ,select);
		map.put("searchInput"	  ,searchInput);
		map.put("selectDate"	  ,selectDate);
		map.put("beginDate"		  ,beginDate);
		map.put("endDate"		  ,endDate);
		
		//검색결과의 전체행의 갯수를 가져오는 쿼리
		double customerCount = customerMapper.getSearchAllCount(contractShopCode, returnNo, overdueYes
																,select, searchInput, selectDate, beginDate, endDate);
							//올림함수 소수점이있으면 무조건 올림
		int lastPage = (int)(Math.ceil(customerCount/rowPerPage));
		
		if(currentPage >= (lastPage-4)) {
			lastPageNum = lastPage;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//System.out.println(customerMapper.searchCustomer(map) + "<---검색 후");
		resultMap.put("list"		,customerMapper.searchCustomer(map));
		resultMap.put("currentPage" ,currentPage);
		resultMap.put("lastPage"	,lastPage);
		resultMap.put("startPageNum",startPageNum);
		resultMap.put("lastPageNum"	,lastPageNum);
		
		return resultMap;
	}
}
