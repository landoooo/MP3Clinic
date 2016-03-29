package org.fbarros.mp3clinic.data.builder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.Track;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;

public class AlbumBuilder {

	public static final String DEFAULT_ARTIST = "Default_artist";
	public static final String DEFAULT_ALBUM = "Default_album";
	public static final Integer DEFAULT_NUMBER_OF_TRACKS = 0;
	public static final Integer DEFAULT_YEAR = 1990;
	public static final List<Track> DEFAULT_TRACKS = Arrays.asList(TrackBuilder.aNumberedTrack()); 

	public static final Property<Album,Integer> numberOfTracks = new Property<Album,Integer>();
	public static final Property<Album,Integer> year = new Property<Album,Integer>();
	public static final Property<Album,String> artist = new Property<Album,String>();
	public static final Property<Album,String> albumName = new Property<Album,String>();
	public static final Property<Album,List<Track>> tracks = new Property<Album,List<Track>>();
	public static final Property<Album,String> name = new Property<Album,String>();

	public static final Instantiator<Album> Album = new Instantiator<Album>() {
		public Album instantiate(PropertyLookup<Album> lookup) {
			Album album = new Album();
			album.setNumberOfTracks(lookup.valueOf(numberOfTracks, DEFAULT_NUMBER_OF_TRACKS));
			album.setArtist(lookup.valueOf(artist, DEFAULT_ARTIST));
			album.setAlbumName(lookup.valueOf(albumName, DEFAULT_ALBUM));
			album.setTracks(lookup.valueOf(tracks, DEFAULT_TRACKS));
			album.setYear(lookup.valueOf(year, DEFAULT_YEAR));
			return album;
		}
	};

}
