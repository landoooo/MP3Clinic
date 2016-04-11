package org.fbarros.mp3clinic;

import java.io.File;
import java.util.prefs.Preferences;

import org.fbarros.mp3clinic.gui.App;

public class FilePathManager {
	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 * 
	 * @return
	 */
	public File getPersonFilePath() {
	    Preferences prefs = Preferences.userNodeForPackage(App.class);
	    String filePath = prefs.get("filePath", null);
	    if (filePath != null) {
	        return new File(filePath);
	    } else {
	        return null;
	    }
	}

	/**
	 * Sets the file path of the currently loaded file. The path is persisted in
	 * the OS specific registry.
	 * 
	 * @param file the file or null to remove the path
	 */
	public void setPersonFilePath(File file) {
	    Preferences prefs = Preferences.userNodeForPackage(App.class);
	    if (file != null) {
	        prefs.put("filePath", file.getPath());
	    } else {
	        prefs.remove("filePath");
	    }
	}
}
