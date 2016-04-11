package org.fbarros.mp3clinic.gui.dashboard;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.fbarros.mp3clinic.gui.dashboard.library.LibraryView;
import org.fbarros.mp3clinic.gui.dashboard.messages.MessagesView;
import org.fbarros.mp3clinic.gui.data.ExecutionData;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

/**
 *
 * @author fbarros
 */
public class DashboardPresenter implements Initializable {

	@Inject
	ExecutionData data;
	
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
    	
    	
    }

}
