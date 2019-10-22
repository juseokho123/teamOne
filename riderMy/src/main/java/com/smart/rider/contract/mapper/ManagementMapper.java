package com.smart.rider.contract.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.smart.rider.contract.dto.ContractManagementDTO;
import com.smart.rider.contract.dto.ManagementDTO;
import com.smart.rider.main.dto.SearchDTO;


@Mapper
public interface ManagementMapper {

	//계약금 생성
	public int managementInsert(ManagementDTO management);
	
	//계약금 중 가장 높은 숫자 가져오기
	public String managementCodeMax();
	
	//계약금 상세보기
	public List<ManagementDTO> getManagementList(String managementCode);
	
	//계약금 수정하기
	public int managementUpdate(ManagementDTO management);
	
	
	//계약금 목록
	public List<ContractManagementDTO> managementList(Map<String, Integer> map);
	/* - ContractManagement table의 전체 행의 갯수 -
	 * @brief ManagementMapper.xml(id)를 인터페이스 ManagementMapper.java(메서드명)와 맵핑
	 * @return int
	 */

	int selectManagementCount();
	
	/* -insert처리-
	 * @param ManagementDTO management
	 * @brief ManagementMapper.xml(id)를 인터페이스 ShopMapper.java(메서드명)와 맵핑
	 * @return int
	 */
	
	//계약금 검색
	public List<ContractManagementDTO> managementSearchList(SearchDTO search);
	
	//계약금 목록(계약 수정시 사용)
	public List<ManagementDTO> managementUseList();
}
