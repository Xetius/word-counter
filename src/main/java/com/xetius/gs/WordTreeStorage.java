package com.xetius.gs;

import java.util.ArrayList;
import java.util.List;

public class WordTreeStorage {

    private WordTreeNode root;

    public WordTreeStorage() {
        root = new WordTreeNode();
    }

    public int add(List<Character> word) {
        WordTreeNode currentWordTreeNode = root;

        for (Character character : word) {
            currentWordTreeNode = currentWordTreeNode.nextNode(character);
        }

        return currentWordTreeNode.incrementCount();
    }

    public List<WordCountItem> getCounts() {
        List<WordCountItem> details = new ArrayList<>();
        List<Character> path = new ArrayList<>();
        internalGetData(details, root, path);
        return details;
    }

    private void internalGetData(final List<WordCountItem> details, final WordTreeNode currentNode,
                                 final List<Character> path) {
        if (currentNode.getCount() > 0) {
            details.add(new WordCountItem(currentNode, path));
        }

        for (WordTreeNode node : currentNode.getAllNextNodes()) {
            List<Character> newPath = new ArrayList<>(path);
            newPath.add(node.getCharacter());
            internalGetData(details, node, newPath);
        }
    }
}
