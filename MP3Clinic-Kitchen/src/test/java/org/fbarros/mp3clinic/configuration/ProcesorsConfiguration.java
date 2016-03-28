package org.fbarros.mp3clinic.configuration;

import org.fbarros.mp3clinic.Category;
import org.fbarros.mp3clinic.Priority;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.procesor.DuplicatesFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcesorsConfiguration {

	@Bean 
	public DuplicatesFinder duplicatesFinder(){
		return new DuplicatesFinder(new ReportingData(Priority.HIGH, Category.DUPLICATED, "error.message.duplicate_tracks"));
	}


}
