package com.smart.rider.work.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.work.dto.WorkDTO;
import com.smart.rider.work.mapper.WorkMapper;

@Service
public class WorkService{
	
	@Autowired
	private WorkMapper workMapper;
	
	//직원 아이디,비밀번호 확인
	public Map<String, Object> employeeCheck(MemberDTO memberdto) {
		
		String result = "";
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		MemberDTO employeeCheck = workMapper.employeeCheck(memberdto);
		
		if(employeeCheck == null) {
			//System.out.println(employeeCheck +"<--입력값");
			result = "아이디와 비밀번호를 확인해주세요!";
		}else {
			result = "확인";
			map.put("checkEmployee", employeeCheck);
		}
		
		map.put("result", result);
		
		return map;
	}
	
	//출근등록처리
	public int goInsert(WorkDTO workdto) {
		String workCode = "W" + workMapper.workCodeCount();
		
		if(workCode.equals("Wnull")) {
			workCode = "W0001";
		}
		workdto.setWorkCode(workCode);
		
		return workMapper.goInsert(workdto);
	}
	
	/***********************19.10.01 작성********************************/
	//직원 근태목록 페이지 작업
	public Map<String, Object> workList(int currentPage, String contractShopCode, String memberId) {
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
		map.put("memberId"		  ,memberId);
		
		//전체행의 갯수를 가져오는 쿼리
		double workCount = workMapper.getWorkAllCount(contractShopCode, memberId);
							//올림함수 소수점이있으면 무조건 올림
		int lastPage = (int)(Math.ceil(workCount/rowPerPage));
		
		if(currentPage >= (lastPage-4)) {
			lastPageNum = lastPage;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list"		,workMapper.workList(map));
		resultMap.put("currentPage" ,currentPage);
		resultMap.put("lastPage"	,lastPage);
		resultMap.put("startPageNum",startPageNum);
		resultMap.put("lastPageNum" ,lastPageNum);
		
		return resultMap;
	}
	
	//직원 퇴근등록
	public int leaveInsert(WorkDTO workdto) {
		
		return workMapper.leaveInsert(workdto);
	}
	
	//근태 검색처리
	public Map<String, Object> searchWork(int currentPage, String contractShopCode, String select
										 ,String searchInput, String beginDate, String endDate) {
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
		map.put("select"		  ,select);
		map.put("searchInput"	  ,searchInput);
		map.put("beginDate"		  ,beginDate);
		map.put("endDate"		  ,endDate);
		
		//검색한 전체행의 갯수를 가져오는 쿼리
		double workCount = workMapper.getSearchAllCount(contractShopCode, select, searchInput, beginDate, endDate);
							//올림함수 소수점이있으면 무조건 올림
		int lastPage = (int)(Math.ceil(workCount/rowPerPage));
		
		if(currentPage >= (lastPage-4)) {
			lastPageNum = lastPage;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list"		,workMapper.searchWork(map));
		resultMap.put("currentPage" ,currentPage);
		resultMap.put("lastPage"	,lastPage);
		resultMap.put("startPageNum",startPageNum);
		resultMap.put("lastPageNum" ,lastPageNum);
		
		return resultMap;
	}
}
