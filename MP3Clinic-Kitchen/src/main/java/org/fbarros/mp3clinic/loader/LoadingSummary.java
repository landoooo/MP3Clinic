package org.fbarros.mp3clinic.loader;

import java.util.ArrayList;
import java.util.Collection;

import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.Track;

public class LoadingSummary {

	private Collection<Track> collection = new ArrayList<Track>();
	
	private Collection<Message> messages = new ArrayList<Message>();

	public void addMessage(Message message){
		messages.add(message);
	}
	
	public void addTrack(Track track){
		collection.add(track);
	}
	
	public Collection<Track> getCollection() {
		return collection;
	}

	public Collection<Message> getMessages() {
		return messages;
	}
	
}
