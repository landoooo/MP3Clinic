/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fbarros.mp3clinic;

import org.fbarros.mp3clinic.exceptions.NoTrackNumberException;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author fernando
 */
public class Id3TagTools {

    private Id3TagTools(){}
    
    public static String getTrackNumber (String trackNumbers) throws NoTrackNumberException {
        if (StringUtils.isEmpty(trackNumbers)) {
            throw new NoTrackNumberException();
        } else if (trackNumbers.indexOf(Constants.TRACK_NUMBERS_SEPARATOR) == -1){
            return trackNumbers;
        } else {
            return trackNumbers.substring(0, trackNumbers.indexOf(Constants.TRACK_NUMBERS_SEPARATOR));
        }    
    }
    
}
