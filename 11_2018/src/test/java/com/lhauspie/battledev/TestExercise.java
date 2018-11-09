package com.lhauspie.battledev;

import javafx.collections.transformation.SortedList;

import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TestExercise {

    private Exercise exercise;
    private int nbTestCases;
    private List<Integer> testCases;

    public TestExercise(Exercise exercise) {
        Pattern pattern = Pattern.compile("input([0-9]+)\\.txt");

        this.exercise = exercise;
        File[] samples = new File(exercise.getClass().getResource("sample").getPath())
                .listFiles((dir, name) -> name.matches("input([0-9]+)\\.txt"));

        testCases = Arrays.stream(samples)
                .map(s -> {
                    Matcher m = pattern.matcher(s.getName());
                    if (m.find()) {
                        return Integer.valueOf(m.group(1));
                    }
                    return 0;
                })
                .sorted(Comparator.comparing(s -> s))
                .collect(Collectors.toList());

//        Arrays.stream(samples).map(s -> {
//            Matcher matcher = pattern.matcher(s.getName());
//            if (matcher.find()) {
//                return System.out.println(matcher.group(1));
//            }
//            return 0;
//        }).forEach(System.err::println);
    }

    public void run() {
        for (int tc : testCases) {
            testCompute(tc);
        }
    }

    public void testCompute(int testNumber) {
        long start = new Date().getTime();
        InputStream input = exercise.getClass().getResourceAsStream("sample/input" + testNumber + ".txt");
        InputStream output = exercise.getClass().getResourceAsStream("sample/output" + testNumber + ".txt");

        List<String> actual = exercise.compute(new Scanner(input));
        List<String> expected = new ArrayList<>();
        new Scanner(output).forEachRemaining(s -> expected.add(s));

        long end = new Date().getTime();
        if (expected.equals(actual)) {
            System.out.println("✅ Test " + testNumber + " is OK : expected(" + expected + ") == actual(" + actual + ")");
        } else {
            System.out.println("❎ Test " + testNumber + " is KO : expected(" + expected + ") <> actual(" + actual + ")");
        }
        System.out.println("ℹ Test spent " + (end-start) + " ms");

    }
}
