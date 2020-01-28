package model;

public class Przypis {
	//pola - kolumny
	private int id_przypis;
	private int id_grupa;
	private int id_zadanie;
	
	//konstruktory
	public Przypis() {}
	public Przypis(int id_przypis, int id_grupa, int id_zadanie) {
		this.id_przypis = id_przypis;
		this.id_grupa = id_grupa;
		this.id_zadanie = id_zadanie;
	}
	
	//obs³uga danych
	public int pobierz_id_przypis() { return id_przypis; }
	public void ustaw_id_przypis(int id_przypis) { this.id_przypis = id_przypis; }
	
	public int pobierz_id_grupa() { return id_grupa; }
	public void ustaw_id_grupa(int id_grupa) { this.id_grupa = id_grupa; }
	
	public int pobierz_id_zadanie() { return id_zadanie; }
	public void ustaw_id_zadanie(int id_zadanie) { this.id_zadanie = id_zadanie; }
}
