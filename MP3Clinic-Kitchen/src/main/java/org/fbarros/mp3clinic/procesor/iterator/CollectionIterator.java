package org.fbarros.mp3clinic.procesor.iterator;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.Album;

public class CollectionIterator {

	public static List<Message> apply(Collection<Album> albums, Predicate<Album> predicate, Function<Album, Message> messageCreator) {
		List<Album> selectedAlbums = albums.stream()
				.filter(predicate)
				.collect(toList());
		List<Message> messages = selectedAlbums.stream()
				.map(messageCreator)
				.collect(toList());
		return messages;
	}
}
