package model;

import java.sql.Date;

public class Zlozone {
	//pola - zadania
	private int id_zadanie;
	private Date data_zadanie;
	private String tytul_zadanie;
	private String opis_zadanie;
	private String priorytet_zadanie;
	private boolean czy_wykonane;
	
	//pola - grupy
	private int id_grupa;
	private String nazwa_grupa;
	private String opis_grupa;
	
	public Zlozone() {}
	public Zlozone(int id_zadanie, Date data_zadanie, String tytul_zadanie, String opis_zadanie, String priorytet_zadanie, boolean czy_wykonane, int id_grupa, String nazwa_grupa, String opis_grupa) {
		this.id_zadanie = id_zadanie;
		this.data_zadanie = data_zadanie;
		this.tytul_zadanie = tytul_zadanie;
		this.opis_zadanie = opis_zadanie;
		this.priorytet_zadanie = priorytet_zadanie;
		this.czy_wykonane = czy_wykonane;
		
		this.id_grupa = id_grupa;
		this.nazwa_grupa = nazwa_grupa;
		this.opis_grupa = opis_grupa;
	}
	
	//obs³uga danych
	public int pobierz_id_zadanie() { return id_zadanie; }
	public void ustaw_id_zadanie(int id_zadanie) { this.id_zadanie = id_zadanie; }
		
	public Date pobierz_data_zadanie() { return data_zadanie; }
	public void ustaw_data_zadanie(Date data_zadanie) { this.data_zadanie = data_zadanie; }
		
	public String pobierz_tytul_zadanie() { return tytul_zadanie; }
	public void ustaw_tytul_zadanie(String tytul_zadanie) { this.tytul_zadanie = tytul_zadanie; }
		
	public String pobierz_opis_zadanie() { return opis_zadanie; }
	public void ustaw_opis_zadanie(String opis_zadanie) { this.opis_zadanie = opis_zadanie; }
		
	public String pobierz_priorytet_zadanie() { return priorytet_zadanie; }
	public void ustaw_priorytet_zadanie(String priorytet_zadanie) { this.priorytet_zadanie = priorytet_zadanie; }
		
	public boolean pobierz_czy_wykonane() { return czy_wykonane; }
	public void ustaw_czy_wykonane(boolean czy_wykonane) { this.czy_wykonane = czy_wykonane; }
	
	//obs³uga danych
	public int pobierz_id_grupa() { return id_grupa; }
	public void ustaw_id_grupa(int id_grupa) { this.id_grupa = id_grupa; }
		
	public String pobierz_nazwa_grupa() { return nazwa_grupa; }
	public void ustaw_nazwa_grupa(String nazwa_grupa) { this.nazwa_grupa = nazwa_grupa; }
		
	public String pobierz_opis_grupa() { return opis_grupa; }
	public void ustaw_opis_grupa(String opis_grupa) { this.opis_grupa = opis_grupa; }
}
