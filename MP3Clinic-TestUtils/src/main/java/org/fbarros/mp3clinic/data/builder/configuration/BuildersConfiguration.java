package org.fbarros.mp3clinic.data.builder.configuration;

import org.fbarros.mp3clinic.data.builder.AlbumBuilder;
import org.fbarros.mp3clinic.data.builder.CollectionBuilder;
import org.fbarros.mp3clinic.data.builder.TrackBuilder;
import org.fbarros.mp3clinic.data.builder.TrackCollectionBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuildersConfiguration {

	@Bean
	public TrackBuilder trackBuilder(){
		return new TrackBuilder();
	}
	
	@Bean 
	public TrackCollectionBuilder trackCollectionBuilder(){
		return new TrackCollectionBuilder();
	}
	
	@Bean 
	public AlbumBuilder albumBuilder(){
		return new AlbumBuilder();
	}
	
	@Bean
	public CollectionBuilder collectionBuilder(){
		return new CollectionBuilder(trackCollectionBuilder(), albumBuilder());
	}
	
	
}
