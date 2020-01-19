package model;

public class Czytelnik {
	//pola
	private int id;
	private String imie;
	private String nazwisko;
	private String pesel;
	
	//operacje na danych
	public int get_id() { return id; }
	public void set_id(int id) { this.id = id; }
	
	public String get_imie() { return imie; }
	public void set_imie(String imie) { this.imie = imie; }
	
	public String get_nazwisko() { return nazwisko; }
	public void set_nazwisko(String nazwisko) { this.nazwisko = nazwisko; }
	
	public String get_pesel() { return pesel; }
	public void set_pesel(String pesel) { this.pesel = pesel; }
	
	//konstruktory
	public Czytelnik() {}
	public Czytelnik(int id, String imie, String nazwisko, String pesel) {
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.pesel = pesel;
	}
	
	public String toString() {
		return id + "\t" + imie + "\t" + nazwisko + "\t" + pesel;
	}
}
