import java.awt.EventQueue;
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
		//TEST BD
		
		//otwarcie po��czenia
		Biblioteka biblioteka = new Biblioteka();
		
		//listy czytelnik�w i ksi��ek
		List<Czytelnik>czytelnicy = biblioteka.select_czytelnicy();
		List<Ksiazka>ksiazki = biblioteka.select_ksiazki();
		
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
		
		//zamkni�cie po��czenia
		biblioteka.close_connection();
	}
}
