package org.fbarros.mp3clinic.gui.configuration;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.fbarros.mp3clinic.Category;
import org.fbarros.mp3clinic.Priority;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.procesor.DuplicateArtistNamesFinder;
import org.fbarros.mp3clinic.procesor.DuplicateSongsFinder;
import org.fbarros.mp3clinic.procesor.MissingFinder;

public class ApplicationConfiguration{

	private Processors processors;
	
	@PostConstruct
	public void init() {
		//TODO: change the injection of the Processors
		DuplicateSongsFinder duplicateSongsFinder = new DuplicateSongsFinder(new ReportingData(Priority.HIGH, Category.DUPLICATED, "error.message.duplicate_tracks", "Duplicates Finder"));
		DuplicateArtistNamesFinder duplicateArtistNamesFinder = new DuplicateArtistNamesFinder(new ReportingData(Priority.MEDIUM, Category.DUPLICATED, "error.message.duplicate_artist_name", "Duplicate Artist Name"));
		MissingFinder missingFinder = new MissingFinder(new ReportingData(Priority.HIGH, Category.MISSING_INFORMATION, "error.message.missing_tracks", "Missing Songs Finder"));
		this.processors = new Processors(Arrays.asList(duplicateSongsFinder,duplicateArtistNamesFinder, missingFinder));
	}
	
	public Processors getProcessors() {
		return processors;
	}

}
