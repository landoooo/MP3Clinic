package org.fbarros.mp3clinic.gui;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.fbarros.mp3clinic.Category;
import org.fbarros.mp3clinic.Priority;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.gui.configuration.ApplicationConfiguration;
import org.fbarros.mp3clinic.gui.configuration.Processors;
import org.fbarros.mp3clinic.gui.dashboard.DashboardView;
import org.fbarros.mp3clinic.procesor.DuplicateSongsFinder;
import org.fbarros.mp3clinic.procesor.MissingFinder;

import com.airhacks.afterburner.injection.Injector;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author fbarros
 */
public class App extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		/*
		 * Properties of any type can be easily injected.
		 */
		LocalDate date = LocalDate.now();
		Map<Object, Object> customProperties = new HashMap<>();
		customProperties.put("date", date);
		/*
		 * any function which accepts an Object as key and returns
		 * and return an Object as result can be used as source.
		 */
		Injector.setConfigurationSource(customProperties::get);

		//System.setProperty("happyEnding", " Enjoy the flight!");
		DashboardView appView = new DashboardView();
		Scene scene = new Scene(appView.getView());
		stage.setTitle("MP3Clinic");
		final String uri = getClass().getResource("app.css").toExternalForm();
		scene.getStylesheets().add(uri);
		stage.setMinHeight(540);
		stage.setMinWidth(620);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void stop() throws Exception {
		Injector.forgetAll();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
