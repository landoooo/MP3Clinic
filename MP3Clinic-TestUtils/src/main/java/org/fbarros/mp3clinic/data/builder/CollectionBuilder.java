package org.fbarros.mp3clinic.data.builder;

import java.util.ArrayList;
import java.util.Collection;

import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.Track;

public class CollectionBuilder {

	public static final String ARTIST_PREFIX = "Artist_";
	public static final String ALBUM_PREFIX = "Album_";
	public static final String TRACKNAME_PREFIX = "Track_";

	private AlbumBuilder albumBuilder = new AlbumBuilder();

	public CollectionBuilder(AlbumBuilder albumBuilder){
		this.albumBuilder = albumBuilder;
	}
	
	public Collection<Album> buildCollection (int artists, int albums, int songs){
		Collection<Album> collection = new ArrayList<Album>();
		for (int artistIndex = 1; artistIndex <= artists; artistIndex++ ){
			for (int albumIndex = 1; albumIndex <= albums; albumIndex++ ){
				Album album = new Album();
				String artistStr = ARTIST_PREFIX + artistIndex;
				String albumStr = ALBUM_PREFIX + albumIndex;
				album.setTracks(albumBuilder.buildAlbum(artistStr, albumStr, songs));
				album.setAlbum(albumStr);
				album.setArtist(artistStr);
				collection.add(album);
			}
		}
		return collection;
	}
	
	/////////////////// GETTERS / SETTERS ///////////////////////////////
	
	public void setAlbumBuilder(AlbumBuilder albumBuilder){
		this.albumBuilder= albumBuilder;
	}
}
