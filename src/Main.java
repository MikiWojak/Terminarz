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
		
		//otwarcie po��czenia
		Biblioteka biblioteka = new Biblioteka();
		
		//listy czytelnik�w i ksi��ek
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
		/*
		System.out.println("Lista wypozycze�:");
		//skr�cona wersja FOR dla ka�dego elementu listy
		for(int i = 0; i < wypozyczenia.size(); i++)
		{
			System.out.println(wypozyczenia.get(i).get_id_czytelnika() + "\t" + wypozyczenia.get(i).get_id_ksiazki());
		}
		*/
		
		lista_wypozyczen();
		//zamkni�cie po��czenia
		biblioteka.close_connection();
	}
	
	public static void lista_wypozyczen() {
		for(int i = 0; i < czytelnicy.size(); i++) {
			//wy�wietlenie czytelnika
			System.out.println(czytelnicy.get(i));
			//szukanie wsr�d wypozyczen
			for (int j = 0; j < wypozyczenia.size(); j++) {
				//czy id_czytelnika jest r�wne
				if(czytelnicy.get(i).get_id() == wypozyczenia.get(j).get_id_czytelnika()) {
					//szukanie w�r�d ksi��ek
					for (int k = 0; k < ksiazki.size(); k++) {
						if (wypozyczenia.get(j).get_id_ksiazki() == ksiazki.get(k).get_id()) {
							System.out.println("\t" + ksiazki.get(k));
						}
					}
				}
			}
		}
	}
}
