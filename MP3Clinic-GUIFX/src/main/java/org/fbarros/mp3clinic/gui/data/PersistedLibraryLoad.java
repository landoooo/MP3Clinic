package org.fbarros.mp3clinic.gui.data;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.LibraryLoad;

/**
 * 
 * @author fbarros
 *
 */
@XmlRootElement(name = "library")
public class PersistedLibraryLoad {

    private List<Message> messages;
    private List<Album> albums;
    private LibraryLoad libraryLoad;
    
    @XmlElement(name = "message")
    public List<Message> getMessages() {
        return messages;
    }

    @XmlElement(name = "album")
    public List<Album> getAlbums() {
        return albums;
    }
    
    @XmlElement(name = "libraryLoad")
    public LibraryLoad getLibraryLoad() {
        return libraryLoad;
    }
    
    public void setLibraryLoad(LibraryLoad libraryLoad){
    	this.libraryLoad = libraryLoad;
    }
    
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
   
    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}