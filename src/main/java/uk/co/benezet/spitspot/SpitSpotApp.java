package uk.co.benezet.spitspot;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SpitSpotApp {

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new RuntimeException("Usage: \n" +
                "DictionaryFile - the file name of a text file containing four letter words (included in the test pack)\n" +
                "StartWord - a four letter word (that you can assume is found in the DictionaryFile file)\n" +
                "EndWord - a four letter word (that you can assume is found in the DictionaryFile file)\n" +
                "ResultFile - the file name of a text file that will contain the result ");
        }

        String dictionaryFile = args[0];
        String startWord = args[1];
        String endWord = args[2];
        String resultFile = args[3];

        List<String> entries = Functions.entries.apply(dictionaryFile);

        Functions.verify(startWord, endWord, entries);

        write(Functions.squash(startWord, endWord, entries), resultFile);
    }

    private static void write(List<String> squashed, String resultFile) throws IOException {
        FileUtils.write(new File(resultFile), squashed.stream().collect(Collectors.joining("\n")));
    }
}
