package horizons_ihm;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

/**
 * Contient les fonctions communes dans les classes.
 */
public class Utils {
	public static Scanner scanner = new Scanner(System.in);
	public static final String dir = System.getProperty("user.dir") + File.separator;
	/**
	 * Vérifie qu'une valeur est comprise entre deux autres ou égale.
	 * @param x Valeur à comparer
	 * @param y Valeur minimale
	 * @param z Valeur maximale */
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
	* @throws InterruptedException */
	public static void progressivePrint(String str, int delay) throws InterruptedException {
		for(String c : str.split("")) {
			System.out.print(c);
			Thread.sleep(delay);
		}
	}
	
	public static String dateToString(LocalDateTime date) {
		return date.getDayOfMonth() + " " + date.getMonth().getDisplayName(TextStyle.FULL, Locale.FRANCE) + " " + date.getYear() + " � " + date.getHour() + "h" + date.getMinute();
	}
	
	public static String randomString(int length) {
		String str = "";
		for(int i=0;i<length;i++) {
			str += (char) ('a' + Utils.random(0,26));
		}
		return str;
	}
	
	/**
	 * Demande en boucle à l'utilisateur une valeur tant qu'il n'est pas inclus entre les bornes
	 * @param borneInf Borne inférieure
	 * @param borneSup Borne supérieure
	 */
	public static int entrerInt(int borneInf, int borneSup) {
		System.out.print("Choix : ");
		String xStr = "";
		while(xStr.equals("")) {
			xStr = scanner.nextLine();
		}
		int x = Integer.parseInt(xStr);
		while(!Utils.isBetween(x,borneInf, borneSup)) {
			System.out.println("Nombre invalide !");
			System.out.print("Choix : ");
			x = scanner.nextInt();
		}
		return x;
	}
	
	public static void shutdown() {
		Utils.closeScanner();
		System.exit(0);
	}
	
	/**Afficher le menu textuel 
	 * @param menu Menu
     * @return l'indice du menu */
	public static OptionsMenu afficherMenu(Menus menu) {
		System.out.println(menu.getTitle());
		OptionsMenu[] options = menu.getOptions();
		for(int i=0;i<options.length;i++) System.out.println("\t" + (i+1) + ". " + options[i]);
		System.out.println("Que voulez-vous faire ?");
		int choice = Utils.entrerInt(1, options.length);
		return options[choice-1];
	}
	
	public static void closeScanner() {
		scanner.close();
	}
	
}
