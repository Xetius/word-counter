package com.xetius.gs;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CharacterListWordReader {

    public static final int DEFAULT_BUFFER_SIZE = 50;
    List<Character> readBuffer;
    InputStreamReader reader = null;
    private boolean endOfFile = false;

    public CharacterListWordReader() {
        readBuffer = new ArrayList<>(DEFAULT_BUFFER_SIZE);
    }

    public boolean open(final InputStream stream) {

        if (stream == null) {
            return false;
        }

        reader = new InputStreamReader(stream);
        return true;
    }

    public List<Character> get() throws IOException {
        int nextValue;
        readBuffer.clear();

        while ((nextValue = reader.read()) != -1) {
            if (Character.isLetter(nextValue)) {
                readBuffer.add(Character.toLowerCase((char) nextValue));
            } else {
                return readBuffer;
            }
        }

        endOfFile = true;
        return readBuffer;
    }

    public boolean isEndOfFile() {
        return endOfFile;
    }
}
