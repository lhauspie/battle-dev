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
    int nbStudent = sc.nextInt();

    List<Course> courses = new ArrayList<>();

    for (int i = 0; i < nbStudent; i++) {
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

    courses.sort(Comparator.comparingInt(o -> o.debut));
    System.err.println("sorted courses = " + courses);


    List<Course> selectedCourses = choseCourses(courses, nbStudent);

    if (selectedCourses.size() < nbStudent) {
      return Arrays.asList("KO");
    }

    selectedCourses.sort(Comparator.comparingInt(o -> o.student));
    return selectedCourses.stream().map(c -> Integer.toString(c.number)).collect(Collectors.toList());
  }

  private static List<Course> choseCourses(List<Course> courses, int nbStudents) {
    if (courses.size() < nbStudents) {
      return Collections.EMPTY_LIST; // meaning "no solution found"
    }

    Course firstAvailableCourse = courses.get(0);
    if (nbStudents == 1) {
      return Arrays.asList(firstAvailableCourse);
    }

    List<Course> nextCourses = courses.stream()
            // on supprime les autres cours de l'etudiant selectionné
            .filter(c -> c.student != firstAvailableCourse.student)
            // on supprime les autres cours qui ne sont pas compatibles avec le cours selectionné
            .filter(c -> c.debut > firstAvailableCourse.fin)
            .collect(Collectors.toList());

    List<Course> selectedCourses = new ArrayList<>();
    selectedCourses.add(firstAvailableCourse);
    selectedCourses.addAll(choseCourses(nextCourses, nbStudents - 1));

    // selectedCourses n'est pas satisfaisant donc je vais commencer avec le cours suivant
    if (selectedCourses.size() < nbStudents) {
      return choseCourses(courses.subList(1, courses.size()), nbStudents);
    }

    return selectedCourses;
  }

  public static class Course {
    int student;
    int number;
    int debut;
    int fin;

    @Override
    public String toString() {
      return "Course{" +
              "student=" + student +
              ", number=" + number +
              ", debut=" + debut +
              ", fin=" + fin +
              '}';
    }
  }
}