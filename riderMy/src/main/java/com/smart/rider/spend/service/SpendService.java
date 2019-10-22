package com.smart.rider.spend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.rider.main.dto.SearchDTO;
import com.smart.rider.spend.dto.SpendAdminDTO;
import com.smart.rider.spend.mapper.SpendMapper;

@Service
public class SpendService {
	
	@Autowired
	private SpendMapper spendMapper;
	
	/**** 191004 재욱, Read : 관리자 확인 카운트, 2일 때 관리자 비밀번호와 점주 생년월일 일치 ****/
	public int spendAdminCheck(String contractShopCode, String memberBirth, String memberId, String adminPw) {
		return spendMapper.spendAdminCheck(contractShopCode, memberBirth, memberId,adminPw);
	}
	
	/**** 191004 재욱, Read : 계약된 매장 리스트 상세보기 ****/
	public List<SpendAdminDTO> spendAdminDetails(String contractShopCode){
		return spendMapper.spendAdminDetails(contractShopCode);
	}
	
	/**** 191004 재욱, Read : 계약된 매장 리스트  ****/
	public Map<String, Object> spendShopList(int currentPage, SearchDTO searchDTO){
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		
        final int ROW_PER_PAGE = 10; // 페이지에 보여줄 행의 개수 ROW_PER_PAGE = 6으로 고정
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
        
        double shopCount = spendMapper.shopAllCount();
        
        int lastPage = (int)(Math.ceil(shopCount/ROW_PER_PAGE));

        if(lastPage == 0) {
        	lastPage = 1;
        }
        if(currentPage >= (lastPage-3)) {
            lastPageNum = lastPage;
        }
        
        map.put("searchKey", searchDTO.getSearchKey());
		map.put("searchValue", searchDTO.getSearchValue());
		map.put("beginDate", searchDTO.getBeginDate());
		map.put("endDate", searchDTO.getEndDate());
		//System.out.println(map.toString() + " <-- map.toString() spendShopList() SpendService.java");
		
		List<SpendAdminDTO> list = spendMapper.spendShopList(map);
		//System.out.println(list + " <-- list spendShopList() SpendService.java");
		
		resultMap.put("spendShopList", list);
        resultMap.put("currentPage", currentPage);
        resultMap.put("lastPage", lastPage);
        resultMap.put("startPageNum", startPageNum);
        resultMap.put("lastPageNum", lastPageNum);
		
		return resultMap;
	}

}
