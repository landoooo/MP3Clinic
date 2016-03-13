package org.fbarros.mp3clinic.procesor;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.exceptions.NumberOfTracksNotFound;

public class DuplicatesFinder extends Reporter implements IProcesor  {

	@Override
	public List<Message> process(Collection<Album> collection) {
		List<Message> messages = collection.stream()
				.filter(a -> a.getNumberOfTracks() > 0 && containsDuplicates(a))
				.map(a -> createDuplicatesMessage(a))
				.collect(toList());
		return messages;
	}

	private Message createDuplicatesMessage(Album album) {
		
		return createMessage();
	}

	public Map<String, List<Track>> albumGrouper(Collection<Track> collection) {
		Map<String, List<Track>> simpleAlbums = collection.stream().collect(groupingBy(t -> t.getArtist() + t.getAlbum()));
		return simpleAlbums;
	}

	private boolean containsDuplicates (Album album){
		boolean[] exist = new boolean[album.getNumberOfTracks()];
		for (Track track : album.getTracks()){
			if(exist[track.getNumber()]){
				return true;
			}
		}
		return false;
	}
	
}
