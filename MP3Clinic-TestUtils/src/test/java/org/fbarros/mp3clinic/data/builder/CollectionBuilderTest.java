package org.fbarros.mp3clinic.data.builder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.fbarros.mp3clinic.data.Album;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CollectionBuilderTest extends BaseTestCase {

	@Autowired
	private CollectionBuilder collectionBuilder;
	
	@Test
	public void testBuildCompleteCollection() {
		int artists = 2;
		int albums = 2;
		int songs = 2;
		Collection<Album> collection = collectionBuilder.buildCollection(artists, albums, songs);
		assertThat(collection).hasSize(artists * albums);
		assertThat(collection).flatExtracting("tracks").extracting("artist").containsOnly(CollectionBuilder.ARTIST_PREFIX + 1, CollectionBuilder.ARTIST_PREFIX + 2);
		assertThat(collection).flatExtracting("tracks").extracting("album").containsOnly(CollectionBuilder.ALBUM_PREFIX + 1, CollectionBuilder.ALBUM_PREFIX + 2);
		assertThat(collection).flatExtracting("tracks").extracting("number").containsOnly(1, 2);		
	}

}
