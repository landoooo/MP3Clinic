package org.fbarros.mp3clinic.loader;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.fbarros.mp3clinic.Category;
import org.fbarros.mp3clinic.Priority;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.data.loader.TrackLoader;
import org.fbarros.mp3clinic.procesor.Reporter;

public class FileSystemLoader extends Reporter implements ICollectionLoader{
	
	public FileSystemLoader(ReportingData reportingData) {
		super(reportingData);
	}

	@Override
	public LoadingSummary loadCollection(File dir) {
		LoadingSummary result = new LoadingSummary();
		Collection<File> files = FileUtils.listFiles(
				dir, 
				new RegexFileFilter("^(.+?)\\.mp3"), 
				DirectoryFileFilter.DIRECTORY
				);
		for (File file : files){
			try{
				Track track  = TrackLoader.createTrack(file);
				result.addTrack(track);
			} catch (Exception e) {
				result.addMessage(
						createMessage(Arrays.asList(file.getPath())));
			}	
		}
		return result;
	}	
	
}
