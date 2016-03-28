package org.fbarros.mp3clinic.loader;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.exceptions.AlbumYearCalculationException;
import org.fbarros.mp3clinic.exceptions.NumberOfTracksCalculationException;
import org.fbarros.mp3clinic.procesor.Reporter;

public class AlbumsCalculator extends Reporter implements IAlbumsCalculator{

	public AlbumsCalculator(ReportingData reportingData) {
		super(reportingData);
	}
	
	@Override
	public ProcessingReport<Album> calculateAlbum(final Collection<Track> tracks, ProcessingReport<Album> result) {
		Album album = new Album();
		//TODO: verify all the artists and albums from a folder are the same
		// and throw DifferentArtists and DifferentAlbum exceptions
		try{
			Track[] tracksArr = new Track[tracks.size()];
			tracks.toArray(tracksArr);
			album.setArtist(tracksArr[0].getArtist());
			album.setAlbumName(tracksArr[0].getAlbum());
			album.setTracks(tracks);
			album.setNumberOfTracks(extractNumberOfTracks(tracks));
			album.setYear(extractAlbumYear(tracks));
			result.addElement(album);
		} catch (NumberOfTracksCalculationException | AlbumYearCalculationException e) {
			result.addMessage(createMessage(album, e));
		}
		return result;
	}
	
	public int extractNumberOfTracks(final Collection<Track> tracks) throws NumberOfTracksCalculationException{
		int numberOfTracks = tracks.size();
		Track[] tracksArr = new Track[tracks.size()];
		tracks.toArray(tracksArr);
		for (int i = 0; i<tracks.size(); i++){
			if (tracksArr[i].getNumber().intValue() > numberOfTracks) {
				throw new NumberOfTracksCalculationException("The number of tracks of the album does not match the meta information of the tracks");
			}
		}
		return numberOfTracks;
	}
	
	public int extractAlbumYear(final Collection<Track> tracks) throws AlbumYearCalculationException{
		//TODO: treat compilations
		List<Integer> years = tracks.stream().map(Track::getYear).distinct().collect(Collectors.toList());
		if (years.size() == 1){
			return years.get(0);
		} else {
			throw new AlbumYearCalculationException("The year of tracks of the album is not coherent");	
		}
	}
}
