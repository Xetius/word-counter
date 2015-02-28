package com.xetius.gs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class WordTreeStorageTest {

    private static final int NUMBER_OF_ADDITIONS = 10;
    private static final String TESTWORD = "testword";

    private WordTreeStorage storage;

    @Before
    public void setUp() throws Exception {
        storage = new WordTreeStorage();
    }

    @Test
    public void addWordCreatesInitialStateWithOneCount() throws Exception {
        List<Character> word = createWord(TESTWORD);
        int count = storage.add(word);
        assertThat(count, equalTo(1));
    }


    @Test
    public void addingSameWordIncrementsCounterForEachAddition() throws Exception {
        List<Character> word = createWord(TESTWORD);

        int count = 0;

        for (int attempt = 0; attempt < NUMBER_OF_ADDITIONS; attempt++) {
            count = storage.add(word);
        }

        assertThat(count, equalTo(NUMBER_OF_ADDITIONS));
    }

    @Test
    public void getWordCountsReturnsListOfAllWordCountsInTree() throws Exception {
        List<Character> word = createWord(TESTWORD);
        storage.add(word);
        storage.add(word);

        List<WordCountItem> occurenceItems = storage.getCounts();
        assertThat(occurenceItems, hasSize(1));

        WordCountItem item = occurenceItems.get(0);
        assertThat(item.getCharacters(), equalTo(word));
        assertThat(item.getCount(), equalTo(2));
    }

    @Test
    public void countItemContainsAllCharacterForThatWord() throws Exception {
        List<Character> word = createWord(TESTWORD);
        storage.add(word);
        List<WordCountItem> wordCountItems = storage.getCounts();
        WordCountItem item = wordCountItems.get(0);
        assertThat(item.getCharacters(), equalTo(word));
    }

    private List<Character> createWord(final String string) {
        List<Character> word = new ArrayList<>(string.length());
        char[] chars = string.toCharArray();

        for (char character : chars) {
            word.add(character);
        }

        return word;
    }
}