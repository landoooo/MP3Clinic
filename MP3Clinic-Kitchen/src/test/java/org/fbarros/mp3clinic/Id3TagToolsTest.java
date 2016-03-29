/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fbarros.mp3clinic;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.*;

import org.fbarros.mp3clinic.exceptions.NoTrackNumberException;
import org.junit.Test;

/**
 *
 * @author fernando
 */
public class Id3TagToolsTest {
    
    public Id3TagToolsTest() {
    }

    /**
     * Test of getTrackNumber method, of class Id3TagTools.
     */
    @Test
    public void testGetTrackNumber() {
        System.out.println("getTrackNumber");
        String trackNumbers = "01/17";
        String expResult = "01";
        String result = "";
        try { 
            result = Id3TagTools.getTrackNumber(trackNumbers);
        } catch (NoTrackNumberException ntne){
        
        }
        assertEquals(expResult, result);
    }

    
    @Test
    public void testGetTrackNumberWithoutTotal() {
        System.out.println("getTrackNumber");
        String trackNumbers = "01";
        String expResult = "01";
        String result = "";
        try { 
            result = Id3TagTools.getTrackNumber(trackNumbers);
        } catch (NoTrackNumberException ntne){
        
        }
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetTrackNumberFromEmpty(){
        System.out.println("getTrackNumber");
        String trackNumbers = "";
        assertThatExceptionOfType(NoTrackNumberException.class).isThrownBy(() -> { Id3TagTools.getTrackNumber(trackNumbers); });
    }

    @Test
    public void testGetTrackNumber3Numbers() {
        System.out.println("getTrackNumber");
        String trackNumbers = "101";
        String expResult = "101";
        String result = "";
        try { 
            result = Id3TagTools.getTrackNumber(trackNumbers);
        } catch (NoTrackNumberException ntne){
        
        }
        assertEquals(expResult, result);
    }
    

}
