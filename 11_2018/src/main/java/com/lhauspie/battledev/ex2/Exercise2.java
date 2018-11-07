package com.lhauspie.battledev.ex2;

import com.lhauspie.battledev.Exercise;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Exercise2 implements Exercise {

  public static void main(String[] argv) throws Exception {
    List<String> result = new Exercise2().compute(new Scanner(System.in));
    result.forEach(System.out::println);
  }

  public List<String> compute(Scanner sc) {
    int nbMots = sc.nextInt();

    HashSet<String> magics = new HashSet<>();
    for (int i = 0; i < nbMots; i++) {
      String str = sc.next();
      if (isMagic(str.toLowerCase())) {
        magics.add(str.toLowerCase());
      }
    }
    return Arrays.asList(Integer.toString(magics.size()));
  }

  public static boolean isMagic(String str) {
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    if (str.length() >= 5 && str.length() <= 7) {
      if (str.endsWith("a")
          || str.endsWith("e")
          || str.endsWith("i")
          || str.endsWith("o")
          || str.endsWith("u")
          || str.endsWith("y")) {
        for (int i = 0; i < alphabet.length - 1; i++) {
          if (str.startsWith(alphabet[i] + "" + alphabet[i + 1])) {
            return true;
          }
        }
      }
    }

    return false;
  }
}