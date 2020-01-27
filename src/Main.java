import java.awt.EventQueue;
import java.sql.Date;
import java.util.List;

import biblioteka.Biblioteka;
import model.Czytelnik;
import model.Ksiazka;
import model.Wypozyczenia;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//otwarcie po��czenia
		Biblioteka biblioteka = new Biblioteka();
		
		//listy czytelnik�w i ksi��ek
		List<Czytelnik>czytelnicy = biblioteka.select_czytelnicy();
		List<Ksiazka>ksiazki = biblioteka.select_ksiazki();
		List<Wypozyczenia>wypozyczenia = biblioteka.select_wypozyczenia();
		
		//wyswietlenie
		System.out.println("Lista czytelnik�w:");
		//skr�cona wersja FOR dla ka�dego elementu listy
		for(Czytelnik c : czytelnicy)
		{
			System.out.println(c);
		}
		
		System.out.println("\nLista ksi��ek:");
		//skr�cona wersja FOR dla ka�dego elementu listy
		for(Ksiazka k : ksiazki)
		{
			System.out.println(k);
		}
		
		System.out.println("\nLista Wypo�ycze�:");
		//skr�cona wersja FOR dla ka�dego elementu listy
		for(int i = 0; i < wypozyczenia.size(); i++)
		{
			System.out.println(wypozyczenia.get(i).get_data_wypozyczenia());
		}
		
		//zamkni�cie po��czenia
		biblioteka.close_connection();
	}
}
