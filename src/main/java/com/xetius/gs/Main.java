package com.xetius.gs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        final CharacterListWordReader reader = new CharacterListWordReader();
        final WordTreeStorage storage = new WordTreeStorage();

        if (reader.open(new FileInputStream(args[0]))) {

            List<Character> word;
            while (!reader.isEndOfFile()) {
                word = reader.get();
                if (word.size() > 0) {
                    storage.add(word);
                }
            }

            final List<WordCountItem> counts = storage.getCounts();
            Collections.sort(counts);

            for (WordCountItem item : counts.subList(0, 20)) {
                System.out.println(item);
            }

        } else {
            System.out.println("Bugger");
        }
    }
}
