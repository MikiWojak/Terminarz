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
		List<Wypozyczenia>wypozyczenia = biblioteka.select_wypozyczenia();
		
		/*
		biblioteka.insert_wypozyczenia(1, 2);
		biblioteka.insert_wypozyczenia(3, 1);
		biblioteka.insert_wypozyczenia(1, 1);
		biblioteka.insert_wypozyczenia(2, 3);
		biblioteka.insert_wypozyczenia(1, 3);
		biblioteka.insert_wypozyczenia(3, 2);
		*/
		
		/*
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
		*/
		System.out.println("Lista wypozycze�:");
		//skr�cona wersja FOR dla ka�dego elementu listy
		for(int i = 0; i < wypozyczenia.size(); i++)
		{
			System.out.println(wypozyczenia.get(i).get_id_czytelnika() + "\t" + wypozyczenia.get(i).get_id_ksiazki());
		}
		
		//zamkni�cie po��czenia
		biblioteka.close_connection();
	}
}
