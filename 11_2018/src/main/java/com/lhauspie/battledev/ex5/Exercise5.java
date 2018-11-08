package com.lhauspie.battledev.ex5;

import com.lhauspie.battledev.Exercise;

import java.util.*;
import java.util.stream.Collectors;


public class Exercise5 implements Exercise {

  public static void main(String[] argv) throws Exception {
    List<String> result = new Exercise5().compute(new Scanner(System.in));
    result.forEach(System.out::println);
  }

  public List<String> compute(Scanner sc) {
    int nbEtudiants = sc.nextInt();

    List<Etudiant> etudiants = new ArrayList<>();

    for (int i = 0; i < nbEtudiants; i++) {
      int debut1 = sc.nextInt();
      int fin1 = debut1 + 60;
      int debut2 = sc.nextInt();
      int fin2 = debut2 + 60;

      Cours cours1 = new Cours();
      cours1.debut = debut1;
      cours1.fin = fin1;
      cours1.creneau = 1;

      Cours cours2 = new Cours();
      cours2.debut = debut2;
      cours2.fin = fin2;
      cours2.creneau = 2;

      Etudiant etudiant = new Etudiant();
      etudiant.id = i;
      etudiant.cours1 = cours1;
      etudiant.cours2 = cours2;

      etudiants.add(etudiant);
    }

    System.err.println("================================");
    for (Etudiant etudiant : etudiants) {
      boolean cours1CompatibleWithOthers = true;
      boolean cours2CompatibleWithOthers = true;
      System.err.println("-----------------------");
      System.err.println("etudiant.cours1 : " + etudiant.cours1);
      System.err.println("etudiant.cours2 : " + etudiant.cours2);
      for (Etudiant currentEtudiant : etudiants) {
        // on ne veut comparer le creneau en cours qu'avec les autres etudiants (pas avec l'etudiant lui-meme)
        // si on a deja choisi un creneau sur le `currentEtudiant` (chosen != 0), alors ca veut dire qu'il est forcement compatible avec le `etudiant`
        if (currentEtudiant.id != etudiant.id && currentEtudiant.chosen == 0) {
          System.err.println("currentEtudiant.cours1 : " + currentEtudiant.cours1);
          System.err.println("currentEtudiant.cours2 : " + currentEtudiant.cours2);
          // on sait qu'un creneau est incompatible avec un autre étudiant si il est en collision avec 1 des 2 creneaux de cet étudiant
          boolean c1c1 = etudiant.cours1.isCompatible(currentEtudiant.cours1);
          boolean c1c2 = etudiant.cours1.isCompatible(currentEtudiant.cours2);
          boolean c2c1 = etudiant.cours2.isCompatible(currentEtudiant.cours1);
          boolean c2c2 = etudiant.cours2.isCompatible(currentEtudiant.cours2);
          System.err.println("c1c1: " + c1c1 + " | c1c2: " + c1c2 + " | c2c1: " + c2c1 + " | c2c2: " + c2c2);


          cours1CompatibleWithOthers = cours1CompatibleWithOthers && (etudiant.cours1.isCompatible(currentEtudiant.cours1) || etudiant.cours1.isCompatible(currentEtudiant.cours2));
          cours2CompatibleWithOthers = etudiant.cours2.isCompatible(currentEtudiant.cours1) || etudiant.cours2.isCompatible(currentEtudiant.cours2);
          if (!cours1CompatibleWithOthers && !cours2CompatibleWithOthers) {
            return Arrays.asList("KO");
          }
        }
      }

      if (cours1CompatibleWithOthers) {
        etudiant.chosen = 1;
      } else if (cours2CompatibleWithOthers) {
        etudiant.chosen = 2;
      }
    }

    return etudiants.stream().map(e -> Integer.toString(e.chosen)).collect(Collectors.toList());
  }

  private static class Cours {
    int debut;
    int fin;
    int creneau;

    @Override
    public String toString() {
      return "Cours{" +
              "creneau=" + creneau +
              ", debut=" + debut +
              ", fin=" + fin +
              '}';
    }

    public boolean isCompatible(Cours other) {
      return this.fin < other.debut || this.debut > other.fin;
    }
  }

  public static class Etudiant {
    int id;
    Cours cours1;
    Cours cours2;
    int chosen = 0;
  }
}