package org.fbarros.mp3clinic.procesor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.builder.CollectionBuilder;
import org.fbarros.mp3clinic.kitchen.basetest.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MissingFinderTest extends BaseTest{

	@Autowired
	private MissingFinder missingFinder;

	@Autowired
	private CollectionBuilder collectionBuilder;

	@Test
	public void noMissingTest() {
		Collection<Album> collection = collectionBuilder.buildCollection(2, 2, 2);
		List<Message> messages = missingFinder.process(collection);
		assertThat(messages).isEmpty();
	}

	@Test
	public void missingFinderTest() {
		Collection<Album> collection = collectionBuilder.buildCollection(1, 1, 3);
		for (Album album : collection){
			album.getTracks().remove(0);
		}
		List<Message> messages = missingFinder.process(collection);
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(messages).hasSize(1);
		softly.assertThat(messages.get(0).getDate()).isToday();
		softly.assertAll();
	}

	@Test
	public void hasMissingSongsPositiveTest(){
		Album album = collectionBuilder.buildAlbum("1", "1", 3);
		album.getTracks().remove(0);
		assertThat(missingFinder.hasMissingSongs(album)).isTrue();

	}

	@Test
	public void containsMissingNegativeTest(){
		Album album = collectionBuilder.buildAlbum("1", "1", 3);
		assertThat(missingFinder.hasMissingSongs(album)).isFalse();
	}

}
