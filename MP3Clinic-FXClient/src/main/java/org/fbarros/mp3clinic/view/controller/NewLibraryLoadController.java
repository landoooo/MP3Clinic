package org.fbarros.mp3clinic.view.controller;

import java.io.File;
import java.time.LocalDate;

import org.fbarros.mp3clinic.MainApp;
import org.fbarros.mp3clinic.data.LibraryLoad;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class NewLibraryLoadController {

	public static final String LIBRARY_PATH_LABEL_DEFAULT_TEXT = "Please select the library folder";

	@FXML
	private Label libraryPathLabel;

	@FXML
	private TextField nameField;

	private Stage dialogStage;
	private MainApp mainApp;
	private String libraryPath;


	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public NewLibraryLoadController() {
	}

	@FXML
	private void initialize() {
		this.libraryPathLabel.setText(LIBRARY_PATH_LABEL_DEFAULT_TEXT);
	}

	@FXML
	private void handleOkButton() {
		if (validateFields()){
			LibraryLoad libraryLoad = new LibraryLoad();
			libraryLoad.setDate(LocalDate.now());
			libraryLoad.setName(nameField.getText());
			libraryLoad.setLibraryPath(libraryPath);
			
			mainApp.setCurrentLibraryLoad(libraryLoad);
			dialogStage.close();
		}
	}

	private boolean validateFields() {
		String errorMessage = new String();
		if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n"; 
        }
		if (libraryPath == null || libraryPath.length() == 0) {
            errorMessage += "No valid library path!\n"; 
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Invalid Fields");
        	alert.setHeaderText("Please correct invalid fields");
        	alert.setContentText(errorMessage);
        	alert.showAndWait();      	
            return false;
        }	}

	@FXML
	private void handleCancelButton() {
		dialogStage.close();
	}

	@FXML
	private void handleBrowseButton() {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Open Library Folder");
		File selectedFolder = chooser.showDialog(dialogStage);
		if (selectedFolder != null) {
			this.libraryPathLabel.setText(selectedFolder.getAbsolutePath());
			this.libraryPath = selectedFolder.getAbsolutePath();
		} else {
			this.libraryPathLabel.setText(LIBRARY_PATH_LABEL_DEFAULT_TEXT);
			this.libraryPath = null;
		}   
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;		
	}

}