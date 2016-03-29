package org.fbarros.mp3clinic.procesor;

import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.Album;

public interface IMessageCreator {

	public Message createMessage(Album album);
}
