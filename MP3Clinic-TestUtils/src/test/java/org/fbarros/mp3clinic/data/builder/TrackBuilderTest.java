package org.fbarros.mp3clinic.data.builder;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static org.assertj.core.api.Assertions.assertThat;

import org.fbarros.mp3clinic.data.Track;
import static org.fbarros.mp3clinic.data.builder.TrackBuilder.*;
import org.junit.Test;

public class TrackBuilderTest extends BaseTestCase {

	private static final String CUSTOM_NAME = "custom_number";
	private static final String CUSTOM_ARTIST = "custom_artist";
	private static final String CUSTOM_ALBUM = "custom_album";
	private static final String CUSTOM_PATH = "custom_path";
	private static final Integer CUSTOM_NUMBER = 13;

	@Test
	@SuppressWarnings("unchecked")
	public void defaultCreationTest() {
		Track track = make(a(Track));
		assertThat(track.getNumber()).isEqualTo(DEFAULT_NUMBER);
		assertThat(track.getPath()).isEqualTo(DEFAULT_PATH);
		assertThat(track.getName()).isEqualTo(DEFAULT_NAME);
		assertThat(track.getArtist()).isEqualTo(DEFAULT_ARTIST);
		assertThat(track.getAlbum()).isEqualTo(DEFAULT_ALBUM);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void customCreationTest() {
		Track track = make(a(Track, with(number, CUSTOM_NUMBER), with(path, CUSTOM_PATH),
				with(name, CUSTOM_NAME), with(artist, CUSTOM_ARTIST), with(album, CUSTOM_ALBUM)));
		assertThat(track.getPath()).isEqualTo(CUSTOM_PATH);
		assertThat(track.getNumber()).isEqualTo(CUSTOM_NUMBER);
		assertThat(track.getName()).isEqualTo(CUSTOM_NAME);
		assertThat(track.getArtist()).isEqualTo(CUSTOM_ARTIST);
		assertThat(track.getAlbum()).isEqualTo(CUSTOM_ALBUM);
	}

}
