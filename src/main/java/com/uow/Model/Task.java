package com.uow.Model;

import org.springframework.stereotype.Component;

@Component
public class Task {

	private int taskID;
	private int categoryID;
	private String taskDesc;
	private int questID;
	private int subTaskNum;
	
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	public int getQuestID() {
		return questID;
	}
	public void setQuestID(int questID) {
		this.questID = questID;
	}
	public int getSubTaskNum() {
		return subTaskNum;
	}
	public void setSubTaskNum(int subTaskNum) {
		this.subTaskNum = subTaskNum;
	}
	
}
