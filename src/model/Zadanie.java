package model;

import java.sql.Date;

public class Zadanie {
	//pola - kolumny
	protected int id_zadanie;
	protected Date data_zadanie;			//KESTIA DATY!!!
	protected String tytul_zadanie;
	protected String opis_zadanie;
	protected String priorytet_zadanie;
	protected boolean czy_wykonane;
	
	//konstruktory
	public Zadanie() {}
	public Zadanie(int id_zadanie, Date data_zadanie, String tytul_zadanie, String opis_zadanie, String priorytet_zadanie, boolean czy_wykonane) {
		this.id_zadanie = id_zadanie;
		this.data_zadanie = data_zadanie;
		this.tytul_zadanie = tytul_zadanie;
		this.opis_zadanie = opis_zadanie;
		this.priorytet_zadanie = priorytet_zadanie;
		this.czy_wykonane = czy_wykonane;
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
}
