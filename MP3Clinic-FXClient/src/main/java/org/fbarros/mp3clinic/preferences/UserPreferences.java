/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fbarros.mp3clinic.preferences;

import java.util.Locale;
import org.fbarros.mp3clinic.configuration.ApplicationProperties;
import org.fbarros.mp3clinic.configuration.PropertyKeys;

/**
 *
 * @author fernando
 */
public class UserPreferences {
  
    private static Locale choosenLocale = new Locale(ApplicationProperties.getValue(PropertyKeys.DEFAULT_LANGUAGE.getKey()));
    
    private UserPreferences(){}
    
    ////////////// getters / setters /////////////////////
    
    public static Locale getChoosenLocale(){
        return choosenLocale;
    }
}
