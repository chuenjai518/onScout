package com.uow.Model;

import org.springframework.stereotype.Component;

@Component
public class Category {
	
	private int categoryID;
	private int awardID;
	private String categoryName;
	private int questID;
	private int taskNum;
	
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public int getAwardID() {
		return awardID;
	}
	public void setAwardID(int awardID) {
		this.awardID = awardID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getQuestID() {
		return questID;
	}
	public void setQuestID(int questID) {
		this.questID = questID;
	}
	public int getTaskNum() {
		return taskNum;
	}
	public void setTaskNum(int taskNum) {
		this.taskNum = taskNum;
	}
}
