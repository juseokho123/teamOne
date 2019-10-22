package com.smart.rider.contract.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.rider.contract.dto.ContractManagementDTO;
import com.smart.rider.contract.dto.ManagementDTO;
import com.smart.rider.contract.mapper.ManagementMapper;
import com.smart.rider.main.dto.SearchDTO;

@Service
public class ManagementService {

	@Autowired
	private ManagementMapper managementMapper;
	
	//계약금 생성
	public int managementInsert(ManagementDTO management,HttpSession session) {
		return managementMapper.managementInsert(management);
	}
	
	//계약금 목록
	public Map<String, Object> managementList(int currentPage) {
		// view(list)에서 보여줄 행의 갯수
		final int ROW_PER_PAGE = 5;
		// view(list)에서 보여줄 첫번째 페이지번호 초기화 (view쪽의 반복문의 시작값)
		int startPageNum = 1;
		// view(list)에서 보여줄 페이지번호의 갯수 초기화 (view쪽의 반복문의 반복횟수)
		int lastPageNum = ROW_PER_PAGE;
		// view(list)에서 6번 페이지부터 보여줄 첫번재 페이지번호 변동)
		if(currentPage > (ROW_PER_PAGE/2)) {
			// view(list)에서 보여줄 첫번째 페이지번호
			startPageNum = currentPage - ((lastPageNum/2)-1);
			// view(list)에서 보열줄 마지막 페이지 번호
			lastPageNum += (startPageNum-1);
		}
		// Map(키, 값) 생성 -> DB에 접근하여 리스트로 보여줄 시작점과 행의 갯수 
		Map<String, Integer> map = new HashMap<String, Integer>();
		// Table(board)에 담겨진 행의 시작점은 0이므로 현재페이지가 첫번째일 경우 0으로 시작하는 알고리즘
		int startRow = (currentPage-1)*ROW_PER_PAGE;
		// map에 보여줄 행의 시작점과 보여줄 행의 갯수를 키와 함께 담는다.
		map.put("currentPage", startRow);
		map.put("rowPerPage", ROW_PER_PAGE);
		// Table(board)에 담겨진 전체 행의 갯수
		double shopCount = managementMapper.selectManagementCount();
		// view에 보여질 마지막 페이지의 수(전체행의 갯수/보여줄 행의 갯수-> 올림)
		int lastPage = (int)(Math.ceil(shopCount/ROW_PER_PAGE));
		// view에 현재 페이지가 마지막페이지보다 02작을 경우 view쪽의 반복문의 반복횟수를 조정
		if(currentPage >= (lastPage-2)) {
			lastPageNum = lastPage;
		}
		// view에 보여질 페이징 처리를 위해 값을 Map에 담아 리턴
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("list", managementMapper.managementList(map));
		returnMap.put("currentPage", currentPage);
		returnMap.put("lastPage", lastPage);
		returnMap.put("startPageNum", startPageNum);
		returnMap.put("lastPageNum", lastPageNum);	
		return returnMap;
	}
	
	//계약금 상세조회 
	public List<ManagementDTO> getManagementList(String managementCode){
		return managementMapper.getManagementList(managementCode);
	}
	
	//계약금 수정하기
	public int managementUpdate(ManagementDTO management) {
		return managementMapper.managementUpdate(management);
	}
	
	//계약금 검색
	public List<ContractManagementDTO> managementSearchList(SearchDTO search){
		return managementMapper.managementSearchList(search);
	}
}
