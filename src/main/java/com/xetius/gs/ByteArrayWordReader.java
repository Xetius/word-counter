package com.xetius.gs;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ByteArrayWordReader {

    public static final int DEFAULT_BUFFER_SIZE = 50;

    List<Character> readBuffer;
    InputStreamReader reader = null;

    public ByteArrayWordReader() {
        readBuffer = new ArrayList<>(DEFAULT_BUFFER_SIZE);
    }

    public boolean open(final String fileName) {
        final InputStream resourceAsStream = getClass().getResourceAsStream(fileName);

        if (resourceAsStream == null) {
            return false;
        }

        reader = new InputStreamReader(resourceAsStream);
        return true;
    }

    public List<Character> get() throws IOException {
        int nextValue;
        readBuffer.clear();

        while ((nextValue = reader.read()) != -1) {
            if (Character.isLetter(nextValue)) {
                readBuffer.add(Character.toLowerCase((char) nextValue));
            } else {
                break;
            }
        }
        return readBuffer;
    }

}
