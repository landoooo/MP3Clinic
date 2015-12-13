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
    
    private String content;
    
    public Message(String content){
        this.content = content;
    }
    
    @Override
    public String toString(){
        return content;
    }
    
    //////////// GETTERS / SETTERS ///////////////////

    public String getContent() {
        return content;
    }

    public void setMessage(String content) {
        this.content = content;
    }
    
}
