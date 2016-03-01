package org.fbarros.mp3clinic.data.builder;

import java.util.ArrayList;
import java.util.Collection;

import org.fbarros.mp3clinic.data.Track;

public class CollectionBuilder {

	public static final String ARTIST_PREFIX = "Artist_";
	public static final String ALBUM_PREFIX = "Album_";
	public static final String TRACKNAME_PREFIX = "Track_";

	private AlbumBuilder albumBuilder = new AlbumBuilder();

	public CollectionBuilder(AlbumBuilder albumBuilder){
		this.albumBuilder = albumBuilder;
	}
	
	public Collection<Track> buildCollection (int artists, int albums, int songs){
		Collection<Track> albumTracks = new ArrayList<Track>();
		for (int artistIndex = 1; artistIndex <= artists; artistIndex++ ){
			for (int albumIndex = 1; albumIndex <= albums; albumIndex++ ){
				albumTracks.addAll(albumBuilder.buildAlbum(ARTIST_PREFIX + artistIndex, ALBUM_PREFIX + albumIndex, songs));
			}
		}
		return albumTracks;
	}
	
	/////////////////// GETTERS / SETTERS ///////////////////////////////
	
	public void setAlbumBuilder(AlbumBuilder albumBuilder){
		this.albumBuilder= albumBuilder;
	}
}
