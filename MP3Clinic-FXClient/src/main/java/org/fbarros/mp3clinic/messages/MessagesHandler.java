/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fbarros.mp3clinic.messages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author fernando
 */
public class MessagesHandler {
    
    private static Properties prop;

    static { 
	try (InputStream input = MessagesHandler.class.getClassLoader().getResourceAsStream("bundles/messages.properties")){
            prop = new Properties();
            //prop.load(input);
	} catch (IOException ex) {
		ex.printStackTrace();
	}
    }
   
    public static String getMessage(String key){
        return key;
//return prop.getProperty(key);
    }
}
