package org.fbarros.mp3clinic.view.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.fbarros.mp3clinic.Category;
import org.fbarros.mp3clinic.MainApp;
import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.Priority;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.procesor.DuplicatesFinder;
import org.springframework.beans.factory.annotation.Autowired;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LibraryOverviewController {

	public static final String SELECT_LIBRARY_LABEL_TEXT = "Select a library";

	// Reference to the main application.
	private MainApp mainApp;

	@FXML
	private TableView<Message> messagesTable;
	@FXML
	private TableColumn<Message, LocalDate> dateColumn;
	@FXML
	private TableColumn<Message, Category> categoryColumn;
	//@FXML
	//private TableColumn<Message, String> contentColumn;
	@FXML
	private TableColumn<Message, Priority> priorityColumn;
	@FXML
	private TableColumn<Message, String> albumColumn;
	//@FXML
	//private TableColumn<Message, List<String>> pathsColumn;
	@FXML
	private Label selectedLibraryLabel;

	//TODO: new class holding all the procesors and configuration of which ones should be executed 
	private DuplicatesFinder duplicatesFinder = new DuplicatesFinder(new ReportingData(Priority.HIGH, Category.DUPLICATED, "error.message.duplicate_tracks"));
	
	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public LibraryOverviewController() {
		this.selectedLibraryLabel = new Label(SELECT_LIBRARY_LABEL_TEXT);
	}

	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		dateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(cellData.getValue().getDate()));
		//contentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReportingData().getMessageKey()));
		categoryColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Category>(cellData.getValue().getReportingData().getCategory()));
		priorityColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Priority>(cellData.getValue().getReportingData().getPriority()));
		albumColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlbum()));
		//pathsColumn.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getPaths()));
	}

	@FXML
	private void handleChangeLibraryButton(){
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("fxml/LibraryManager.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Change Library");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(mainApp.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			LibraryManagerController controller = loader.getController();
			controller.setCurrentLibraryLoad(mainApp.getCurrentLibraryLoad());
			controller.setDialogStage(dialogStage);
			controller.setMainApp(mainApp);
			controller.setLibraryOverviewController(this);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleScanButton() {
		Task<List<Message>> scanTask = createScanTask(mainApp.getAlbums());
		scanTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,  new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent t) {
				mainApp.addMessages(scanTask.getValue());
			}
		});
		new Thread(scanTask).start();
	}

	private Task<List<Message>> createScanTask(Collection<Album> albums) {
		return new Task<List<Message>>() {
			@Override
			protected List<Message> call() throws Exception {
				List<Message> report = duplicatesFinder.process(albums);
				return report;
			}
		};
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		messagesTable.setItems(mainApp.getMessages());
	}

	public void setSelectedLibraryLabelText(String value){
		this.selectedLibraryLabel.setText(value);
	}

}