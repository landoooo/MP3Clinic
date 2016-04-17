package org.fbarros.mp3clinic.gui.dashboard.messages;

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
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.fbarros.mp3clinic.Category;
import org.fbarros.mp3clinic.DateUtils;
import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.Priority;
import org.fbarros.mp3clinic.gui.data.ExecutionData;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author fbarros
 */
public class MessagesPresenter implements Initializable {

	@Inject
	private ExecutionData data; 
	
	@FXML
	private TableView<Message> messagesTable;
	@FXML
	private TableColumn<Message, String> dateColumn;
	@FXML
	private TableColumn<Message, Category> categoryColumn;
	@FXML
	private TableColumn<Message, String> contentColumn;
	@FXML
	private TableColumn<Message, Priority> priorityColumn;
	@FXML
	private TableColumn<Message, String> albumColumn;
	@FXML
	private TableColumn<Message, List<String>> pathsColumn;
	@FXML
	private Label selectedLibraryLabel;

	private ResourceBundle rb;
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	this.rb = resources;
    	messagesTable.setItems(data.getMessages());

    	// Initialize the messages table with the two columns.
		dateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getDate().format(DateUtils.DATE_PATTERN)));
		contentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(rb.getString(cellData.getValue().getReportingData().getMessageKey())));
		categoryColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Category>(cellData.getValue().getReportingData().getCategory()));
		priorityColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Priority>(cellData.getValue().getReportingData().getPriority()));
		albumColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlbum()));
		pathsColumn.setCellValueFactory(cellData ->  new SimpleObjectProperty<List<String>>(cellData.getValue().getPaths()));
    }

}
