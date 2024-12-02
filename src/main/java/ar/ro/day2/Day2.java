package ar.ro.day2;

import ar.ro.utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicInteger;

public class Day2 {


    public Result result(String input) throws IOException, URISyntaxException {
        AtomicInteger safeCounter = new AtomicInteger();
        AtomicInteger safeCounterWithTolerance = new AtomicInteger();

        Utils.forEachLinesFromFile(input).forEach(line -> {
            System.out.println("====================="+line+"===================");
            BinaryTree tree = new BinaryTree(line);

            if(tree.isSafe()){
                safeCounter.getAndIncrement();
            }
            if(tree.isSafeWithTolerance()){
                safeCounterWithTolerance.getAndIncrement();
            }
        });

        return new Result(safeCounter.get(),safeCounterWithTolerance.get());
    }


    public record Result(int nbSafe, int nbSafeWithTolerance) {
    }
}
