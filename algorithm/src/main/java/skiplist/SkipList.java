package skiplist;

import java.util.Random;

public class SkipList<T extends Comparable<T>> {
    private static final int MAX_LEVEL = 32; // Maximum level for the skip list
    private Node<T> head = new Node<>(MAX_LEVEL, null); // Head node
    private int level = 1; // Current level of the skip list
    private Random rand = new Random();

    public boolean contains(T value) {
        Node<T> node = head;
        for (int i = level - 1; i >= 0; i--) {
            while (node.forward[i] != null && node.forward[i].value.compareTo(value) < 0) {
                node = node.forward[i];
            }
        }
        node = node.forward[0];
        return node != null && node.value.equals(value);
    }

    public void insert(T value) {
        Node<T> node = head;
        Node<T>[] update = new Node[MAX_LEVEL];
        for (int i = level - 1; i >= 0; i--) {
            while (node.forward[i] != null && node.forward[i].value.compareTo(value) < 0) {
                node = node.forward[i];
            }
            update[i] = node;
        }
        node = node.forward[0];

        if (node == null || !node.value.equals(value)) {
            int newLevel = randomLevel();
            if (newLevel > level) {
                for (int i = level; i < newLevel; i++) {
                    update[i] = head;
                }
                level = newLevel;
            }

            node = new Node<>(newLevel, value);
            for (int i = 0; i < newLevel; i++) {
                node.forward[i] = update[i].forward[i];
                update[i].forward[i] = node;
            }
        }
    }

    public void delete(T value) {
        Node<T> node = head;
        Node<T>[] update = new Node[MAX_LEVEL];
        for (int i = level - 1; i >= 0; i--) {
            while (node.forward[i] != null && node.forward[i].value.compareTo(value) < 0) {
                node = node.forward[i];
            }
            update[i] = node;
        }
        node = node.forward[0];

        if (node != null && node.value.equals(value)) {
            for (int i = 0; i < level; i++) {
                if (update[i].forward[i] != node) {
                    break;
                }
                update[i].forward[i] = node.forward[i];
            }
            while (level > 1 && head.forward[level - 1] == null) {
                level--;
            }
        }
    }

    private int randomLevel() {
        int level = 1;
        while (rand.nextInt() % 2 == 0 && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    private static class Node<T> {
        T value;
        Node<T>[] forward;

        Node(int level, T value) {
            this.value = value;
            forward = new Node[level];
        }
    }

    // Other methods and utilities can be added as needed
}
