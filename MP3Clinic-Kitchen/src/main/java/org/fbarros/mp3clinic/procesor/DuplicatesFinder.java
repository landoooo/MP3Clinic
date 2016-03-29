package org.fbarros.mp3clinic.procesor;

import java.util.Collection;
import java.util.List;

import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.procesor.iterator.CollectionIterator;

public class DuplicatesFinder extends Reporter implements IProcesor  {

	public DuplicatesFinder(ReportingData reportingData, String name) {
		super(reportingData, name);
	}

	@Override
	public List<Message> process(Collection<Album> collection) {
		return CollectionIterator.apply(collection, a -> a.getNumberOfTracks() > 0 && containsDuplicates(a), a -> createMessage(a));
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
