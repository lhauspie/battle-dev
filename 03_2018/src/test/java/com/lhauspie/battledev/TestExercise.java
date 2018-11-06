package com.lhauspie.battledev;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class TestExercise {

    private Exercise exercise;
    private int nbTestCases;

    public TestExercise(Exercise exercise) {
        this.exercise = exercise;
        File[] samples = new File(exercise.getClass().getResource("sample").getPath())
                .listFiles((dir, name) -> name.matches("input[0-9]+\\.txt"));
        this.nbTestCases = samples.length;
    }

    public void run() {
        for (int i = 1; i <= nbTestCases; i++) {
            testCompute(i);
        }
    }

    public void testCompute(int testNumber) {
        InputStream input = exercise.getClass().getResourceAsStream("sample/input" + testNumber + ".txt");
        InputStream output = exercise.getClass().getResourceAsStream("sample/output" + testNumber + ".txt");

        String expected = new Scanner(output).next();
        String result = exercise.compute(new Scanner(input));
        if (expected.equals(result)) {
            System.out.println("Test " + testNumber + " is OK : expected(" + expected + ") == result(" + result + ")");
        } else {
            System.out.println("Test " + testNumber + " is KO : expected(" + expected + ") <> result(" + result + ")");
        }
    }
}
