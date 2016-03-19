package org.fbarros.mp3clinic.data;

import org.fbarros.mp3clinic.Category;
import org.fbarros.mp3clinic.Priority;

public class ReportingData {
	
	private Priority priority;
	private Category category;
	private String messageKey;

	
	public ReportingData(Priority priority, Category category, String messageKey) {
		super();
		this.priority = priority;
		this.category = category;
		this.messageKey = messageKey;
	}

	@Override
	public String toString() {
		return "[" + priority + "|" + category + "|" + messageKey + "]";
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((messageKey == null) ? 0 : messageKey.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReportingData other = (ReportingData) obj;
		if (category != other.category)
			return false;
		if (messageKey == null) {
			if (other.messageKey != null)
				return false;
		} else if (!messageKey.equals(other.messageKey))
			return false;
		if (priority != other.priority)
			return false;
		return true;
	}

	///////////////////// GETTERS / SETTERS ///////////////////////////
	
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