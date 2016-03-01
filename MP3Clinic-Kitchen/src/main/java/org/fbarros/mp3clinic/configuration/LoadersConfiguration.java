package org.fbarros.mp3clinic.configuration;

import org.fbarros.mp3clinic.loader.FileSystemLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadersConfiguration {

	@Bean
	public FileSystemLoader fileSystemLoader(){
		return new FileSystemLoader();
	}
}
