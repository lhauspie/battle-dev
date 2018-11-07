package com.lhauspie.battledev.ex3;

import com.lhauspie.battledev.Exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Exercise3 implements Exercise {

  public static void main(String[] argv) throws Exception {
    List<String> result = new Exercise3().compute(new Scanner(System.in));
    result.forEach(System.out::println);
  }

  public List<String> compute(Scanner sc) {
    int n = sc.nextInt();
    float n_2 = n / 2f;

    int prev = sc.nextInt();
    int x = 0;
    for (int i=1; i<=n; i++) {
      int y = sc.nextInt();
      if (prev == n_2 && y == n_2) {
        return Arrays.asList("INF");
      }

      if (prev == n_2 && y != n_2
              || prev > n_2 && y < n_2
              || prev < n_2 && y > n_2) {
        x++;
      }
      prev = y;
    }
    return Arrays.asList(Integer.toString(x));
  }
}