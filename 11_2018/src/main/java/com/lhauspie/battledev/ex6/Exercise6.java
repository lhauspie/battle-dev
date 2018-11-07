package com.lhauspie.battledev.ex6;

import com.lhauspie.battledev.Exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Exercise6 implements Exercise {

  public static void main(String[] argv) throws Exception {
    List<String> result = new Exercise6().compute(new Scanner(System.in));
    result.forEach(System.out::println);
  }

  public List<String> compute(Scanner sc) {
    int result = 0;
    return Arrays.asList(Integer.toString(result));
  }
}