package ar.ro.day4;

import ar.ro.utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day4Part1 {


    private static final String XMAS = "XMAS";
    private static final String SAMX = "SAMX";

    public static int countWordOccurrences(String text) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(XMAS, index)) != -1) {
            count++;
            index += XMAS.length();
        }

        index = 0;
        while ((index = text.indexOf(SAMX, index)) != -1) {
            count++;
            index += SAMX.length();
        }

        return count;
    }

    public static Stream<String> streamDiagonalTopLeftToBottomRight(char[][] matrix, int nbLines, int maxLine) {

        List<String> diagonals = new ArrayList<>();

        for (int columnNumber = 0; columnNumber < nbLines + maxLine - 1; columnNumber++) {
            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < nbLines; row++) {
                int col = columnNumber - row;
                if (col >= 0 && col < maxLine) {
                    sb.append(matrix[row][col]);
                }
            }
            if (!sb.isEmpty()) {
                diagonals.add(sb.toString());
            }
        }

        return diagonals.stream();
    }



    public Integer process(String input) throws IOException, URISyntaxException {

        int nbLines = Long.valueOf(Utils.nbLines(input)).intValue();
        int maxLine = Long.valueOf(Utils.maxSizeLine(input)).intValue();

        char[][] matrix = buildMatrix(input, nbLines, maxLine);

        int horizontalCount = streamNominal(matrix, nbLines).map(Day4Part1::countWordOccurrences).reduce(Integer::sum).orElse(0);
        int verticalCount = streamTopBottom(matrix, nbLines, maxLine).map(Day4Part1::countWordOccurrences).reduce(Integer::sum).orElse(0);
        int topLeftToBottomRightCount = streamDiagonalTopLeftToBottomRight(matrix, nbLines, maxLine).map(Day4Part1::countWordOccurrences).reduce(Integer::sum).orElse(0);
        int topRightToBottomLeftCount = streamDiagonalTopRightToBottomLeft(matrix, nbLines, maxLine).map(Day4Part1::countWordOccurrences).reduce(Integer::sum).orElse(0);


        return horizontalCount + verticalCount + topLeftToBottomRightCount + topRightToBottomLeftCount;
    }

    public Stream<String> streamNominal(char[][] matrix, int nbLines) {
        return IntStream.range(0, nbLines).mapToObj(row -> new String(matrix[row]));
    }

    public Stream<String> streamTopBottom(char[][] matrix, int nbLines, int maxLine) {
        return IntStream.range(0, maxLine).mapToObj(col -> {
            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < nbLines; row++) {
                sb.append(matrix[row][col]);
            }
            return sb.toString();
        });
    }


    public static Stream<String> streamDiagonalTopRightToBottomLeft(char[][] matrix, int nbLines, int maxLine) {
        List<String> diagonals = new ArrayList<>();

        for (int d = nbLines + maxLine - 2; d >= 0; d--) {
            StringBuilder sb = new StringBuilder();
            for (int row = nbLines - 1; row >= 0; row--) {
                int col = row + d - nbLines + 1;
                if (col >= 0 && col < maxLine) {
                    sb.append(matrix[row][col]);
                }
            }
            if (!sb.isEmpty()) {
                diagonals.add(sb.toString());
            }
        }

        return diagonals.stream();
    }


    private char[][] buildMatrix(String input, int nbLines, int maxLine) throws IOException, URISyntaxException {
        char[][] matrix = new char[nbLines][maxLine];
        AtomicInteger line = new AtomicInteger(0);
        Utils.forEachLinesFromFile(input).forEach(value -> matrix[line.getAndAdd(1)] = value.toCharArray());
        return matrix;

    }
}
