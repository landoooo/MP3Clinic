package org.fbarros.mp3clinic.data.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;

import org.fbarros.mp3clinic.data.Track;
import static org.fbarros.mp3clinic.data.builder.TrackBuilder.*;

public class TrackCollectionBuilder {
	
	public static final String NAME_PREFIX = "track";
	public static final String PATH_PREFIX = "Path/to/" + NAME_PREFIX;
	
	@SuppressWarnings("unchecked")
	public Collection<Track> buildAlbumTracks (String albumArtist, String albumAlbum, Integer albumYear, int songs){
		Collection<Track> albumTracks = new ArrayList<Track>();
		for (int i = 1; i <= songs; i++ ){
			albumTracks.add(make(a(Track, with (artist, albumArtist), with(album, albumAlbum), 
					with(name, NAME_PREFIX + i ), with(number, i), with(path, PATH_PREFIX + i),
					with(year, albumYear))));
		}
		return albumTracks;
	}

	public Collection<Track> buildAlbumTracks (String albumArtist, String albumAlbum, Integer albumYear, int songs, List<Integer> missingSongs){
		Collection<Track> albumTracks = buildAlbumTracks (albumArtist, albumAlbum, albumYear, songs);
		Iterator<Track> iter = albumTracks.iterator();
		while (iter.hasNext()) {
		    Track track = iter.next();
			if (missingSongs.contains(track.getNumber())){
				iter.remove();
			}
		}
		return albumTracks;
	}
}
