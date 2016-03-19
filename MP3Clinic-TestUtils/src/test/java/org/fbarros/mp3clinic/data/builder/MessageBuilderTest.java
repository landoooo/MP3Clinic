package org.fbarros.mp3clinic.data.builder;
import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fbarros.mp3clinic.data.builder.MessageBuilder.DEFAULT_ALBUM;
import static org.fbarros.mp3clinic.data.builder.MessageBuilder.DEFAULT_PATHS;
import static org.fbarros.mp3clinic.data.builder.MessageBuilder.DEFAULT_REPORTING_DATA;
import static org.fbarros.mp3clinic.data.builder.MessageBuilder.Message;
import static org.fbarros.mp3clinic.data.builder.MessageBuilder.album;
import static org.fbarros.mp3clinic.data.builder.MessageBuilder.paths;
import static org.fbarros.mp3clinic.data.builder.MessageBuilder.reportingData;
import static org.fbarros.mp3clinic.data.builder.ReportingDataBuilder.ReportingData;
import static org.fbarros.mp3clinic.data.builder.ReportingDataBuilder.messageKey;

import java.util.Arrays;
import java.util.List;

import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.ReportingData;
import org.junit.Test;

public class MessageBuilderTest extends BaseTestCase {

	@SuppressWarnings("unchecked")
	private static final ReportingData CUSTOM_REPORTING_DATA = 
			make(a(ReportingData, with(messageKey, "customMessageKey")));
	private static final List<String> CUSTOM_PATHS = Arrays.asList("/a/simple/path");
	private static final String CUSTOM_ALBUM = "custom album";

	@Test
	@SuppressWarnings("unchecked")
	public void defaultCreationTest() {
		Message message = make(a(Message));
		assertThat(message.getAlbum()).isEqualTo(DEFAULT_ALBUM);
		assertThat(message.getPaths()).isEqualTo(DEFAULT_PATHS);
		assertThat(message.getReportingData()).isEqualTo(DEFAULT_REPORTING_DATA);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void customCreationTest() {
		Message message = make(a(Message, with(paths, CUSTOM_PATHS), with(album, CUSTOM_ALBUM),
				with(reportingData, CUSTOM_REPORTING_DATA)));
		assertThat(message.getAlbum()).isEqualTo(CUSTOM_ALBUM);
		assertThat(message.getPaths()).isEqualTo(CUSTOM_PATHS);
		assertThat(message.getReportingData()).isEqualTo(CUSTOM_REPORTING_DATA);
	}
}
