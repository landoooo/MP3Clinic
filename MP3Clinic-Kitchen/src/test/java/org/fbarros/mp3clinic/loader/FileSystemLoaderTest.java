package org.fbarros.mp3clinic.loader;

import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.kitchen.basetest.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.util.Collection;

public class FileSystemLoaderTest extends BaseTest{

	@Autowired
	private FileSystemLoader fsLoader;
	
	@Test
	public void loadCollectionTest() {
		Collection<Track> collection = fsLoader.loadCollection(new File(ClassLoader.getSystemResource("TestCollection").getFile())).getCollection();
		assertThat(collection).hasSize(8);
	}
	
}
