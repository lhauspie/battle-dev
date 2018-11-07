package com.lhauspie.battledev.ex1;

import com.lhauspie.battledev.Exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Exercise1 implements Exercise {

  public static void main(String[] argv) throws Exception {
    List<String> result = new Exercise1().compute(new Scanner(System.in));
    result.forEach(System.out::println);
  }

  public List<String> compute(Scanner sc) {
    int nbEncheres = sc.nextInt();
    int reservePrice = sc.nextInt();

    int max = 0;
    String winner = null;
    for (int i = 0; i < nbEncheres; i++) {
      int encherePrice = sc.nextInt();
      String who = sc.next();
      if (encherePrice > reservePrice && encherePrice > max) {
        winner = who;
        max = encherePrice;
      }
    }

    if (winner == null) {
      return Arrays.asList("KO");
    }
    return Arrays.asList(winner.toUpperCase());
  }
}