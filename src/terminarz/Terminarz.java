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
		// TODO Auto-generated method stub
		String tabela_zadania = "CREATE TABLE IF NOT EXISTS zadania"
				+ "id_zadanie INT PRIMARY KEY AUTOINCREMENT"
				+ "data_zadanie DATE"
				+ "tytul_zadanie TEXT"
				+ "opis_zadanie TEXT"
				+ "priorytet_zadanie TEXT"
				+ "czy_wykonane TINYINT(1)";
		
		return true;
	}
}
