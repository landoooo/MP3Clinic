package org.fbarros.mp3clinic.loader.albumcalculator;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.fbarros.mp3clinic.Category;
import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.Priority;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.exceptions.AlbumYearCalculationException;
import org.fbarros.mp3clinic.exceptions.NumberOfTracksCalculationException;
import org.fbarros.mp3clinic.procesor.Reporter;
import org.fbarros.mp3clinic.report.ProcessingReport;
import org.fbarros.mp3clinic.report.ReporterFactory;

public class AlbumsCalculator implements IAlbumsCalculator{
	
	private Reporter reporter;
	
	public AlbumsCalculator(){
		this.reporter =  ReporterFactory.getReporter(new ReportingData(Priority.HIGH, Category.WRONG_INFORMATION, "error.message.calculatingalbum", "Album Calculator"));
	}

	public AlbumsCalculator(ReportingData reportingData) {
		this.reporter = ReporterFactory.getReporter(reportingData);
	}
	
	@Override
	public ProcessingReport<Album> calculateAlbum(final List<Track> tracks, ProcessingReport<Album> result) {
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
			Message message = reporter.createMessage(album);
			message.setException(e);
			message.setDescription(e.getMessage());
			result.addMessage(message);
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
		List<Integer> years = tracks.parallelStream().map(Track::getYear).distinct().collect(Collectors.toList());
		if (years.size() == 1){
			return years.get(0);
		} else {
			throw new AlbumYearCalculationException("The year of tracks of the album is not coherent");	
		}
	}
}
