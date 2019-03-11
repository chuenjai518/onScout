package com.uow.Model;

import org.springframework.stereotype.Component;

@Component
public class SubTask {

	private int subTaskID;
	private int taskID;
	private String subTaskDesc;
	private int questID;
	private int missionNum;
	
	public int getSubTaskID() {
		return subTaskID;
	}
	public void setSubTaskID(int subTaskID) {
		this.subTaskID = subTaskID;
	}
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public String getSubTaskDesc() {
		return subTaskDesc;
	}
	public void setSubTaskDesc(String subTaskDesc) {
		this.subTaskDesc = subTaskDesc;
	}
	public int getQuestID() {
		return questID;
	}
	public void setQuestID(int questID) {
		this.questID = questID;
	}
	public int getMissionNum() {
		return missionNum;
	}
	public void setMissionNum(int missionNum) {
		this.missionNum = missionNum;
	}
}
