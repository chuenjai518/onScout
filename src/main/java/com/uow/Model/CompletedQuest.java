package com.uow.Model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CompletedQuest {
	private int questID;
	private Date FinishDate;
	private String username;
	
	public int getQuestID() {
		return questID;
	}
	public void setQuestID(int questID) {
		this.questID = questID;
	}
	public Date getFinishDate() {
		return FinishDate;
	}
	public void setFinishDate(Date finishDate) {
		FinishDate = finishDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
