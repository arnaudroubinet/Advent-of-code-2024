package ar.ro;

import ar.ro.day3.Day3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class Day3Test {

    @Test
    public void day3FullData() throws IOException, URISyntaxException {
        Day3 day3 =new Day3();
        Assertions.assertEquals(88811886, day3.process("day3/input.txt"));
    }


    @Test
    public void day3TestData() throws IOException, URISyntaxException {
        Day3 day3 =new Day3();
        Assertions.assertEquals(48, day3.process("day3/test.txt"));
    }


}
