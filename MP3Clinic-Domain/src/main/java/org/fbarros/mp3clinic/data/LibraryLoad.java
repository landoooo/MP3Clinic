package org.fbarros.mp3clinic.data;

import java.time.LocalDateTime;
import java.util.List;

import org.fbarros.mp3clinic.Message;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LibraryLoad {

	private ObjectProperty<LocalDateTime> dateProperty;
	private StringProperty nameProperty;
	private StringProperty reportXMLPathProperty;
	private StringProperty albumCollectionXMLPathProperty;
	private StringProperty libraryPathProperty;
	private ObjectProperty<List<Album>> albumsProperty;
	private ObjectProperty<List<Message>> messagesProperty;

	public LibraryLoad(){
		this.dateProperty = new SimpleObjectProperty<LocalDateTime>();
		this.nameProperty = new SimpleStringProperty();
		this.reportXMLPathProperty = new SimpleStringProperty();
		this.albumCollectionXMLPathProperty = new SimpleStringProperty();
		this.libraryPathProperty = new SimpleStringProperty();
		this.albumsProperty = new SimpleObjectProperty<List<Album>>();
		this.messagesProperty = new SimpleObjectProperty<List<Message>>();
	}
	
	public LocalDateTime getDate() {
		return dateProperty.get();
	}
	
	public void setDate(LocalDateTime date) {
		this.dateProperty.set(date);
	}
	
	public String getName() {
		return nameProperty.get();
	}
	
	public void setName(String name) {
		this.nameProperty.set(name);
	}
	
	public String getReportXMLPath() {
		return reportXMLPathProperty.get();
	}
	
	public void setReportXMLPath(String reportXMLPath) {
		this.reportXMLPathProperty.set(reportXMLPath);
	}
	
	public String getAlbumCollectionXMLPath() {
		return albumCollectionXMLPathProperty.get();
	}
	
	public void setAlbumCollectionXMLPath(String albumCollectionXMLPath) {
		this.albumCollectionXMLPathProperty.set(albumCollectionXMLPath);
	}

	public String getLibraryPath() {
		return libraryPathProperty.get();
	}

	public void setLibraryPath(String libraryPath) {
		this.libraryPathProperty.set(libraryPath);
	}
	
	
	public List<Album> getAlbums() {
		return albumsProperty.get();
	}

	public void setAlbums(List<Album> albums) {
		this.albumsProperty.set(albums);
	}

	public List<Message> getMessages() {
		return messagesProperty.get();
	}

	public void setMessages(List<Message> messages) {
		this.messagesProperty.set(messages);
	}

	public String toString(){
		return nameProperty + " (" + dateProperty + ")"; 
	}
}
