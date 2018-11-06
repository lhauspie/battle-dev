package com.lhauspie.battledev.ex4;

import com.lhauspie.battledev.Exercise;

import java.util.*;


public class Exercise4 implements Exercise {
  private static Integer NB_CREPES = 6;

  public static void main(String[] argv) throws Exception {
    System.out.println(new Exercise4().compute(new Scanner(System.in)));
  }

  public String compute(Scanner sc) {
    List<Integer> originalCrepes = new ArrayList<>();
    List<Integer> sortedCrepes = new ArrayList<>();

    for (long i = 0; i < NB_CREPES; i++) {
      int crepe = sc.nextInt();
      originalCrepes.add(crepe);
      sortedCrepes.add(crepe);
    }
    sortedCrepes.sort(Comparator.naturalOrder());

    List<Integer> crepes = new ArrayList<>(originalCrepes);
    int nbRetournements = 0;
    while (!isSolvable(crepes, nbRetournements, sortedCrepes)) {
      nbRetournements++;
    }
    return Integer.toString(nbRetournements);
  }

  private static boolean isSolvable(List<Integer> crepes, int nbRetournement, List<Integer> sorted) {
    if (nbRetournement == 0) {
      return Objects.equals(crepes, sorted);
    } else {
      for (int i = 2; i < crepes.size() + 1; i++) {
        List<Integer> crepesRetournees = retournerLesCrepes(new ArrayList<>(crepes), i);
        if (isSolvable(crepesRetournees, nbRetournement - 1, sorted)) {
          return true;
        }
      }
      return false;
    }
  }

  private static List<Integer> retournerLesCrepes(List<Integer> crepes, int position) {
    List<Integer> crepesRetournees = crepes.subList(0, position);
    Collections.reverse(crepesRetournees);
    crepesRetournees.addAll(crepes.subList(position, NB_CREPES));
    return crepesRetournees;
  }
}