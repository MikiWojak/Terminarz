import java.awt.EventQueue;
import java.util.List;

import biblioteka.Biblioteka;
import model.Czytelnik;
import model.Ksiazka;
import model.Wypozyczenia;

public class Main {
	static List<Czytelnik>czytelnicy ;
	static List<Ksiazka>ksiazki;
	static List<Wypozyczenia>wypozyczenia;
	
	public static void main(String[] args) {
		//TEST BD
		
		//otwarcie po³¹czenia
		Biblioteka biblioteka = new Biblioteka();
		
		//listy czytelników i ksi¹¿ek
		czytelnicy = biblioteka.select_czytelnicy();
		ksiazki = biblioteka.select_ksiazki();
		wypozyczenia = biblioteka.select_wypozyczenia();
		
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
		*/
		System.out.println("Lista wypozyczeñ:");
		//skrócona wersja FOR dla ka¿dego elementu listy
		for(int i = 0; i < wypozyczenia.size(); i++)
		{
			System.out.println(wypozyczenia.get(i).get_id_czytelnika() + "\t" + wypozyczenia.get(i).get_id_ksiazki());
		}
		
		//zamkniêcie po³¹czenia
		biblioteka.close_connection();
	}
	
	public void lista_wypozyczen() {
		for(int i = 0; i < czytelnicy.size(); i++) {
			for (int j = 0; j < wypozyczenia.size(); j++) {
				//TO DO
			}
		}
	}
}
