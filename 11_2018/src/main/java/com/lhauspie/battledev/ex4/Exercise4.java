package com.lhauspie.battledev.ex4;

import com.lhauspie.battledev.Exercise;

import java.util.*;


public class Exercise4 implements Exercise {

  public static void main(String[] argv) throws Exception {
    List<String> result = new Exercise4().compute(new Scanner(System.in));
    result.forEach(System.out::println);
  }

  public List<String> compute(Scanner sc) {
    int nbEtudiants = sc.nextInt();

    List<Cours> cours = new ArrayList<>();

    for (int i =0; i<nbEtudiants; i++) {
      int debut1 = sc.nextInt();
      int fin1 = debut1+60;
      int debut2 = sc.nextInt();
      int fin2 = debut2+60;

      Cours cours1 = new Cours();
      cours1.debut = debut1;
      cours1.fin = fin1;
      cours.add(cours1);

      Cours cours2 = new Cours();
      cours2.debut = debut2;
      cours2.fin = fin2;
      cours.add(cours2);
    }

    cours.sort((o1, o2) -> {
      if (Integer.compare(o1.fin, o2.debut) == 0) {
        return Integer.compare(o1.debut, o2.debut);
      }
      return Integer.compare(o1.fin, o2.debut);
    });

    int maxEtudiants = 0;
    Cours previsouCours = cours.get(0);
    List<Cours> nextCours = cours.subList(1, cours.size());
    for (int i=0; i<cours.size(); i++) {
      int currentMaxEtudiants = 1 + maxEtudiants(previsouCours, nextCours);
      if (currentMaxEtudiants > maxEtudiants) {
        maxEtudiants = currentMaxEtudiants;
      }
      if (nextCours.size() > 1) {
        previsouCours = nextCours.get(0);
        nextCours = nextCours.subList(1, nextCours.size());
      }
    }

    return Arrays.asList(Integer.toString(maxEtudiants));
  }

  public static int maxEtudiants(Cours previousCours, List<Cours> cours) {
    if (cours.size() == 0) {
      return 0;
    }
    Cours currentCours = cours.get(0);
    if (previousCours.fin >= currentCours.debut) {
      return maxEtudiants(previousCours, cours.subList(1, cours.size()));
    }
    return 1 + maxEtudiants(currentCours, cours.subList(1, cours.size()));
  }

  private static class Cours {
    int debut;
    int fin;
  }
}