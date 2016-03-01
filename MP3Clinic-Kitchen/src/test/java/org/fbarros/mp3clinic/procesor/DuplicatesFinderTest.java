package org.fbarros.mp3clinic.procesor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;

import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.data.builder.AlbumBuilder;
import org.fbarros.mp3clinic.data.builder.CollectionBuilder;
import org.fbarros.mp3clinic.kitchen.basetest.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DuplicatesFinderTest extends BaseTest{

	@Autowired
	private DuplicatesFinder duplicatesFinder;
	
	@Autowired
	private CollectionBuilder collectionBuilder;
	
	@Test
	public void noDuplicatesTest() {
		Collection<Track> collection = collectionBuilder.buildCollection(2, 2, 2);
		List<Message> messages = duplicatesFinder.process(collection);
		assertThat(messages).isEmpty();
	}

	@Test
	public void duplicatesFinderTest() {
		Collection<Track> collection = collectionBuilder.buildCollection(1, 1, 3);
		for (Track track : collection){
			track.setNumber(1);
		}
		List<Message> messages = duplicatesFinder.process(collection);
		assertThat(messages).hasSize(1);
		assertThat(messages.get(0).getReportingData()).isEqualTo(duplicatesFinder.getReportingData());
		assertThat(messages.get(0).getDate()).isToday();
		assertThat(messages.get(0).getPaths()).containsExactly(AlbumBuilder.PATH_PREFIX + 1, AlbumBuilder.PATH_PREFIX + 2, AlbumBuilder.PATH_PREFIX + 3);
	}
		

	////////////////////// GETTERS / SETTERS /////////////////////////////
	
	public void setDuplicatesFinder(DuplicatesFinder duplicatesFinder) {
		this.duplicatesFinder = duplicatesFinder;
	}

	public void setCollectionsBuilder(CollectionBuilder collectionBuilder) {
		this.collectionBuilder = collectionBuilder;
	}
	
	
}
