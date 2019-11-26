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

public class IsoContest_4 {

	public static class Item {
		float prixAuGramme = 0; // permettra de trier les items par valeur

		public float getPrixAuGramme() {
			return prixAuGramme;
		}
	}

	public static class Pierre extends Item {
		int prix = 0;
		int poids = 0;

		public Pierre(int prix, int poids) {
			this.prix = prix;
			this.poids = poids;
			this.prixAuGramme = prix / poids;
		}
	}

	public static class Poudre extends Item {
		int quantiteDispo = 0;

		public Poudre(int prixAuGramme, int quantiteDispo) {
			this.prixAuGramme = prixAuGramme;
			this.quantiteDispo = quantiteDispo;
		}
	}

	public static void main( String[] argv ) throws Exception {
		Scanner sc = new Scanner(System.in);
		String[] split = sc.nextLine().split(" ");
		int nbPierres = Integer.valueOf(split[0]);
		int nbTypesPoudre = Integer.valueOf(split[1]);
		int capaciteLampe = Integer.valueOf(split[2]);

		List<Item> items = new ArrayList<>();
		// Pierres précieuses
		List<Pierre> pierres = new ArrayList<>();
		for (int i = 0; i < nbPierres; i++) {
			split = sc.nextLine().split(" ");
			Integer prixUnitaire = Integer.valueOf(split[0]);
			Integer poids = Integer.valueOf(split[1]);
			Pierre pierre = new Pierre(prixUnitaire, poids);
			items.add(pierre);
			pierres.add(pierre);
		}

		// Poudre
		List<Poudre> poudres = new ArrayList<>();
		for (int i = 0; i < nbTypesPoudre; i++) {
			split = sc.nextLine().split(" ");
			Integer prixUnitaire = Integer.valueOf(split[0]);
			Integer quantiteDispo = Integer.valueOf(split[1]);
			Poudre poudre = new Poudre(prixUnitaire, quantiteDispo);
			items.add(poudre);
			poudres.add(poudre);
		}

		/* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
		Integer valeurMax = 0;
		items.sort(Comparator.comparing(Item::getPrixAuGramme).reversed());
		int placeDispo = capaciteLampe;
		for (Item i : items) {
			if (placeDispo == 0) {
				break;
			}
			if (i instanceof Pierre) {
				Pierre p = (Pierre) i;
				if (p.poids <= placeDispo) {
					valeurMax += p.prix;
					placeDispo -= p.poids;
				}
			}
			if (i instanceof Poudre) {
				Poudre p = (Poudre) i;
				if (p.quantiteDispo <= placeDispo) {
					valeurMax += p.quantiteDispo * (int) p.prixAuGramme;
					placeDispo -= p.quantiteDispo;
				} else {
					valeurMax += placeDispo * (int) p.prixAuGramme;
					placeDispo = 0;
				}
			}
		}

		System.out.println(valeurMax);
	}
}