package com.uow.Model;

import org.springframework.stereotype.Component;

@Component
public class Award {
	
	private int awardID;
	private String awardName;
	private String awardDesc;
	private int questID;
	private int totalQuestNum;
	private int categoryNum;
	
	public int getAwardID() {
		return awardID;
	}
	public void setAwardID(int awardID) {
		this.awardID = awardID;
	}
	public String getAwardName() {
		return awardName;
	}
	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}
	public String getAwardDesc() {
		return awardDesc;
	}
	public void setAwardDesc(String awardDesc) {
		this.awardDesc = awardDesc;
	}
	public int getQuestID() {
		return questID;
	}
	public void setQuestID(int questID) {
		this.questID = questID;
	}
	public int getTotalQuestNum() {
		return totalQuestNum;
	}
	public void setTotalQuestNum(int totalQuestNum) {
		this.totalQuestNum = totalQuestNum;
	}
	public int getCategoryNum() {
		return categoryNum;
	}
	public void setCategoryNum(int categoryNum) {
		this.categoryNum = categoryNum;
	}
}
