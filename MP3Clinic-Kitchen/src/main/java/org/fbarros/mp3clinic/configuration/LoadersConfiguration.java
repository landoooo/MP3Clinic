package org.fbarros.mp3clinic.configuration;

import org.fbarros.mp3clinic.Category;
import org.fbarros.mp3clinic.Priority;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.loader.AlbumGrouper;
import org.fbarros.mp3clinic.loader.AlbumsCalculator;
import org.fbarros.mp3clinic.loader.FileSystemLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadersConfiguration {

	@Bean
	public FileSystemLoader fileSystemLoader(){
		return new FileSystemLoader(new ReportingData(Priority.HIGH, Category.LOADING, "error.message.loading_data"));
	}

	@Bean
	public AlbumsCalculator albumsCalculator(){
		return new AlbumsCalculator(new ReportingData(Priority.HIGH, Category.WRONG_INFORMATION, "error.message.calculating_album"));
	}

	@Bean
	public AlbumGrouper albumGrouper(){
		return new AlbumGrouper();
	}
}
