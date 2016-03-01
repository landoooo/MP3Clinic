package org.fbarros.mp3clinic.procesor;

import java.util.Collection;
import java.util.List;

import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.Track;

public interface IProcesor {

	List<Message> process (Collection<Track> collection);
	}
