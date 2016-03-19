package org.fbarros.mp3clinic.data.builder;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static org.fbarros.mp3clinic.data.builder.ReportingDataBuilder.ReportingData;

import java.util.ArrayList;
import java.util.List;

import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.ReportingData;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;

public class MessageBuilder {

	public static final ReportingData DEFAULT_REPORTING_DATA = make(a(ReportingData));
	public static final String DEFAULT_ALBUM = "Default_album";
	public static final List<String> DEFAULT_PATHS = new ArrayList<String>();
    
	public static final Property<Message,ReportingData> reportingData = new Property<Message,ReportingData>();
	public static final Property<Message,String> album = new Property<Message,String>();
	public static final Property<Message,List<String>> paths = new Property<Message,List<String>>();
	
	public static final Instantiator<Message> Message = new Instantiator<Message>() {
		public Message instantiate(PropertyLookup<Message> lookup) {
			Message message = new Message(lookup.valueOf(reportingData, DEFAULT_REPORTING_DATA));
			message.setAlbum(lookup.valueOf(album, DEFAULT_ALBUM));
			message.setPaths(lookup.valueOf(paths, DEFAULT_PATHS));
			return message;
		}
	};

}
