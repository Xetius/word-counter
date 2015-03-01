package com.xetius.gs;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

public class CharacterListWordReaderTest {

    public static final String FIRST_WORD = "once";
    private static final String EXISTING_FILE = "/mobydick.txt";
    private static final String MISSING_FILE = "/nothere.txt";
    private static final String SHORT_FILE = "/short.txt";
    private CharacterListWordReader reader;

    @Before
    public void setUp() throws Exception {
        reader = new CharacterListWordReader();
    }

    @Test
    public void openingFileReturnsTrue() throws Exception {
        final InputStream stream = this.getClass().getResourceAsStream(EXISTING_FILE);
        assertThat(reader.open(stream), equalTo(true));
    }

    @Test
    public void openingMissingFileReturnsFalse() throws Exception {
        final InputStream stream = this.getClass().getResourceAsStream(MISSING_FILE);
        assertThat(reader.open(stream), equalTo(false));
    }

    @Test
    public void getReturnsWord() throws Exception {
        final InputStream stream = this.getClass().getResourceAsStream(SHORT_FILE);
        reader.open(stream);
        List<Character> word = reader.get();
        assertThat(word, hasSize(4));
    }

    @Test
    public void getConvertsToLowercase() throws Exception {
        final InputStream stream = this.getClass().getResourceAsStream(SHORT_FILE);
        reader.open(stream);
        String word = convertToString(reader.get());
        assertThat(word, equalTo(FIRST_WORD));
    }

    @Test
    public void multipleCallsReturnSubsequentWords() throws Exception {
        final InputStream stream = this.getClass().getResourceAsStream(SHORT_FILE);
        reader.open(stream);
        String firstWord = convertToString(reader.get());
        String secondWord = convertToString(reader.get());

        assertThat(firstWord, not(equalTo(secondWord)));
    }

    @Test
    public void endOfFileOnlySetAtEndOfFile() throws Exception {
        final InputStream stream = this.getClass().getResourceAsStream(SHORT_FILE);
        reader.open(stream);

        for (int index = 0; index < 13; index++) {
            reader.get();
            assertThat(reader.isEndOfFile(), equalTo(false));
        }

        reader.get();
        assertThat(reader.isEndOfFile(), equalTo(true));
    }

    private String convertToString(final List<Character> array) {
        StringBuilder builder = new StringBuilder();

        for (Character character : array) {
            builder.append(character);
        }
        return builder.toString();
    }
}