package org.fbarros.mp3clinic.loader;

import java.io.File;

import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.report.ProcessingReport;
import javafx.concurrent.Task;

public interface ICollectionLoader {

	Task<ProcessingReport<Track>> getLoader(File dir);
}
