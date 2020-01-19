package model;

public class Wypozyczenia {
	//pola
	private int id_wypozyczenia;
	private int id_ksiazki;
	private int id_czytelnika;
	
	//operacje na danych
	private int get_id_wypozyczenia() { return id_wypozyczenia; }
	private void set_id_wypozyczenia(int id_wypozyczenia) { this.id_wypozyczenia = id_wypozyczenia; }
}
