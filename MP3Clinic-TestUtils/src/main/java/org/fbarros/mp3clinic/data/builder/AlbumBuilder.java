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

public class AlbumBuilder {
	
	public static final String NAME_PREFIX = "track";
	public static final String PATH_PREFIX = "Path/to/" + NAME_PREFIX;
	
	@SuppressWarnings("unchecked")
	public Collection<Track> buildAlbum (String albumArtist, String albumAlbum, int songs){
		Collection<Track> albumTracks = new ArrayList<Track>();
		for (int i = 1; i <= songs; i++ ){
			albumTracks.add(make(a(Track, with (artist, albumArtist), with(album, albumAlbum), 
					with(name, NAME_PREFIX + i ), with(number, i), with(path, PATH_PREFIX + i))));
		}
		return albumTracks;
	}

	public Collection<Track> buildAlbum (String albumArtist, String albumAlbum, int songs, List<Integer> missingSongs){
		Collection<Track> albumTracks = buildAlbum (albumArtist, albumAlbum, songs);
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
