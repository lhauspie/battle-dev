/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.lhauspie.battledev;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IsoContest_3 {

	public static class Usage {
		int id = 0; // permettra de trier les cables dans l'ordre d'apparition
		int start = 0;
		int end = 0;
		Integer cable = 0;

		public Usage(int id, int start, int end) {
			this.id = id;
			this.start = start;
			this.end = end;
		}

		public int getStart() {
			return start;
		}

		public int getId() {
			return id;
		}
	}

	public static void main(String[] argv) throws Exception {
		Scanner sc = new Scanner(System.in);
		String[] split = sc.nextLine().split(" ");
		int nbCables = Integer.valueOf(split[0]);
		int nbUsages = Integer.valueOf(split[1]);

		int[] cables = new int[nbCables];
		for (int i = 0; i < nbCables; i++) {
			cables[i] = 0;
		}

		List<Usage> usages = new ArrayList<>();
		for (int i = 0; i < nbUsages; i++) {
			split = sc.nextLine().split(" ");
			Integer start = Integer.valueOf(split[0]);
			Integer end = Integer.valueOf(split[1]);
			usages.add(new Usage(i, start, end));
		}

		/* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
		usages.sort(Comparator.comparing(Usage::getStart));
		for (Usage u : usages) {
			boolean cableFound = false;
			for (int i = 0; i < nbCables; i++) {
				// si cable n'est plus occupé, on le prend
				if (cables[i] <= u.start) {
					u.cable = i + 1;
					cables[i] = u.end; // occupé jusque la fin de l'usage
					cableFound = true;
					break;
				}
			}
			if (!cableFound) {
				System.out.println("pas possible");
				return;
			}
		}

		usages.sort(Comparator.comparing(Usage::getId));
		System.out.println(String.join(" ", usages.stream().map(u -> u.cable.toString()).collect(Collectors.toList())));
	}
}