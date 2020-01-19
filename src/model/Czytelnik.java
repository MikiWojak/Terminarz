package model;

public class Czytelnik {
	//pola
	private int id_czytelnika;
	private String imie;
	private String nazwisko;
	private String pesel;
	
	//operacje na danych
	public int get_id() { return id_czytelnika; }
	public void set_id(int id_czytelnika) { this.id_czytelnika = id_czytelnika; }
	
	public String get_imie() { return imie; }
	public void set_imie(String imie) { this.imie = imie; }
	
	public String get_nazwisko() { return nazwisko; }
	public void set_nazwisko(String nazwisko) { this.nazwisko = nazwisko; }
	
	public String get_pesel() { return pesel; }
	public void set_pesel(String pesel) { this.pesel = pesel; }
	
	//konstruktory
	public Czytelnik() {}
	public Czytelnik(int id_czytelnika, String imie, String nazwisko, String pesel) {
		this.id_czytelnika = id_czytelnika;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.pesel = pesel;
	}
	
	public String toString() {
		return id_czytelnika + "\t" + imie + "\t" + nazwisko + "\t" + pesel;
	}
}
