/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.lhauspie.battledev;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IsoContest_5 {

	public static class Temple {
		String nom = "";
		String nomAncien = null;
		Map<String, Temple> children = new HashMap<>();
		Map<String, Temple> parents = new HashMap<>();

		public Temple(String nom) {
			this.nom = nom;
		}

		public void addParent(Temple temple) {
			parents.put(temple.nom, temple);
		}

		public void addChild(Temple temple) {
			children.put(temple.nom, temple);
		}
	}

	public static void main( String[] argv ) throws Exception {
		Scanner sc = new Scanner(System.in);
		String emplacement = sc.nextLine();

		String line;
		Map<String, Temple> carte = new HashMap<>();
		while(sc.hasNextLine()) {
			line = sc.nextLine();
			String[] split = line.split(" ");
			linkTemples(carte, split[0], split[1]);
		}

		/* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
		Temple temple_L = carte.values().stream()
				.filter(t -> t.children.size() == 0) // sans enfant
				.filter(t -> t.parents.size() == 3) // avec 3 parents
				.filter(t -> t.parents.values().stream().filter(tt -> tt.children.size() == 2).count() == 1) // dont un parent ayant 2 enfants
				.filter(t -> t.parents.values().stream().filter(tt -> tt.children.size() == 1).count() == 2) // dont 2 parents ayant 1 enfant
				.findFirst().get();
		temple_L.nomAncien = "L";
		Temple temple_D = temple_L.parents.values().stream().filter(t -> t.children.size() == 2).findFirst().get();
		temple_D.nomAncien = "D";
		Temple temple_C = temple_D.parents.values().stream().findFirst().get();
		temple_C.nomAncien = "C";
		Temple temple_G = temple_C.children.values().stream().filter(t -> t.nomAncien == null).findFirst().get();
		temple_G.nomAncien = "G";
		Temple temple_B = temple_C.parents.values().stream().findFirst().get();
		temple_B.nomAncien = "B";
		Temple temple_E = temple_G.parents.values().stream().filter(t -> t.nomAncien == null).findFirst().get();
		temple_E.nomAncien = "E";
		Temple temple_K = temple_B.children.values().stream().filter(t -> t.children.size() == 1).findFirst().get();
		temple_K.nomAncien = "K";
		Temple temple_H = temple_B.children.values().stream().filter(t -> t.nomAncien == null).findFirst().get();
		temple_H.nomAncien = "H";
		Temple temple_I = temple_L.parents.values().stream().filter(t -> t.children.size() == 1 && t.nomAncien == null).findFirst().get();
		temple_I.nomAncien = "I";
		Temple temple_J = temple_H.children.values().stream().filter(t -> t.nomAncien == null).findFirst().get();
		temple_J.nomAncien = "J";
		Temple temple_A = temple_J.parents.values().stream().filter(t -> t.nomAncien == null).findFirst().get();
		temple_A.nomAncien = "A";
		Temple temple_N = temple_J.children.values().stream().findFirst().get();
		temple_N.nomAncien = "N";
		Temple temple_F = temple_K.parents.values().stream().filter(t -> t.nomAncien == null).findFirst().get();
		temple_F.nomAncien = "F";
		Temple temple_M = temple_A.children.values().stream().filter(t -> t.nomAncien == null).findFirst().get();
		temple_M.nomAncien = "M";

		System.out.println(carte.values().stream().filter(t -> t.nomAncien.equals(emplacement)).findAny().get().nom);
	}

	public static void linkTemples(Map<String, Temple> carte, String t1, String t2) {
		Temple temple_1 = carte.getOrDefault(t1, new Temple(t1));
		Temple temple_2 = carte.getOrDefault(t2, new Temple(t2));
		temple_1.addChild(temple_2);
		temple_2.addParent(temple_1);
		carte.put(t1, temple_1);
		carte.put(t2, temple_2);
	}
}