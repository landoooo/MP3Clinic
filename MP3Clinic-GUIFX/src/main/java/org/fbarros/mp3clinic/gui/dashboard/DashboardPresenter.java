package org.fbarros.mp3clinic.gui.dashboard;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.xml.bind.JAXBException;

import org.fbarros.mp3clinic.FilePathManager;
import org.fbarros.mp3clinic.XMLExporter;
import org.fbarros.mp3clinic.gui.dashboard.library.LibraryView;
import org.fbarros.mp3clinic.gui.dashboard.messages.MessagesView;
import org.fbarros.mp3clinic.gui.data.ExecutionData;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

/**
 *
 * @author fbarros
 */
public class DashboardPresenter implements Initializable {

	@Inject
	ExecutionData data;
	
	@Inject
	XMLExporter xmlExporter;
	
	@Inject
	FilePathManager filePathManager;
	
	@FXML
	private MenuItem saveMenuItem;
	
    @FXML
    private Pane libraryPane;
    
    @FXML
    private Pane messagesPane;
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        LibraryView libraryView = new LibraryView();
        MessagesView messagesView = new MessagesView();
       
        libraryPane.getChildren().add(libraryView.getView());
        messagesPane.getChildren().add(messagesView.getView());   

        this.saveMenuItem.setDisable(true);
        
    	this.data.getLoadedPorperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean libraryLoaded) {
				saveMenuItem.setDisable(!libraryLoaded);
			}
		});
    }
    
    public void handleSaveMenuItem(){
    	File file = filePathManager.getLastLibraryFilePath(data.getCurrentLibraryLoad().getName());
    	try {
			xmlExporter.savePersonDataToFile(data, file);
		} catch (JAXBException e) {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Persisting Library");
        	alert.setHeaderText("Error when persisting library");
        	alert.setContentText(e.toString());
        	//TODO: get rid of the printStackTrace once the message is properly displayed
        	e.printStackTrace();
        	alert.showAndWait();      	
		}
    	
    }

}
