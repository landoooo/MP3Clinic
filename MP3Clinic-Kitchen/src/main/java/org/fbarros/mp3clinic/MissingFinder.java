/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fbarros.mp3clinic;

import org.fbarros.mp3clinic.exceptions.NoId3TagFoundException;
import org.fbarros.mp3clinic.exceptions.NoTrackNumberException;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
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
import org.fbarros.mp3clinic.exceptions.ProcessingException;

/**
 *
 * @author fernando
 */
public class MissingFinder {

    private MissingFinder(){}
    
    public static List<Message> processGrupo(Path grupo) throws ProcessingException {
        List<Message> messages = new ArrayList<>();
        try (DirectoryStream<Path> discos = Files.newDirectoryStream(grupo);) {
            for (Path disco : discos){
                messages.addAll(processAlbum(disco));
            }
        } catch (IOException e) {
            throw new ProcessingException(e);
        }
        return messages;
    }

    public static List<Message> processAlbum(Path album) throws ProcessingException {

        List<Message> messages = new ArrayList<>();
        try {
            if (!areSongsConsecutive(album)) {
                messages.add(new Message("Songs from folder >>" + album.toString() + "<< are not consecutive"));
            }
        } catch (Exception e) {
            throw new ProcessingException(e);
        }
        return messages;
    }

    public static boolean areSongsConsecutive(Path albumPath) throws ProcessingException {

        String[] extensions = new String[]{"mp3"};
        Collection<File> files = FileUtils.listFiles(albumPath.toFile(), extensions, false);
        int fileNumber = files.size();
        if (fileNumber > 0) {
            return checkConsecutiveness(fileNumber, files);
        }
        return true;
    }

    private static boolean checkConsecutiveness(int fileNumber, Collection<File> files) throws NumberFormatException, ProcessingException {
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
        } catch (IOException|InvalidDataException|NoId3TagFoundException|NoTrackNumberException|UnsupportedTagException e) {
            throw new ProcessingException(e);
        }
        return Arrays.asList(array).stream().allMatch(e -> e);
    }

    private static int extractTrackNumber(Mp3File mp3file) throws NoId3TagFoundException, NumberFormatException, NoTrackNumberException {
        if (mp3file.hasId3v2Tag()) {
            ID3v2 tag = mp3file.getId3v2Tag();
            return Integer.parseInt(Id3TagTools.getTrackNumber(tag.getTrack()));
        } else if (mp3file.hasId3v1Tag()) {
            ID3v1 tag = mp3file.getId3v1Tag();
            return Integer.parseInt(Id3TagTools.getTrackNumber(tag.getTrack()));
        } else {
            throw new NoId3TagFoundException();
        }
    }

    public static List<Message> processLibrary(String libraryPath) throws ProcessingException{
        List<Message> messages = new ArrayList<>();
        try (final DirectoryStream<Path> grupos = Files.newDirectoryStream(Paths.get(libraryPath))) {
            for (Path grupo : grupos) {
                try {
                    messages.addAll(MissingFinder.processGrupo(grupo));
                } catch (ProcessingException pe){
                    messages.add(new Message("Exception processing " + grupo));
                }
            }
        } catch (IOException e) {
            throw new ProcessingException(e);
        }
        return messages;
    }

}
