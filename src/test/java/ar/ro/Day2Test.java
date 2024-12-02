package ar.ro;

import ar.ro.day2.BinaryTree;
import ar.ro.day2.Day2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class Day2Test {

    @Test
    public void day2TestData() throws IOException, URISyntaxException {
        Day2 day2 =new Day2();
        Day2.Result result = day2.result("day2/test.txt");
        Assertions.assertEquals(2, result.nbSafe(),"nbSafe");
        Assertions.assertEquals(4, result.nbSafeWithTolerance(),"nbSafeWithTolerance");
    }

    @Test
    public void day2FullData() throws IOException, URISyntaxException {
        Day2 day2 =new Day2();
        Day2.Result result = day2.result("day2/input.txt");
        Assertions.assertEquals(279, result.nbSafe(),"nbSafe");
        Assertions.assertEquals(320, result.nbSafeWithTolerance(),"nbSafeWithTolerance");
    }

    @Test
    public void incrementPure(){
        BinaryTree tree = new BinaryTree("1 2 3 4");
        Assertions.assertTrue(tree.isSafeWithTolerance());
    }

    @Test
    public void decrementPure(){
        BinaryTree tree = new BinaryTree("6 5 4 3");
        Assertions.assertTrue(tree.isSafeWithTolerance());
    }

    @Test
    public void incrementImpure(){
        BinaryTree tree = new BinaryTree("1 2 0 3");
        Assertions.assertTrue(tree.isSafeWithTolerance());
    }

    @Test
    public void decrementImpure(){
        BinaryTree tree = new BinaryTree("6 5 7 4");
        Assertions.assertTrue(tree.isSafeWithTolerance());
    }

    @Test
    public void incrementWithDouble(){
        BinaryTree tree = new BinaryTree("1 2 2 3");
        Assertions.assertTrue(tree.isSafeWithTolerance());
    }

    @Test
    public void decrementWithDouble(){
        BinaryTree tree = new BinaryTree("6 5 5 4");
        Assertions.assertTrue(tree.isSafeWithTolerance());
    }

    @Test
    public void incrementWithNotFollowingDouble(){
        BinaryTree tree = new BinaryTree("1 2 3 2 5");
        Assertions.assertTrue(tree.isSafeWithTolerance());
    }

    @Test
    public void decrementWithNotFollowingDouble(){
        BinaryTree tree = new BinaryTree("6 5 4 5 3");
        Assertions.assertTrue(tree.isSafeWithTolerance());
    }

    @Test
    public void incrementWithTriple(){
        BinaryTree tree = new BinaryTree("1 2 2 2 3");
        Assertions.assertFalse(tree.isSafeWithTolerance());
    }

    @Test
    public void decrementWithTriple(){
        BinaryTree tree = new BinaryTree("6 5 5 5 4");
        Assertions.assertFalse(tree.isSafeWithTolerance());
    }

    @Test
    public void incrementWithNotFollowingTriple(){
        BinaryTree tree = new BinaryTree("1 2 2 3 2");
        Assertions.assertFalse(tree.isSafeWithTolerance());
    }

    @Test
    public void decrementWithNotFollowingTriple(){
        BinaryTree tree = new BinaryTree("6 5 5 4 5");
        Assertions.assertFalse(tree.isSafeWithTolerance());
    }

    @Test
    public void incrementWithPlus3(){
        BinaryTree tree = new BinaryTree("1 4 7 8 11");
        Assertions.assertTrue(tree.isSafeWithTolerance());
    }

    @Test
    public void decrementWithMinus3(){
        BinaryTree tree = new BinaryTree("11 8 7 4 1");
        Assertions.assertTrue(tree.isSafeWithTolerance());
    }

    @Test
    public void incrementWithPlus4(){
        BinaryTree tree = new BinaryTree("1 5 7 8 11");
        Assertions.assertFalse(tree.isSafeWithTolerance());
    }

    @Test
    public void decrementWithMinus4(){
        BinaryTree tree = new BinaryTree("12 8 7 4 1");
        Assertions.assertFalse(tree.isSafeWithTolerance());
    }
}
