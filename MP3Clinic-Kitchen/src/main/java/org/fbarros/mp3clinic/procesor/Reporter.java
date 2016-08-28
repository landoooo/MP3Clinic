package org.fbarros.mp3clinic.procesor;

import java.util.List;

import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.report.ReportingDataAware;

public class Reporter implements ReportingDataAware{

	private ReportingData data;
	
	public Reporter(ReportingData reportingData){
		this.data = reportingData;
	}
	
	public Message createMessage(List<String> paths){
		Message message = new Message(getReportingData());
		message.setPaths(paths);
		return message;
	}

	public Message createMessage(Album album){
		Message message = new Message(getReportingData());
		message.setAlbum(album.toString());
		return message;
	}
	
	public Message createMessage(Album album, String txt){
		Message message = new Message(getReportingData());
		message.setAlbum(album.toString());
		message.setDescription(txt);
		return message;
	}
	
	///////////////// GETTERS / SETTERS ////////////////////////
	
	public ReportingData getReportingData() {
		return data;
	}

}
