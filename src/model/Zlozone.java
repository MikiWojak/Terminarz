package model;

import java.sql.Date;

public class Zlozone extends Zadanie{
	//pola - grupy
	private int id_grupa;
	private String nazwa_grupa;
	private String opis_grupa;
	
	public Zlozone() {}
	public Zlozone(int id_zadanie, Date data_zadanie, String tytul_zadanie, String opis_zadanie, String priorytet_zadanie, boolean czy_wykonane, int id_grupa, String nazwa_grupa, String opis_grupa) {
		super(id_zadanie, data_zadanie, tytul_zadanie, opis_zadanie, priorytet_zadanie, czy_wykonane);
		
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
