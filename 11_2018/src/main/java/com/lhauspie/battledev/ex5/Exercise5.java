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
      etudiant.cours1 = cours1;
      etudiant.cours2 = cours2;

      etudiants.add(etudiant);
    }

    List<Cours> courses = getCombinaison(new ArrayList<>(), etudiants, 0);

    if (courses.size() == 0) {
      return Arrays.asList("KO");
    }

    return courses.stream().map(c -> Integer.toString(c.creneau)).collect(Collectors.toList());
  }

  public static List<Cours> getCombinaison(List<Cours> takenCourses, List<Etudiant> etudiants, int etudiantIndex) {
    if (etudiantIndex == etudiants.size()) {
      return takenCourses;
    } else {
      Etudiant currentEtudiant = etudiants.get(etudiantIndex);
      List<Cours> result = new ArrayList<>();
      List<Cours> takenCoursesCopy = new ArrayList<>(takenCourses);
      if (isCompatibleWithPlanning(currentEtudiant.cours1, takenCourses)) {
        takenCoursesCopy.add(currentEtudiant.cours1);
        result.addAll(getCombinaison(takenCoursesCopy, etudiants, etudiantIndex + 1));
        takenCoursesCopy.remove(takenCoursesCopy.size() - 1);
      }
      if (result.isEmpty()) {
        if (isCompatibleWithPlanning(currentEtudiant.cours2, takenCourses)) {
          takenCoursesCopy.add(currentEtudiant.cours2);
          result.addAll(getCombinaison(takenCoursesCopy, etudiants, etudiantIndex + 1));
          takenCoursesCopy.remove(takenCoursesCopy.size() - 1);
        }
      }
      return result;
    }
  }

  public static boolean isCompatibleWithPlanning(Cours cours, List<Cours> takenCourses) {
    for (Cours currentCours : takenCourses) {
      if (!areCompatible(cours, currentCours)) {
        return false;
      }
    }
    return true;
  }

  public static boolean areCompatible(Cours c1, Cours c2) {
    return c1.fin < c2.debut || c1.debut > c2.fin;
  }

  private static class Cours {
    int debut;
    int fin;
    int creneau;

    @Override
    public String toString() {
      return Integer.toString(creneau);
    }
  }

  public static class Etudiant {
    Cours cours1;
    Cours cours2;
  }
}