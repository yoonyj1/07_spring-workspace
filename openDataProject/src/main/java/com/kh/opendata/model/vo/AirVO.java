package com.kh.opendata.model.vo;

public class AirVO {

	private String stationName; 		// 측정소명
	private String dataTime;			// 측정일시
	private String khaiValue;			// 통합대기환경수치
	
	private String pm10Value;			// 미세먼지농도
	private String so2Value;			// 아황산가스농도
	private String coValue;				// 일산화탄소농도
	private String no2Value;			// 이산화질소농도
	private String o3Vlaue;				// 오존농도
	
	public AirVO() {}

	public AirVO(String stationName, String dataTime, String khaiValue, String pm10Value, String so2Value,
			String coValue, String no2Value, String o3Vlaue) {
		super();
		this.stationName = stationName;
		this.dataTime = dataTime;
		this.khaiValue = khaiValue;
		this.pm10Value = pm10Value;
		this.so2Value = so2Value;
		this.coValue = coValue;
		this.no2Value = no2Value;
		this.o3Vlaue = o3Vlaue;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public String getKhaiValue() {
		return khaiValue;
	}

	public void setKhaiValue(String khaiValue) {
		this.khaiValue = khaiValue;
	}

	public String getPm10Value() {
		return pm10Value;
	}

	public void setPm10Value(String pm10Value) {
		this.pm10Value = pm10Value;
	}

	public String getSo2Value() {
		return so2Value;
	}

	public void setSo2Value(String so2Value) {
		this.so2Value = so2Value;
	}

	public String getCoValue() {
		return coValue;
	}

	public void setCoValue(String coValue) {
		this.coValue = coValue;
	}

	public String getNo2Value() {
		return no2Value;
	}

	public void setNo2Value(String no2Value) {
		this.no2Value = no2Value;
	}

	public String getO3Vlaue() {
		return o3Vlaue;
	}

	public void setO3Vlaue(String o3Vlaue) {
		this.o3Vlaue = o3Vlaue;
	}

	@Override
	public String toString() {
		return "AirVO [stationName=" + stationName + ", dataTime=" + dataTime + ", khaiValue=" + khaiValue
				+ ", pm10Value=" + pm10Value + ", so2Value=" + so2Value + ", coValue=" + coValue + ", no2Value="
				+ no2Value + ", o3Vlaue=" + o3Vlaue + "]";
	}
	
	
}
