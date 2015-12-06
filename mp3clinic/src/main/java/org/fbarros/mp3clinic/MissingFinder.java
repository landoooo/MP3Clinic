/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fbarros.mp3clinic;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fernando
 */
public class MissingFinder {

    //private static Logger logger = LoggerFactory.getLogger(MissingFinder.class);

    public static List<Message> processGrupo(Path grupo) {
        List<Message> messages = new ArrayList<>();
        try (DirectoryStream<Path> discos = Files.newDirectoryStream(grupo);) {
            for (Path disco : discos){
                messages.addAll(processAlbum(disco));
            }
        } catch (Exception e) {
      //      logger.error("Error processing band " + grupo.getFileName());
        }
        return messages;
    }

    public static List<Message> processAlbum(Path album) {

        List<Message> messages = new ArrayList<>();
        try {
            if (!areSongsConsecutive(album)) {
                messages.add(new Message("Songs from folder >>" + album.toString() + "<< are not consecutive"));
            }
        } catch (Exception e) {
        //    logger.error("Error processing album", e);
        }
        return messages;
    }

    public static boolean areSongsConsecutive(Path albumPath) {

        String[] extensions = new String[]{"mp3"};
        Collection<File> files = FileUtils.listFiles(albumPath.toFile(), extensions, false);
        int fileNumber = files.size();
        if (fileNumber > 0) {
            Boolean[] array = new Boolean[fileNumber];

            try {
                for (File song : files) {
                    Mp3File mp3file = new Mp3File(song);
                    int trackNumber = extractTrackNumber(mp3file);
                    if (trackNumber > fileNumber) {
                        return false;
                    }
                    array[trackNumber - 1] = true;
                }
            } catch (Exception e) {
          //      logger.error("Error checking consecutive songs on " + albumPath.toString(), e);
            }
            return Arrays.asList(array).stream().allMatch(e -> e);
        }
        return true;
    }

    private static int extractTrackNumber(Mp3File mp3file) throws NoId3TagFoundException, NumberFormatException, NoTrackNumberException {
        if (mp3file.hasId3v2Tag()) {
            ID3v2 tag = mp3file.getId3v2Tag();
            return Integer.parseInt(Id3TagTools.getTrackNumber(tag.getTrack()));
        } else if (mp3file.hasId3v1Tag()) {
            ID3v1 tag = mp3file.getId3v1Tag();
            return Integer.parseInt(Id3TagTools.getTrackNumber(tag.getTrack()));
        } else {
            //logger.info("Song " + mp3file.getFilename() + " doesnt have Id3 tags");
            throw new NoId3TagFoundException();
        }
    }

    public static List<Message> processLibrary(String libraryPath) {
        List<Message> messages = new ArrayList<>();
        try (final DirectoryStream<Path> grupos = Files.newDirectoryStream(Paths.get(libraryPath))) {
            for (Path grupo : grupos) {
                messages.addAll(MissingFinder.processGrupo(grupo));
            }
        } catch (IOException e) {
        }
        return(messages);
    }

}
