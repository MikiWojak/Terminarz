package terminarz;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
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
		String tabela_zadania = "CREATE TABLE IF NOT EXISTS zadania"
				+ "id_zadanie INT PRIMARY KEY AUTOINCREMENT,"
				+ "data_zadanie DATE,"
				+ "tytul_zadanie VARCHAR(255),"
				+ "opis_zadanie TEXT,"
				+ "priorytet_zadanie TEXT,"
				+ "czy_wykonane TINYINT(1)";
		String tabela_grupy = "CREATE TABLE IF NOT EXISTS grupy"
				+ "id_grupa INT PRIMARY KEY AUTOINCREMENT,"
				+ "nazwa_grupa VARCHAR(255),"
				+ "opis_grupa TEXT";
		String tabela_przypisania = "CREATE TABLE IF NOT EXISTS przypisania"
				+ "id_przypis INT PRIMARY KEY AUTOINCREMENT,"
				+ "id_grupa INT,"
				+ "id_zadanie INT";
		
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
}
