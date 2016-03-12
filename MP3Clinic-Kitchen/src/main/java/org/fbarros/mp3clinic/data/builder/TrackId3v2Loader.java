package org.fbarros.mp3clinic.data.builder;

import java.io.File;
import java.io.IOException;

import org.fbarros.mp3clinic.data.Track;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class TrackId3v2Loader implements ITrackLoader {

	public Track createTrack(File file) throws UnsupportedTagException, InvalidDataException, IOException{
		Mp3File mp3File = new Mp3File(file);
		Track track = new Track();
		track.setPath(file.getPath());
		track.setName(mp3File.getId3v2Tag().getTitle());
		return track;
	}
}
