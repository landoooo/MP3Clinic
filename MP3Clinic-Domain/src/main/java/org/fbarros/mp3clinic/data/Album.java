package org.fbarros.mp3clinic.data;

import java.util.ArrayList;
import java.util.List;

public class Album {
 
	private String albumName;
	private String artist;
	private boolean compilation;
	private Integer year;
	private int numberOfTracks;
	private List<Track> tracks;
	
	public Album() {
		tracks = new ArrayList<Track>();
	}
	
	public String getAlbumName() {
		return albumName;
	}
	
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public boolean isCompilation() {
		return compilation;
	}
	
	public void setCompilation(boolean compilation) {
		this.compilation = compilation;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getNumberOfTracks() {
		return numberOfTracks;
	}
	
	public void setNumberOfTracks(int numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	@Override
	public String toString() {
		return "[" + albumName + ", by " + artist + "(" + year + ")]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((albumName == null) ? 0 : albumName.hashCode());
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
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
		Album other = (Album) obj;
		if (albumName == null) {
			if (other.albumName != null)
				return false;
		} else if (!albumName.equals(other.albumName))
			return false;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		return true;
	}
	

}
