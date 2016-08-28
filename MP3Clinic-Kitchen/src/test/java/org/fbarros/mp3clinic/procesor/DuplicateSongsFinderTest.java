package org.fbarros.mp3clinic.procesor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.data.builder.CollectionBuilder;
import org.fbarros.mp3clinic.data.builder.TrackCollectionBuilder;
import org.fbarros.mp3clinic.kitchen.basetest.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DuplicateSongsFinderTest extends BaseTest{

	@Autowired
	private DuplicateSongsFinder duplicatesFinder;

	@Autowired
	private CollectionBuilder collectionBuilder;

	@Autowired
	private TrackCollectionBuilder trackCollectionBuilder;

	@Test
	public void noDuplicatesTest() {
		Collection<Album> collection = collectionBuilder.buildCollection(2, 2, 2);
		List<Message> messages = duplicatesFinder.process(collection);
		assertThat(messages).isEmpty();
	}

	@Test
	public void duplicatesFinderTest() {
		Collection<Album> collection = collectionBuilder.buildCollection(1, 1, 3);
		for (Album album : collection){
			for (Track track : album.getTracks()){
				track.setNumber(1);
			}
		}
		List<Message> messages = duplicatesFinder.process(collection);
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(messages).hasSize(1);
		//softly.assertThat(messages.get(0).getDate()).isToday();
		softly.assertAll();
	}

	@Test
	public void containsDuplicatesPositiveTest(){
		Album album = collectionBuilder.buildAlbum("1", "1", 3);
		for (Track track : album.getTracks()){
			track.setNumber(1);
		}
		assertThat(duplicatesFinder.containsDuplicates(album)).isTrue();

	}

	@Test
	public void containsDuplicatesNegativeTest(){
		Album album = collectionBuilder.buildAlbum("1", "1", 3);
		assertThat(duplicatesFinder.containsDuplicates(album)).isFalse();

	}

}
