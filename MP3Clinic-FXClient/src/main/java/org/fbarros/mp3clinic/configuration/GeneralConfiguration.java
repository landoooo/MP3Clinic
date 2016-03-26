package org.fbarros.mp3clinic.configuration;

import org.fbarros.mp3clinic.MainApp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfiguration {

	@Bean 
	public MainApp mainApp(){
		return new MainApp();
	}


}
