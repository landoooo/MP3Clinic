package org.fbarros.mp3clinic.loader;

import java.io.File;

import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.report.ProcessingReport;

public interface ICollectionLoader {

	ProcessingReport<Track> loadCollection (File dir);
}
