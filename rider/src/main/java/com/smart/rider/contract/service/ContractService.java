package com.smart.rider.contract.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.rider.contract.dto.AgreementDTO;
import com.smart.rider.contract.dto.ContractDTO;
import com.smart.rider.contract.dto.ManagementDTO;
import com.smart.rider.contract.dto.UnitDTO;
import com.smart.rider.contract.mapper.ContractMapper;
import com.smart.rider.contract.mapper.ManagementMapper;
import com.smart.rider.main.dto.SearchDTO;
import com.smart.rider.shop.dto.ShopDTO;
import com.smart.rider.shop.mapper.ShopMapper;

@Service
@Transactional
public class ContractService {
	 
	@Autowired 
	private ContractMapper contractMapper;
	@Autowired
	private ManagementService managementService;
	@Autowired
	private ManagementMapper managementMapper;
	@Autowired
	private ShopMapper shopmapper;
	
	//최근단가표
	public List<UnitDTO> unitNew(){
		return contractMapper.unitNew();
	}
	
	//계약 목록
	public List<ContractDTO> contractList(){
		return contractMapper.contractList();
	}
	//년도별 조회
	public int contractYear(String year) {
		return contractMapper.contractYear(year);
	}
	
	
	//계약금 검색
	public List<ContractDTO> contractSearchList(SearchDTO search){
		return contractMapper.contractSearchList(search);
	}
	
	//계약  생성하기
	public int contractInsert(ContractDTO contract,HttpSession session,ManagementDTO management) {
		//계약코드 생성
		String contractCode = "C"+ contractMapper.contractCodeMax();
		if(contractCode.equals("Cnull")) { 
			contractCode = "C0001";
		}
		//로그인 되어있는 아이디값 가져오기
		contract.setMemberId((String)session.getAttribute("SID"));
		//System.out.println(contract.getMemberId());
		//최근단가표 세션으로 가져오기
		contract.setContractUnitCode((String)session.getAttribute("SCUC"));
		//System.out.println(contract.getContractUnitCode());
		//계약코드 생성 후 ContractDTO에 넣기
		contract.setContractCode(contractCode);
		//System.out.println(contract.getContractCode());
		//리턴을 2번 하기 위해  선언
		int result = 0;
		result += contractMapper.contractInsert(contract);
		//contractList에 담겨있는 contractCode 중에  마지막에 등록된 코드 가져오기
		List<ContractDTO> contractList = contractList();
		String getContractCode = contractList.get(contractList.size()-1).getContractCode();
		//System.out.println(getContractCode);
		
		//가장 최근에 계약한 코드 가져오기
		session.setAttribute("SCC",getContractCode);
		
		//계약관리쪽 마지막 contractCode를 가져와서  managemenDTO 쪽에 set을 한다.
		management.setContractCode((String)session.getAttribute("SCC"));
		System.out.println(management.getContractCode());
		
		//코드 만들기 : managementCodeMax로 마지막 번호를 받아서 M +번호를 붙여서 코드를 만든다
		String managementCode= "M"+ managementMapper.managementCodeMax();
		if(managementCode.equals("Mnull")) { 
			managementCode = "M0001";
		}
		//기본값 넣어주기
		management.setContractManagementAmout(0);
		management.setContractManagementUnpaid(0);
		management.setContractManagementContents("");
		management.setContractManagementState("계약상태");
		//만든 코드를  ContractManagementCode에다가 set 해주고 get으로 값이 들어가있는지 확인
		management.setContractManagementCode(managementCode);
		//System.out.println(management.getContractManagementCode());
		//리턴
		result += managementService.managementInsert(management, session);
		return result;
	}
	
	//계약서 목록
	public List<AgreementDTO> agreementList(HttpSession session){
		String id = (String)session.getAttribute("SID");
		String level = (String)session.getAttribute("SLEVEL");
		//System.out.println(level+"<--로그인 권한 확인");
		//System.out.println(id+"<--로그인 아이디 확인");
		return contractMapper.agreementList(id,level);
	}
	
