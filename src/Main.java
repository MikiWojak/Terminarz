import java.awt.EventQueue;
import java.sql.Date;

import model.Zadanie;
import model.Grupa;
import model.Przypis;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//Test klas - rekordów
		Zadanie zadanie = new Zadanie(1, Date.valueOf("2020-01-29"), "Stanowiska egzaminacyjne", "Opis zadania", "nie takie wa¿ne", false);
		System.out.println("Zadanie:");
		System.out.println(
				"\t" + zadanie.pobierz_id_zadanie() + "\n" +
				"\t" + zadanie.pobierz_data_zadanie() + "\n" +
				"\t" + zadanie.pobierz_tytul_zadanie() + "\n" +
				"\t" + zadanie.pobierz_opis_zadanie() + "\n" +
				"\t" + zadanie.pobierz_priorytet_zadanie() + "\n" +
				"\t" + zadanie.pobierz_czy_wykonane() + "\n");
		
		Grupa grupa = new Grupa(1, "Szko³a", "Zadania zw. ze szko³¹");
		System.out.println("Grupa:");
		System.out.println(
				"\t" + grupa.pobierz_id_grupa() + "\n" +
				"\t" + grupa.pobierz_nazwa_grupa() + "\n" +
				"\t" + grupa.pobierz_opis_grupa() + "\n");
		
		Przypis przypis = new Przypis(1, 2, 3);
		System.out.println("Przypis:");
		System.out.println(
				"\t" + przypis.pobierz_id_przypis() + "\n" +
				"\t" + przypis.pobierz_id_grupa() + "\n" +
				"\t" + przypis.pobierz_id_zadanie() + "\n");
	}
}
