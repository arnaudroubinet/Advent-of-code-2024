package ar.ro.day1;

import ar.ro.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {

    private static final Pattern PATTERN = Pattern.compile("(\\d+)\\s+(\\d+)");
    private final BinarySearchTree list1 = new BinarySearchTree();
    private final BinarySearchTree list2 = new BinarySearchTree();

    public int result(String input) throws IOException, URISyntaxException {
        buildTree(input);
        return processTree();
    }

    private void buildTree(String input) throws IOException, URISyntaxException {
        Utils.forEachLinesFromFile(input).forEach(line -> {
            Matcher matcher = PATTERN.matcher(line);
            matcher.find();
            list1.add(Integer.parseInt(matcher.group(1)));
            list2.add(Integer.parseInt(matcher.group(2)));
        });
    }

    private int processTree(){
        Iterator<Integer> iteratorList1 = list1.iterator();
        Iterator<Integer> iteratorList2 = list2.iterator();

        int sum = 0;
        while (iteratorList1.hasNext() && iteratorList2.hasNext()) {
            sum += Math.abs(iteratorList1.next() - iteratorList2.next());
        }

        return sum;
    }

    static class TreeNode {
        int value;
        TreeNode left, right;

        public TreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    static class BinarySearchTree implements Iterable<Integer> {
        private TreeNode root;

        public void add(int value) {
            root = addRecursive(root, value);
        }

        private TreeNode addRecursive(TreeNode node, int value) {
            if (node == null) {
                return new TreeNode(value);
            }

            if (value <= node.value) {
                node.left = addRecursive(node.left, value); // Doublons insérés à gauche
            } else {
                node.right = addRecursive(node.right, value);
            }

            return node;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new InOrderIterator(root);
        }

        private static class InOrderIterator implements Iterator<Integer> {
            private final Stack<TreeNode> stack;

            public InOrderIterator(TreeNode root) {
                stack = new Stack<>();
                pushLeft(root);
            }

            private void pushLeft(TreeNode node) {
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new IllegalStateException("No more elements in the tree");
                }

                TreeNode current = stack.pop();
                int value = current.value;

                if (current.right != null) {
                    pushLeft(current.right);
                }

                return value;
            }
        }
    }
}
