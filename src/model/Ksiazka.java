package model;

public class Ksiazka {
	private int id;
	private String tytul;
	private String autor;
	
	//operacje na danych
	public int get_id() { return id; }
	public void set_id(int id) { this.id = id; }
	
	public String get_tytul() { return tytul; }
	public void set_tytul(String tytul) { this.tytul = tytul; }
	
	public String get_autor() { return autor; }
	public void set_autor(String autor) { this.autor = autor; }
	
	//konstruktory
	public Ksiazka (int id, String tytul, String autor) {
		this.id = id;
		this.tytul = tytul;
		this.autor = autor;
	}
	
	//wyœwietlanie
	public String toString() {
		return id + "\t" + tytul + "\t" + autor;
	}
		
}
