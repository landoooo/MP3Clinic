/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fbarros.mp3clinic.exceptions;

/**
 *
 * @author fernando
 */
public class InitializationException extends Exception {

    public InitializationException(String message, Exception e){
        super(message, e);
    }
    public InitializationException(){
    }
    
}
