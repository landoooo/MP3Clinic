package org.fbarros.mp3clinic.data.builder;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static org.fbarros.mp3clinic.data.builder.AlbumBuilder.Album;
import static org.fbarros.mp3clinic.data.builder.AlbumBuilder.albumName;
import static org.fbarros.mp3clinic.data.builder.AlbumBuilder.artist;
import static org.fbarros.mp3clinic.data.builder.AlbumBuilder.numberOfTracks;

import java.util.ArrayList;
import java.util.Collection;

import org.fbarros.mp3clinic.data.Album;

public class CollectionBuilder {

	public static final String ARTIST_PREFIX = "Artist_";
	public static final String ALBUM_PREFIX = "Album_";
	public static final String TRACKNAME_PREFIX = "Track_";

	private TrackCollectionBuilder trackCollecionBuilder;
	
		
	public CollectionBuilder(TrackCollectionBuilder trackCollecionBuilder){
		this.trackCollecionBuilder = trackCollecionBuilder;
	}
	
	public Collection<Album> buildCollection (int artists, int albums, int songs){
		Collection<Album> collection = new ArrayList<Album>();
		for (int artistIndex = 1; artistIndex <= artists; artistIndex++ ){
			for (int albumIndex = 1; albumIndex <= albums; albumIndex++ ){		
				Album newAlbum = buildAlbum(String.valueOf(artistIndex), String.valueOf(albumIndex), songs);
				collection.add(newAlbum);
			}
		}
		return collection;
	}

	public Album buildAlbum(String artistId, String albumId, int songs) {
		String artistStr = ARTIST_PREFIX + artistId;
		String albumStr = ALBUM_PREFIX + albumId;
		Integer albumYear = 1990;
		Album newAlbum = make(a(Album, with(artist, artistStr), with(albumName, albumStr), with(numberOfTracks, songs)));
		newAlbum.setTracks(trackCollecionBuilder.buildAlbumTracks(artistStr, albumStr, albumYear, songs));
		return newAlbum;
	}
	
}
