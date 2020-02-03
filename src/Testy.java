import java.sql.Date;
import java.util.List;

import model.Grupa;
import model.Przypis;
import model.Zadanie;
import model.Zlozone;
import terminarz.Terminarz;

public class Testy {
	
	public Testy() {}
	
	public void test_klas_rekordow() {
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
	
	public void test_bd() {
		//test tworzenia tabel
		Terminarz terminarz = new Terminarz();
		
		//test wstawiania rekordów
		//terminarz.wstaw_zadanie(Date.valueOf("2020-04-15"), "Konkurs SEP na program komputerowy", "Program komputerowy edukacyjny/u¿ytkowy\nTerminarz", "wa¿ne", false);
		//terminarz.wstaw_grupe("Zawody", "Zawody, konkursy, turnieje, itd.");
		//terminarz.wstaw_przypisanie(1, 3);
		
		terminarz.zamknij_polaczenie();
	}
	
	public void test_list_bd() {
		Terminarz terminarz = new Terminarz();
		
		//zadania
		List<Zadanie>zadania = terminarz.lista_zadania();
		System.out.println("\tZadania:");
		for (int i = 0 ; i < zadania.size(); i++) {
			System.out.println(zadania.get(i).pobierz_id_zadanie());
			System.out.println(zadania.get(i).pobierz_data_zadanie());
			System.out.println(zadania.get(i).pobierz_tytul_zadanie());
			System.out.println(zadania.get(i).pobierz_opis_zadanie());
			System.out.println(zadania.get(i).pobierz_priorytet_zadanie());
			System.out.println(zadania.get(i).pobierz_czy_wykonane());
			System.out.println();
		}
		
		//grupy
		List<Grupa>grupy = terminarz.lista_grupy();
		System.out.println("\tGrupy");
		for (int i = 0; i < grupy.size(); i++) {
			System.out.println(grupy.get(i).pobierz_id_grupa());
			System.out.println(grupy.get(i).pobierz_nazwa_grupa());
			System.out.println(grupy.get(i).pobierz_opis_grupa());
			System.out.println();
		}
		
		terminarz.zamknij_polaczenie();
	}
	
	public void test_zlozone() {
		Zlozone zlozone = new Zlozone(1, Date.valueOf("2020-04-15"), "Konkurs SEP na najlepszy program komputerowy", "Program Terminarz napisany w jêzyku Java", "wa¿ne", false, 3, "Zawody", "Zadania zw. z zawodami");
		
		System.out.println(zlozone.pobierz_id_grupa());
		System.out.println(zlozone.pobierz_nazwa_grupa());
		System.out.println(zlozone.pobierz_opis_grupa());
		
		System.out.println();
		
		System.out.println(zlozone.pobierz_id_zadanie());
		System.out.println(zlozone.pobierz_data_zadanie());
		System.out.println(zlozone.pobierz_tytul_zadanie());
		System.out.println(zlozone.pobierz_opis_zadanie());
		System.out.println(zlozone.pobierz_priorytet_zadanie());
		System.out.println(zlozone.pobierz_czy_wykonane());
	}
	
	public void test_zapytanie_zlozone() {
		Terminarz terminarz = new Terminarz();
		
		List<Zlozone>zlozone = terminarz.lista_zlozone();
		
		//System.out.println(zlozone.size());
		
		for(int i = 0; i < zlozone.size(); i++) {
			System.out.println(zlozone.get(i).pobierz_nazwa_grupa());
			System.out.println(zlozone.get(i).pobierz_tytul_zadanie());
			System.out.println(zlozone.get(i).pobierz_data_zadanie());
			System.out.println(zlozone.get(i).pobierz_czy_wykonane());
			System.out.println();
		}
		
		terminarz.zamknij_polaczenie();
	}
}
