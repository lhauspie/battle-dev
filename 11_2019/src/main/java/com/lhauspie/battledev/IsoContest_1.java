/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.lhauspie.battledev;

import java.util.Scanner;

public class IsoContest_1 {

	public static void main( String[] argv ) throws Exception {
		Scanner sc = new Scanner(System.in);
		Integer nbParticipants = Integer.valueOf(sc.nextLine());
		int tirage = Integer.MAX_VALUE;
		String gagnant = "";
		for (int i = 0; i< nbParticipants; i++) {
			String[] split = sc.nextLine().split(" ");
			Integer t = Integer.valueOf(split[1]);
			if (t < tirage) {
				tirage = t;
				gagnant = split[0];
			}
		}
		System.out.println(gagnant);
		/* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
	}
}