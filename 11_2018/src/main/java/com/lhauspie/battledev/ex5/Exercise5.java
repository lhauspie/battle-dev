package com.lhauspie.battledev.ex5;

import com.lhauspie.battledev.Exercise;

import java.util.*;
import java.util.stream.Collectors;


public class Exercise5 implements Exercise {

  static final int COURSE_DURATION = 60;


  public static void main(String[] argv) throws Exception {
    List<String> result = new Exercise5().compute(new Scanner(System.in));
    result.forEach(System.out::println);
  }

  public List<String> compute(Scanner sc) {
    int nbStudents = sc.nextInt();

    List<Course> courses = new ArrayList<>();

    int courseId = 0;
    Course course;
    for (int i = 0; i < nbStudents; i++) {
      courses.add(course = new Course());
      course.id = courseId++;
      course.student = i;
      course.number = 1;
      course.debut = sc.nextInt();
      course.fin = course.debut + COURSE_DURATION;

      courses.add(course = new Course());
      course.id = courseId++;
      course.student = i;
      course.number = 2;
      course.debut = sc.nextInt();
      course.fin = course.debut + COURSE_DURATION;
    }

    for (Course aCourse: courses) {
      aCourse.next.addAll(
              courses.stream()
                      // on supprime les autres cours de l'etudiant selectionné
                      // on supprime les autres cours qui ne sont pas compatibles avec le cours selectionné
                      .filter(c -> c.student != aCourse.student && c.debut > aCourse.fin)
                      .collect(Collectors.toList())
      );
    }

    MemoizedItem[] memo = new MemoizedItem[courses.size()];
    List<Planning> plannings = new ArrayList<>();

    courses.sort(Comparator.comparingInt(o -> -o.debut));
    for (Course aCourse : courses) {
      System.err.print("getting plannings for course " + aCourse.toStringLight());
      List<Planning> coursePlannings = getPlannings(aCourse, memo);
      System.err.println(" returns " + coursePlannings.size() + " viable plannings");
      plannings.addAll(coursePlannings);
    }

    for (Planning planning: plannings) {
      if (planning.isViable() && planning.size == nbStudents) {
        return planning.extractCourses().stream()
                .sorted(Comparator.comparingInt(c -> c.student))
                .map(c -> Integer.toString(c.number))
                .collect(Collectors.toList());
      }
    }

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return Arrays.asList("KO");
  }

  public static List<Planning> getPlannings(Course course, MemoizedItem[] memo) {
    // MemoizedItem == null signifie : "calcul non effectué"
    if (memo[course.id] != null) {
      return memo[course.id].list;
    }

    List<Course> result = new ArrayList<>();
    if (course.next.size() == 0) {
      return Arrays.asList(new Planning(course));
    }

    List<Planning> plannings = new ArrayList<>();
    for (int i = 0; i < course.next.size(); i++) {
      List<Planning> nextPlannings = getPlannings(course.next.get(i), memo);
      for (Planning nextPlanning : nextPlannings) {
        if (nextPlanning.isCompatibleWith(course)) {
          plannings.add(new Planning(course, nextPlanning));
        }
      }
    }
    memo[course.id] = new MemoizedItem(plannings);
    return plannings;
  }


  public static class MemoizedItem {
    List<Planning> list;

    public MemoizedItem(List<Planning> list) {
      this.list = list;
    }
  }

  // this class represents a planning that contains courses
  // this planning is viable if all courses stands for distinct Students
  // this planning cached the fact that it's viable or not
  public static class Planning {
    private int student;
    private Course course;
    private Planning next;
    private boolean viable = false;
    private int size = 0;

    public Planning(Course course) {
      this.viable = true;
      this.course = course;
      this.size = 1;
    }

    public Planning(Course course, Planning planning) {
      if (planning.isCompatibleWith(course)) {
        this.viable = true;
        this.course = course;
        this.next = planning;
        this.size = planning.size + 1;
      }
    }

    public boolean isViable() {
      return viable;
    }

    public boolean isCompatibleWith(Course course) {
      return this.course.student != course.student && (this.next == null || this.next.isCompatibleWith(course));
    }

    public List<Course> extractCourses() {
      List<Course> courses;
      if (next == null) {
        courses = new ArrayList<>();
      } else {
        courses = next.extractCourses();
      }
      courses.add(course);
      return courses;
    }

    @Override
    public String toString() {
      return "Planning{" +
              "course=" + course.toStringLight() +
              ", next=" + next +
              '}';
    }
  }

  public static class Course {
    int id;
    int student;
    int number;
    int debut;
    int fin;
    List<Course> next = new ArrayList<>();

    @Override
    public String toString() {
      return "Course{" +
              "id=" + id +
              ", student=" + student +
              ", number=" + number +
              ", debut=" + debut +
              ", fin=" + fin +
              ", next=" + String.join(" | ",next.stream().map(Course::toStringLight).collect(Collectors.toList())) +
              '}';
    }

    public String toStringLight() {
      return "Course{" +
              "id=" + id +
              ", student=" + student +
              ", number=" + number +
              '}';
    }
    public String toStringMicro() {
      return id +
              "(" + student +
              ", " + number +
              ')';
    }
  }
}