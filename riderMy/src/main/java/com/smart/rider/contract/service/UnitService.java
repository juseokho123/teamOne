package com.smart.rider.contract.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.rider.contract.dto.UnitDTO;
import com.smart.rider.contract.mapper.UnitMapper;

@Service
public class UnitService {

	@Autowired 
	private UnitMapper unitMapper;
	
	//계약단가표 목록
	public List<UnitDTO> unitList(){
		return unitMapper.unitList();
	}
	
	//계약단가표 생성
	public int unitInsert(UnitDTO unit,HttpSession session) {
		//계약코드 만들기
		String unitCode = "U"+ unitMapper.unitCodeMax();
		//전체 삭제 후 다시 등록시 null을 받아올 때 null을 변환
		if(unitCode.equals("Unull")) { 
			unitCode = "U0001";
		}
		//만든 계약코드 UnitDTO 세팅 및 로그인한 아이디의 값 넣어주기
		unit.setMemberId((String)session.getAttribute("SID"));
		unit.setContractUnitCode(unitCode);
		
		return unitMapper.unitInsert(unit);
	}
}
