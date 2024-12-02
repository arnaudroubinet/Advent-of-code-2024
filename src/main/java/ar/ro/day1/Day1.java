package ar.ro.day1;

import ar.ro.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {


    public int result(String input) throws IOException, URISyntaxException {
        Pattern pattern = Pattern.compile("(\\d+)\\s+(\\d+)");
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        Utils.forEachLinesFromFile(input).forEach(line -> {
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            list1.add(Integer.valueOf(matcher.group(1)));
            list2.add(Integer.valueOf(matcher.group(2)));
        });
        list1.sort(Integer::compareTo);
        list2.sort(Integer::compareTo);

        int result = 0;
        for(int cpt = 0 ; cpt < list1.size() ; cpt++){
            result += Math.abs(list1.get(cpt) - list2.get(cpt));
        }
        return result;
    }
}
