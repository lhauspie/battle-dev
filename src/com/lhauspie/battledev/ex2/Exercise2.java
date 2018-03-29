package com.lhauspie.battledev.ex2;

import java.util.Scanner;


public class Exercise2 {

  public static void main(String[] argv) throws Exception {
    Scanner sc = new Scanner(System.in);

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

    System.out.println((int) Math.ceil(recette));
    /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
  }
}