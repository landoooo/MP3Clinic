package org.fbarros.mp3clinic.data;

import org.fbarros.mp3clinic.Category;
import org.fbarros.mp3clinic.Priority;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ReportingData {
	
	private ObjectProperty<Priority> priorityProperty;
	private ObjectProperty<Category> categoryProperty;
	private StringProperty messageKeyProperty;
	private StringProperty processorName;

	
	public ReportingData(Priority priority, Category category, String messageKey, String processorName) {
		super();
		this.priorityProperty = new SimpleObjectProperty<Priority>(priority);
		this.categoryProperty = new SimpleObjectProperty<Category>(category);
		this.messageKeyProperty = new SimpleStringProperty(messageKey);
		this.processorName = new SimpleStringProperty();
	}

	@Override
	public String toString() {
		return "[" + priorityProperty + "|" + categoryProperty + "|" + messageKeyProperty + "]";
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryProperty == null) ? 0 : categoryProperty.hashCode());
		result = prime * result + ((messageKeyProperty == null) ? 0 : messageKeyProperty.hashCode());
		result = prime * result + ((priorityProperty == null) ? 0 : priorityProperty.hashCode());
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
		if (categoryProperty != other.categoryProperty)
			return false;
		if (messageKeyProperty == null) {
			if (other.messageKeyProperty != null)
				return false;
		} else if (!messageKeyProperty.equals(other.messageKeyProperty))
			return false;
		if (priorityProperty != other.priorityProperty)
			return false;
		return true;
	}

	///////////////////// GETTERS / SETTERS ///////////////////////////
	
	public Priority getPriority() {
		return priorityProperty.get();
	}

	public void setPriority(Priority priority) {
		this.priorityProperty.set(priority);
	}

	public Category getCategory() {
		return categoryProperty.get();
	}

	public void setCategory(Category category) {
		this.categoryProperty.set(category);
	}

	public String getMessageKey() {
		return messageKeyProperty.get();
	}

	public void setMessageKey(String messageKey) {
		this.messageKeyProperty.set(messageKey);
	}
	
	public String getProcessorName(){
		return processorName.get();
	}
	
	public void setProcessorName(String name){
		this.processorName.set(name);	
	}


}