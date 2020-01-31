package terminarz;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Terminarz {
	//sta³e
	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DB_URL = "jdbc:sqlite:terminarz.db";		//baza danych
	
	//pola
	private Connection conn;
	private Statement stat;
	
	//konstruktor
	public Terminarz() {
		//sterownik JDBC
		try {
			Class.forName(Terminarz.DRIVER);
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
		
		//utworzenie tabel
		utworz_tabele();
	}

	private boolean utworz_tabele() {
		//zapytania do tworzenia tabel (o ile nie istniej¹)
		String tabela_zadania = "CREATE TABLE IF NOT EXISTS zadania("
				+ "id_zadanie INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "data_zadanie DATE,"
				+ "tytul_zadanie VARCHAR(255),"
				+ "opis_zadanie TEXT,"
				+ "priorytet_zadanie TEXT,"
				+ "czy_wykonane TINYINT(1))";
		
		String tabela_grupy = "CREATE TABLE IF NOT EXISTS grupy("
				+ "id_grupa INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "nazwa_grupa VARCHAR(255),"
				+ "opis_grupa TEXT)";
		
		String tabela_przypisania = "CREATE TABLE IF NOT EXISTS przypisania("
				+ "id_przypis INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "id_grupa INT,"
				+ "id_zadanie INT)";
		try {
			//wykonanie zapytañ
			stat.execute(tabela_zadania);
			stat.execute(tabela_grupy);
			stat.execute(tabela_przypisania);
		} catch (SQLException e) {
			System.err.println("B³¹d przy tworzeniu tabel!");
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	//wstawienie rekordu do tabeli zadania
	public boolean wstaw_zadanie(Date data_zadanie, String tytul_zadanie, String opis_zadanie, String priorytet_zadanie, boolean czy_wykonane) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement(
				"INSERT INTO zadania VALUES (NULL, ?, ?, ?, ?, ?)");
			
			prepStmt.setDate(1, data_zadanie);
			prepStmt.setString(2, tytul_zadanie);
			prepStmt.setString(3, opis_zadanie);
			prepStmt.setString(4, priorytet_zadanie);
			prepStmt.setBoolean(5, czy_wykonane);
			
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("B³¹d przy wstawianiu zadania!");
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	//wstawienie rekordu do tabeli grupy
	public boolean wstaw_grupe(String nazwa_grupa, String opis_grupa) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement(
				"INSERT INTO grupy VALUES (NULL, ?, ?)");
			
			prepStmt.setString(1, nazwa_grupa);
			prepStmt.setString(2, opis_grupa);
			
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("B³¹d przy wstawianiu grupy!");
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	//wstawienie rekordu do tabeli przypisania
	public boolean wstaw_przypisanie(int id_grupa, int id_zadanie) {
		try {
			PreparedStatement prepStmp = conn.prepareStatement(
				"INSERT INTO przypisania VALUES (NULL, ?, ?)");
			
			prepStmp.setInt(1, id_grupa);
			prepStmp.setInt(2, id_zadanie);
			
			prepStmp.execute();
		} catch (SQLException e) {
			System.err.println("B³¹d przy wstawianiu przzypisania!");
			e.printStackTrace();
			
			return false;
		}
		return true;
	}
	
	//zamkniecie polaczenia
	public void zamknij_polaczenie() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.err.println("Problem z zamkniêciem po³¹czenia!");
			e.printStackTrace();
		}
	}
}
