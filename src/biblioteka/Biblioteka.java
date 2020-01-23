package biblioteka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

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
	
	//wstawianie rekordu do tabeli czytelnicy
	public boolean insert_czytelnik(String imie, String nazwisko, String pesel) {
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(
					"INSERT INTO czytelnicy VALUES (NULL, ?, ?, ?);");
			
			prep_stmt.setString(1, imie);
			prep_stmt.setString(2, nazwisko);
			prep_stmt.setString(3, pesel);
			
			prep_stmt.execute();
		} catch (SQLException e) {
			System.err.println("B³¹d przy wstawianiu czytelnika!");
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	//wstawianie rekordu do tabeli ksiazki
	public boolean insert_ksiazka(String tytul, String autor) {
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(
					"INSERT INTO ksiazki VALUES (NULL, ?, ?);");
			
			prep_stmt.setString(1, tytul);
			prep_stmt.setString(2, autor);
			
			prep_stmt.execute();
		} catch (SQLException e) {
			System.err.println("B³¹d przy wstawianiu ksiazki!");
			//e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	//wstawianie rekordu do tabeli wypozyczenia
	public boolean insert_wypozyczenia(int id_czytelnika, int id_ksiazki) {
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(
					"INSERT INTO wypozyczenia VALUES (NULL, ?, ?);");
			
			prep_stmt.setInt(1, id_czytelnika);
			prep_stmt.setInt(2, id_ksiazki);
			
			prep_stmt.execute();
		} catch (SQLException e) {
			System.err.println("B³¹d przy wypo¿yczaniu!");
			//e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	//pobieranie wszystkich rekordów z tabeli czytelnicy
	public List<Czytelnik>select_czytelnicy() {
		List<Czytelnik>czytelnicy = new LinkedList<Czytelnik>();
		
		try {
			ResultSet result = stat.executeQuery("SELECT * FROM czytelnicy");
			int id_czytelnika;
			String imie, nazwisko, pesel;
			
			while (result.next()) {
				id_czytelnika = result.getInt("id_czytelnika");
				imie = result.getString("imie");
				nazwisko = result.getString("nazwisko");
				pesel = result.getString("pesel");
				
				czytelnicy.add(new Czytelnik(id_czytelnika, imie, nazwisko, pesel));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return czytelnicy;
	}
	
	//pobieranie wszystkich rekordów z tabeli ksiazki
	public List<Ksiazka>select_ksiazki() {
		List<Ksiazka>ksiazki = new LinkedList<Ksiazka>();
		
		try {
			ResultSet result = stat.executeQuery("SELECT * FROM ksiazki");
			int id_ksiazki;
			String tytul, autor;
			
			while(result.next()) {
				id_ksiazki = result.getInt("id_ksiazki");
				tytul = result.getString("tytul");
				autor = result.getString("autor");
				
				ksiazki.add(new Ksiazka(id_ksiazki, tytul, autor));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return ksiazki;
	}
	
	public List<Wypozyczenia>select_wypozyczenia() {
		List<Wypozyczenia>wypozyczenia = new LinkedList<Wypozyczenia>();
		
		try {
			ResultSet result = stat.executeQuery("SELECT * FROM wypozyczenia");
			int id_wypozyczenia, id_czytelnika, id_ksiazki;
			
			while(result.next()) {
				id_wypozyczenia = result.getInt("id_wypozyczenia");
				id_czytelnika = result.getInt("id_czytelnika");
				id_ksiazki = result.getInt("id_ksiazki");
				
				wypozyczenia.add(new Wypozyczenia(id_wypozyczenia, id_ksiazki, id_czytelnika));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return wypozyczenia;
	}
	
	//zamkniêcie po³¹czenia
	public void close_connection() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.err.println("Problem z zamkniêciem po³¹czenia!");
			e.printStackTrace();
		}
	}
}
