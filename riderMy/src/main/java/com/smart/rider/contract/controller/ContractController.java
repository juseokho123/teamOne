package com.smart.rider.contract.controller;

import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.rider.contract.dto.AgreementDTO;
import com.smart.rider.contract.dto.ContractDTO;
import com.smart.rider.contract.dto.ManagementDTO;
import com.smart.rider.contract.dto.UnitDTO;
import com.smart.rider.contract.service.ContractService;
import com.smart.rider.contract.service.ManagementService;
import com.smart.rider.contract.service.UnitService;
import com.smart.rider.main.dto.SearchDTO;


@Controller
public class ContractController {

	@Autowired 
	private ContractService contractService;
	@Autowired 
	private UnitService unitService;
	
	//계약관리 화면
	@GetMapping("/contract")
	public String contract(Model model,HttpSession session) {
		//최신 단가표
		List<UnitDTO>  UnitDTO = contractService.unitNew();
		//계약 목록
		List<AgreementDTO> contractList =  contractService.getAllList();
		for(int i= 2015; i<2030; i++) {
			String year = String.valueOf(i);
			//System.out.println(year + "결과 값 String으로 전환");
			int result = contractService.contractYear(year);
			//System.out.println(result + "<--year을 대입 했을 때 나오는 결과");
			String contractChart = "contract"+year;
			model.addAttribute(contractChart, result);
		}
		model.addAttribute("contractList", contractList);
		model.addAttribute("unitNew", contractService.unitNew());
		//System.out.println(UnitDTO+"<-- 최신 단가표 조회");
		//System.out.println(contractList + "<-- 계약목록 확인");
		return "/contract/contract";
	}
	
	//계약쪽 화면
	@GetMapping("/agreement")
	public String agreementList(Model model,HttpSession session) {
		//입력값 확인
		//System.out.println("=====test=====");
		//System.out.println("agreement:"+contractService.agreementList());
		model.addAttribute("agreement", contractService.agreementList(session));
		model.addAttribute("SLEVEL", (String)session.getAttribute("SLEVEL"));
		model.addAttribute("size", contractService.agreementList(session).size());
		List<UnitDTO>  UnitDTO = contractService.unitNew();
		//최근 계약 단가표 코드 session으로 받아오기
		String getContractUnitCode = null;
		if(UnitDTO.size() != 0) {
		getContractUnitCode = UnitDTO.get(0).getContractUnitCode();
		//System.out.println(getContractUnitCode + "<--최근 단가표 값 받는가 확인");
		session.setAttribute("SCUC",getContractUnitCode);
		}
		return "/contract/agreement";
	}
	
	//계약 목록
	@GetMapping("/contractList")
	public String contractList(Model model) {
		List<ContractDTO> contractList =  contractService.contractList();
		//System.out.println("=====test=====");
		//System.out.println("contractList:"+contractList);
		model.addAttribute("contractList", contractList);
		return "contract/contractList";
	}

	//계약 내용 검색
	@PostMapping("/contractSearchList")
	public String contractSearchList(SearchDTO search, Model model) {
		//System.out.println(search + "<-입력받은값");
		List<ContractDTO> contractList = contractService.contractSearchList(search);
		//System.out.println(contractList + "<- 계약내용 검색 결과값");
		model.addAttribute("contractList", contractList);
		//조회 결과가 없으면 나오는 문장
		if(contractList.size()  == 0) {
			model.addAttribute("alert", "검색 결과가 없습니다");
		}
		return "/contract/contractList";
	}

	//계약 생성화면
	@GetMapping("/contractInsert")
	public String contractInsert(Model model){
		List<UnitDTO> contractInsert =   contractService.unitNew();
		//System.out.println("=====test=====");
		//System.out.println("contractList:"+contractInsert);
		model.addAttribute("contractInsert", contractInsert);
		return "/contract/contractInsert";
	}
	
	//계약 생성하기
	@PostMapping("/contractInsert")
	public String contractInsert(ContractDTO contract,HttpSession session,ManagementDTO management) {
		//System.out.println(contract.toString() + "<-- contract.toString");
		contractService.contractInsert(contract, session, management);
		
		return "redirect:/contractList";
	}
	
	//점주 ,특정 계약코드 조회
	@GetMapping("/agreementList")
	public String getAgreementList(@RequestParam(value="contractCode")String contractCode,Model model) {
		//넘어오는코드 값 확인
		//System.out.println("계약코드값 : " + contractCode);
		//대입결과 확인
		List<AgreementDTO> getAgreementList = contractService.getAgreementList(contractCode);
		//System.out.println("getAgreementList 값 : " + getAgreementList);
		model.addAttribute("getAgreementList", getAgreementList);
		
		return "contract/agreementList";
	}
	
	//관리자 ,특정 계약코드 조회
	@GetMapping("/getContractList")
	public String getContractList(@RequestParam(value="contractCode")String contractCode
								,@RequestParam(value="contractUnitCode")String contractUnitCode
								,Model model){
		//System.out.println(contractCode + "<--계약코드 값");
		//System.out.println(contractUnitCode + "입력받은 계약단가표 값");
		List<ContractDTO> contractList = contractService.getContractList(contractCode);
		//System.out.println(contractList + "계약코드 값으로 데이터 조회 결과 확인");
		model.addAttribute("contractList", contractList);
		if(contractList != null) {
		model.addAttribute("contractMethod", contractList.get(0).getContractMethod());
		//System.out.println("납부방식에 담겨 있는값 :" +contractList.get(0).getContractMethod());
		String unitCode = contractList.get(0).getContractUnitCode();
		model.addAttribute("contractUnitCode", contractUnitCode);
		//System.out.println(unitCode + " <-- 담겨있는 코드값");
			 		}
		//계약 단가표 리스트를 보여주면서 고를 수 잇도록 설계
		List<UnitDTO> uList =  unitService.unitList();
		model.addAttribute("uList", uList);
		//수정시 계약 단가표 수정 할 수 잇도록 모델에  넣는다.
		List<UnitDTO> unitList = contractService.getUnitList(contractUnitCode);
		model.addAttribute("unitList", unitList);
		
		return "contract/contractUpdate";
	}
	
	//계약 수정(생성 및 재계약)
	@PostMapping("/contractUpdate")
	public String contractUpdate(ContractDTO contract,HttpSession session,ManagementDTO management) {
		//System.out.println(contract + "수정하는 값 받는 지 확인");
		contractService.contractUpdate(contract, session, management);
		
		return "redirect:/contractList";
	}

}
