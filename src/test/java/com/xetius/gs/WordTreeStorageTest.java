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
    private static final String FIRST_WORD = "first";
    private static final String SECOND_WORD = "second";


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
        final List<Character> word = createWord(TESTWORD);

        int count = 0;

        for (int attempt = 0; attempt < NUMBER_OF_ADDITIONS; attempt++) {
            count = storage.add(word);
        }

        assertThat(count, equalTo(NUMBER_OF_ADDITIONS));
    }

    @Test
    public void getWordCountsReturnsListOfAllWordCountsInTree() throws Exception {
        final List<Character> word = createWord(TESTWORD);
        storage.add(word);
        storage.add(word);

        final List<WordCountItem> occurenceItems = storage.getCounts();
        assertThat(occurenceItems, hasSize(1));

        final WordCountItem item = occurenceItems.get(0);
        assertThat(item.getCharacters(), equalTo(word));
        assertThat(item.getCount(), equalTo(2));
    }

    @Test
    public void countItemContainsAllCharacterForThatWord() throws Exception {
        final List<Character> word = createWord(TESTWORD);
        storage.add(word);
        final List<WordCountItem> wordCountItems = storage.getCounts();
        final WordCountItem item = wordCountItems.get(0);
        assertThat(item.getCharacters(), equalTo(word));
    }

    @Test
    public void displaysTheCountAndPathFormattedCorrectly() throws Exception {
        final String expectedString = "   2 testword";
        final List<Character> word = createWord(TESTWORD);
        storage.add(word);
        storage.add(word);

        final List<WordCountItem> counts = storage.getCounts();
        assertThat(expectedString, equalTo(counts.get(0).toString()));
    }

    @Test
    public void comparibleReturnsDifferenceInCounts() throws Exception {
        final List<Character> firstWord = createWord(FIRST_WORD);
        final List<Character> secondWord = createWord(SECOND_WORD);

        storage.add(secondWord);
        storage.add(secondWord);
        storage.add(secondWord);
        storage.add(secondWord);
        storage.add(secondWord);

        storage.add(firstWord);
        storage.add(firstWord);
        storage.add(firstWord);

        final List<WordCountItem> counts = storage.getCounts();
        assertThat(counts.get(0).compareTo(counts.get(1)), equalTo(5-3));
    }

    private List<Character> createWord(final String string) {
        final List<Character> word = new ArrayList<>(string.length());
        final char[] chars = string.toCharArray();

        for (char character : chars) {
            word.add(character);
        }

        return word;
    }


}