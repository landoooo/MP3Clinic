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
	public ReportingData duplicatesFinderReportingData(){
		ReportingData duplicatesFinderReportingData = new ReportingData();
		duplicatesFinderReportingData.setCategory(Category.DUPLICATED);
		duplicatesFinderReportingData.setPriority(Priority.MEDIUM);
		duplicatesFinderReportingData.setMessageKey("procesor.duplicatesfinder.message");
		return duplicatesFinderReportingData;
	}

	@Bean 
	public DuplicatesFinder duplicatesFinder(){
		DuplicatesFinder duplicatesFinder = new DuplicatesFinder();
		duplicatesFinder.setReportingData(duplicatesFinderReportingData());
		return duplicatesFinder;
	}


}
