package com.xetius.gs;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

public class ByteArrayWordReaderTest {

    public static final String FIRST_WORD = "once";
    private static final String EXISTING_FILE = "/mobydick.txt";
    private static final String MISSING_FILE = "/nothere.txt";
    private static final String SHORT_FILE = "/short.txt";
    private ByteArrayWordReader reader;

    @Before
    public void setUp() throws Exception {
        reader = new ByteArrayWordReader();
    }

    @Test
    public void openingFileReturnsTrue() throws Exception {
        assertThat(reader.open(EXISTING_FILE), equalTo(true));
    }

    @Test
    public void openingMissingFileReturnsFalse() throws Exception {
        assertThat(reader.open(MISSING_FILE), equalTo(false));
    }

    @Test
    public void getReturnsWord() throws Exception {
        reader.open(SHORT_FILE);
        List<Character> word = reader.get();
        assertThat(word, hasSize(4));
    }

    @Test
    public void getConvertsToLowercase() throws Exception {
        reader.open(SHORT_FILE);
        String word = convertToString(reader.get());
        assertThat(word, equalTo(FIRST_WORD));
    }

    @Test
    public void multipleCallsReturnSubsequentWords() throws Exception {
        reader.open(SHORT_FILE);
        String firstWord = convertToString(reader.get());
        String secondWord = convertToString(reader.get());

        assertThat(firstWord, not(equalTo(secondWord)));
    }

    private String convertToString(final List<Character> array) {
        StringBuilder builder = new StringBuilder();

        for (Character character : array) {
            builder.append(character);
        }
        return builder.toString();
    }
}