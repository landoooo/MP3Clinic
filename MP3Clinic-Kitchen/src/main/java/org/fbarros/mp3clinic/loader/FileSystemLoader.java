package org.fbarros.mp3clinic.loader;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.fbarros.mp3clinic.Category;
import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.Priority;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.data.loader.TrackLoader;
import org.fbarros.mp3clinic.procesor.Reporter;
import org.fbarros.mp3clinic.report.ProcessingReport;
import org.fbarros.mp3clinic.report.ReporterFactory;

public class FileSystemLoader {
	
	private Reporter reporter;
	
	public FileSystemLoader(){
		this.reporter =  ReporterFactory.getReporter(new ReportingData(Priority.HIGH, Category.LOADING, "error.message.loadingdata", "Filesystem Loader"));
	}
	
	public FileSystemLoader(ReportingData reportingData) {
		this.reporter = ReporterFactory.getReporter(reportingData);
	}

	public ProcessingReport<Track> loadCollection(File dir) {
		ProcessingReport<Track> result = new ProcessingReport<Track>();
		Collection<File> files = FileUtils.listFiles(
				dir, 
				new RegexFileFilter("^(.+?)\\.mp3"), 
				DirectoryFileFilter.DIRECTORY
				);
		for (File file : files){
			try{
				Track track  = TrackLoader.createTrack(file);
				result.addElement(track);
			} catch (Exception e) {
				Message message = reporter.createMessage(Arrays.asList(file.getPath()));
				message.setException(e);
				result.addMessage(message);
			}	
		}
		return result;
	}	
	
}
