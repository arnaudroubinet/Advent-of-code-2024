package ar.ro.day4;

import ar.ro.utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicInteger;

public class Day4Part2 {

    public static int count(char[][] matrix, int nbLines, int sizeLine) {
        int masCounter = 0;
        for (int vertical = 1; vertical < nbLines - 1; vertical++) {
            for (int horizontal = 1; horizontal < sizeLine - 1; horizontal++) {
                if ('A' == matrix[vertical][horizontal] && isTopLeftToBottomRight(matrix, horizontal, vertical) && isTopRightToBottomLeft(matrix, horizontal, vertical)) {
                    masCounter++;
                }
            }
        }
        return masCounter;
    }


    /**
     * M . .  or  S . .
     * . A .  or  . A .
     * . . S  or  . . M
     */
    private static boolean isTopLeftToBottomRight(char[][] matrix, int horizontal, int vertical) {
        return ('M' == matrix[vertical - 1][horizontal - 1] && 'S' == matrix[vertical + 1][horizontal + 1]) || ('S' == matrix[vertical - 1][horizontal - 1] && 'M' == matrix[vertical + 1][horizontal + 1]);
    }

    /**
     * . . M  or  . . S
     * . A .  or  . A .
     * S . .  or  M . .
     */
    private static boolean isTopRightToBottomLeft(char[][] matrix, int horizontal, int vertical) {
        return ('M' == matrix[vertical - 1][horizontal + 1] && 'S' == matrix[vertical + 1][horizontal - 1]) || ('S' == matrix[vertical - 1][horizontal + 1] && 'M' == matrix[vertical + 1][horizontal - 1]);
    }


    public static Integer process(String input) throws IOException, URISyntaxException {

        int nbLines = Long.valueOf(Utils.nbLines(input)).intValue();
        int maxLine = Long.valueOf(Utils.maxSizeLine(input)).intValue();

        char[][] matrix = buildMatrix(input, nbLines, maxLine);
        return count(matrix, nbLines, maxLine);
    }

    private static char[][] buildMatrix(String input, int nbLines, int maxLine) throws IOException, URISyntaxException {
        char[][] matrix = new char[nbLines][maxLine];
        AtomicInteger line = new AtomicInteger(0);
        Utils.forEachLinesFromFile(input).forEach(value -> matrix[line.getAndAdd(1)] = value.toCharArray());
        return matrix;

    }
}
