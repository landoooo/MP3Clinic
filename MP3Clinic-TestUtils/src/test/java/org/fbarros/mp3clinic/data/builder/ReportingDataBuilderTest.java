package org.fbarros.mp3clinic.data.builder;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fbarros.mp3clinic.data.builder.ReportingDataBuilder.DEFAULT_CATEGORY;
import static org.fbarros.mp3clinic.data.builder.ReportingDataBuilder.DEFAULT_MESSAGE_KEY;
import static org.fbarros.mp3clinic.data.builder.ReportingDataBuilder.DEFAULT_PRIORITY;
import static org.fbarros.mp3clinic.data.builder.ReportingDataBuilder.ReportingData;
import static org.fbarros.mp3clinic.data.builder.ReportingDataBuilder.category;
import static org.fbarros.mp3clinic.data.builder.ReportingDataBuilder.messageKey;
import static org.fbarros.mp3clinic.data.builder.ReportingDataBuilder.priority;

import org.fbarros.mp3clinic.Category;
import org.fbarros.mp3clinic.Priority;
import org.fbarros.mp3clinic.data.ReportingData;
import org.junit.Test;

public class ReportingDataBuilderTest extends BaseTestCase {

	private static final Category CUSTOM_CATEGORY = Category.LOADING;
	private static final Priority CUSTOM_PRIORITY = Priority.HIGH;
	private static final String CUSTOM_MESSAGE_KEY = "custom.key";

	@Test
	@SuppressWarnings("unchecked")
	public void defaultCreationTest() {
		ReportingData reportingData = make(a(ReportingData));
		assertThat(reportingData.getPriority()).isEqualTo(DEFAULT_PRIORITY);
		assertThat(reportingData.getCategory()).isEqualTo(DEFAULT_CATEGORY);
		assertThat(reportingData.getMessageKey()).isEqualTo(DEFAULT_MESSAGE_KEY);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void customCreationTest() {
		ReportingData reportingData = make(a(ReportingData, with(priority, CUSTOM_PRIORITY), with(category, CUSTOM_CATEGORY),
				with(messageKey, CUSTOM_MESSAGE_KEY)));
		assertThat(reportingData.getPriority()).isEqualTo(CUSTOM_PRIORITY);
		assertThat(reportingData.getCategory()).isEqualTo(CUSTOM_CATEGORY);
		assertThat(reportingData.getMessageKey()).isEqualTo(CUSTOM_MESSAGE_KEY);
	}

}
