package org.fbarros.mp3clinic.gui.dashboard.library;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.fbarros.mp3clinic.Category;
import org.fbarros.mp3clinic.DateUtils;
import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.Priority;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.gui.configuration.ApplicationConfiguration;
import org.fbarros.mp3clinic.gui.data.ExecutionData;
import org.fbarros.mp3clinic.gui.newlibrary.NewLibraryPresenter;
import org.fbarros.mp3clinic.gui.newlibrary.NewLibraryView;
import org.fbarros.mp3clinic.procesor.IProcesor;

import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author fbarros
 */
public class LibraryPresenter implements Initializable {
	
	@Inject
	ApplicationConfiguration applicationConfiguration;
	@Inject
	ExecutionData data;
	@FXML
	Label loadedLibraryName;
	@FXML
	Label loadedLibraryPath;
	@FXML
	Label loadedLibraryNumberOfAlbums;
	@FXML
	Label loadedLibraryLoadDate;
	@FXML
	Button newLibraryButton;
	@FXML
	Button scanButton;
	@FXML
	Button loadLibraryButton;
	@FXML
	ImageView loadingImage;
	
	private BooleanPropertyBase currentlyScanning;
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
		this.currentlyScanning = new SimpleBooleanProperty(false); 

    	this.data.getLoadedPorperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean libraryLoaded) {
				
				//Update labels
				loadedLibraryLoadDate.setText(data.getCurrentLibraryLoad().getDate().format(DateUtils.DATE_PATTERN));
				loadedLibraryName.setText(data.getCurrentLibraryLoad().getName());
				loadedLibraryNumberOfAlbums.setText(String.valueOf(data.getAlbums().size()));
				loadedLibraryPath.setText(data.getCurrentLibraryLoad().getLibraryPath());
				
				//Enable/Disable scan button
				scanButton.setDisable(!libraryLoaded && !currentlyScanning.get());
			}
		});
    	this.loadLibraryButton.setDisable(true);
    	this.scanButton.setDisable(true);
		
		//Move this image to resources
		Image image = new Image("/img/loading.gif");
		this.loadingImage.setImage(image);
		this.loadingImage.visibleProperty().set(false);
		this.currentlyScanning.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				loadingImage.visibleProperty().setValue(newValue);	
			}			
		});
		
    }
    
    public void handleNewLibraryButton(ActionEvent event){
		NewLibraryView newLibraryView = new NewLibraryView();
		NewLibraryPresenter newLibraryPresenter= (NewLibraryPresenter) newLibraryView.getPresenter();
		newLibraryPresenter.display();
    }

	public void handleScanButton(ActionEvent event) {
    	currentlyScanning.setValue(true);
    	Task<List<Message>> scanTask = createScanTask(data.getAlbums());
		scanTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,  new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent t) {
				data.addMessages(scanTask.getValue());
		    	currentlyScanning.setValue(false);
			}
		});
		new Thread(scanTask).start();
	}

	private Task<List<Message>> createScanTask(Collection<Album> albums) {
		return new Task<List<Message>>() {
			@Override
			protected List<Message> call() throws Exception {
				List<Message> report = new ArrayList<Message>();
				for (IProcesor procesor: applicationConfiguration.getProcessors().getAvailableProcessors()){
					report.addAll(procesor.process(albums));
				}
				return report;
			}
		};
	}
	
    //TODO: not yet implemented
    public void handleLoadLibraryButton(ActionEvent event){
    }

}
