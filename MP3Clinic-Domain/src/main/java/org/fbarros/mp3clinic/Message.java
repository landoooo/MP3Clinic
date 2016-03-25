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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author fernando
 */
public class Message {
    
    private ObjectProperty<ReportingData> reportingDataProperty;
    private ObjectProperty<LocalDate> dateProperty;    
    private StringProperty albumProperty;
    private ObjectProperty<List<String>> pathsProperty;
    
    public Message(ReportingData reportingData){
    	this.reportingDataProperty = new SimpleObjectProperty<ReportingData>(reportingData);
    	this.dateProperty= new SimpleObjectProperty<LocalDate>(LocalDate.now());
    	this.pathsProperty= new SimpleObjectProperty<List<String>>(new ArrayList<String>());
    	this.albumProperty = new SimpleStringProperty();
    }

    
    //////////// GETTERS / SETTERS ///////////////////

	@Override
	public String toString() {
		return "Message [" + reportingDataProperty + " " + dateProperty + ": " + albumProperty + " - " + pathsProperty + "]";
	}

	public LocalDate getDate() {
		return dateProperty.get();
	}

	public List<String> getPaths() {
		return pathsProperty.get();
	}

	public void setPaths(List<String> paths) {
		this.pathsProperty.set(paths);
	}

	public void addPath(String path){
		this.pathsProperty.get().add(path);
	}
	
	public ReportingData getReportingData() {
		return reportingDataProperty.get();
	}

	public String getAlbum() {
		return albumProperty.get();
	}

	public void setAlbum(String album) {
		this.albumProperty.set(album);
	}
	
}
