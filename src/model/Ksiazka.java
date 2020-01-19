package model;

public class Ksiazka {
	//pola
	private int id_ksiazki;
	private String tytul;
	private String autor;
	
	//operacje na danych
	public int get_id() { return id_ksiazki; }
	public void set_id(int id_ksiazki) { this.id_ksiazki = id_ksiazki; }
	
	public String get_tytul() { return tytul; }
	public void set_tytul(String tytul) { this.tytul = tytul; }
	
	public String get_autor() { return autor; }
	public void set_autor(String autor) { this.autor = autor; }
	
	//konstruktory
	public Ksiazka (int id_ksiazki, String tytul, String autor) {
		this.id_ksiazki = id_ksiazki;
		this.tytul = tytul;
		this.autor = autor;
	}
	
	//wyœwietlanie
	public String toString() {
		return id_ksiazki + "\t" + tytul + "\t" + autor;
	}
		
}
