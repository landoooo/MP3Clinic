package org.fbarros.mp3clinic.loader;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.data.builder.TrackCollectionBuilder;
import org.fbarros.mp3clinic.exceptions.NumberOfTracksCalculationException;
import org.fbarros.mp3clinic.kitchen.basetest.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AlbumsCalculatorTest extends BaseTest {

	@Autowired
	private AlbumsCalculator albumsCalculator;
	
	@Autowired
	private TrackCollectionBuilder trackCollectionBuilder;
	
	@Test(expected = NumberOfTracksCalculationException.class)
	public void extractNumberOfTracksExceptionTest() throws NumberOfTracksCalculationException {
		Collection<Track> tracks = trackCollectionBuilder.buildAlbumTracks("1", "1", 1);
		tracks.forEach(t -> t.setNumber(4));
		albumsCalculator.extractNumberOfTracks(tracks);
	}

	@Test
	public void extractNumberOfTracksOKTest() throws NumberOfTracksCalculationException {
		int numberOfSongs = 5;
		Collection<Track> tracks = trackCollectionBuilder.buildAlbumTracks("1", "1", numberOfSongs);
		assertThat(albumsCalculator.extractNumberOfTracks(tracks)).isEqualTo(numberOfSongs);
	}

}
