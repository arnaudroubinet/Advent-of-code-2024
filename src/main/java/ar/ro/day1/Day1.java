package ar.ro.day1;

import ar.ro.utils.Utils;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {

    private static final Pattern PATTERN = Pattern.compile("(\\d+)\\s+(\\d+)");
    private final BinaryTree tree1 = new BinaryTree();
    private final BinaryTree tree2 = new BinaryTree();
    private final Map<Long, BigInteger> similarityMap = new HashMap<>();


    public Result result(String input) throws IOException, URISyntaxException {
        buildTree(input);
        return processTree();
    }

    private void buildTree(String input) throws IOException, URISyntaxException {
        Utils.forEachLinesFromFile(input).forEach(line -> {
            Matcher matcher = PATTERN.matcher(line);
            if(matcher.find()) {
                tree1.add(Long.parseLong(matcher.group(1)));
                tree2.add(Long.parseLong(matcher.group(2)));
            }else{
                throw new RuntimeException("No match found");
            }

        });
    }

    private Result processTree() {
        Iterator<Long> iteratorList1 = tree1.iterator();
        Iterator<Long> iteratorList2 = tree2.iterator();

        BigInteger sum = BigInteger.ZERO;
        while (iteratorList1.hasNext() && iteratorList2.hasNext()) {
            sum = sum.add(BigInteger.valueOf(Math.abs(iteratorList1.next() - iteratorList2.next())));
        }

        BigInteger similarity = BigInteger.ZERO;

        Iterator<Long> it = tree1.iterator();

        while (it.hasNext()) {
            long current = it.next();
            if (!similarityMap.containsKey(current)) {
                similarityMap.put(current, BigInteger.valueOf(current).multiply(BigInteger.valueOf(tree2.nbOfOccurrences(current))));
            }
            similarity = similarity.add(similarityMap.get(current));
        }
        return new Result(sum, similarity);
    }

    public record Result(BigInteger sum, BigInteger similarity) {
    }

}
