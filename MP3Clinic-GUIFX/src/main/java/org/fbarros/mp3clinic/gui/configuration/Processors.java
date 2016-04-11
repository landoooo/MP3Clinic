package org.fbarros.mp3clinic.gui.configuration;

import java.util.ArrayList;
import java.util.List;

import org.fbarros.mp3clinic.procesor.IProcesor;

/**
 * list of available processors to scan mp3 collections
 * @author D-UK24FA
 *
 */
public class Processors {

	private List<IProcesor> availableProcessors = new ArrayList<>();

	public Processors(List<IProcesor> availableProcesors){
		this.availableProcessors = availableProcesors;
	}
	
	////////////////////////// GETTERS / SETTERS ////////////////////////////////////
	
	public List<IProcesor> getAvailableProcessors() {
		return availableProcessors;
	}

	public void setAvailableProcessors(List<IProcesor> availableProcessors) {
		this.availableProcessors = availableProcessors;
	}
	
	
	
}
