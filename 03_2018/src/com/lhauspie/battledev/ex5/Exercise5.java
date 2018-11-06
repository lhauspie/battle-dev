package com.lhauspie.battledev.ex5;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;


public class Exercise5 {

  public static void main(String[] argv) throws Exception {
    Scanner sc = new Scanner(System.in);

    int nbTerminus = sc.nextInt();
    Integer[][] matrice = new Integer[nbTerminus][nbTerminus];

    for (int ligne = 0; ligne < nbTerminus; ligne++) {
      for (int colonne = 0; colonne < nbTerminus; colonne++) {
        matrice[ligne][colonne] = sc.nextInt();
      }
    }

    System.out.println(score(matrice, 0, nbTerminus - 1));
    /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
  }

  public static long score(Integer[][] matrice, int start, int end) {
    Tuple key = new Tuple(start, end);
    if (maxScores.containsKey(key)) {
      return maxScores.get(key);
    }

    if (start >= end) {
      return 0;
    } else {
      long bestScore = 0;
      for (int ligne = start; ligne <= end; ligne++) {
        for (int colonne = ligne + 1; colonne <= end; colonne++) {
          long score = matrice[ligne][colonne];
          score += score(matrice, start, ligne - 1);
          score += score(matrice, ligne + 1, colonne - 1);
          score += score(matrice, colonne + 1, end);
          bestScore = Math.max(bestScore, score);
        }
      }
      maxScores.put(key, bestScore);
      return bestScore;
    }
  }

  static Map<Tuple, Long> maxScores = new HashMap<>();

  static class Tuple {
    public int start;
    public int end;

    public Tuple(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Tuple)) return false;
      Tuple ligne = (Tuple) o;
      return start == ligne.start && end == ligne.end;
    }

    @Override
    public int hashCode() {
      return Objects.hash(start, end);
    }
  }
}