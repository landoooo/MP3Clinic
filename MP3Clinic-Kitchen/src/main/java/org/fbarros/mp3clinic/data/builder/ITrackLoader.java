package org.fbarros.mp3clinic.data.builder;

import java.io.File;
import java.io.IOException;

import org.fbarros.mp3clinic.data.Track;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

public interface ITrackLoader {

	public Track createTrack(File file) throws UnsupportedTagException, InvalidDataException, IOException;
}
