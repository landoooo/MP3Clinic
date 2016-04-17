package org.fbarros.mp3clinic.task;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.data.loader.TrackLoader;
import org.fbarros.mp3clinic.loader.FileSystemLoader;
import org.fbarros.mp3clinic.loader.albumcalculator.AlbumsCalculator;
import org.fbarros.mp3clinic.loader.albumcalculator.IAlbumsCalculator;
import org.fbarros.mp3clinic.loader.grouper.AlbumGrouper;
import org.fbarros.mp3clinic.loader.grouper.IAlbumGrouper;
import org.fbarros.mp3clinic.report.ProcessingReport;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class LoadLibraryTask extends Task<ObservableList<Album>> {

	private File folder;

	private FileSystemLoader collectionLoader = new FileSystemLoader(new ReportingData(Priority.HIGH, Category.LOADING, "error.message.loading_data", "Filesystem Loader"));

	private IAlbumGrouper albumGrouper = new AlbumGrouper();;

	private IAlbumsCalculator albumCalculator = new AlbumsCalculator(new ReportingData(Priority.HIGH, Category.WRONG_INFORMATION, "error.message.calculating_album", "Album Calculator"));

	public LoadLibraryTask(File folder){
		this.folder= folder;
	}

	protected ObservableList<Album> call() throws Exception {
		ProcessingReport<Track> result = new ProcessingReport<Track>();
		Collection<File> files = FileUtils.listFiles(folder , new RegexFileFilter("^(.+?)\\.mp3"),  DirectoryFileFilter.DIRECTORY );
		this.updateTitle("Loading Traks...");
		int counter = 0;
		int nFiles = files.size();
		for (File file : files){
			counter++;
			if (this.isCancelled()) {
				break;
			}
			try{
				Track track = TrackLoader.createTrack(file);
				this.updateMessage("Adding " + track);
				result.addElement(track);
				updateProgress(counter, nFiles);
			} catch (Exception e) {
				//result.addMessage(reporter.createMessage(Arrays.asList(file.getPath())));
			}	
		}
		return null;
		//return result;
	};

	@Override
	protected void cancelled() {
		super.cancelled();
		updateMessage("The task was cancelled.");
	}

	@Override
	protected void failed() {
		super.failed();
		updateMessage("The task failed.");
	}

	@Override
	public void succeeded() {
		super.succeeded();
		updateMessage("The task finished successfully.");
	}



	protected ObservableList<Album> call2() throws Exception {
		//ProcessingReport<Track> report = collectionLoader.getLoader(dir)(folder);
		//Collection<List<Track>> groupedAlbums = albumGrouper.group(report.getCollection());
		ProcessingReport<Album> result = new ProcessingReport<>();
		//for(List<Track> tracks : groupedAlbums){
			//albumCalculator.calculateAlbum(tracks, result);
		//}
		return null;
	}

}
