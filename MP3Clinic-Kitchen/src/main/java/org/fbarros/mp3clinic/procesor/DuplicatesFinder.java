package org.fbarros.mp3clinic.procesor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.Track;

public class DuplicatesFinder extends Reporter implements IProcesor  {

	@Override
	public List<Message> process(Collection<Track> collection) {
		List<Message> messages = new ArrayList<Message>();
		
		return messages;
	}

}
