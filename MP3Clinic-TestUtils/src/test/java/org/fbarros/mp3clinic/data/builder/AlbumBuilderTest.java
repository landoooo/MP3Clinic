package org.fbarros.mp3clinic.data.builder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.fbarros.mp3clinic.data.Track;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AlbumBuilderTest extends BaseTestCase {

	@Autowired
	private AlbumBuilder albumBuilder;

	@Test
	public void testBuildCompleteAlbum() {
		int songs = 5;
		String artistName = "Artist123";
		String albumName = "Abum 12354";
		Collection<Track> tracks = albumBuilder.buildAlbum(artistName, albumName, songs);
		assertThat(tracks).hasSize(5);
		assertThat(tracks).extracting("artist").containsOnly(artistName);
		assertThat(tracks).extracting("album").containsOnly(albumName);
		assertThat(tracks).extracting("number").containsExactly(1, 2, 3, 4, 5);		
	}

	@Test
	public void testBuildAlbumStringStringIntListOfInteger() {
		int songs = 5;
		String artistName = "Artist123";
		String albumName = "Abum 12354";
		Collection<Track> tracks = albumBuilder.buildAlbum(artistName, albumName, songs, Arrays.asList(3, 4));
		assertThat(tracks).hasSize(3);
		assertThat(tracks).extracting("artist").containsOnly(artistName);
		assertThat(tracks).extracting("album").containsOnly(albumName);
		assertThat(tracks).extracting("number").containsExactly(1, 2, 5);		
	}

	//////////////////////////////GETTERS / SETTERS /////////////////////////////////

	public void setAlbumBuilder(AlbumBuilder albumBuilder) {
		this.albumBuilder = albumBuilder;
	}

}
