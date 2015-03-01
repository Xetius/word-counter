package com.xetius.gs;

import java.util.List;

public class WordCountItem implements Comparable<WordCountItem> {

    List<Character> path;
    int count = 0;

    public WordCountItem(final WordTreeNode currentNode, final List<Character> path) {
        this.path = path;
        this.count = currentNode.getCount();
    }

    public int getCount() {
        return count;
    }

    public List<Character> getCharacters() {
        return path;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(WordCountItem other) {
        return other.getCount() - this.getCount();
    }

    @Override
    public String toString() {
        return String.format("%4s %s", count, pathToString());
    }

    private String pathToString() {
        final StringBuilder builder = new StringBuilder();

        for (Character character : path) {
            builder.append(character);
        }

        return builder.toString();
    }
}
