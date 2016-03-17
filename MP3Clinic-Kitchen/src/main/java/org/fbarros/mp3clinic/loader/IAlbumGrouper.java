package org.fbarros.mp3clinic.loader;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.fbarros.mp3clinic.data.Track;

public interface IAlbumGrouper {
	
	Map<String, List<Track>> groupAlbums(Collection<Track> collection);

}
