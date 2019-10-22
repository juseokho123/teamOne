package com.smart.rider.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.smart.rider.contract.dto.ContractDTO;
import com.smart.rider.main.dto.SearchDTO;
import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.shop.dto.ShopDTO;
import com.smart.rider.shop.dto.ShopRelationDTO;

import com.smart.rider.shop.mapper.ShopMapper;

@Service
public class ShopService {
	@Autowired
	private ShopMapper shopMapper;


	//매장생성시 매장계약코드 자동 생성,매장계약코드 생성시에 아이디에 있는 매장계약코드가 입력된다(수정).
	public int shopInsert(ShopDTO shop,HttpSession session,ShopRelationDTO relation) {
		// 담겨있는 contractCode로 contractDTO조회
		String contractCode = shop.getContractCode();
		//System.out.println(contractCode + "-->담겨 있는 값");
		// 조회된 값에서 id만 가져오기 및 확인작업
		List<ContractDTO> List = shopMapper.getCode(contractCode);
		//System.out.println(List + "<--담긴 데이터");
		String memberId = List.get(0).getMemberId();
		//System.out.println(memberId+"<--담긴 ID값");
		//자동코드 증가
		String shopCode = "S"+ shopMapper.shopCodeMax();
		if(shopCode.equals("Snull")) { 
			shopCode = "S0001";
		}
		//ShopDTO에 자동 코드 증가 값과 아이디값 담기
		shop.setShopCode(shopCode);
		shop.setMemberId(memberId);
		//result 0으로 설정
		int result = 0;
		//result 리턴데이터를 넣는다(매장)
		result += shopMapper.shopInsert(shop);
		//shop 코드를 조회해서 가장 마지막 코드 값 가져오기
		List<ShopDTO> shopList = shopListAll();
		String getshopCode = shopList.get(shopList.size()-1).getShopCode();
		String getmemberId = shopList.get(shopList.size()-1).getMemberId();
		//System.out.println(getshopCode);
		//세션에 담기
		session.setAttribute("SSHOPCODE",getshopCode);
		//자동코드 증가
		String relationCode = "SR"+ shopMapper.relationCodeMax();
		if(relationCode.equals("SRnull")) { 
			relationCode = "SR0001";
		}
		//relation 에 가장 마지막에 담은 Code 값과 id 값 담고 조합된 relationCode 생성
		relation.setContractShopCode(relationCode);
		relation.setMemberId(getmemberId);
		relation.setShopCode(getshopCode);
		//result에 리턴 값을 넣는다(계약매장).
		result += shopMapper.relationInsert(relation);
		//계약매장코드 확인 후 가장 마지막 코드 값 가져오기 및 아이디값 가져오기
		List<ShopRelationDTO> relationList = relationList();
		String getrelationCode = relationList.get(relationList.size()-1).getContractShopCode();
		String getrelationId = relationList.get(relationList.size()-1).getMemberId();
		//System.out.println(getrelationCode + "리스트에 마지막에서 가져온 코드값");
		//System.out.println(getrelationId + "리스트에 마지막에서 가져온 id값");
		//점주 아이디
		result += shopMapper.memberUpdate(getrelationCode,getrelationId);
		return result;
	}
	
	//계약매장목록
	public List<ShopRelationDTO> relationList(){
		return shopMapper.relationList();
	};
	
	//매장 조회
	//맵으로 리턴 시키기 위해서 맵으로 선언해준다.
	public Map<String, Object> getShopList(HttpSession session){
		//로그인된 아이디의 권한이 점주면 id에 해당되는 데이터만불러오기
		String id = null;
		String level = (String)session.getAttribute("SLEVEL");
		//System.out.println(level + "<-- 로그인한 아이디의 권한");
		if(level.equals("점주")) {
			id =(String) session.getAttribute("SID");
			//System.out.println(id + "<-- 로그인한 아이디");	
		}
		//맵으로 선언
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("shopListYes", shopMapper.shopListYes(id));
		map.put("shopListNo", shopMapper.shopListNo(id));
		//맵으로 리턴 시킨다.
		return map;
	}
	
	//특정 값으로 매장검색
	public Map<String,Object> shopSearchList(SearchDTO search,HttpSession session){
		//맵으로 선언
		Map<String,Object> map = new HashMap<String,Object>();
		//map 넣을 내용을 String,Object 형식으로 넣어준다.
		//로그인된 아이디의 권한이 점주면 id에 해당되는 데이터만불러오기
				String level = (String)session.getAttribute("SLEVEL");
				//System.out.println(level + "<-- 로그인한 아이디의 권한");
				if(level.equals("점주")) {
					String id =(String) session.getAttribute("SID");
					//System.out.println(id + "<-- 로그인한 아이디");	
					search.setSearchKey(id);;
				}
		map.put("shopSearchListYes", shopMapper.shopSearchListYes(search));
		map.put("shopSearchListNo", shopMapper.shopSearchListNo(search));
		//System.out.println(shopMapper.shopSearchListYes(search) + "yes서비스에서 담긴값 확인");
		//System.out.println(shopMapper.shopSearchListNo(search) + " no서비스에서 담긴값 확인");
		return map;
	}
	
