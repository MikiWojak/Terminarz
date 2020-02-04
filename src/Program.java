import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import terminarz.Terminarz;
import model.Grupa;
import model.Przypis;
import model.Zadanie;
import model.Zlozone;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class Program {
	
	private Terminarz terminarz;				//po³¹czenie z BD
	private List<Zadanie>zadania;				//lista zadania
	private List<Grupa>grupy;
	private List<Przypis>przypisania;
	private List<Zlozone>zlozone;
	
	private JFrame frame;
	private JLabel lbl_nazwa_grupa;
	private JLabel lbl_opis_grupa;
	private JLabel lbl_tytul_zadanie;
	private JLabel lbl_data_zadanie;
	private JLabel lbl_opis_zadanie;
	private JLabel lbl_priorytet_zadanie;
	private JLabel lbl_czy_wykonane;
	private JButton Aktualizuj;
	private JList lista;

	/**
	 * Create the application.
	 */
	public Program() {
		inicjalizacja_pol();
		initialize();
		
		terminarz = new Terminarz();
		zadania = terminarz.pobierz_zadania();
		lista.setModel(lista_zadania());
		terminarz.zamknij_polaczenie();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1111, 824);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lbl_nazwa_grupa = new JLabel("New label");
		lbl_nazwa_grupa.setFont(new Font("Arial", Font.PLAIN, 20));
		lbl_nazwa_grupa.setBounds(24, 436, 1069, 30);
		frame.getContentPane().add(lbl_nazwa_grupa);
		
		lbl_opis_grupa = new JLabel("New label");
		lbl_opis_grupa.setFont(new Font("Arial", Font.PLAIN, 20));
		lbl_opis_grupa.setBounds(24, 479, 1069, 30);
		frame.getContentPane().add(lbl_opis_grupa);
		
		lbl_tytul_zadanie = new JLabel("New label");
		lbl_tytul_zadanie.setFont(new Font("Arial", Font.PLAIN, 20));
		lbl_tytul_zadanie.setBounds(24, 522, 1069, 30);
		frame.getContentPane().add(lbl_tytul_zadanie);
		
		lbl_data_zadanie = new JLabel("New label");
		lbl_data_zadanie.setFont(new Font("Arial", Font.PLAIN, 20));
		lbl_data_zadanie.setBounds(24, 565, 1069, 30);
		frame.getContentPane().add(lbl_data_zadanie);
		
		lbl_opis_zadanie = new JLabel("New label");
		lbl_opis_zadanie.setFont(new Font("Arial", Font.PLAIN, 20));
		lbl_opis_zadanie.setBounds(24, 608, 1069, 30);
		frame.getContentPane().add(lbl_opis_zadanie);
		
		lbl_priorytet_zadanie = new JLabel("New label");
		lbl_priorytet_zadanie.setFont(new Font("Arial", Font.PLAIN, 20));
		lbl_priorytet_zadanie.setBounds(24, 651, 1069, 30);
		frame.getContentPane().add(lbl_priorytet_zadanie);
		
		lbl_czy_wykonane = new JLabel("New label");
		lbl_czy_wykonane.setFont(new Font("Arial", Font.PLAIN, 20));
		lbl_czy_wykonane.setBounds(24, 694, 1069, 30);
		frame.getContentPane().add(lbl_czy_wykonane);
		
		Aktualizuj = new JButton("Aktualizuj");
		Aktualizuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aktualizuj_dane();
			}
		});
		Aktualizuj.setFont(new Font("Arial", Font.PLAIN, 20));
		Aktualizuj.setBounds(871, 740, 222, 37);
		frame.getContentPane().add(Aktualizuj);
		
		lista = new JList();
		lista.setFont(new Font("Arial", Font.PLAIN, 20));
		lista.setBounds(326, 13, 755, 414);
		frame.getContentPane().add(lista);
		
		frame.setVisible(true);
	}
	
	public void inicjalizacja_pol() {				
		zadania = new LinkedList<Zadanie>();
		grupy = new LinkedList<Grupa>();
		przypisania = new LinkedList<Przypis>();
		zlozone = new LinkedList<Zlozone>();
	}
	
	public void aktualizuj_dane() {
		terminarz = new Terminarz();
		zlozone = terminarz.pobierz_zlozone();
		
		String czy_wykonane;
		if (zlozone.get(0).pobierz_czy_wykonane()) { czy_wykonane = "nie"; }
		else { czy_wykonane = "tak"; }
		
		lbl_nazwa_grupa.setText(zlozone.get(0).pobierz_nazwa_grupa());
		lbl_opis_grupa.setText(zlozone.get(0).pobierz_opis_grupa());
		lbl_tytul_zadanie.setText(zlozone.get(0).pobierz_tytul_zadanie());
		lbl_data_zadanie.setText(zlozone.get(0).pobierz_data_zadanie().toString());
		lbl_opis_zadanie.setText(zlozone.get(0).pobierz_opis_zadanie());
		lbl_priorytet_zadanie.setText(zlozone.get(0).pobierz_priorytet_zadanie());
		lbl_czy_wykonane.setText(czy_wykonane);
				
		terminarz.zamknij_polaczenie();
	}
	
	//do listy w programie
	public DefaultListModel<Object> lista_zadania() {
		DefaultListModel<Object>lista = new DefaultListModel<Object>();
			
		try {
			for(int i = 0; i < zadania.size(); i++) {
				lista.addElement(zadania.get(i).pobierz_data_zadanie().toString() + "   " + zadania.get(i).pobierz_tytul_zadanie());
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
			
		return lista;
	}
}
