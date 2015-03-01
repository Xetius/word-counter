package com.xetius.gs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.min;

public class Main {

    public static void main(String[] args) throws IOException {
        final CharacterListWordReader reader = new CharacterListWordReader();
        final WordTreeStorage storage = new WordTreeStorage();
        final InputStream stream = getStreamFromFile(args[0]);
        if (reader.open(stream)) {

            List<Character> word;
            while (!reader.isEndOfFile()) {
                word = reader.get();
                if (word.size() > 0) {
                    storage.add(word);
                }
            }

            final List<WordCountItem> counts = storage.getCounts();
            Collections.sort(counts);

            for (WordCountItem item : counts.subList(0, min(20, counts.size()))) {
                System.out.println(item);
            }

        } else {
            System.out.println("Unable to open file " + args[0]);
        }
    }

    private static InputStream getStreamFromFile(String fileName) {
        try {
            return new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
