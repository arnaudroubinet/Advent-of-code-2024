package ar.ro.day5;

import ar.ro.utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Day5 {


    public static int processAndRemoveNotOrderedPages(String input) throws IOException, URISyntaxException {
        Map<Integer, List<Integer>> rules = new HashMap<>();

        Utils.forEachLinesFromFile("day5/rules/" + input).forEach(line -> {
            String[] array = line.split("\\|");
            int key = Integer.parseInt(array[0]);
            List<Integer> before = rules.getOrDefault(key, new ArrayList<>());
            before.add(Integer.valueOf(array[1]));
            rules.put(key, before);
        });

        AtomicInteger sum = new AtomicInteger();
        Utils.forEachLinesFromFile("day5/updates/" + input).map(line -> Arrays.stream(line.split(",")).map(Integer::valueOf).toList()).forEach(valueList -> {
            boolean isValide = true;
            for (int currentIndex = 0; currentIndex < valueList.size(); currentIndex++) {
                if (rules.containsKey(valueList.get(currentIndex))) {
                    List<Integer> beforeList = valueList.subList(0, currentIndex);
                    if (rules.get(valueList.get(currentIndex)).stream().anyMatch(beforeList::contains)) {
                        isValide = false;
                        break;
                    }
                }
            }

            if (isValide) {
                sum.addAndGet(valueList.get((valueList.size() / 2)));
            }
        });

        return sum.get();
    }


    public static int processAndKeepOnlyNotOrderedPages(String input) throws IOException, URISyntaxException {
        Map<Integer, List<Integer>> rules = new HashMap<>();

        Utils.forEachLinesFromFile("day5/rules/" + input).forEach(line -> {
            String[] array = line.split("\\|");
            int key = Integer.parseInt(array[0]);
            List<Integer> before = rules.getOrDefault(key, new ArrayList<>());
            before.add(Integer.valueOf(array[1]));
            rules.put(key, before);
        });

        AtomicInteger sum = new AtomicInteger();
        Utils.forEachLinesFromFile("day5/updates/" + input).map(line -> Arrays.stream(line.split(",")).map(Integer::valueOf).toList()).forEach(valueList -> {
            ArrayList<Integer> workingList = new ArrayList<>(valueList);
            AtomicBoolean isValid = new AtomicBoolean(true);

            for (AtomicInteger currentIndex = new AtomicInteger(); currentIndex.get() < workingList.size(); currentIndex.getAndIncrement()) {
                final int index = currentIndex.get();
                if (rules.containsKey(workingList.get(currentIndex.get()))) {
                    List<Integer> beforeList = List.copyOf(workingList.subList(0, currentIndex.get()));
                    rules.get(workingList.get(currentIndex.get())).forEach(value -> {
                        if (beforeList.contains(value)) {
                            workingList.remove(value);
                            if(workingList.size() == index){
                                workingList.add(value);
                            }else{
                                workingList.add(index, value);
                            }

                            isValid.set(false);
                            currentIndex.getAndDecrement();
                        }

                    });
                }

            }
            if (!isValid.get()) {
                sum.addAndGet(workingList.get((workingList.size() / 2)));
            }
        });
        return sum.get();
    }


}
