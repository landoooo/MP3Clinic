/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fbarros.mp3clinic.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.fbarros.mp3clinic.messages.MessagesHandler;

/**
 *
 * @author fernando
 */
public class ApplicationProperties {

    private static Properties prop;

    { 
	try (InputStream input = MessagesHandler.class.getClassLoader().getResourceAsStream("bundles/config.properties")){

                prop = new Properties();
		prop.load(input);

	} catch (IOException ex) {
		ex.printStackTrace();
	}
    }
   
    public static String getValue(String key){
        return prop.getProperty(key);
    }
}
