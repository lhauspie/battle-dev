package com.lhauspie.battledev.ex2;

import java.io.InputStream;
import java.util.Scanner;

public class TestExercise2 {

    public static void main(String[] args) throws Exception {
        testCompute(1);
        testCompute(2);
        testCompute(3);
    }

    public static void testCompute(int testNumber) {
        InputStream input = TestExercise2.class.getResourceAsStream("sample/input" + testNumber + ".txt");
        InputStream output = TestExercise2.class.getResourceAsStream("sample/output" + testNumber + ".txt");

        String expected = new Scanner(output).next();
        String result = Exercise2.compute(new Scanner(input));
        if (expected.equals(result)) {
            System.out.println("Test " + testNumber + " is OK : expected(" + expected + ") == result(" + result + ")");
        } else {
            System.out.println("Test " + testNumber + " is KO : expected(" + expected + ") <> result(" + result + ")");
        }
    }
}
