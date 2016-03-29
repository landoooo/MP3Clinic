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
import org.fbarros.mp3clinic.loader.albumcalculator.AlbumsCalculator;
import org.fbarros.mp3clinic.report.ProcessingReport;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

public class AlbumsCalculationSummaryTest extends BaseTest {

	@Autowired
	private AlbumsCalculator albumsCalculator;
	
	@Autowired
	private TrackCollectionBuilder trackCollectionBuilder;
	
	@Test
	public void mergeEmptySummaryTest(){
		ProcessingReport summary1 = new ProcessingReport();
		ProcessingReport summary2 = new ProcessingReport();
		
		summary1.merge(summary2);
		assertThat(summary1.getCollection()).isEmpty();
		assertThat(summary1.getMessages()).isEmpty();
	}

}
