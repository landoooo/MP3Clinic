package org.fbarros.mp3clinic.data.loader;

import org.fbarros.mp3clinic.exceptions.Id3TagVersionNotSupportedException;

import com.mpatric.mp3agic.Mp3File;

public class TrackLoaderFactory {

	private TrackId3v1Loader id3v1Builder = new TrackId3v1Loader();
	private TrackId3v2Loader id3v2Builder = new TrackId3v2Loader();
	
	public ITrackLoader builder(Mp3File file) throws Id3TagVersionNotSupportedException{
		if (file.hasId3v2Tag()){
			return id3v2Builder;
		} else if ( file.hasId3v1Tag()){
			return id3v1Builder;
		} else {
			throw new Id3TagVersionNotSupportedException();
		}
	}
	
}
