package ar.ro.day1;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;


public class Day1Test {

    @Test
    public void day1Test() throws IOException, URISyntaxException {
        Day1 day1 =new Day1();
        Assertions.assertEquals(day1.result("day1/test.txt"), 11);
    }
    @Test
    public void day1FullData() throws IOException, URISyntaxException {
        Day1 day1 =new Day1();
        Assertions.assertEquals(day1.result("day1/input.txt"), 3569916);
    }
}
