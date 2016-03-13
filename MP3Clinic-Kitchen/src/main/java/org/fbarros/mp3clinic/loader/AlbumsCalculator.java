package org.fbarros.mp3clinic.loader;

import java.util.Arrays;
import java.util.List;

import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.exceptions.NumberOfTracksCalculationException;
import org.fbarros.mp3clinic.procesor.Reporter;

public class AlbumsCalculator extends Reporter implements IAlbumsCalculator{

	
	@Override
	public AlbumsCalculationSummary calculateAlbums(List<List<Track>> tracks) {
		AlbumsCalculationSummary result = new AlbumsCalculationSummary();
		for (List<Track> albumTracks : tracks){
				result.merge(calculateAlbum(albumTracks));
		}
		return result;
	}
	
	public AlbumsCalculationSummary calculateAlbum(List<Track> tracks) {
		AlbumsCalculationSummary result = new AlbumsCalculationSummary();
		Album album = new Album();
		album.setArtist(tracks.get(0).getArtist());
		album.setAlbum(tracks.get(0).getAlbum());
		try{
			album.setNumberOfTracks(extractNumberOfTracks(tracks));
		} catch (NumberOfTracksCalculationException e) {
			result.addMessage(createMessage(Arrays.asList(tracks.get(0).getPath())));
		}
		return result;
	}
	
	private int extractNumberOfTracks(List<Track> tracks) throws NumberOfTracksCalculationException{
		int numberOfTracks = tracks.size();
		for (int i = 0; i<tracks.size(); i++){
			if (tracks.get(i).getNumber().intValue() != numberOfTracks) {
				throw new NumberOfTracksCalculationException("The number of tracks of the album does not match the meta information");
			}
		}
		return numberOfTracks;
	}
	
}
