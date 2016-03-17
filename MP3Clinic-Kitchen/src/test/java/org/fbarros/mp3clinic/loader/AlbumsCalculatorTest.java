package org.fbarros.mp3clinic.loader;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.fbarros.mp3clinic.data.builder.AlbumBuilder.Album;
import static org.fbarros.mp3clinic.data.builder.AlbumBuilder.albumName;
import static org.fbarros.mp3clinic.data.builder.AlbumBuilder.artist;
import static org.fbarros.mp3clinic.data.builder.AlbumBuilder.numberOfTracks;

import java.util.Collection;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.data.builder.TrackCollectionBuilder;
import org.fbarros.mp3clinic.exceptions.NumberOfTracksCalculationException;
import org.fbarros.mp3clinic.kitchen.basetest.BaseTest;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

public class AlbumsCalculatorTest extends BaseTest {

	@Autowired
	private AlbumsCalculator albumsCalculator;
	
	@Autowired
	private TrackCollectionBuilder trackCollectionBuilder;
	
	private static final int NUMBER_OF_TRACKS = 5;
	private static final String ARTIST_NAME = "ArtistName";
	private static final String ALBUM_NAME = "AlbumName";
	
	@Test
	public void extractNumberOfTracksExceptionTest() throws NumberOfTracksCalculationException {
		Collection<Track> tracks = trackCollectionBuilder.buildAlbumTracks("1", "1", 1);
		tracks.forEach(t -> t.setNumber(4));
		assertThatExceptionOfType(NumberOfTracksCalculationException.class).isThrownBy(				
				() -> albumsCalculator.extractNumberOfTracks(tracks));
	}

	@Test
	public void extractNumberOfTracksOKTest() throws NumberOfTracksCalculationException {
		Collection<Track> tracks = trackCollectionBuilder.buildAlbumTracks("1", "1", NUMBER_OF_TRACKS);
		assertThat(albumsCalculator.extractNumberOfTracks(tracks)).isEqualTo(NUMBER_OF_TRACKS);
	}
	
	@Test
	public void calculateAlbumsTest() throws NumberOfTracksCalculationException {

		Collection<Track> tracks = trackCollectionBuilder.buildAlbumTracks(ARTIST_NAME, ALBUM_NAME, NUMBER_OF_TRACKS);
		AlbumsCalculator albumsCalculatorSpy = Mockito.spy(albumsCalculator);
		Mockito.when(albumsCalculatorSpy.extractNumberOfTracks(tracks)).thenReturn(5);
		
		Album expectedAlbum = make(a(Album, with(albumName, ALBUM_NAME), with(artist, ARTIST_NAME), with(numberOfTracks, NUMBER_OF_TRACKS)));
		
		AlbumsCalculationSummary summary = albumsCalculatorSpy.calculateAlbum(tracks);
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(summary.getMessages()).isEmpty();
		softly.assertThat(summary.getCollection()).containsExactly(expectedAlbum);
		softly.assertThat(summary.getCollection()).extracting("tracks").containsOnly(tracks);
		softly.assertAll();
	}

	@Test
	public void calculateAlbumsExceptionTest() throws NumberOfTracksCalculationException {

		Collection<Track> tracks = trackCollectionBuilder.buildAlbumTracks(ARTIST_NAME, ALBUM_NAME, NUMBER_OF_TRACKS);
		AlbumsCalculator albumsCalculatorSpy = Mockito.spy(albumsCalculator);
		Mockito.when(albumsCalculatorSpy.extractNumberOfTracks(tracks)).thenThrow(NumberOfTracksCalculationException.class);
		
		AlbumsCalculationSummary summary = albumsCalculatorSpy.calculateAlbum(tracks);
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(summary.getCollection()).isEmpty();
		softly.assertThat(summary.getMessages()).hasSize(1);
		softly.assertAll();
	}

}
