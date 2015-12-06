/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fbarros.mp3clinic.messages;

/**
 *
 * @author fernando
 */
public enum MessageKeys {
    
    START_BUTTON("button_start"),
    LIBRARY_CHOOSER_BUTTON("button_library_chooser"),
    LIBRARY_LABEL("label_library"),
    NO_DIRECTORY_SELECTED("no_directory_selected"),
    
    
    ;
        
    private String key;

    private MessageKeys(String key){
        this.key = key;
    }
    
    ///////////// getters / setters ///////////////////
    
    public String getKey (){
        return key;
    }
}
