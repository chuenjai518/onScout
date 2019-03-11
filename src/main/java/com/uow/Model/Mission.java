package com.uow.Model;

import org.springframework.stereotype.Component;

@Component
public class Mission {

	private int missionID;
	private int subTaskID;
	private String missionDesc;
	private int questID;
	
	public int getMissionID() {
		return missionID;
	}
	public void setMissionID(int missionID) {
		this.missionID = missionID;
	}
	public int getSubTaskID() {
		return subTaskID;
	}
	public void setSubTaskID(int subTaskID) {
		this.subTaskID = subTaskID;
	}
	public String getMissionDesc() {
		return missionDesc;
	}
	public void setMissionDesc(String missionDesc) {
		this.missionDesc = missionDesc;
	}
	public int getQuestID() {
		return questID;
	}
	public void setQuestID(int questID) {
		this.questID = questID;
	}
	
}
