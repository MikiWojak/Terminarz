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
}
