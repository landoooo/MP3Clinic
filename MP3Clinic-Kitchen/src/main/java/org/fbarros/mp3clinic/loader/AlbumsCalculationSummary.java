package org.fbarros.mp3clinic.loader;

import java.util.ArrayList;
import java.util.Collection;

import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.Album;

public class AlbumsCalculationSummary {

	private Collection<Album> collection = new ArrayList<Album>();
	
	private Collection<Message> messages = new ArrayList<Message>();

	public void merge(AlbumsCalculationSummary summary){
		this.collection.addAll(summary.getCollection());
		this.messages.addAll(summary.getMessages());
	}
	
	public void addMessage(Message message){
		messages.add(message);
	}
	
	public void addAlbum(Album album){
		collection.add(album);
	}
	
	public Collection<Album> getCollection() {
		return collection;
	}

	public Collection<Message> getMessages() {
		return messages;
	}
	
}
