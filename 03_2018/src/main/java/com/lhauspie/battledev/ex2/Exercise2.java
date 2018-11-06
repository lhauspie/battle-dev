package com.lhauspie.battledev.ex2;

import java.util.Scanner;


public class Exercise2 {

  public static void main(String[] argv) throws Exception {
    System.out.println(compute(new Scanner(System.in)));
  }

  public static String compute(Scanner sc) {
    long prixParPersonne = sc.nextLong();
    int nbTables = sc.nextInt();

    double recette = 0;
    for (long i = 0; i < nbTables; i++) {
      int nbPersonnes = sc.nextInt();
      double noteTable = nbPersonnes * prixParPersonne;
      if (nbPersonnes >= 10) {
        noteTable = noteTable * 0.7;
      } else if (nbPersonnes >= 6) {
        noteTable = noteTable * 0.8;
      } else if (nbPersonnes >= 4) {
        noteTable = noteTable * 0.9;
      }
      recette += noteTable;
    }

    return Integer.toString((int) Math.ceil(recette));
  }
}