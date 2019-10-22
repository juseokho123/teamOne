package com.smart.rider.account.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.rider.account.dto.AccountDTO;
import com.smart.rider.account.mapper.AccountMapper;
import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.shop.dto.SsrHapDTO;


@Service
@Transactional
public class AccountService {

	@Autowired
	private AccountMapper accountmapper;
	
	//거래처 조회
	//맵으로 리턴 시키기 위해서 맵으로 선언해준다.
	public Map<String, Object> accountList(String sCode,String sLevel){
		//맵으로 선언
		Map<String,Object> map = new HashMap<String,Object>();
		//권한확인으로 관리자일 경우 Code값 넣기
		if(sLevel.equals("관리자")) {
			//map 넣을 내용을 String,Object 형식으로 넣어준다.
			sCode = "A";
			map.put("accountListYes", accountmapper.accountListYes(sCode));
			map.put("accountListNo", accountmapper.accountListNo(sCode));
			return map;
		}
		//아닐경우 정상값
		map.put("accountListYes", accountmapper.accountListYes(sCode));
		map.put("accountListNo", accountmapper.accountListNo(sCode));
		//맵으로 리턴 시킨다.
		return map;
	}
	
	//세션 아이디로 조회
	public List<SsrHapDTO> getShopRelationCode(String SID){
		return accountmapper.getShopRelationCode(SID);
	}
	
	//거래처 생성하기
	public int accountInsert(AccountDTO account) {
		//매입거래처 코드 생성
		String accountCode = "AC"+ accountmapper.accountMaxCode();
		if(accountCode.equals("ACnull")) { 
			accountCode = "AC0001";
		}
		//AccountDTO 매입거래처 코드 담기
		account.setAccountCode(accountCode);
		//System.out.println(account.getAccountCode()+"<--값 담겨있는지 확인");
		return accountmapper.accountInsert(account);
	}
	
	//거래처 검색
	public Map<String,Object> accountSearchList(String select, String searchName, String beginDate, String endDate,HttpSession session){
		//맵으로 선언
		Map<String,Object> map = new HashMap<String,Object>();
		//로그인한 계약매장코드 값과 권한 가져오기
		String sCode = (String)session.getAttribute("SCODE");
		String sLevel = (String)session.getAttribute("SLEVEL");
		//System.out.println(sCode + "세션 코드 입력값");
		//System.out.println(sLevel + "세션 권한 입력값");
		//권한 확인
		if(sLevel.equals("관리자")) {
			//map 넣을 내용을 String,Object 형식으로 넣어준다.
			sCode = "A";
			map.put("accountSearchListYes", accountmapper.accountSearchListYes(select, searchName, beginDate, endDate, sCode));
			map.put("accountSearchListNo", accountmapper.accountSearchListNo(select, searchName, beginDate, endDate, sCode));
			return map;
			
		}
		//map 넣을 내용을 String,Object 형식으로 넣어준다.
		map.put("accountSearchListYes", accountmapper.accountSearchListYes(select, searchName, beginDate, endDate, sCode));
		map.put("accountSearchListNo", accountmapper.accountSearchListNo(select, searchName, beginDate, endDate, sCode));	
		return map;
	}
	
	//수정화면(특정 거래처코드로 데이터 조회)
	public List<AccountDTO> accountUpdate(String acCode){
		return accountmapper.accountUpdate(acCode);
	}
	
	//거래처 수정하기
	public int accountUpdateSet(AccountDTO account){
		return accountmapper.accountUpdateSet(account);
	}
	
	//아이디를 이용해서 PW 구하기
	public List<MemberDTO> getPw(String SID){
		return accountmapper.getPw(SID);
	}
	
	//거래처 삭제
	public int accountDelete(String memberPw,String accountCode,HttpSession session) {
		//session에 담긴 값을 exactPw에 담는다.
		String exactPw = (String)session.getAttribute("SACPW");
		//입력한 비밀번호가 session에 담겨있는 비밀번호랑 일치 시에 삭제 가능
		if(memberPw.equals(exactPw)) {
			//입력한 비밀번호랑 일치시에 원래 담겨잇는 코드가 들어간다.
			return 	accountmapper.accountDelete(accountCode);
		}else {
			//불일치시에 FALSE라는 값이 담긴다.
			accountCode = "false";
		}
		return 	accountmapper.accountDelete(accountCode);
	}
}
