package com.uow.Model;

public class EmerContact {

	private int emerID;
	private String username;
	private String emerName;
	private String emerRelation;
	private int emerTel;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmerName() {
		return emerName;
	}
	public void setEmerName(String emerName) {
		this.emerName = emerName;
	}
	public String getEmerRelation() {
		return emerRelation;
	}
	public void setEmerRelation(String emerRelation) {
		this.emerRelation = emerRelation;
	}
	public int getEmerTel() {
		return emerTel;
	}
	public void setEmerTel(int emerTel) {
		this.emerTel = emerTel;
	}
	public int getEmerID() {
		return emerID;
	}
	public void setEmerID(int emerID) {
		this.emerID = emerID;
	}
}
