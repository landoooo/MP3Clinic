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

import org.assertj.core.api.SoftAssertions;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.data.builder.TrackCollectionBuilder;
import org.fbarros.mp3clinic.exceptions.NumberOfTracksCalculationException;
import org.fbarros.mp3clinic.kitchen.basetest.BaseTest;
import org.fbarros.mp3clinic.loader.grouper.IAlbumGrouper;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

public class AlbumGrouperTest extends BaseTest {

	@Autowired
	private IAlbumGrouper albumGrouper;

	@Autowired
	private TrackCollectionBuilder trackCollectionBuilder;

	private static final int NUMBER_OF_TRACKS = 5;
	private static final String ARTIST_NAME = "ArtistName";
	private static final String ALBUM_NAME = "AlbumName";

	@Test
	public void extractNumberOfTracksExceptionTest() throws NumberOfTracksCalculationException {
		Collection<Track> tracks = trackCollectionBuilder.buildAlbumTracks("1", "1", 1990, 3);
		tracks.addAll(trackCollectionBuilder.buildAlbumTracks("2", "2", 1990, 5));
		assertThat(albumGrouper.group(tracks)).hasSize(2);
	}


}
