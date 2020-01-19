package model;

public class Wypozyczenia {
	//pola
	private int id_wypozyczenia;
	private int id_ksiazki;
	private int id_czytelnika;
	
	//operacje na danych
	public int get_id_wypozyczenia() { return id_wypozyczenia; }
	public void set_id_wypozyczenia(int id_wypozyczenia) { this.id_wypozyczenia = id_wypozyczenia; }
	
	public int get_id_ksiazki() { return id_ksiazki; }
	public void set_id_ksiazki(int id_ksiazki) { this.id_ksiazki = id_ksiazki; }
	
	public int get_id_czytelnika() { return id_czytelnika; }
	public void set_id_czytelnika(int id_czytelnika) { this.id_czytelnika = id_czytelnika; }
	
	//konstruktory
	public Wypozyczenia() {}
	public Wypozyczenia(int id_wypozyczenia, int id_ksiazki, int id_czytelnika) {
		this.id_wypozyczenia = id_wypozyczenia;
		this.id_ksiazki = id_ksiazki;
		this.id_czytelnika = id_czytelnika;
	}
}
