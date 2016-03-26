package org.fbarros.mp3clinic.data.loader;

import java.io.File;
import java.io.IOException;

import org.fbarros.mp3clinic.data.Track;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class TrackId3v2Loader extends TrackId3v1Loader implements ITrackLoader {

	public Track createTrack(File file) throws UnsupportedTagException, InvalidDataException, IOException{
		Track track = super.createTrack(file);
		Mp3File mp3File = new Mp3File(file);
		return track;
	}
}
