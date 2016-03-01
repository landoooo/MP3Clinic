/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fbarros.mp3clinic;

import org.fbarros.mp3clinic.exceptions.ProcessingException;

/**
 *
 * @author fernando
 */
public class Mp3ClinicLauncher {

    private Mp3ClinicLauncher(){}
    
    public static void main (String[] args){
        try {
            System.out.println(MissingFinder.processLibrary(args[0]));
        } catch (ProcessingException e){
            System.err.println(e);
        }
    }
}