	//계약 내용 및 계약금 납부 현황 보기
	public List<AgreementDTO> getAgreementList(String agreementCode){
		return contractMapper.getAgreementList(agreementCode);
	}
	
	//특정 계약코드로 데이터조회
	public List<ContractDTO> getContractList(String contractCode){
		return contractMapper.getContractList(contractCode);
	}
	
	//특정 단가표코드로 데이터 조회
	public List<UnitDTO> getUnitList(String contractUnitCode){
		return contractMapper.getUnitList(contractUnitCode);
	}
	
	//계약관리 쪽 계약매장
	public List<AgreementDTO> getAllList(){
		return contractMapper.getAllList();
	}

	//계약 수정(생성)
	public int contractUpdate(ContractDTO contract,HttpSession session,ManagementDTO management) {
		session.setAttribute("SORIGIN", contract.getContractCode());
		//System.out.println(contract.getContractCode() + "<-- 세션에 담길 기존 값 ");
		
		//계약코드 생성
		String contractCode = "C"+ contractMapper.contractCodeMax();
		if(contractCode.equals("Cnull")) { 
			contractCode = "C0001";
		}
		//계약코드 생성 후 ContractDTO에 넣기
		contract.setContractCode(contractCode);
		//System.out.println(contract.getContractCode() + "<-- 계약코드 생성 값 확인");
		//리턴을 2번 하기 위해  선언
		int result = 0;
		result += contractMapper.contractInsert(contract);
		
		//contractList에 담겨있는 contractCode 중에  마지막에 등록된 코드 가져오기
		List<ContractDTO> contractList = contractList();
		String getContractCode = contractList.get(contractList.size()-1).getContractCode();
		//System.out.println(getContractCode + " <-- 계약목록에서 마지막에 등록되어 있는 코드값 가져오기");
		//가장 최근에 계약한 코드 가져오기
		session.setAttribute("SCC",getContractCode);
		//계약관리쪽 마지막 contractCode를 가져와서  managemenDTO 쪽에 set을 한다.
		management.setContractCode((String)session.getAttribute("SCC"));
		//System.out.println(management.getContractCode() + "<-- 세션에 담겨져 있는데 마지막 계약코드 값");	
		//코드 만들기 : managementCodeMax로 마지막 번호를 받아서 M +번호를 붙여서 코드를 만든다
		String managementCode= "M"+ managementMapper.managementCodeMax();
		if(managementCode.equals("Mnull")) { 
			managementCode = "M0001";
		}
		//기본값 넣어주기
		management.setContractManagementAmout(0);
		management.setContractManagementUnpaid(0);
		management.setContractManagementContents("");
		management.setContractManagementState("계약상태");
		management.setContractManagementContents("서비스 시작일자 전");
		//만든 코드를  ContractManagementCode에다가 set 해주고 get으로 값이 들어가있는지 확인
		management.setContractManagementCode(managementCode);
		//System.out.println(management.getContractManagementCode() + "<--계약금 코드 확인");
		List<ShopDTO> shopUseList = shopmapper.shopUseList();
		//System.out.println(shopUseList + "매장목록 가져오기");	
		//리턴
		result += managementService.managementInsert(management, session);
		
		if(shopUseList != null) {
			//System.out.println(contract.getContractCode() + "<-- 기존 계약코드에 데이터 확인");
			List<ShopDTO> shopList = shopmapper.shopData((String)session.getAttribute("SORIGIN"));	
			//System.out.println(shopList + "<-- 수정시키려는 계약코드로 매장 조회 결과 ");
			String shopCode = shopList.get(0).getShopCode();
			//System.out.println(shopCode + "<-- 수정할 매장코드 값 가져오기");
			result += shopmapper.shopUpdateCode(shopCode,(String)session.getAttribute("SCC"));
		}
		
		return result;
	}
}
