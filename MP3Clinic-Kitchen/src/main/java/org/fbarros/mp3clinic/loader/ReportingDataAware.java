package org.fbarros.mp3clinic.loader;

import org.fbarros.mp3clinic.data.ReportingData;

public interface ReportingDataAware {

	void setReportingData(ReportingData reportData);
	ReportingData getReportingData();
}
