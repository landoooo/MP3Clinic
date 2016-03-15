package org.fbarros.mp3clinic.procesor;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.List;

import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.data.Track;

public class DuplicatesFinder extends Reporter implements IProcesor  {

	public DuplicatesFinder(ReportingData reportingData) {
		super(reportingData);
	}

	@Override
	public List<Message> process(Collection<Album> collection) {
		List<Album> albumsWithDuplicates = collection.stream()
				.filter(a -> a.getNumberOfTracks() > 0 && containsDuplicates(a))
				.collect(toList());
		List<Message> messages = albumsWithDuplicates.stream()
				.map(a -> createMessage(a))
				.collect(toList());
		return messages;
	}

	public boolean containsDuplicates (Album album){
		boolean[] exist = new boolean[album.getNumberOfTracks()];
		for (Track track : album.getTracks()){
			if(track.getNumber() > 0 && track.getNumber() <= album.getNumberOfTracks() && exist[track.getNumber()-1]){
				return true;
			} else {
				exist[track.getNumber()-1] = true;
			}
		}
		return false;
	}

}
