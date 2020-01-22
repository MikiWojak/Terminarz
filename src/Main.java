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
		/*
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program window = new Program();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		*/
		
		//TEST BD
		
		//otwarcie po³¹czenia
		Biblioteka biblioteka = new Biblioteka();
		
		/*
		//dodanie czytelników
		biblioteka.insert_czytelnik("Mikolaj", "Zarnowski", "01234567890");
		biblioteka.insert_czytelnik("Jan", "Kowalski", "09876543210");
		biblioteka.insert_czytelnik("Pawel", "Nowak", "13579246801");
		
		//dodanie ksiazek
		biblioteka.insert_ksiazka("Wiedzmin", "Andrzej Sapkowski");
		biblioteka.insert_ksiazka("Wladca Pierscieni", "John R.R. Tolkien");
		biblioteka.insert_ksiazka("Sword Art Online", "Reki Kawahara");
		*/
		
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
