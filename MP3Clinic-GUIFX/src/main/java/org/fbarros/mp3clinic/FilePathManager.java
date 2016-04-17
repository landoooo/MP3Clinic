package org.fbarros.mp3clinic;

import java.io.File;
import java.util.prefs.Preferences;

import org.fbarros.mp3clinic.gui.App;

public class FilePathManager {

	public static final String DEFAULT_FOLDER_NAME = "MP3ClinicLibraries";
	
	public File getLastLibraryFilePath(String fileName) {
	    Preferences prefs = Preferences.userNodeForPackage(App.class);
	    String filePath = prefs.get("filePath", null);
	    if (filePath != null) {
	        return new File(filePath + File.separator + fileName + ".xml");
	    } else {
	        return generateDefaultFolder();
	    }
	}

	private File generateDefaultFolder(){
	    String userDirectoryString = System.getProperty("user.home");
	    File defaultFolder =  new File(userDirectoryString + File.separator + DEFAULT_FOLDER_NAME);
	    if (!defaultFolder.exists()){	    	
	    	defaultFolder.mkdir();	
	    }
	    return defaultFolder;
	}
	
	public void setLastLibraryFilePath(File file) {
	    Preferences prefs = Preferences.userNodeForPackage(App.class);
	    if (file != null) {
	        prefs.put("filePath", file.getPath());
	    } else {
	        prefs.remove("filePath");
	    }
	}
}
