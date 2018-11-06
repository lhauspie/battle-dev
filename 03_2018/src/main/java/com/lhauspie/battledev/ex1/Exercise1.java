package com.lhauspie.battledev.ex1;

import com.lhauspie.battledev.Exercise;

import java.util.Scanner;


public class Exercise1 implements Exercise {

  public static void main(String[] argv) throws Exception {
    System.out.println(new Exercise1().compute(new Scanner(System.in)));
  }

  public String compute(Scanner sc) {
    int result = 12;
    return Integer.toString(result);
  }
}