package org.fbarros.mp3clinic;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.LibraryLoad;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.view.controller.LibraryOverviewController;
import org.fbarros.mp3clinic.view.controller.RootLayoutController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    //TODO: move this two objects into an executionData object
    
    /**
     * The data as an observable list of messages.
     */
    private ObservableList<Message> messages = FXCollections.observableArrayList();

    //Selected libraryLoad
    private LibraryLoad currentLibraryLoad;
	private Collection<Album> albums;
    
    /**
     * Constructor
     */
    public MainApp() {
    	//TODO: initialization, preferences, libraryLoads, load last libraryLoad...
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MP3Clinic");

        initRootLayout();

        showLibraryOverview();
    }

    /**
     * Initializes the root layout and tries to load the last opened
     * person file.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("fxml/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the library overview inside the root layout.
     */
    public void showLibraryOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("fxml/LibraryOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            LibraryOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
       
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    
    public ObservableList<Message> getMessages() {
		return messages;
	}

	public void setMessages(ObservableList<Message> messages) {
		this.messages = messages;
	}

	public LibraryLoad getCurrentLibraryLoad() {
		return currentLibraryLoad;
	}

	public void setCurrentLibraryLoad(LibraryLoad currentLibraryLoad) {
		this.currentLibraryLoad = currentLibraryLoad;
	}

	public static void main(String[] args) {
        launch(args);
    }

	public void setAlbums(Collection<Album> collection) {
		this.albums = collection;
		
	}

	public void setMessages(Collection<Message> messages) {
		this.messages.setAll(messages);
		
	}

	public Collection<Album> getAlbums() {
		return albums;
	}

	public void addMessages(List<Message> value) {
		this.messages.addAll(value);
		
	}
}