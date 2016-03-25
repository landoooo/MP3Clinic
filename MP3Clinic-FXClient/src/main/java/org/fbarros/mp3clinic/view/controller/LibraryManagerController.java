package org.fbarros.mp3clinic.view.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.fbarros.mp3clinic.MainApp;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.LibraryLoad;
import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.loader.IAlbumGrouper;
import org.fbarros.mp3clinic.loader.IAlbumsCalculator;
import org.fbarros.mp3clinic.loader.ICollectionLoader;
import org.fbarros.mp3clinic.loader.ProcessingReport;
import org.springframework.beans.factory.annotation.Autowired;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LibraryManagerController {
	@FXML
	private TableView<LibraryLoad> libraryLoadsTable;
	@FXML
	private TableColumn<LibraryLoad, LocalDate> dateColumn;
	@FXML
	private TableColumn<LibraryLoad, String> nameColumn;
	@FXML
	private TableColumn<LibraryLoad, String> collectionPathColumn;
	@FXML
	private LibraryLoad currentLibraryLoad;
	@FXML
	private Button closeButton;

	// Reference to the main application.
	private MainApp mainApp;
	private Stage dialogStage;

	@Autowired
	private ICollectionLoader collectionLoader;

	@Autowired
	private IAlbumGrouper albumGrouper;

	@Autowired
	private IAlbumsCalculator albumCalculator;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public LibraryManagerController() {
	}

	@FXML
	private void initialize() {
		//TODO: load libraryLoads and set the table data
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		//libraryLoadsTable.setItems();
	}

	@FXML
	private void handleLoadButton() {
		//TODO: create LibraryLoad instance, scan library to create report, link libraryLoad
	}

	@FXML
	private void handleNewButton() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("fxml/NewLibraryLoad.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("New Library Load");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(mainApp.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			NewLibraryLoadController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			if (controller.getLibraryLoad() != null){
				Stage currentStage = (Stage) closeButton.getScene().getWindow();
				currentStage.close();
				Task<ProcessingReport<Album>> collectionLoaderTask = createCollectionLoaderTask(new File(controller.getLibraryLoad().getLibraryPath()));
				collectionLoaderTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,  new EventHandler<WorkerStateEvent>() {
				    @Override
				    public void handle(WorkerStateEvent t) {
				    	mainApp.setMessages(collectionLoaderTask.getValue().getMessages());
				    	mainApp.setAlbums(collectionLoaderTask.getValue().getCollection());
				    }
				});
				new Thread(collectionLoaderTask).start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleCancelButton() {
		dialogStage.close();
	}

	private Task<ProcessingReport<Album>> createCollectionLoaderTask(File folder) {
		return new Task<ProcessingReport<Album>>() {
			@Override
			protected ProcessingReport<Album> call() throws Exception {
				ProcessingReport<Track> report = collectionLoader.loadCollection(folder);
				Collection<List<Track>> groupedAlbums = albumGrouper.albumGrouper(report.getCollection());
				ProcessingReport<Album> result = new ProcessingReport<>();
				for(List<Track> tracks : groupedAlbums){
					albumCalculator.calculateAlbum(tracks, result);
				}
				return result;
			}
		};
	}

	public void setCurrentLibraryLoad(LibraryLoad currentLibraryLoad) {
		this.currentLibraryLoad = currentLibraryLoad;

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;

	}


}