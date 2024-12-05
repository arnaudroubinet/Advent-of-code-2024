package ar.ro;

import ar.ro.day5.Day5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class Day5Test {

    @Test
    public void day5FullData() throws IOException, URISyntaxException {
        Assertions.assertEquals(5762, Day5.processAndRemoveNotOrderedPages("input.txt"));
    }

    @Test
    public void day5TestData() throws IOException, URISyntaxException {
        Assertions.assertEquals(143, Day5.processAndRemoveNotOrderedPages("test.txt"));
    }


    @Test
    public void day5KeepFullData() throws IOException, URISyntaxException {
        Assertions.assertEquals(4130, Day5.processAndKeepOnlyNotOrderedPages("input.txt"));
    }

    @Test
    public void day5KeepTestData() throws IOException, URISyntaxException {
        Assertions.assertEquals(123, Day5.processAndKeepOnlyNotOrderedPages("test.txt"));
    }

}
