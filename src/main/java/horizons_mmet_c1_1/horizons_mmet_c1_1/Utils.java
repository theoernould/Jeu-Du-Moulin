package horizons_mmet_c1_1.horizons_mmet_c1_1;

import java.util.Scanner;

/**
 * Contient les fonctions communes dans les classes.
 */
public class Utils {
	private static Scanner scanner = new Scanner(System.in);
	/**
	 * Vérifie qu'une valeur est comprise entre deux autres ou égale.
	 * @param x Valeur à comparer
	 * @param y Valeur minimale
	 * @param z Valeur maximale
	 */
	public static boolean isBetween(int x, int y, int z) {
		return x >= y && x <= z;
	}
	
	public static int random(int x, int y) {
		return (int) (Math.random() * (y - x) + x);
	}

	/**
	 * Ecrie le texte de façon progressive
	 * @param str Texte à afficher
	 * @param delay Délai entre l'affichage de chaque caractère
	 */
	public static void progressivePrint(String str, int delay) throws InterruptedException {
		for(String c : str.split("")) {
			System.out.print(c);
			Thread.sleep(delay);
		}
	}
	
	/**
	 * Demande en boucle à l'utilisateur une valeur tant qu'il n'est pas inclus entre les bornes
	 * @param borneInf Borne inférieure
	 * @param borneSup Borne supérieure
	 */
	public static int entrerInt(int borneInf, int borneSup) {
		int x = scanner.nextInt();
		while(!Utils.isBetween(x,borneInf, borneSup)) {
			System.out.println("Nombre invalide !");
			x = scanner.nextInt();
		}
		return x;
	}
}
