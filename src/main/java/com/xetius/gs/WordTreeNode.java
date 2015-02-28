package com.xetius.gs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class WordTreeNode {

    private Character character;

    private int count;

    private Map<Character, WordTreeNode> nextWordTreeNodes;

    public WordTreeNode() {
        this(null);
    }

    public WordTreeNode(Character character) {
        this.character = character;
        count = 0;
        nextWordTreeNodes = new HashMap<>(26);
    }

    public WordTreeNode nextNode(Character character) {
        if (nextWordTreeNodes.containsKey(character)) {
            return nextWordTreeNodes.get(character);
        }

        final WordTreeNode wordTreeNode = new WordTreeNode(character);
        nextWordTreeNodes.put(character, wordTreeNode);
        return wordTreeNode;
    }

    public int incrementCount() {
        return ++count;
    }

    public int getCount() {
        return count;
    }

    public Collection<WordTreeNode> getAllNextNodes() {
        return nextWordTreeNodes.values();
    }

    public Character getCharacter() {
        return character;
    }
}
