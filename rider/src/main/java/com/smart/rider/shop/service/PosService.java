package com.smart.rider.shop.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.member.mapper.MemberMapper;
import com.smart.rider.shop.dto.PosDTO;
import com.smart.rider.shop.dto.SsrHapDTO;
import com.smart.rider.shop.mapper.PosMapper;

@Service
public class PosService {

	@Autowired
	private PosMapper posMapper;
	@Autowired
	private MemberMapper memberMapper;
	
	//세션 아이디 값을 대입해서 결과값 얻기
	public List<SsrHapDTO> getMemberId(String memberId) {
		return posMapper.getMemberId(memberId);
	}
	
	//POS생성
	public int posInsert(PosDTO pos) {
		//자동코드 증가
		String posCode = "POS"+ posMapper.posCodeMax();
		if(posCode.equals("POSnull")) { 
			posCode = "POS0001";
		}
		//코드 값 DTO에 대입하기
		pos.setPosCode(posCode);
		return posMapper.posInsert(pos);
	}
	
	//계약매장코드에 해당되는 목록 보기
	public List<PosDTO> getPosList(HttpSession session){
		//로그인 된 권한 확인
		String level = (String)session.getAttribute("SLEVEL");
		System.out.println(level + "로그인된 아이디의 권한 확인");
		//로그인 된 아이디 확인
		String sid =(String)session.getAttribute("SID");
		System.out.println(sid + "로그인된 아이디 확인");
		//로그인된 아이디로 계약매장코드 가져오기
		List<SsrHapDTO> ssrList = posMapper.getMemberId(sid);
		System.out.println(ssrList + "ssrList에 담긴 값 확인");
		String contractShopCode  = null;
		//값이 없을 경우
		if(ssrList.size() != 0) {
		contractShopCode  = null;
		contractShopCode  = ssrList.get(0).getContractShopCode();
		//System.out.println(contractShopCode);
		}
		if(level.equals("직원")) {
			MemberDTO memberList = memberMapper.getMemberList(sid);
			contractShopCode = memberList.getContractShopCode();
			System.out.println(contractShopCode);		
		}
		
		return posMapper.getPosList(contractShopCode,level);
	}
	
	//수정할 데이터 목록
	public List<PosDTO> posUpdate(String posCode){
		return posMapper.posUpdate(posCode);
	}
	
	//입력한 값으로 수정
	public int posUpdateSet(PosDTO pos) {
		return posMapper.posUpdateSet(pos);
	}
	
	//POS 삭제
	public int posDeleteSet(String memberPw,String posCode,HttpSession session) {
		//입력받은 posCode로 id 가져오기
		String id = posMapper.getId(posCode);
		System.out.println(id + "코드 조회시 나오는 ID");
		if(id == null) {
			id= "id001";
		}
		//가져온 id로 비밀번호 와 생년월일 가져오기
		List<MemberDTO> mList = posMapper.getMember(id);
		String pw = mList.get(0).getMemberPw();
		String birth =mList.get(0).getMemberBirth();
		System.out.println(pw + "id 조회시 나오는 비밀번호");
		System.out.println(birth + "id 조회시 나오는 생년월일");
		String level =(String)session.getAttribute("SLEVEL");
		//System.out.println(level + "관리자 권한 확인");
		//권한이 관리자일 경우 관리자 비밀번호 입력
		if(level.equals("관리자")) {
			pw = "pw001";
		}
		//비번 확인
		if(memberPw.equals(pw)) {
			//비번 일치 시 올바른 코드 입력
			return posMapper.posDeleteSet(posCode);
			
		}else {
			//불일치시에 FALSE라는 값이 담긴다.
			posCode = "false";
		}
		return posMapper.posDeleteSet(posCode);
	}
}
