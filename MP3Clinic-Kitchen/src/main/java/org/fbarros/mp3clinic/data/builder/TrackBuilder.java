package org.fbarros.mp3clinic.data.builder;

import java.io.File;
import java.io.IOException;

import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.exceptions.Id3TagVersionNotSupported;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class TrackBuilder {

	private static TrackBuilderFactory trackBuilderFactory = new TrackBuilderFactory();
	
	public static Track createTrack(File file) throws UnsupportedTagException, InvalidDataException, IOException, Id3TagVersionNotSupported{
		Mp3File mp3File = new Mp3File(file);
		ITrackBuilder trackBuilder = trackBuilderFactory.builder(mp3File);
		return trackBuilder.createTrack(file);
	}
}
