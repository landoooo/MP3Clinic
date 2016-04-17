package org.fbarros.mp3clinic.configuration;

import org.fbarros.mp3clinic.Category;
import org.fbarros.mp3clinic.Priority;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.procesor.DuplicatesFinder;
import org.fbarros.mp3clinic.procesor.MissingFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcesorsConfiguration {

	@Bean 
	public DuplicatesFinder duplicatesFinder(){
		return new DuplicatesFinder(new ReportingData(Priority.HIGH, Category.DUPLICATED, "error.message.duplicate_tracks", "Duplicates Finder"));
	}

	@Bean 
	public MissingFinder missingFinder(){
		return new MissingFinder(new ReportingData(Priority.HIGH, Category.MISSING_INFORMATION, "error.message.missing_tracks", "Missing Songs Finder"));
	}


}
