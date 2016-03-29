package org.fbarros.mp3clinic.loader.grouper;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.fbarros.mp3clinic.data.Track;

public class AlbumGrouper implements IAlbumGrouper {

	@Override
	public Collection<List<Track>> group(Collection<Track> collection) {
		Map<String, List<Track>> simpleAlbums = 
				collection.parallelStream().collect(Collectors.groupingBy(t -> t.getArtist() + t.getAlbum()));
		return simpleAlbums.values();
	}	
}
