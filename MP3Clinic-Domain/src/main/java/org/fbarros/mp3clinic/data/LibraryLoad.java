package org.fbarros.mp3clinic.data;

import java.time.LocalDate;

public class LibraryLoad {

	private LocalDate date;
	private String name;
	private String reportXMLPath;
	private String albumCollectionXMLPath;
	private String libraryPath;
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getReportXMLPath() {
		return reportXMLPath;
	}
	
	public void setReportXMLPath(String reportXMLPath) {
		this.reportXMLPath = reportXMLPath;
	}
	
	public String getAlbumCollectionXMLPath() {
		return albumCollectionXMLPath;
	}
	
	public void setAlbumCollectionXMLPath(String albumCollectionXMLPath) {
		this.albumCollectionXMLPath = albumCollectionXMLPath;
	}

	public String getLibraryPath() {
		return libraryPath;
	}

	public void setLibraryPath(String libraryPath) {
		this.libraryPath = libraryPath;
	}
	
	
}
