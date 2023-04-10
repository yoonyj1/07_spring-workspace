package com.kh.opendata.model.vo;

public class Company {

	private String cno;
	private String corpNm;
	private String enpBsadr;
	
	public Company () {}

	public Company(String cno, String corpNm, String enpBsadr) {
		super();
		this.cno = cno;
		this.corpNm = corpNm;
		this.enpBsadr = enpBsadr;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getCorpNm() {
		return corpNm;
	}

	public void setCorpNm(String corpNm) {
		this.corpNm = corpNm;
	}

	public String getEnpBsadr() {
		return enpBsadr;
	}

	public void setEnpBsadr(String enpBsadr) {
		this.enpBsadr = enpBsadr;
	}

	@Override
	public String toString() {
		return "Company [cno=" + cno + ", corpNm=" + corpNm + ", enpBsadr=" + enpBsadr + "]";
	}
	
	
}
