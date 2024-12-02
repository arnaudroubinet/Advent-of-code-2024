package ar.ro.day1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Stack;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Binary tree with duplication counter
 */
public class BinaryTree {

    private Node root;

    /**
     * No. of hits for parameter value
     * @param value the searched value
     * @return the number of hits
     */
    public int nbOfOccurrences(long value) {
        Node current = root;
        while (current != null) {
            if (value == current.value) {
                return current.count;
            } else if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return 0;
    }

    /**
     * Create a node for this value or increment the node counter if it already exists
     * @param value the value to add in this tree
     */
    public void add(long value) {
        root = addRecursive(root, value);
    }

    /**
     * Search the node to increment or create it if missing.
     * Go to left node if the value is lower than current node value, to the right if higher or increment the node if equals.
     * @param node the current node we check
     * @param value the value to add
     * @return the updated or created current node
     */
    private Node addRecursive(Node node, long value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = addRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = addRecursive(node.right, value);
        } else {
            node.count++;
        }

        return node;
    }

    /**
     * To stream
     * @return an ordered flow of tree numbers with a number of occurrences equal to the node counter
     */
    public Stream<Long> toStream() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED), false);
    }

    /**
     * Iterator
     * @return an iterator of the numbers making up this tree, with a number of occurrences equal to the node counter, sorted in ascending order
     */
    public Iterator<Long> iterator() {
        return new Iterator<>() {
            private final Stack<Node> stack = new Stack<>();
            private Node current = root;
            private int currentCount = 0;

            {
                pushLeft(current);
            }

            private void pushLeft(Node node) {
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty() || currentCount > 0;
            }

            @Override
            public Long next() {
                while (currentCount == 0 && !stack.isEmpty()) {
                    current = stack.pop();
                    pushLeft(current.right);
                    currentCount = current.count;
                }

                if (currentCount > 0) {
                    currentCount--;
                    return current.value;
                }

                throw new NoSuchElementException();
            }
        };
    }

    static class Node {
        final long value;
        int count;
        Node left;
        Node right;

        Node(long value) {
            this.value = value;
            this.count = 1;
        }
    }
}
