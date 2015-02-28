package com.xetius.gs;

import java.util.List;

public class WordCountItem {

    List<Character> path;
    int count = 0;

    public WordCountItem(final int count, final List<Character> path) {
        this.path = path;
        this.count = count;
    }

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


}
