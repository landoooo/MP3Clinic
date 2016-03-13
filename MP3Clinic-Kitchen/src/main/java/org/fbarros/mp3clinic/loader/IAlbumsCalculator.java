package org.fbarros.mp3clinic.loader;

import java.util.Collection;
import java.util.List;

import org.fbarros.mp3clinic.data.Track;

public interface IAlbumsCalculator {

	AlbumsCalculationSummary calculateAlbums(List<List<Track>> tracks);

}
