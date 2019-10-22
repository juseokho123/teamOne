package com.smart.rider.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.smart.rider.account.dto.AccountDTO;
import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.shop.dto.SsrHapDTO;


@Mapper
public interface AccountMapper {
	//리스트 조회 시
	public List<AccountDTO> accountListYes(String sCode);
	public List<AccountDTO> accountListNo(String sCode);

	//가장높은코드 가져오기
	public String accountMaxCode();
	
	//세션에 담긴 아이디로 계약매장코드 가져오기
	public List<SsrHapDTO> getShopRelationCode(String SID);
	
	//거래처 생성하기
	public int accountInsert(AccountDTO account);
	
	//리스트 검색시
	public List<AccountDTO> accountSearchListYes(String select,String searchName,String beginDate,String endDate,String sCode);
	public List<AccountDTO> accountSearchListNo(String select,String searchName,String beginDate,String endDate,String sCode);
	
	//거래처 코드로 특정 데이터 불러오기
	public List<AccountDTO> accountUpdate(String acCode);
	
	//거래처 수정하기
	public int accountUpdateSet(AccountDTO account);
	
	//세션 아이디로 패스워드 불러오기
	public List<MemberDTO> getPw(String SID);
	
	//거래처 삭제하기
	public int accountDelete(String accountCode);	
}