	//전체 리스트 가져오기
	public List<ShopDTO> shopListAll(){
		return shopMapper.shopListAll();
	}
	
	//리스트에서 목록처럼 보여주기
	public Map<String, Object> shopList(int currentPage,HttpSession session) {
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
		Map<String, Object> map = new HashMap<String, Object>();
		// Table(board)에 담겨진 행의 시작점은 0이므로 현재페이지가 첫번째일 경우 0으로 시작하는 알고리즘
		int startRow = (currentPage-1)*ROW_PER_PAGE;
		// map에 보여줄 행의 시작점과 보여줄 행의 갯수를 키와 함께 담는다.
		map.put("currentPage", startRow);
		map.put("rowPerPage", ROW_PER_PAGE);
		// Table(board)에 담겨진 전체 행의 갯수
		double shopCount = shopMapper.selectShopCount();
		// view에 보여질 마지막 페이지의 수(전체행의 갯수/보여줄 행의 갯수-> 올림)
		int lastPage = (int)(Math.ceil(shopCount/ROW_PER_PAGE));
		// view에 현재 페이지가 마지막페이지보다 02작을 경우 view쪽의 반복문의 반복횟수를 조정
		if(currentPage >= (lastPage-2)) {
			lastPageNum = lastPage;
		}
		//로그인된 아이디의 권한이 점주면 id에 해당되는 데이터만불러오기
		String level = (String)session.getAttribute("SLEVEL");
		//System.out.println(level + "<-- 로그인한 아이디의 권한");
		map.put("level",level);
		if(level.equals("점주")) {
			String id =(String) session.getAttribute("SID");
			//System.out.println(id + "<-- 로그인한 아이디");	
			map.put("id", id);
		}
		
		// view에 보여질 페이징 처리를 위해 값을 Map에 담아 리턴
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("list", shopMapper.shopList(map));
		returnMap.put("currentPage", currentPage);
		returnMap.put("lastPage", lastPage);
		returnMap.put("startPageNum", startPageNum);
		returnMap.put("lastPageNum", lastPageNum);
				
	
		
		return returnMap;
	}
	
	//매장인원
	public List<ShopDTO> personnelList(HttpSession session){
		String code = (String)session.getAttribute("SCODE");
		//System.out.println(code+"<--현재 아이디에 담겨잇는 세션의 코드값");
		return shopMapper.personnelList(code);
	}
	
	//코드 값 확인 후 데이터 조회
	public List<ShopDTO> shopUpdate(String shopCode){
		return shopMapper.shopUpdate(shopCode); 
	}
	
	//수정하기
	public int shopUpdate(ShopDTO shop) {
		return shopMapper.shopUpdateSet(shop);
	}
		
	//계약매장코드 조회
	public Map<String, Object> getRelationList(){
		//맵으로 선언
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("relationYes", shopMapper.relationYes());
		map.put("relationNo", shopMapper.relationNo());
		//System.out.println(shopMapper.relationYes() + "yes서비스에서 담긴값 확인");
		//System.out.println(shopMapper.relationNo() + " no서비스에서 담긴값 확인");
		//맵으로 리턴 시킨다.
		return map;
	}
	
	//특정 값으로 매장검색
	public Map<String,Object> getRelationSearch(SearchDTO search){
		//맵으로 선언
		Map<String,Object> map = new HashMap<String,Object>();
		//map 넣을 내용을 String,Object 형식으로 넣어준다.
		map.put("relationSearchYes", shopMapper.relationSearchYes(search));
		map.put("relationSearchNo", shopMapper.relationSearchNo(search));
		//System.out.println(shopMapper.relationSearchYes(serach) + "yes서비스에서 담긴값 확인");
		//System.out.println(shopMapper.relationSearchNo(serach) + " no서비스에서 담긴값 확인");
		return map;
	}
	
	//코드 값 확인 후 데이터 조회
	public List<ShopRelationDTO> relationUpdate(String contractShopCode){
		return shopMapper.relationUpdate(contractShopCode); 
	}
	
	//수정하기
	public int relationUpdate(ShopRelationDTO relation) {
		return shopMapper.relationUpdateSet(relation);
	}
	
	//점주아이디 목록 가져오기
	public List<MemberDTO> getMemberId(){
		return shopMapper.getMemberId();
	}
	
}
