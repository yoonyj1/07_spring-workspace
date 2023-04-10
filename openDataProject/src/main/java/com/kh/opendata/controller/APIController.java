package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {

	public static final String servicekey = "HXcKB69ftuMxl%2FonJiZE0MPt4OmuFeB%2B1Y%2Fxop%2FTi1LCCutCJL8sczK9W0c%2BsPWv7x6Pl8h%2BsSnwrE39SnUa5g%3D%3D";

	@ResponseBody
	@RequestMapping(value = "air.do", produces="application/json; charset=utf-8")
	public String airPollution(String location) throws IOException {
		String url = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";

		url += "?serviceKey=" + servicekey;
		url += "&sidoName=" + URLEncoder.encode(location, "UTF-8");
		url += "&returnType=json";
		url += "&numOfRows=50";

		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();

		urlConnection.setRequestMethod("GET");

		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

		String line;
		String responseText = "";

		while ((line = br.readLine()) != null) {
			responseText += line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		// System.out.println(responseText);
		
		return responseText;
	}
	
	@ResponseBody
	@RequestMapping(value="comp.do", produces="application/json; charset=utf-8")
	public String roadList() throws IOException {
		String url = "https://apis.data.go.kr/1160100/service/GetCorpBasicInfoService_V2/getAffiliate_V2";
		url += "?serviceKey=" + servicekey;
		url += "&pageNo=1";
		url += "&numOfRows=10";
		url += "&returnType=json";
		url += "&basDt=20200517";
		
		URL requestUrl = new URL(url);
		
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String line;
		String responseText = "";
		
		while((line = br.readLine())!= null) {
			responseText += line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		return responseText;
	}
}
