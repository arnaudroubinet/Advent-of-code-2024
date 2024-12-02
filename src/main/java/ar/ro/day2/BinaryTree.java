package ar.ro.day2;

import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Binary tree with duplication counter
 */
public class BinaryTree {

    private Node root;
    private final String line;
    private static final Pattern PATTERN = Pattern.compile("(\\d+)+");

    public BinaryTree(String line) {
        this(line,null);
    }

    public BinaryTree(String line, Ignore ignore) {
        this.line = line;
        Matcher matcher = PATTERN.matcher(line);

        StringBuilder filteredLine = new StringBuilder();
        while (matcher.find()) {
            Long value = Long.parseLong(matcher.group(1));
            if(ignore != null && value.equals(ignore.value)){
                if(ignore.first){
                    ignore.first = false;
                    this.add(value);
                }else {
                    ignore.value = null;
                }
            }else {
                this.add(value);
                filteredLine.append(value).append(" ");
            }
        }

        System.out.println("BinaryTree build for : " + filteredLine);
    }


    private static class Ignore{
        Long value;
        boolean first;

        private Ignore(Long value, boolean first) {
            this.value = value;
            this.first = first;
        }

        public static Ignore of(Long value, boolean first){
            return new Ignore(value,first);
        }
    }
    /**
     * No. of hits for parameter value
     *
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
     *
     * @param value the value to add in this tree
     */
    public void add(long value) {
        root = addRecursive(root, value);
    }

    public boolean isSafe() {
        return root == null || (onlyDecreaseAndIsSafe(root, null) ^ onlyIncreaseAndIsSafe(root, null));
    }

    public boolean onlyDecreaseAndIsSafe(Node current, Node previous) {
        if (current == null) {
            return true;
        }
        if (current.count > 1) {
            return false;
        }
        if (current.right != null) {
            return false;
        }
        if (previous != null) {
            long distance = previous.value - current.value;
            if (distance < 1 || distance > 3) {
                return false;
            }
        }
        return onlyDecreaseAndIsSafe(current.left, current);
    }

    public boolean onlyIncreaseAndIsSafe(Node current, Node previous) {
        if (current == null) {
            return true;
        }
        if (current.count > 1) {
            return false;
        }
        if (current.left != null) {
            return false;
        }
        if (previous != null) {
            long distance = current.value - previous.value;
            if (distance < 1 || distance > 3) {
                return false;
            }
        }
        return onlyIncreaseAndIsSafe(current.right, current);
    }

    public boolean isSafeWithTolerance() {
        if (root == null) return true;

        boolean incrementInError = false;
        boolean decrementInError = false;

        Node nodeToRemove1 = onlyDecreaseAndIsSafeWithTolerance(root, null);

        if (nodeToRemove1 != null && nodeToRemove1.count <= 2) {
            System.out.println("Error with decrease, rebuild tree without " + nodeToRemove1.value);

            if(nodeToRemove1.count == 2) {
                //Cas en ignorant la première occurrence
                Node newRoot = new BinaryTree(line, Ignore.of(nodeToRemove1.value,true)).root;
                nodeToRemove1 = onlyDecreaseAndIsSafeWithTolerance(newRoot, null);
            }

            if(nodeToRemove1 != null) {
                Node newRoot = new BinaryTree(line, Ignore.of(nodeToRemove1.value, false)).root;
                nodeToRemove1 = onlyDecreaseAndIsSafeWithTolerance(newRoot, null);
            }
        }

        if (nodeToRemove1 != null) {
            incrementInError = true;
            System.out.println("Error with decrease, line is refused");
        }

        Node nodeToRemove2 = onlyIncreaseAndIsSafeWithTolerance(root, null);
        if (nodeToRemove2 != null && nodeToRemove2.count <= 2) {
            if(nodeToRemove2.count == 2) {
                //Cas en ignorant la première occurrence
                Node newRoot = new BinaryTree(line, Ignore.of(nodeToRemove2.value,true)).root;
                nodeToRemove2 = onlyIncreaseAndIsSafeWithTolerance(newRoot, null);
            }

            if(nodeToRemove2 != null) {
                Node newRoot = new BinaryTree(line, Ignore.of(nodeToRemove2.value, false)).root;
                nodeToRemove2 = onlyIncreaseAndIsSafeWithTolerance(newRoot, null);
            }
        }

        if (nodeToRemove2 != null) {
            decrementInError = true;
            System.out.println("Error with increase, line is refused");
        }

        boolean isSafe = incrementInError ^ decrementInError;

        if(isSafe){
            System.out.println(line + " is safe");
        }else{
            System.out.println(line + " is not safe");
        }

        return isSafe;

    }

    public Node onlyDecreaseAndIsSafeWithTolerance(Node current, Node previous) {
        if (current == null) {
            return null;
        }
        if (current.right != null) {
            System.out.println("Increment in decrease only flow : ko");
            return current.right;
        }
        if (previous != null) {
            long distance = previous.value - current.value;
            if (distance < 1 || distance > 3) {
                System.out.println("Distance = "+distance+" between "+previous.value+" and "+current.value+" : ko");
                return current;
            }
        }
        if (current.count > 1) {
            System.out.println("Counter > 1 : ko");
            return current;
        }
        return onlyDecreaseAndIsSafeWithTolerance(current.left, current);
    }

    public Node onlyIncreaseAndIsSafeWithTolerance(Node current, Node previous) {
        if (current == null) {
            return null;
        }
        if (current.left != null) {
            System.out.println("Decrement in increase only flow : ko");
            return current.left;
        }
        if (previous != null) {
            long distance = current.value - previous.value;
            if (distance < 1 || distance > 3) {
                System.out.println("Distance = "+distance+" between "+previous.value+" and "+current.value+" : ko");
                return current;
            }
        }
        if (current.count > 1) {
            System.out.println("Counter > 1 : ko");
            return current;
        }
        return onlyIncreaseAndIsSafeWithTolerance(current.right, current);
    }

    /**
     * Search the node to increment or create it if missing.
     * Go to left node if the value is lower than current node value, to the right if higher or increment the node if equals.
     *
     * @param node  the current node we check
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
     *
     * @return an ordered flow of tree numbers with a number of occurrences equal to the node counter
     */
    public Stream<Long> toStream() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED), false);
    }

    /**
     * Iterator
     *
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
