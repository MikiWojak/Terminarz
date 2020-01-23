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
		
		//otwarcie po³¹czenia
		Biblioteka biblioteka = new Biblioteka();
		
		//listy czytelników i ksi¹¿ek
		List<Czytelnik>czytelnicy = biblioteka.select_czytelnicy();
		List<Ksiazka>ksiazki = biblioteka.select_ksiazki();
		
		//wyswietlenie
		System.out.println("Lista czytelników:");
		//skrócona wersja FOR dla ka¿dego elementu listy
		for(Czytelnik c : czytelnicy)
		{
			System.out.println(c);
		}
		
		System.out.println("\nLista ksi¹¿ek:");
		//skrócona wersja FOR dla ka¿dego elementu listy
		for(Ksiazka k : ksiazki)
		{
			System.out.println(k);
		}
		
		//zamkniêcie po³¹czenia
		biblioteka.close_connection();
	}
}
