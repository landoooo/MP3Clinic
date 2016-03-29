package org.fbarros.mp3clinic.loader.grouper;

import java.util.Collection;
import java.util.List;

import org.fbarros.mp3clinic.data.Track;

public interface IAlbumGrouper {
	
	public Collection<List<Track>> group(Collection<Track> collection);

}
