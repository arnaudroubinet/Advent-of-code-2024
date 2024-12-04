package ar.ro;

import ar.ro.day4.Day4Part1;
import ar.ro.day4.Day4Part2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Day4Test {

    @Test
    public void day4FullData() throws IOException, URISyntaxException {
        Day4Part1 day4 =new Day4Part1();
        Assertions.assertEquals(2493, day4.process("day4/input.txt"));
    }


    @Test
    public void day4TestData() throws IOException, URISyntaxException {
        Day4Part1 day4 =new Day4Part1();
        Assertions.assertEquals(18, day4.process("day4/test.txt"));
    }

    @Test
    public void countWordOccurrences(){
        Assertions.assertEquals(9, Day4Part1.countWordOccurrences("XMASSXMSMMXMASMSSSSXAMXMXAMXXAAXMAMSAMMXMAMXSAMXMXMMMMSMMXMXMASMMSMMSSSMAXXMSMSSXMASMXSSSXSMMSSSSSXMASXSAMXSMMSSMXXXXXMASXMXMSSSMMSSMSSSMMMMSSXMXMAS"));
        Assertions.assertEquals(9, Day4Part1.countWordOccurrences("SAMXSXMSMMXMASMSSSSXAMXMXAMXXAAXMAMSAMMXMAMXSAMXMXMMMMSMMXMXMASMMSMMSSSMAXXMSMSSXMASMXSSSXSMMSSSSSXMASXSAMXSMMSSMXXXXXMASXMXMSSSMMSSMSSSMMMMSSXMSAMX"));
    }

    @Test
    public void streamDiagonalTopLeftToBottomRight(){

        char[][] matrix = {
                {'A', 'B', 'C', 'D'},
                {'E', 'F', 'G', 'H'},
                {'I', 'J', 'K', 'L'},
                {'M', 'N', 'O', 'P'}
        };

        List<String> list = Day4Part1.streamDiagonalTopLeftToBottomRight(matrix, 4, 4).peek(System.out::println).toList();

        Assertions.assertEquals("A", list.get(0));
        Assertions.assertEquals("BE", list.get(1));
        Assertions.assertEquals("CFI", list.get(2));
        Assertions.assertEquals("DGJM", list.get(3));
        Assertions.assertEquals("HKN", list.get(4));
        Assertions.assertEquals("LO", list.get(5));
        Assertions.assertEquals("P", list.get(6));
    }

    @Test
    public void streamDiagonalTopRightToBottomLeft(){

        char[][] matrix = {
                {'A', 'B', 'C', 'D'},
                {'E', 'F', 'G', 'H'},
                {'I', 'J', 'K', 'L'},
                {'M', 'N', 'O', 'P'}
        };

        List<String> list = Day4Part1.streamDiagonalTopRightToBottomLeft(matrix, 4, 4).peek(System.out::println).toList();

        Assertions.assertEquals("D", list.get(0));
        Assertions.assertEquals("HC", list.get(1));
        Assertions.assertEquals("LGB", list.get(2));
        Assertions.assertEquals("PKFA", list.get(3));
        Assertions.assertEquals("OJE", list.get(4));
        Assertions.assertEquals("NI", list.get(5));
        Assertions.assertEquals("M", list.get(6));
    }

    @Test
    public void day4Part2FullData() throws IOException, URISyntaxException {
        Assertions.assertEquals(1890, Day4Part2.process("day4/input.txt"));
    }

    @Test
    public void day4Part2TestData() throws IOException, URISyntaxException {
        Assertions.assertEquals(9, Day4Part2.process("day4/test.txt"));
    }

}
