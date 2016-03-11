package org.fbarros.mp3clinic.data;

public class Track {

	private String name;
	private String path;
	private String artist;
	private String album;
	private Integer number;
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
		
}
