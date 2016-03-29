package org.fbarros.mp3clinic.loader.albumcalculator;

import java.util.List;

import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.report.ProcessingReport;

public interface IAlbumsCalculator {

	ProcessingReport<Album> calculateAlbum(List<Track> tracks, ProcessingReport<Album> result); 

}
