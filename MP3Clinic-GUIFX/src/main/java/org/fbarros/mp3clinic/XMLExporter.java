package org.fbarros.mp3clinic;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.fbarros.mp3clinic.gui.data.ExecutionData;
import org.fbarros.mp3clinic.gui.data.PersistedLibraryLoad;

public class XMLExporter {

	/**
	 * Loads library data from the specified file. 
	 * 
	 * @param file
	 * @throws JAXBException 
	 */
	public PersistedLibraryLoad loadLibraryFromFile(File file) throws JAXBException {
		JAXBContext context = JAXBContext
				.newInstance(PersistedLibraryLoad.class);
		Unmarshaller um = context.createUnmarshaller();

		return (PersistedLibraryLoad) um.unmarshal(file);
	}

	/**
	 * Saves the current library data to the specified file.
	 * 
	 * @param file
	 * @throws JAXBException 
	 */
	public void savePersonDataToFile(ExecutionData data, File file) throws JAXBException {
		JAXBContext context = JAXBContext
				.newInstance(PersistedLibraryLoad.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		// Wrapping our person data.
		PersistedLibraryLoad wrapper = new PersistedLibraryLoad();
		wrapper.setAlbums(data.getAlbums());
		wrapper.setMessages(data.getMessages());
		wrapper.setLibraryLoad(data.getCurrentLibraryLoad());

		// Marshalling and saving XML to the file.
		m.marshal(wrapper, file);
	}

}
