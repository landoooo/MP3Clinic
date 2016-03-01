/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fbarros.mp3clinic.exceptions;

import java.nio.file.Path;

/**
 *
 * @author fernando
 */
public class ProcessingException extends Exception {
        
    public ProcessingException(){}
    
    public ProcessingException(Exception e){
        super(e);
    }
}
