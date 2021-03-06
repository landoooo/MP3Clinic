package org.fbarros.mp3clinic.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.fbarros.mp3clinic.Message;

public class ProcessingReport<T> {

	private List<T> collection = new ArrayList<T>();
	
	private List<Message> messages = new ArrayList<Message>();

	public void merge(ProcessingReport<T> summary){
		this.collection.addAll(summary.getCollection());
		this.messages.addAll(summary.getMessages());
	}
	
	public void addMessage(Message message){
		messages.add(message);
	}
	
	public void addElement(T element){
		collection.add(element);
	}
	
	public List<T> getCollection() {
		return collection;
	}

	public Collection<Message> getMessages() {
		return messages;
	}

	public void addMessages(Collection<Message> messages2) {
		this.messages.addAll(messages2);
		
	}
	
}
