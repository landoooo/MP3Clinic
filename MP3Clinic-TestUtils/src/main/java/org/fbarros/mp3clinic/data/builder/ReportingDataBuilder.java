package org.fbarros.mp3clinic.data.builder;

import org.fbarros.mp3clinic.Category;
import org.fbarros.mp3clinic.Priority;
import org.fbarros.mp3clinic.data.ReportingData;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;

public class ReportingDataBuilder {

	public static final Category DEFAULT_CATEGORY = Category.WRONG_INFORMATION;
	public static final Priority DEFAULT_PRIORITY = Priority.LOW;
	public static final String DEFAULT_MESSAGE_KEY = "message.key";

	public static final Property<ReportingData,Category> category = new Property<ReportingData,Category>();
	public static final Property<ReportingData,Priority> priority = new Property<ReportingData,Priority>();
	public static final Property<ReportingData,String> messageKey = new Property<ReportingData,String>();

	public static final Instantiator<ReportingData> ReportingData = new Instantiator<ReportingData>() {
		public ReportingData instantiate(PropertyLookup<ReportingData> lookup) {
			ReportingData reportingData = new ReportingData(
					lookup.valueOf(priority, DEFAULT_PRIORITY), 
					lookup.valueOf(category, DEFAULT_CATEGORY), 
					lookup.valueOf(messageKey, DEFAULT_MESSAGE_KEY));
			return reportingData;
		}
	};
}
