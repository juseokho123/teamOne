package com.smart.rider.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.rider.main.dto.ChartValueDTO;
import com.smart.rider.main.mapper.MainMapper;

@Service
public class MainService {
	
	@Autowired
	private MainMapper mainMapper;
		
	
	/*** 190926 재욱, 월별 컬럼의 합 ***/
	public int[] chartValue(Map<String, Object> map){
		
		List<ChartValueDTO> list = new ArrayList<>();
		
		String month = "";
		String checkMonth = "";
		
		for(int i=0; i<12; i++) {
			
			// 9이하, 한자리 숫자 앞에 0을 붙이기 위한 조건문
			if(i<9) {
				month = "0" + String.valueOf(i+1); // int i to String month
			} else {
				month = String.valueOf(i+1); // int i to String month
			}
			
			// 입력된 chartYear + 반복문으로 만든 month, ex) 2019-09
			checkMonth = map.get("chartYear") + "-" + month;
			map.put("checkMonth", checkMonth);
			// System.out.println(map.toString() + " <-- map.toString() chartValue MainService.java");
			
			ChartValueDTO chartValueDTO = mainMapper.chartValue(map).get(0);

			list.add(i, chartValueDTO); // 결과들을 list에 담음
	
		}
		// System.out.println(list + " <-- list chartValue MainService.java");
		
		int[] monthArray = new int[12]; // 배열 선언
		
		// 12달에 값을 넣기 위한 반복문, DB 조회 후 값이 없을 경우 0
		for(int i=0; i<12; i++) {
			monthArray[i] = 0;
			if(list.get(i) != null) {
				monthArray[i] = list.get(i).getChartValueSum(); // DB 조회 후 값이 있을 경우 값 대입
			}
			
		}
		//System.out.println(Arrays.toString(monthArray) + " <-- monthArray.toString() chartValue MainService.java");
		
		return monthArray;
	}
}
