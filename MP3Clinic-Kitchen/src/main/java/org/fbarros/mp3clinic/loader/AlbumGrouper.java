package org.fbarros.mp3clinic.loader;

import static java.util.stream.Collectors.groupingBy;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.fbarros.mp3clinic.data.Track;

public class AlbumGrouper {
	
	public Collection<List<Track>> albumGrouper(Collection<Track> collection) {
		Map<String, List<Track>> simpleAlbums = collection.stream().collect(groupingBy(t -> t.getArtist() + t.getAlbum()));
		return simpleAlbums.values();
	}
}
