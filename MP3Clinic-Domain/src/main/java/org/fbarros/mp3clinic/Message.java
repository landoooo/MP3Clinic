/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fbarros.mp3clinic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.fbarros.mp3clinic.data.ReportingData;

/**
 *
 * @author fernando
 */
public class Message {
    
    private ReportingData reportingData;
    private LocalDate date;    
    private String album;
    private List<String> paths;
    
    public Message(ReportingData reportingData){
    	this.reportingData = reportingData;
    	this.date = LocalDate.now();
    	this.paths = new ArrayList<String>();
    }

    
    //////////// GETTERS / SETTERS ///////////////////

	@Override
	public String toString() {
		return "Message [reportingData=" + reportingData + ", date=" + date + ", album=" + album + ", paths=" + paths
				+ "]";
	}

	public LocalDate getDate() {
		return date;
	}

	public List<String> getPaths() {
		return paths;
	}

	public void setPaths(List<String> paths) {
		this.paths = paths;
	}

	public void addPath(String path){
		this.paths.add(path);
	}
	
	public ReportingData getReportingData() {
		return reportingData;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}
	
}
