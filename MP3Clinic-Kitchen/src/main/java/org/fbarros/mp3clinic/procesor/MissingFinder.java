package org.fbarros.mp3clinic.procesor;

import java.util.Collection;
import java.util.List;

import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.procesor.iterator.CollectionIterator;
import org.fbarros.mp3clinic.report.ReporterFactory;

public class MissingFinder implements IProcesor {

	private Reporter reporter;
	
	public MissingFinder(ReportingData reportingData) {
		this.reporter = ReporterFactory.getReporter(reportingData);
	}

	@Override
	public List<Message> process(Collection<Album> collection) {
		return CollectionIterator.apply(
				collection, 
				a -> a.getNumberOfTracks() > 0 && hasMissingSongs(a), 
				a -> reporter.createMessage(a));
	}

	public boolean hasMissingSongs (Album album){
		boolean[] exist = new boolean[album.getNumberOfTracks()];
		for (Track track : album.getTracks()){
			exist[track.getNumber()-1] = true;
		}
		for (int i = 0; i< exist.length; i++){
			if (!exist[i]){
				return true;
			}
		}
		return false;
	}

}
