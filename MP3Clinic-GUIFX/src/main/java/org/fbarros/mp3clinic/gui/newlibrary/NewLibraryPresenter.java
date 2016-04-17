package org.fbarros.mp3clinic.gui.newlibrary;

import java.io.File;

/*
 * #%L
 * igniter
 * %%
 * Copyright (C) 2013 - 2014 Adam Bien
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.LibraryLoad;
import org.fbarros.mp3clinic.gui.data.ExecutionData;
import org.fbarros.mp3clinic.report.ProcessingReport;

import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author fbarros
 */
public class NewLibraryPresenter implements Initializable {

	public static final String LIBRARY_PATH_LABEL_DEFAULT_TEXT = "Please select the library folder";

	@Inject 
	ExecutionData data;	
	@FXML
	Pane newLibraryPane;
	@FXML
	ImageView loadingImage;
	@FXML
	Label libraryPathLabel;
	@FXML
	TextField nameField;
	@FXML
	Button loadButton;
	@FXML
	Button browseButton;
	@FXML
	Button cancelButton;

	private Stage dialogStage;
	private String libraryPath;
	private BooleanPropertyBase currentlyLoading;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.libraryPathLabel.setText(LIBRARY_PATH_LABEL_DEFAULT_TEXT);
		this.currentlyLoading = new SimpleBooleanProperty(false); 
		this.loadButton.disableProperty().bind(currentlyLoading);
		this.browseButton.disableProperty().bind(currentlyLoading);
		this.nameField.disableProperty().bind(currentlyLoading);
		//Move this image to resources
		Image image = new Image("/img/loading.gif");
		this.loadingImage.setImage(image);
		this.loadingImage.visibleProperty().bind(currentlyLoading);
	}

	public void display(){
		dialogStage = new Stage();
		dialogStage.setTitle("New Library Load");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setScene(new Scene(newLibraryPane));
		dialogStage.showAndWait();  
	}

	public void handleLoadButton() {
		if (validateFields()){
			LibraryLoad newLibraryLoad = new LibraryLoad();
			newLibraryLoad.setDate(LocalDateTime.now());
			newLibraryLoad.setName(nameField.getText());
			newLibraryLoad.setLibraryPath(libraryPath);
			this.currentlyLoading.set(true);
			data.setLoaded(false);

			Task<ProcessingReport<Album>> collectionLoaderTask = new LoadLibraryTask(new File(libraryPath));
			collectionLoaderTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,  new EventHandler<WorkerStateEvent>() {
				@Override
				public void handle(WorkerStateEvent t) {
					currentlyLoading.set(false);
					if (collectionLoaderTask.getValue().getCollection().isEmpty()){
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Loading library");
						alert.setHeaderText("No songs found");
						alert.setContentText("Please select another folder. No songs were found on the choosen location.");
						alert.showAndWait();      	
					} else {
						data.removeAllMessages();
						data.addMessages(collectionLoaderTask.getValue().getMessages());
						data.setAlbums(collectionLoaderTask.getValue().getCollection());
						data.setCurrentLibraryLoad(newLibraryLoad);
						data.setLoaded(true);
						dialogStage.close();
					}
				}
			});
			new Thread(collectionLoaderTask).start();
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
		}	
	}

	public void handleCancelButton() {
		dialogStage.close();
	}

	public void handleBrowseButton() {
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

}
