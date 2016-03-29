package org.fbarros.mp3clinic.data.loader;

import java.io.File;
import java.io.IOException;

import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.exceptions.Id3TagVersionNotSupportedException;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class TrackLoader {

	private static TrackLoaderFactory trackBuilderFactory = new TrackLoaderFactory();
	
	public static Track createTrack(File file) throws UnsupportedTagException, InvalidDataException, IOException, Id3TagVersionNotSupportedException{
		Mp3File mp3File = new Mp3File(file);
		ITrackLoader trackBuilder = trackBuilderFactory.builder(mp3File);
		return trackBuilder.createTrack(file);
	}
}
