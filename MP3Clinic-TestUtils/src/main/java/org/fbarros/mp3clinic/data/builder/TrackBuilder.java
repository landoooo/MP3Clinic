package org.fbarros.mp3clinic.data.builder;

import org.fbarros.mp3clinic.data.Track;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;

public class TrackBuilder {

	public static final String DEFAULT_NAME = "Default_track_name";
	public static final String DEFAULT_PATH = "Default_track_path";
	public static final String DEFAULT_ARTIST = "Default_artist";
	public static final String DEFAULT_ALBUM = "Default_album";
	public static final Integer DEFAULT_NUMBER = 0;

	public static final Property<Track,Integer> number = new Property<Track,Integer>();
	public static final Property<Track,String> artist = new Property<Track,String>();
	public static final Property<Track,String> album = new Property<Track,String>();
	public static final Property<Track,String> path = new Property<Track,String>();
	public static final Property<Track,String> name = new Property<Track,String>();

	public static final Instantiator<Track> Track = new Instantiator<Track>() {
		public Track instantiate(PropertyLookup<Track> lookup) {
			Track track = new Track();
			track.setNumber(lookup.valueOf(number, DEFAULT_NUMBER));
			track.setName(lookup.valueOf(name, DEFAULT_NAME));
			track.setArtist(lookup.valueOf(artist, DEFAULT_ARTIST));
			track.setAlbum(lookup.valueOf(album, DEFAULT_ALBUM));
			track.setPath(lookup.valueOf(path, DEFAULT_PATH));
			return track;
		}
	};

	public static final Track aNumberedTrack(){
		return make(a(Track, with(number, 1)));
	}
}
