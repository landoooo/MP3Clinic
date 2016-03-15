
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fbarros.mp3clinic;

//~--- non-JDK imports --------------------------------------------------------
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.fbarros.mp3clinic.messages.MessageKeys;
import org.fbarros.mp3clinic.messages.MessagesHandler;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author fernando
 */
public class MainApplication extends Application {

    Scene scene1, scene2;
    Stage window;

    @Override
    public void start(Stage primaryStage) {

        window = primaryStage;

        VBox selectionBox = new VBox();
        VBox outputBox = new VBox();

        final Label labelSelectedDirectory = new Label();

        final Button startButton = new Button();
        startButton.setText(MessagesHandler.getMessage(MessageKeys.START_BUTTON.getKey()));
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene2);
                try {
                    List<Message> messages = new ArrayList<Message>();
                    		//MissingFinder.processLibrary(labelSelectedDirectory.getText());
                    for (Message m : messages) {
                        final Label output = new Label();
                        //output.setText(m.getContent());
                        outputBox.getChildren().add(output);
                    }
                } catch (Exception e) {
                    //print error
                }
            }
        });

        selectionBox.getChildren().add(labelSelectedDirectory);
        selectionBox.getChildren().add(startButton);

        final Button btnOpenDirectoryChooser = new Button();
        btnOpenDirectoryChooser.setText(MessagesHandler.getMessage(MessageKeys.LIBRARY_CHOOSER_BUTTON.getKey()));
        btnOpenDirectoryChooser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory
                        = directoryChooser.showDialog(primaryStage);

                if (selectedDirectory == null) {
                    labelSelectedDirectory.setText(MessagesHandler.getMessage(MessageKeys.NO_DIRECTORY_SELECTED.getKey()));
                } else {
                    labelSelectedDirectory.setText(selectedDirectory.getAbsolutePath());
                }
            }
        });
        selectionBox.getChildren().add(btnOpenDirectoryChooser);

        scene1 = new Scene(selectionBox, 300, 200);
        scene2 = new Scene(outputBox, 200, 300);

        Button closeButton = new Button();

        //TODO
        closeButton.setText("Clocloclose");
        closeButton.setOnAction(e -> primaryStage.setScene(scene1));

        outputBox.getChildren().add(closeButton);

        //TODO
        primaryStage.setTitle("EMEPETRESCLINIC");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
