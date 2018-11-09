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
    int nbStudents = sc.nextInt();

    List<Course> courses = new ArrayList<>();

    for (int i = 0; i < nbStudents; i++) {
      int debut1 = sc.nextInt();
      int fin1 = debut1 + 60;
      int debut2 = sc.nextInt();
      int fin2 = debut2 + 60;

      Course course1 = new Course();
      course1.debut = debut1;
      course1.fin = fin1;
      course1.number = 1;
      course1.student = i;

      Course course2 = new Course();
      course2.debut = debut2;
      course2.fin = fin2;
      course2.number = 2;
      course2.student = i;

      courses.add(course1);
      courses.add(course2);
    }

    for (Course course: courses) {
      course.next.addAll(
              courses.stream()
                      // on supprime les autres cours de l'etudiant selectionné
                      .filter(c -> c.student != course.student)
                      // on supprime les autres cours qui ne sont pas compatibles avec le cours selectionné
                      .filter(c -> c.debut > course.fin)
                      .collect(Collectors.toList())
      );
    }

    courses.sort(Comparator.comparingInt(o -> o.debut));

    List<Course> selectedCourses = new ArrayList<>();
    System.err.println("======================");
    for (Course course : courses) {
      System.err.println("evaluating course " + course.toStringLight());
      selectedCourses = evaluate(course, new ArrayList<>(), nbStudents);
      if (selectedCourses != null && selectedCourses.size() == nbStudents) {
        break;
      }
    }
    System.err.println("======================");

    if (selectedCourses == null) {
      return Arrays.asList("KO");
    }

    selectedCourses.sort(Comparator.comparingInt(o -> o.student));


    return selectedCourses.stream().map(c -> Integer.toString(c.number)).collect(Collectors.toList());
  }

  public static List<Course> evaluate(Course course, List<Integer> alreadySelectedStudents, int nbStudents) {
    // si je rencontre un cours appartenant à un etudiant dejà choisi,
    // j'arrete tout car c'est un chemin non viable dans le graph
    if (alreadySelectedStudents.contains(course.student)) {
      return null;
    }

    // ici, je sais que le noeud en cours `course` ne correspond à aucun etudiant dejà choisi
    // donc si il n'a pas de feuille, alors je le choisi d'office
    if (course.next.isEmpty()) {
      if (nbStudents == 1) {
        return Arrays.asList(course);
      } else {
        return null;
      }
    }

    alreadySelectedStudents.add(course.student);
    List<Course> nextEval = new ArrayList<>();
    for (Course nextCourse : course.next) {
      nextEval = evaluate(nextCourse, alreadySelectedStudents, nbStudents-1);
      // nextEval == null signifie que les chemins partant du noeud nextCourse ne sont pas viable
      if (nextEval != null) {
        break;
      }
    }
    alreadySelectedStudents.remove(Integer.valueOf(course.student));

    List<Course> result = null;
    if (nextEval != null) {
      result = new ArrayList<>();
      result.add(course);
      result.addAll(nextEval);
    }
    return result;
  }

  public static class Course {
    int student;
    int number;
    int debut;
    int fin;
    List<Course> next = new ArrayList<>();

    @Override
    public String toString() {
      return "Course{" +
              "student=" + student +
              ", number=" + number +
              ", debut=" + debut +
              ", fin=" + fin +
              ", next=" + String.join(" | ",next.stream().map(Course::toStringLight).collect(Collectors.toList())) +
              '}';
    }

    public String toStringLight() {
      return "Course{" +
              "student=" + student +
              ", number=" + number +
              '}';
    }
  }
}