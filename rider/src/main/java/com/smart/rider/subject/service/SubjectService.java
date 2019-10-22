package com.smart.rider.subject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.rider.subject.dto.SubjectDTO;
import com.smart.rider.subject.mapper.SubjectMapper;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectMapper subjectMapper;
	
	// 계정과목 리스트 화면
	public List<SubjectDTO> subjectList(){
		return subjectMapper.subjectList();
	}
	
	// 계정과목 등록 
	public int subjectInsert(SubjectDTO subjectDTO, String memberId) {
		String subjectCode = "AS" + subjectMapper.subjectCodeCount(); // 계정과목 코드 자동증가
		//System.out.println(subjectCode + " <-- subjectCode check subjectInsert SubjectService.java");
		if(subjectCode.equals("ASnull")) { //전체 삭제 후 다시 등록시 null을 받아오는 문제
			subjectCode = "AS0001";
		}
		subjectDTO.setSubjectCode(subjectCode);
		//System.out.println(memberId + " <-- memberId subjectInsert SubjectService.java");
		subjectDTO.setMemberId(memberId);
		return subjectMapper.subjectInsert(subjectDTO);
	}
	
	//삭제 가능한 계정과목 리스트
	public List<SubjectDTO> subjectDeletePossible(String subjectKey, String subjectValue, String beginDate, String endDate){
		return subjectMapper.subjectDeletePossible(subjectKey, subjectValue, beginDate, endDate);
	}
	
	//삭제 불가능한 계정과목 리스트 (외래키 참조중)
	public List<SubjectDTO> subjectDeleteImpossible(String subjectKey, String subjectValue, String beginDate, String endDate){
		return subjectMapper.subjectDeleteImpossible(subjectKey, subjectValue, beginDate, endDate);
	}
	
	//계정과목 삭제
	public int subjectDelete(String subjectCode) {
		return subjectMapper.subjectDelete(subjectCode);
	}
	
	// 계정과목 수정화면
	public List<SubjectDTO> getSubjectList(String subjectCode) {
		return subjectMapper.subjectList(subjectCode);
	}
	
	// 계정과목 수정
	public int subjectUpdate(SubjectDTO subjectDTO) {
		return subjectMapper.subjectUpdate(subjectDTO);
	}

}
