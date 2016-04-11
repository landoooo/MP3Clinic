package org.fbarros.mp3clinic.gui.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.LibraryLoad;

import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExecutionData {

    private ObservableList<Message> messages;
    private List<Album> albums;
    private LibraryLoad currentLibraryLoad;
	private BooleanPropertyBase loaded;
    
    @PostConstruct
	public void initialize(){
    	this.loaded = new SimpleBooleanProperty(false);
		this.messages = FXCollections.observableArrayList();
		this.albums = new ArrayList<Album>();
	}
	
	public ObservableList<Message> getMessages() {
		return messages;
	}
	
	public void addMessages(Collection<Message> messages){
		this.messages.addAll(messages);
	}
	
	public LibraryLoad getCurrentLibraryLoad() {
		return currentLibraryLoad;
	}
	
	public void setCurrentLibraryLoad(LibraryLoad currentLibraryLoad) {
		this.currentLibraryLoad = currentLibraryLoad;
	}
	
	public List<Album> getAlbums() {
		return albums;
	}
	
	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	
	public void setLoaded(boolean loaded){
		this.loaded.set(loaded);
	}

	public boolean isLoaded(){
		return this.loaded.get();
	}

	public BooleanPropertyBase getLoadedPorperty() {
		return loaded;
	}

	public void removeAllMessages() {
		this.messages.removeAll(this.messages);
		
	}

}
