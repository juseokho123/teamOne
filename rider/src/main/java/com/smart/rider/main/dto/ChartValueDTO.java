package com.smart.rider.main.dto;

public class ChartValueDTO {
	
	private String chartValueMonth;
	private int chartValueSum;
	
	public String getChartValueMonth() {
		return chartValueMonth;
	}
	public void setChartValueMonth(String chartValueMonth) {
		this.chartValueMonth = chartValueMonth;
	}
	public int getChartValueSum() {
		return chartValueSum;
	}
	public void setChartValueSum(int chartValueSum) {
		this.chartValueSum = chartValueSum;
	}
	
	@Override
	public String toString() {
		return "ChartValueDTO [chartValueMonth=" + chartValueMonth + ", chartValueSum=" + chartValueSum + "]";
	}
}
