package com.lhauspie.battledev.ex3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Exercise3 {

  public static void main(String[] argv) throws Exception {
    Scanner sc = new Scanner(System.in);

    Votant me = new Votant();
    me.note1 = sc.nextInt();
    me.note2 = sc.nextInt();
    me.note3 = sc.nextInt();
    me.note4 = sc.nextInt();
    me.note5 = sc.nextInt();

    int nbCopains = sc.nextInt();
    int nbBestCopains = sc.nextInt();

    List<Votant> votants = new ArrayList<>();
    for (long i = 0; i < nbCopains; i++) {
      Votant ami = new Votant();
      ami.note1 = sc.nextInt();
      ami.note2 = sc.nextInt();
      ami.note3 = sc.nextInt();
      ami.note4 = sc.nextInt();
      ami.note5 = sc.nextInt();
      ami.note6 = sc.nextInt();

      votants.add(ami);
    }

    votants.sort(new VotantFromReference(me));

    List<Votant> bestCopains = votants.subList(0, nbBestCopains);
    int sum = 0;
    for (Votant v : bestCopains) {
      sum += v.note6;
    }

    int avg = (int) Math.ceil(sum / nbBestCopains * 1.0);

    System.out.println(avg);
    /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
  }

  static class Votant {
    public int note1;
    public int note2;
    public int note3;
    public int note4;
    public int note5;
    public int note6;

    public int distance(Votant other) {
      return Math.abs(other.note1 - this.note1)
          + Math.abs(other.note2 - this.note2)
          + Math.abs(other.note3 - this.note3)
          + Math.abs(other.note4 - this.note4)
          + Math.abs(other.note5 - this.note5);
    }
  }

  static class VotantFromReference implements Comparator<Votant> {
    private Votant reference;

    public VotantFromReference(Votant reference) {
      this.reference = reference;
    }

    @Override
    public int compare(Votant o1, Votant o2) {
      return Integer.compare(reference.distance(o1), reference.distance(o2));
    }
  }
}