package org.fbarros.mp3clinic.procesor;

import java.util.List;

import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.report.ReportingDataAware;

public abstract class Reporter implements ReportingDataAware{

	private ReportingData data;
	private String name;
	
	public Reporter(ReportingData reportingData, String name){
		this.data = reportingData;
		this.name = name;
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

	public Message createMessage(Album album, Exception e){
		Message message = new Message(getReportingData());
		message.setAlbum(album.toString());
		message.setDescription(e.getMessage());
		return message;
	}
	
	///////////////// GETTERS / SETTERS ////////////////////////
	
	public ReportingData getReportingData() {
		return data;
	}
	
	public String getName() {
		return name;
	}

}
