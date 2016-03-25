package org.fbarros.mp3clinic.loader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.fbarros.mp3clinic.Message;

public class ProcessingReport<T> {

	private Collection<T> collection = new ArrayList<T>();
	
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
	
	public Collection<T> getCollection() {
		return collection;
	}

	public Collection<Message> getMessages() {
		return messages;
	}
	
}
