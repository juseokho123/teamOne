package com.smart.rider.spend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.rider.spend.dto.JoinUtilityDTO;
import com.smart.rider.spend.dto.UtilityDTO;
import com.smart.rider.spend.mapper.UtilityMapper;
import com.smart.rider.subject.dto.SubjectDTO;

@Service
public class UtilityService {
	
	@Autowired
	private UtilityMapper utilityMapper;
	
	/*** 190926 재욱, 지출_공과금 등록 내역 수정 ***/
	public int utilityUpdate(UtilityDTO utilityDTO) {
		return utilityMapper.utilityUpdate(utilityDTO);
	}
	
	
	/*** 190926 재욱, 지출_공과금 등록 내역 삭제 ***/
	public int utilityDelete(String spendUtilityCode) {
		return utilityMapper.utilityDelete(spendUtilityCode);
	}
	
	
	/*** 190924 재욱, 지출_공과금 등록 내역 상세보기 ***/
	public List<JoinUtilityDTO> utilityDetails(String spendUtilityCode) {
		return utilityMapper.utilityDetails(spendUtilityCode);
	}
	
	
	/*** 190924 재욱, 지출_공과금 최근 등록 목록 paging ***/
    public Map<String, Object> utilityList(int currentPage, String contractShopCode, String utilityKey, String utilityValue, String beginDate, String endDate){
        
        // 페이지에 보여줄 행의 개수 ROW_PER_PAGE = 4로 고정
        final int ROW_PER_PAGE = 4; 
        
        // 페이지에 보여줄 첫번째 페이지 번호는 1로 초기화
        int startPageNum = 1;
        
        // 처음 보여줄 마지막 페이지 번호는 5
        int lastPageNum = 5;
        
        // 현재 페이지가 lastPageNum/2 보다 클 경우
        if(currentPage > lastPageNum/2) {
            // 보여지는 페이지 첫번째 페이지 번호는 현재페이지 - 2
            // ex 현재 페이지가 3이라면 첫번째 페이지번호는 1
            startPageNum = currentPage - 2;
            
            // 보여지는 마지막 페이지 번호는 현재 페이지 번호 + 2
            lastPageNum = currentPage + 2;
        }
        
        // Map Data Type 객체 참조 변수 map 선언
        // HashMap() 생성자 메서드로 새로운 객체를 생성, 생성된 객체의 주소값을 객체 참조 변수에 할당
        Map<String, Object> map = new HashMap<String, Object>();
        // 한 페이지에 보여지는 첫번째 행은 (현재페이지 - 1) * 4
        int startRow = (currentPage - 1)*ROW_PER_PAGE;
        // 값을 map에 던져줌
        map.put("startRow", startRow);
        map.put("rowPerPage", ROW_PER_PAGE);
        
        
        map.put("contractShopCode", contractShopCode);
        map.put("utilityKey", utilityKey);
        map.put("utilityValue", utilityValue);
        map.put("beginDate", beginDate);
        map.put("endDate", endDate);
        
        // DB 행의 총 개수를 구하는 getBoardAllCount() 메서드를 호출하여 double Date Type의 utilityCount 변수에 대입
        double utilityCount = utilityMapper.utilityAllCount(contractShopCode);
        
        if(!utilityValue.equals("")) {
        	utilityCount = utilityMapper.utilitySearchCount(map);
        } else if(!beginDate.equals("") || !endDate.equals("")) {
        	utilityCount = utilityMapper.utilitySearchCount(map);
        }
        
        // 마지막 페이지번호를 구하기 위해 총 개수 / 페이지당 보여지는 행의 개수 -> 올림 처리 -> lastPage 변수에 대입
        int lastPage = (int)(Math.ceil(utilityCount/ROW_PER_PAGE));
        
        // utilityCount가 4보다 작아 lastPage가 0일 경우 lastPage = 1로 고정
        if(lastPage == 0) {
        	//System.out.println(lastPage + " < -- lastPage utilityList() UtilityService.java");
        	lastPage = 1;
        }
        // 현재 페이지가 (마지막 페이지-3) 보다 같거나 클 경우
        if(currentPage >= (lastPage-3)) {
            // 마지막 페이지 번호는 lastPage
            lastPageNum = lastPage;
        }
        
        // 구성한 값들을 Map Date Type의 resultMap 객체 참조 변수에 던져주고 return
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("list", utilityMapper.utilityList(map));
        resultMap.put("currentPage", currentPage);
        resultMap.put("lastPage", lastPage);
        resultMap.put("startPageNum", startPageNum);
        resultMap.put("lastPageNum", lastPageNum);
        return resultMap;
    }
	
	
    /*** 190924 재욱, 지출_공과금 등록 계정과목 selectBox ***/ 
	public List<SubjectDTO> subjectSelectBox(){
		return utilityMapper.subjectSelectBox();
	}
	
	/*** 190924 재욱, 지출_공과금 등록  ***/
	public int utilityInsert(UtilityDTO utilityDTO, String contractShopCode) {
		
		String spendUtilityCode = "SU" + utilityMapper.utilityCodeCount(); // 지출_공과금 코드 자동증가
		//System.out.println(spendUtilityCode + " <-- spendUtilityCode check utilityInsert UtilityService.java");
		
		if(spendUtilityCode.equals("SUnull")) { //전체 삭제 후 다시 등록시 null을 받아오는 문제
			spendUtilityCode = "SU0001";
		}

		utilityDTO.setSpendUtilityCode(spendUtilityCode);
		utilityDTO.setContractShopCode(contractShopCode);
		//System.out.println(utilityDTO.toString() + " <-- utilityDTO utilityInsert UtilityService.java");
		return utilityMapper.utilityInsert(utilityDTO);
	}

}
