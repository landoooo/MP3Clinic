package org.fbarros.mp3clinic.gui.newlibrary;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import org.fbarros.mp3clinic.Category;
import org.fbarros.mp3clinic.Priority;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.loader.FileSystemLoader;
import org.fbarros.mp3clinic.loader.albumcalculator.AlbumsCalculator;
import org.fbarros.mp3clinic.loader.albumcalculator.IAlbumsCalculator;
import org.fbarros.mp3clinic.loader.grouper.AlbumGrouper;
import org.fbarros.mp3clinic.loader.grouper.IAlbumGrouper;
import org.fbarros.mp3clinic.report.ProcessingReport;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;

public class LoadLibraryTask extends Task<ProcessingReport<Album>> implements Initializable {

	private File folder;

	private FileSystemLoader collectionLoader = new FileSystemLoader(new ReportingData(Priority.HIGH, Category.LOADING, "error.message.loading_data", "Filesystem Loader"));

	private IAlbumGrouper albumGrouper = new AlbumGrouper();;

	private IAlbumsCalculator albumCalculator = new AlbumsCalculator(new ReportingData(Priority.HIGH, Category.WRONG_INFORMATION, "error.message.calculating_album", "Album Calculator"));

	public LoadLibraryTask(File folder){
		this.folder= folder;
	}

	protected ProcessingReport<Album> call() throws Exception {
		ProcessingReport<Album> result = new ProcessingReport<Album>();
		ProcessingReport<Track> loadReport = collectionLoader.loadCollection(folder);
		result.addMessages(loadReport.getMessages());
		Collection<List<Track>> groupedTracks = albumGrouper.group(loadReport.getCollection());
		for (List<Track> albumTracks : groupedTracks){
			albumCalculator.calculateAlbum(albumTracks, result);
		}
		return result;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
