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
public class NoTrackNumberException extends Exception {
    
    public NoTrackNumberException(Exception e){
        super(e);
    }

    public NoTrackNumberException(){
    }
}
