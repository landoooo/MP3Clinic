package org.fbarros.mp3clinic.gui.configuration;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.fbarros.mp3clinic.Category;
import org.fbarros.mp3clinic.Priority;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.procesor.DuplicatesFinder;
import org.fbarros.mp3clinic.procesor.MissingFinder;

public class ApplicationConfiguration{

	private Processors processors;
	
	@PostConstruct
	public void init() {
		//TODO: change the injection of the Processors
		DuplicatesFinder duplicatesFinder = new DuplicatesFinder(new ReportingData(Priority.HIGH, Category.DUPLICATED, "error.message.duplicatetracks", "Duplicates Finder"));
		MissingFinder missingFinder = new MissingFinder(new ReportingData(Priority.HIGH, Category.MISSING_INFORMATION, "error.message.missingtracks", "Missing Songs Finder"));
		this.processors = new Processors(Arrays.asList(duplicatesFinder, missingFinder));
	}
	
	public Processors getProcessors() {
		return processors;
	}

}
