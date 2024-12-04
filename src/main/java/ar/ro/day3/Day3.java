package ar.ro.day3;

import ar.ro.utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    private static final Pattern CLEAN_PATTERN = Pattern.compile("(don't\\(\\))|(do\\(\\))|(mul\\s*\\(\\s*\\d+\\s*,\\s*\\d+\\))");
    private static final Pattern MUL_PATTERN = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
    private static final String DO = "do()";
    private static final String DONT = "don't()";


    public Integer process(String input) throws IOException, URISyntaxException {
        List<String> lists = new ArrayList<>();
        Utils.forEachLinesFromFile(input).forEach(value -> {
            Matcher matcher = CLEAN_PATTERN.matcher(value);
            while (matcher.find()) {
                lists.add(matcher.group());
            }
        });
        AtomicReference<Integer> total = new AtomicReference<>(0);
        AtomicBoolean aBoolean =  new AtomicBoolean(true);
        lists.forEach(value -> {
            System.out.println(value);
            if(DO.equals(value.trim())){
                aBoolean.set(true);
                return;
            }
            if(DONT.equals(value.trim())){
                aBoolean.set(false);
                return;
            }

            if(aBoolean.get()) {
                Matcher matcher = MUL_PATTERN.matcher(value);
                if (matcher.find()) {
                    total.updateAndGet(v -> v + Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2)));
                }
            }
        });

        return total.get();
    }
}
