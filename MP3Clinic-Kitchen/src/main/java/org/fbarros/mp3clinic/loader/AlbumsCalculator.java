package org.fbarros.mp3clinic.loader;

import java.util.Collection;
import java.util.List;

import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.exceptions.NumberOfTracksCalculationException;
import org.fbarros.mp3clinic.procesor.Reporter;

public class AlbumsCalculator extends Reporter implements IAlbumsCalculator{

	public AlbumsCalculator(ReportingData reportingData) {
		super(reportingData);
	}
	
	@Override
	public ProcessingReport calculateAlbum(Collection<Track> tracks) {
		ProcessingReport result = new ProcessingReport();
		Album album = new Album();
		//TODO: verify all the artists and albums from a folder are the same
		// and throw DifferentArtists and DifferentAlbum exceptions
		try{
			album.setNumberOfTracks(extractNumberOfTracks(tracks));
			Track[] tracksArr = new Track[tracks.size()];
			tracks.toArray(tracksArr);
			album.setArtist(tracksArr[0].getArtist());
			album.setAlbumName(tracksArr[0].getAlbum());
			album.setTracks(tracks);
			result.addElement(album);
		} catch (NumberOfTracksCalculationException e) {
			result.addMessage(createMessage(album));
		}
		return result;
	}
	
	public int extractNumberOfTracks(Collection<Track> tracks) throws NumberOfTracksCalculationException{
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
	
}
