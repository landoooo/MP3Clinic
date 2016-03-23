package org.fbarros.mp3clinic.view.controller;

import java.io.IOException;

import org.fbarros.mp3clinic.MainApp;
import org.fbarros.mp3clinic.data.LibraryLoad;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LibraryOverviewController {

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public LibraryOverviewController() {
    }

    @FXML
    private void initialize() {
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
            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleScanButton() {
        
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    
}