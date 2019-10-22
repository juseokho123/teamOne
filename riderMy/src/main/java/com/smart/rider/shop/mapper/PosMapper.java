package com.smart.rider.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.shop.dto.PosDTO;
import com.smart.rider.shop.dto.SsrHapDTO;

@Mapper
public interface PosMapper {
	//DB에서 가장 높은 코드값 확인
	public String posCodeMax();
	
	//POS 생성
	public int posInsert(PosDTO pos);
	
	//아이디값 가져오기
	public List<SsrHapDTO> getMemberId(String memberId);
	
	//POS 목록
	public List<PosDTO> getPosList(String contractShopCode,String level);
	
	//수정하기 위한 목록
	public List<PosDTO> posUpdate(String posCode);
	
	//아이디값으로 비밀번호,생년월일 조회
	public List<MemberDTO> getMember(String memberId);
	
	//수정하기
	public int posUpdateSet(PosDTO pos);
	
	//POS코드로 ID값 가져오기
	public String getId(String posCode);
	
	//삭제하기
	public int posDeleteSet(String posCode);
}
