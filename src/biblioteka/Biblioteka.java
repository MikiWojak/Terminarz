package biblioteka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import model.Czytelnik;
import model.Ksiazka;
import model.Wypozyczenia;

public class Biblioteka {
	//pola
	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DB_URL = "jdbc:sqlite:biblioteka.db";
	
	private Connection conn;
	private Statement stat;
	
	//konstruktor
	public Biblioteka() {
		//za³adowanie sterownika
		try {
			Class.forName(Biblioteka.DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("Brak sterownika JDBC!");
			e.printStackTrace();
		}
		
		//otwarcie po³¹czenia
		try {
			conn = DriverManager.getConnection(DB_URL);
			stat = conn.createStatement();
		} catch (SQLException e) {
			System.err.println("Problem z otwarciem po³¹czenia!");
			e.printStackTrace();
		}
		
		//tworzenie tabel
		create_tables();
	}
	
	//tworzenie tabel
	private boolean create_tables() {
		String create_czytelnicy = "CREATE TABLE IF NOT EXISTS czytelnicy"
				+ "(id_czytelnika INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "imie VARCHAR(255),"
				+ "nazwisko VARCHAR(255),"
				+ "pesel VARCHAR(255))";
		String create_ksiazki = "CREATE TABLE IF NOT EXISTS ksiazki"
				+ "(id_ksiazki INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "tytul VARCHAR(255),"
				+ "autor VARCHAR(255))";
		String create_wypozyczenia = "CREATE TABLE IF NOT EXISTS wypozyczenia"
				+ "(id_wypozyczenia INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "id_czytelnika INT,"
				+ "id_ksiazki INT)";
		
		try {
			stat.execute(create_czytelnicy);
			stat.execute(create_ksiazki);
			stat.execute(create_wypozyczenia);
		} catch (SQLException e) {
			System.err.println("B³¹d przy tworzeniu tabeli!");
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
}
