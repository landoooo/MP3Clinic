package org.fbarros.mp3clinic.loader;

import java.util.Collection;

import org.fbarros.mp3clinic.data.Track;

public interface IAlbumsCalculator {

	ProcessingReport calculateAlbum(Collection<Track> tracks); 

}
