/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fbarros.mp3clinic.configuration;

import org.fbarros.mp3clinic.messages.*;

/**
 *
 * @author fernando
 */
public enum PropertyKeys {
    
    DEFAULT_LANGUAGE("default_language");
        
    private String key;

    private PropertyKeys(String key){
        this.key = key;
    }
    
    ///////////// getters / setters ///////////////////
    
    public String getKey (){
        return key;
    }
}
