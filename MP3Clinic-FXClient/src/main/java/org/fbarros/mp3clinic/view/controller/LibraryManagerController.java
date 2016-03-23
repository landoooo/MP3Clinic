package org.fbarros.mp3clinic.view.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.fbarros.mp3clinic.MainApp;
import org.fbarros.mp3clinic.data.LibraryLoad;

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
			controller.setMainApp(mainApp);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			if (mainApp.getCurrentLibraryLoad() != null){
				Stage currentStage = (Stage) closeButton.getScene().getWindow();
				currentStage.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleCancelButton() {
		dialogStage.close();
	}

	public void setCurrentLibraryLoad(LibraryLoad currentLibraryLoad) {
		this.currentLibraryLoad = currentLibraryLoad;

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;

	}

	
}