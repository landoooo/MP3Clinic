/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fbarros.mp3clinic;

/**
 *
 * @author fernando
 */
public class Message {
    
    private String message;
    
    public Message(String message){
        this.message = message;
    }
    
    public String toString(){
        return message;
    }
    
    //////////// GETTERS / SETTERS ///////////////////

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
