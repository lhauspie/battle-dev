/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.lhauspie.battledev;

import java.util.Scanner;

public class IsoContest_2 {

	public static void main(String[] argv) throws Exception {
		Scanner sc = new Scanner(System.in);
		Integer longueurTotale = 0;
		Integer plusCourte = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			Integer longueurPlanche = Integer.valueOf(sc.nextLine());
			longueurTotale += longueurPlanche;

			if (longueurPlanche < plusCourte) {
				plusCourte = longueurPlanche;
			}
		}
		System.out.println(longueurTotale - (plusCourte * 4));
		/* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
	}
}