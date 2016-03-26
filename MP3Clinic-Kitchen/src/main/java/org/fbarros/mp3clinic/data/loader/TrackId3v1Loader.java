package org.fbarros.mp3clinic.data.loader;

import java.io.File;
import java.io.IOException;

import org.fbarros.mp3clinic.data.Track;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class TrackId3v1Loader implements ITrackLoader {

	public Track createTrack(File file) throws UnsupportedTagException, InvalidDataException, IOException{
		Mp3File mp3File = new Mp3File(file);
		Track track = new Track();
		track.setPath(file.getPath());
		track.setName(mp3File.getId3v1Tag().getTitle());
		track.setArtist(mp3File.getId3v1Tag().getArtist());
		track.setAlbum(mp3File.getId3v1Tag().getAlbum());
		track.setNumber(Integer.parseInt(mp3File.getId3v1Tag().getTrack()));
		return track;
	}
}
