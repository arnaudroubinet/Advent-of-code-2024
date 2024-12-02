package ar.ro;


import ar.ro.day1.Day1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;


public class Day1Test {

    @Test
    public void day1TestData() throws IOException, URISyntaxException {
        Day1 day1 =new Day1();
        Day1.Result result = day1.result("day1/test.txt");
        Assertions.assertEquals(BigInteger.valueOf(11L), result.sum());
        Assertions.assertEquals(BigInteger.valueOf(31L), result.similarity());
    }

    @Test
    public void day1FullData() throws IOException, URISyntaxException {
        Day1 day1 =new Day1();
        Day1.Result result = day1.result("day1/input.txt");
        Assertions.assertEquals(BigInteger.valueOf(3569916L), result.sum());
        Assertions.assertEquals(BigInteger.valueOf(26407426L), result.similarity());
    }
}
