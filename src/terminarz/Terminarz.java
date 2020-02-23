package terminarz;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import model.Grupa;
import model.Zadanie;
import model.Zlozone;

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
			System.err.println("B³¹d przy wstawianiu przypisania!");
			e.printStackTrace();
			
			return false;
		}
		return true;
	}
	
	//lista wszystkich zadan
	public List<Zadanie>lista_zadania() {
		List<Zadanie>zadania = new LinkedList<Zadanie>();
		
		try {
			ResultSet wynik = stat.executeQuery("SELECT * FROM zadania ORDER BY data_zadanie");
			int id_zadanie;
			Date data_zadanie;
			String tytul_zadanie, opis_zadanie, priorytet_zadanie;
			boolean czy_wykonane;
			
			while(wynik.next()) {
				id_zadanie = wynik.getInt("id_zadanie");
				data_zadanie = wynik.getDate("data_zadanie");
				tytul_zadanie = wynik.getString("tytul_zadanie");
				opis_zadanie = wynik.getString("opis_zadanie");
				priorytet_zadanie = wynik.getString("priorytet_zadanie");
				czy_wykonane = wynik.getBoolean("czy_wykonane");
				
				zadania.add(new Zadanie(id_zadanie, data_zadanie, tytul_zadanie, opis_zadanie, priorytet_zadanie, czy_wykonane));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return zadania;
	}
	
	//lista zadan z poszczegolnej grupy
	public List<Zadanie>lista_zadania(int id_grupa) {
		List<Zadanie>zadania = new LinkedList<Zadanie>();
		String zapytanie = ""
				+ "SELECT z.id_zadanie, z.data_zadanie, z.tytul_zadanie, z.opis_zadanie, z.priorytet_zadanie, z.czy_wykonane "
				+ "FROM zadania AS z, grupy AS g, przypisania AS p "
				+ "WHERE z.id_zadanie = p.id_zadanie AND g.id_grupa = p.id_grupa AND p.id_grupa = " + id_grupa
				+ " ORDER BY data_zadanie";
		try {
			ResultSet wynik = stat.executeQuery(zapytanie);
			int id_zadanie;
			Date data_zadanie;
			String tytul_zadanie, opis_zadanie, priorytet_zadanie;
			boolean czy_wykonane;
			
			while(wynik.next()) {
				id_zadanie = wynik.getInt("id_zadanie");
				data_zadanie = wynik.getDate("data_zadanie");
				tytul_zadanie = wynik.getString("tytul_zadanie");
				opis_zadanie = wynik.getString("opis_zadanie");
				priorytet_zadanie = wynik.getString("priorytet_zadanie");
				czy_wykonane = wynik.getBoolean("czy_wykonane");
				
				zadania.add(new Zadanie(id_zadanie, data_zadanie, tytul_zadanie, opis_zadanie, priorytet_zadanie, czy_wykonane));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return zadania;
	}
	
	//lista zadan bez przypisanej grupy
		public List<Zadanie>lista_zadania_bez_grupy() {
			List<Zadanie>zadania = new LinkedList<Zadanie>();
			List<Integer>id_zadan_bg = new LinkedList<Integer>();
			id_zadan_bg = zadania_przypisane();
			int id_zadan_bg_rozmiar = id_zadan_bg.size();
			boolean czy_przypisany;
			try {
				ResultSet wynik = stat.executeQuery("SELECT * FROM zadania ORDER BY data_zadanie");
				int id_zadanie;
				Date data_zadanie;
				String tytul_zadanie, opis_zadanie, priorytet_zadanie;
				boolean czy_wykonane;
				
				while(wynik.next()) {
					czy_przypisany = false;
					id_zadanie = wynik.getInt("id_zadanie");
					for(int i = 0; i < id_zadan_bg_rozmiar; i++) {
						if(id_zadanie == id_zadan_bg.get(i)) {
							czy_przypisany = true;
							break;
						}
					}
					
					//co dalej?
					data_zadanie = wynik.getDate("data_zadanie");
					tytul_zadanie = wynik.getString("tytul_zadanie");
					opis_zadanie = wynik.getString("opis_zadanie");
					priorytet_zadanie = wynik.getString("priorytet_zadanie");
					czy_wykonane = wynik.getBoolean("czy_wykonane");
					
					zadania.add(new Zadanie(id_zadanie, data_zadanie, tytul_zadanie, opis_zadanie, priorytet_zadanie, czy_wykonane));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			
			return zadania;
		}
	
	//lista wszystkich grup
	public List<Grupa>lista_grupy() {
		List<Grupa>grupy = new LinkedList<Grupa>();
		
		try {
			ResultSet wynik = stat.executeQuery("SELECT * FROM grupy ORDER BY nazwa_grupa");
			int id_grupa;
			String nazwa_grupa, opis_grupa;
			
			while(wynik.next()) {
				id_grupa = wynik.getInt("id_grupa");
				nazwa_grupa = wynik.getString("nazwa_grupa");
				opis_grupa = wynik.getString("opis_grupa");
				
				grupy.add(new Grupa(id_grupa, nazwa_grupa, opis_grupa));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return grupy;		
	}
	
	//z³o¿one zapytanie do BD - wszystkie wyniki
	public List<Zlozone>lista_zlozone() {
		List<Zlozone>zlozone = new LinkedList<Zlozone>();
		
		try {
			String zapytanie = "SELECT z.id_zadanie, z.data_zadanie, z.tytul_zadanie, z.opis_zadanie, z.priorytet_zadanie, z.czy_wykonane, g.id_grupa, g.nazwa_grupa, g.opis_grupa "
					+ "FROM zadania AS z, grupy AS g, przypisania AS p "
					+ "WHERE z.id_zadanie = p.id_zadanie AND g.id_grupa = p.id_grupa";
			
			ResultSet wynik = stat.executeQuery(zapytanie);
			
			int id_zadanie;
			Date data_zadanie;
			String tytul_zadanie, opis_zadanie, priorytet_zadanie;
			boolean czy_wykonane;
			
			int id_grupa;
			String nazwa_grupa, opis_grupa;
			
			while(wynik.next()) {
				id_zadanie = wynik.getInt("id_zadanie");
				data_zadanie = wynik.getDate("data_zadanie");
				tytul_zadanie = wynik.getString("tytul_zadanie");
				opis_zadanie = wynik.getString("opis_zadanie");
				priorytet_zadanie = wynik.getString("priorytet_zadanie");
				czy_wykonane = wynik.getBoolean("czy_wykonane");
				
				id_grupa = wynik.getInt("id_grupa");
				nazwa_grupa = wynik.getString("nazwa_grupa");
				opis_grupa = wynik.getString("opis_grupa");
				
				zlozone.add(new Zlozone(id_zadanie, data_zadanie, tytul_zadanie, opis_zadanie, priorytet_zadanie, czy_wykonane, id_grupa, nazwa_grupa, opis_grupa));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return zlozone;
	}
	
	//grupy przypisane do danego zadania
	public List<String>lista_przypisanych_grup(int id_zadanie) {
		List<String>przypisane_grupy = new LinkedList<String>();
		
		try {
			String zapytanie = ""
					+ "SELECT g.nazwa_grupa "
					+ "FROM zadania AS z, grupy AS g, przypisania AS p "
					+ "WHERE z.id_zadanie = p.id_zadanie AND g.id_grupa = p.id_grupa AND p.id_zadanie = " + id_zadanie
					+ " ORDER BY g.nazwa_grupa";
			ResultSet wynik = stat.executeQuery(zapytanie);
			
			String nazwa_grupa;
			while(wynik.next()) {
				nazwa_grupa = wynik.getString("nazwa_grupa");
				przypisane_grupy.add(nazwa_grupa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return przypisane_grupy;
	}
	
	//pobranie ID zadañ nale¿¹cych do jakiejkolwiek grupy
	public List<Integer> zadania_przypisane() {
		List<Integer>id_zadan_przypisanych = new LinkedList<Integer>();
		
		try {
			ResultSet wynik = stat.executeQuery("SELECT DISTINCT id_zadanie FROM przypisania");
			int id_zadanie;
			
			while(wynik.next()) {
				id_zadanie = wynik.getInt("id_zadanie");
				id_zadan_przypisanych.add(id_zadanie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return id_zadan_przypisanych;
	}
	
	public boolean modyfikuj_zadanie(int id_zadanie, Date data_zadanie, String tytul_zadanie, String opis_zadanie, String priorytet_zadanie, boolean czy_wykonane) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement(
						"UPDATE zadania SET "
					+	"data_zadanie = ?, "
					+ 	"tytul_zadanie = ?, "
					+ 	"opis_zadanie = ?, "
					+ 	"priorytet_zadanie = ?, "
					+ 	"czy_wykonane = ? "
					+ 	"WHERE id_zadanie = ?");
			
			prepStmt.setDate(1, data_zadanie);
			prepStmt.setString(2, tytul_zadanie);
			prepStmt.setString(3, opis_zadanie);
			prepStmt.setString(4, priorytet_zadanie);
			prepStmt.setBoolean(5, czy_wykonane);
			prepStmt.setInt(6, id_zadanie);
			
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("B³¹d przy modyfikacji zadania!");
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
