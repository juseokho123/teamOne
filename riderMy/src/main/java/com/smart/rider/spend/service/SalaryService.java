package com.smart.rider.spend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.rider.main.dto.SearchDTO;
import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.spend.dto.JoinSalaryDTO;
import com.smart.rider.spend.mapper.SalaryMapper;

@Service
public class SalaryService {
	
	@Autowired
	private SalaryMapper salaryMapper;
	
	
	
	
	/**** 191002 재욱, Update : 지출_급여 수정 ****/
	public int salaryUpdate(JoinSalaryDTO joinSalaryDTO) {
		return salaryMapper.salaryUpdate(joinSalaryDTO);
	}
	
	
	/**** 191001 재욱, Read : 지출_급여 상세보기 ****/
	public List<JoinSalaryDTO> spendSalaryDetails(String contractShopCode, String spendSalaryCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contractShopCode", contractShopCode);
		map.put("spendSalaryCode", spendSalaryCode);
		
		//System.out.println(map.toString() + " <-- map.toString() spendSalaryDetails() SalaryService.java");
		
		List<JoinSalaryDTO> list = salaryMapper.salaryList(map);
		//System.out.println(list + " <-- list spendSalaryDetails()  SalaryService.java");
		
		return list;
	}
	
	/**** 190930 재욱, Read : 지출_급여 등록 내역 + 페이징 ****/
	public Map<String, Object> salaryList(String contractShopCode, SearchDTO searchDTO, int currentPage){
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		
        final int ROW_PER_PAGE = 6; // 페이지에 보여줄 행의 개수 ROW_PER_PAGE = 6으로 고정
        int startPageNum = 1; // 페이지에 보여줄 첫번째 페이지 번호는 1로 초기화
        int lastPageNum = 5; // 처음 보여줄 마지막 페이지 번호는 5
        
        // 현재 페이지가 lastPageNum/2 보다 클 경우
        if(currentPage > lastPageNum/2) {
            startPageNum = currentPage - 2; // 보여지는 페이지 첫번째 페이지 번호는 현재페이지 - 2, ex) 현재 페이지가 3이라면 첫번째 페이지번호는 1
            lastPageNum = currentPage + 2; // 보여지는 마지막 페이지 번호는 현재 페이지 번호 + 2
        }
        
        int startRow = (currentPage - 1)*ROW_PER_PAGE;
        map.put("startRow", startRow);
        map.put("rowPerPage", ROW_PER_PAGE);
		
        double salaryCount = salaryMapper.salaryAllCount(contractShopCode);
        
		//System.out.println(contractShopCode + " <-- contractShopCode salaryList() SalaryService.java");
		//System.out.println(searchDTO.toString() + " <-- searchDTO salaryList() SalaryService.java");
		
		map.put("contractShopCode", contractShopCode);
		map.put("searchKey", searchDTO.getSearchKey());
		map.put("searchValue", searchDTO.getSearchValue());
		map.put("beginDate", searchDTO.getBeginDate());
		map.put("endDate", searchDTO.getEndDate());
		//System.out.println(map.toString() + " <-- map.toString() salaryList() SalaryService.java");
		
	    int lastPage = (int)(Math.ceil(salaryCount/ROW_PER_PAGE));

        if(lastPage == 0) {
        	lastPage = 1;
        }
        if(currentPage >= (lastPage-3)) {
            lastPageNum = lastPage;
        }
		
		List<JoinSalaryDTO> list = salaryMapper.salaryList(map);
		//System.out.println(list + " <-- list salaryList() SalaryService.java");
		resultMap.put("salaryList", list);
        resultMap.put("currentPage", currentPage);
        resultMap.put("lastPage", lastPage);
        resultMap.put("startPageNum", startPageNum);
        resultMap.put("lastPageNum", lastPageNum);
		
		return resultMap;
	}
	
	/**** 190927 재욱, Insert : 지출_급여 내역 등록 ****/
	public int salaryInsert(JoinSalaryDTO salaryDTO, String contractShopCode) {
		
		String spendSalaryCode = "SS" + salaryMapper.salaryCodeCount(); // 지출_급여 코드 자동증가
		//System.out.println(spendUtilityCode + " <-- spendUtilityCode check salaryInsert SalaryService.java");
		
		if(spendSalaryCode.equals("SSnull")) { //전체 삭제 후 다시 등록시 null을 받아오는 문제
			spendSalaryCode = "SS0001";
		}
		
		salaryDTO.setContractShopCode(contractShopCode);
		salaryDTO.setSpendSalaryCode(spendSalaryCode);
		
		return salaryMapper.salaryInsert(salaryDTO);
	}
	
	/**** 190927 재욱, Read : 지출_급여 직원 select box list ****/
	public List<MemberDTO> salarySelectBox(String contractShopCode) {
		return salaryMapper.salarySelectBox(contractShopCode);
	}

}
