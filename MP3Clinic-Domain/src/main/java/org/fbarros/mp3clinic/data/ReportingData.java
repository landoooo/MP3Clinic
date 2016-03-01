package org.fbarros.mp3clinic.data;

import org.fbarros.mp3clinic.Category;
import org.fbarros.mp3clinic.Priority;

public class ReportingData {
	
	private Priority priority;
	private Category category;
	private String messageKey;

	public ReportingData() {
	}

	/////////////////////////////// GETTERS / SETTERS /////////////////////////////
	
	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}


}