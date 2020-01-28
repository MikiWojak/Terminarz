package model;

public class Grupa {
	//pola - kolumny
	private int id_grupa;
	private String nazwa_grupa;
	private String opis_grupa;
	
	//konstruktory
	public Grupa() {}
	public Grupa(int id_grupa, String nazwa_grupa, String opis_grupa) {
		this.id_grupa = id_grupa;
		this.nazwa_grupa = nazwa_grupa;
		this.opis_grupa = opis_grupa;
	}
	
	//obs³uga danych
	public int pobierz_id_grupa() { return id_grupa; }
	public void ustaw_id_grupa(int id_grupa) { this.id_grupa = id_grupa; }
	
	public String pobierz_nazwa_grupa() { return nazwa_grupa; }
	public void ustaw_nazwa_grupa(String nazwa_grupa) { this.nazwa_grupa = nazwa_grupa; }
	
	public String pobierz_opis_grupa() { return opis_grupa; }
	public void ustaw_opis_grupa(String opis_grupa) { this.opis_grupa = opis_grupa; }
}
