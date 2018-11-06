package com.lhauspie.battledev;

import java.io.InputStream;
import java.util.Scanner;

public class TestExercise {

    private Exercise exercise;
    private int nbTestCases;

    public TestExercise(Exercise exercise, int nbTestCases) {
        this.exercise = exercise;
        this.nbTestCases = nbTestCases;
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
