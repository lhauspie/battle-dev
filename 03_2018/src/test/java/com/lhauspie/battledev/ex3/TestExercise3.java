package com.lhauspie.battledev.ex3;

import java.io.InputStream;
import java.util.Scanner;

public class TestExercise3 {

    public static void main(String[] args) throws Exception {
        testCompute(1);
        testCompute(2);
        testCompute(3);
        testCompute(4);
        testCompute(5);
    }

    public static void testCompute(int testNumber) {
        InputStream input = TestExercise3.class.getResourceAsStream("sample/input" + testNumber + ".txt");
        InputStream output = TestExercise3.class.getResourceAsStream("sample/output" + testNumber + ".txt");

        String expected = new Scanner(output).next();
        String result = Exercise3.compute(new Scanner(input));
        if (expected.equals(result)) {
            System.out.println("Test " + testNumber + " is OK : expected(" + expected + ") == result(" + result + ")");
        } else {
            System.out.println("Test " + testNumber + " is KO : expected(" + expected + ") <> result(" + result + ")");
        }
    }
}
