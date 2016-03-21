package org.fbarros.mp3clinic.loader;

import java.io.File;
import java.util.Collection;

import org.fbarros.mp3clinic.data.Track;

public interface ICollectionLoader<T> {

	ProcessingReport<T> loadCollection (File dir);
}
