package org.fbarros.mp3clinic.report;

import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.procesor.Reporter;

public class ReporterFactory {

	public static Reporter getReporter(ReportingData data){
		return new Reporter(data);
	}
}
