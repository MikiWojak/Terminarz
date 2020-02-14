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
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Program{
	
	private Terminarz terminarz;				//po³¹czenie z BD
	private List<Zadanie>zadania;				//lista zadania
	private List<Grupa>grupy;
	private List<Przypis>przypisania;
	private List<Zlozone>zlozone;
	private List<String>przypisane_grupy;
	
	private JFrame frame;
	private JPanel zad_lista;
	private JPanel zad_szczegowy;
	private JLayeredPane warstwy;
	private JButton btn_zad_lista;
	private JButton btn_zad_szczegoly;
	private JLabel tytul_szczegoly;
	private JLabel tytul_lista;
	private JList lista_zad;
	private JLabel tresc_data_zad;
	private JLabel tresc_tytul_zad;
	private JLabel tresc_opis_zad;
	private JLabel tresc_priorytet_zad;
	private JLabel tresc_wykonane;
	private JLabel opis_data_zad;
	private JLabel opis_tytul_zad;
	private JLabel opis_wykonane;
	private JLabel opis_opis_zad;
	private JLabel opis_priorytet_zad;
	private JLabel opis_id_zad;
	private JLabel tresc_id_zad;
	private JScrollPane scrollPane;
	private Choice wybor_grupa;
	private JLabel opis_grupy;
	private JLabel tresc_grupy;
	private JPanel gru_lista;
	private JPanel gru_szczegoly;
	private JMenuBar menu;
	private JMenu mn_widok;
	private JMenuItem mn_it_zadania;
	private JMenuItem mn_it_grupy;

	/**
	 * Create the application.
	 */
	public Program() {
		initialize();
		inicjalizacja_pol();
		
		//lista jest domyslnym okienkiem
		//tworzona PO inicjalizacji tabeli
		lista_rekordy();
		
		wybor_grupy();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1100, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		warstwy = new JLayeredPane();
		warstwy.setBounds(10, 39, 1058, 575);
		frame.getContentPane().add(warstwy);
		warstwy.setLayout(new CardLayout(0, 0));
		
		zad_lista = new JPanel();
		warstwy.add(zad_lista, "name_762839293036754");
		zad_lista.setLayout(null);
		
		tytul_lista = new JLabel("Lista zada\u0144");
		tytul_lista.setHorizontalAlignment(SwingConstants.CENTER);
		tytul_lista.setFont(new Font("Arial", Font.PLAIN, 20));
		tytul_lista.setBounds(12, 13, 672, 41);
		zad_lista.add(tytul_lista);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 67, 1036, 440);
		zad_lista.add(scrollPane);
		
		lista_zad = new JList();
		scrollPane.setViewportView(lista_zad);
		lista_zad.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		btn_zad_szczegoly = new JButton("Szczeg\u00F3\u0142y\r\n");
		btn_zad_szczegoly.setBounds(12, 520, 133, 39);
		zad_lista.add(btn_zad_szczegoly);
		btn_zad_szczegoly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lista_zad.getSelectedIndex() >= 0) {
					zmiana_panelu(zad_szczegowy);
					zadanie_szczegoly(lista_zad.getSelectedIndex());
				}
			}
		});
		btn_zad_szczegoly.setFont(new Font("Arial", Font.PLAIN, 20));
		
		wybor_grupa = new Choice();
		wybor_grupa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(wybor_grupa.getSelectedIndex() > 0) {
					int id_grupa = grupy.get(wybor_grupa.getSelectedIndex() - 1).pobierz_id_grupa();
					lista_rekordy(id_grupa);
				} else {
					lista_rekordy();
				}
			}
		});
		wybor_grupa.setFont(new Font("Arial", Font.PLAIN, 20));
		wybor_grupa.setBounds(693, 13, 355, 41);
		zad_lista.add(wybor_grupa);
		
		zad_szczegowy = new JPanel();
		warstwy.add(zad_szczegowy, "name_762878183462982");
		zad_szczegowy.setLayout(null);
		
		tytul_szczegoly = new JLabel("Szczeg\u00F3\u0142y");
		tytul_szczegoly.setHorizontalAlignment(SwingConstants.CENTER);
		tytul_szczegoly.setFont(new Font("Arial", Font.PLAIN, 20));
		tytul_szczegoly.setBounds(12, 13, 1034, 41);
		zad_szczegowy.add(tytul_szczegoly);
		
		opis_data_zad = new JLabel("Data");
		opis_data_zad.setVerticalAlignment(SwingConstants.TOP);
		opis_data_zad.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_data_zad.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_data_zad.setBounds(22, 105, 94, 25);
		zad_szczegowy.add(opis_data_zad);
		
		opis_tytul_zad = new JLabel("Tytu\u0142");
		opis_tytul_zad.setVerticalAlignment(SwingConstants.TOP);
		opis_tytul_zad.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_tytul_zad.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_tytul_zad.setBounds(22, 143, 94, 25);
		zad_szczegowy.add(opis_tytul_zad);
		
		opis_opis_zad = new JLabel("Opis");
		opis_opis_zad.setVerticalAlignment(SwingConstants.TOP);
		opis_opis_zad.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_opis_zad.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_opis_zad.setBounds(22, 181, 94, 25);
		zad_szczegowy.add(opis_opis_zad);
		
		opis_priorytet_zad = new JLabel("Priorytet");
		opis_priorytet_zad.setVerticalAlignment(SwingConstants.TOP);
		opis_priorytet_zad.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_priorytet_zad.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_priorytet_zad.setBounds(22, 294, 94, 25);
		zad_szczegowy.add(opis_priorytet_zad);
		
		opis_wykonane = new JLabel("Wykonane");
		opis_wykonane.setVerticalAlignment(SwingConstants.TOP);
		opis_wykonane.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_wykonane.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_wykonane.setBounds(22, 332, 94, 25);
		zad_szczegowy.add(opis_wykonane);
		
		tresc_data_zad = new JLabel("Data zadania");
		tresc_data_zad.setVerticalAlignment(SwingConstants.TOP);
		tresc_data_zad.setHorizontalAlignment(SwingConstants.LEFT);
		tresc_data_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_data_zad.setBounds(128, 105, 918, 25);
		zad_szczegowy.add(tresc_data_zad);
		
		tresc_tytul_zad = new JLabel("Tytu\u0142 zadania");
		tresc_tytul_zad.setVerticalAlignment(SwingConstants.TOP);
		tresc_tytul_zad.setHorizontalAlignment(SwingConstants.LEFT);
		tresc_tytul_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_tytul_zad.setBounds(128, 143, 918, 25);
		zad_szczegowy.add(tresc_tytul_zad);
		
		tresc_opis_zad = new JLabel("Opis zadania");
		tresc_opis_zad.setVerticalAlignment(SwingConstants.TOP);
		tresc_opis_zad.setHorizontalAlignment(SwingConstants.LEFT);
		tresc_opis_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_opis_zad.setBounds(128, 181, 918, 100);
		zad_szczegowy.add(tresc_opis_zad);
		
		tresc_priorytet_zad = new JLabel("Priorytet zadania");
		tresc_priorytet_zad.setVerticalAlignment(SwingConstants.TOP);
		tresc_priorytet_zad.setHorizontalAlignment(SwingConstants.LEFT);
		tresc_priorytet_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_priorytet_zad.setBounds(128, 294, 918, 25);
		zad_szczegowy.add(tresc_priorytet_zad);
		
		tresc_wykonane = new JLabel("Czy wykonane zadanie");
		tresc_wykonane.setVerticalAlignment(SwingConstants.TOP);
		tresc_wykonane.setHorizontalAlignment(SwingConstants.LEFT);
		tresc_wykonane.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_wykonane.setBounds(128, 332, 918, 25);
		zad_szczegowy.add(tresc_wykonane);
		
		btn_zad_lista = new JButton("Wr\u00F3\u0107");
		btn_zad_lista.setBounds(12, 523, 133, 39);
		zad_szczegowy.add(btn_zad_lista);
		btn_zad_lista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zmiana_panelu(zad_lista);
			}
		});
		btn_zad_lista.setFont(new Font("Arial", Font.PLAIN, 20));
		
		opis_id_zad = new JLabel("ID w BD");
		opis_id_zad.setVerticalAlignment(SwingConstants.TOP);
		opis_id_zad.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_id_zad.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_id_zad.setBounds(22, 370, 94, 25);
		zad_szczegowy.add(opis_id_zad);
		
		tresc_id_zad = new JLabel("ID zadania");
		tresc_id_zad.setVerticalAlignment(SwingConstants.TOP);
		tresc_id_zad.setHorizontalAlignment(SwingConstants.LEFT);
		tresc_id_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_id_zad.setBounds(128, 370, 918, 25);
		zad_szczegowy.add(tresc_id_zad);
		
		opis_grupy = new JLabel("Grupy");
		opis_grupy.setVerticalAlignment(SwingConstants.TOP);
		opis_grupy.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_grupy.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_grupy.setBounds(22, 67, 94, 25);
		zad_szczegowy.add(opis_grupy);
		
		tresc_grupy = new JLabel("Grupy przypisane do zadania");
		tresc_grupy.setVerticalAlignment(SwingConstants.TOP);
		tresc_grupy.setHorizontalAlignment(SwingConstants.LEFT);
		tresc_grupy.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_grupy.setBounds(128, 67, 918, 25);
		zad_szczegowy.add(tresc_grupy);
		
		gru_lista = new JPanel();
		warstwy.add(gru_lista, "name_165903459099969");
		
		gru_szczegoly = new JPanel();
		warstwy.add(gru_szczegoly, "name_165906724193228");
		
		menu = new JMenuBar();
		menu.setBounds(0, 0, 1082, 26);
		frame.getContentPane().add(menu);
		
		mn_widok = new JMenu("Widok");
		mn_widok.setFont(new Font("Arial", Font.PLAIN, 16));
		menu.add(mn_widok);
		
		mn_it_zadania = new JMenuItem("Zadania");
		mn_it_zadania.setFont(new Font("Arial", Font.PLAIN, 16));
		mn_widok.add(mn_it_zadania);
		
		mn_it_grupy = new JMenuItem("Grupy");
		mn_it_grupy.setFont(new Font("Arial", Font.PLAIN, 16));
		mn_widok.add(mn_it_grupy);
		
		frame.setVisible(true);
	}
	
	public void inicjalizacja_pol() {				
		zadania = new LinkedList<Zadanie>();
		grupy = new LinkedList<Grupa>();
		przypisania = new LinkedList<Przypis>();
		zlozone = new LinkedList<Zlozone>();
		przypisane_grupy = new LinkedList<String>();
	}
	
	public void zmiana_panelu(JPanel panel) {
		warstwy.removeAll();
		warstwy.add(panel);
		warstwy.repaint();
		warstwy.revalidate();
	}
	
	public void lista_zad() {
		terminarz = new Terminarz();
		zadania = terminarz.lista_zadania();
		terminarz.zamknij_polaczenie();
	}
	
	public void lista_rekordy() {
		lista_zad();
		lista_zad.setModel(lista_zadania());
	}
	
	public void lista_zad(int id_grupa) {
		terminarz = new Terminarz();
		zadania = terminarz.lista_zadania(id_grupa);
		terminarz.zamknij_polaczenie();
	}
	
	public void lista_rekordy(int id_grupa) {
		lista_zad(id_grupa);
		lista_zad.setModel(lista_zadania());
	}
	
	public void lista_przypisane_grupy(int id_zadanie) {
		//Pobranie listy grup
		terminarz = new Terminarz();
		przypisane_grupy = terminarz.lista_przypisanych_grup(id_zadanie);
		terminarz.zamknij_polaczenie();
	}
	
	//do listy w programie
	public DefaultListModel<Object> lista_zadania() {
		DefaultListModel<Object>lista = new DefaultListModel<Object>();
		String czy_wykonane;
		try {
			for(int i = 0; i < zadania.size(); i++) {
				if(zadania.get(i).pobierz_czy_wykonane()) { czy_wykonane = "*"; }
				else { czy_wykonane = " "; }
					
				lista.addElement(czy_wykonane + "   " + zadania.get(i).pobierz_data_zadanie().toString() + "   " + zadania.get(i).pobierz_tytul_zadanie());
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
				
		return lista;
	}
	
	public void zadanie_szczegoly(int index) {
		//lista grup
		int id_zadanie = zadania.get(index).pobierz_id_zadanie();
		lista_przypisane_grupy(id_zadanie);
		String grupy = "";
		for(int i = 0; i < przypisane_grupy.size(); i++) {
			grupy = grupy + przypisane_grupy.get(i) + "   ";
		}
		
		//czy wykonane
		String czy_wykonane;
		if (zadania.get(index).pobierz_czy_wykonane()) { czy_wykonane = "tak"; }
		else { czy_wykonane = "nie"; }
		
		//wyœwietlenie szczegó³ów
		tresc_grupy.setText(grupy);
		tresc_data_zad.setText(zadania.get(index).pobierz_data_zadanie().toString());
		tresc_tytul_zad.setText(zadania.get(index).pobierz_tytul_zadanie());
		tresc_opis_zad.setText(zadania.get(index).pobierz_opis_zadanie());
		tresc_priorytet_zad.setText(zadania.get(index).pobierz_priorytet_zadanie());
		tresc_wykonane.setText(czy_wykonane);
		tresc_id_zad.setText("" + id_zadanie);
	}
	
	public void lista_gru() {
		terminarz = new Terminarz();
		grupy = terminarz.lista_grupy();
		terminarz.zamknij_polaczenie();
	}
	
	public void wybor_grupy() {
		//przygotowanie
		lista_gru();
		int ilosc_grup = grupy.size();
		//wybór
		wybor_grupa.add("(nic)");
		for(int i = 0; i < ilosc_grup; i++) { wybor_grupa.add(grupy.get(i).pobierz_nazwa_grupa()); }
	}
	
	//DEBUG
	public void lista_zadania_debug() {
		System.out.println("Zadania:");
		for(int i = 0 ; i < zadania.size(); i++) {
			System.out.println("\t" + zadania.get(i).pobierz_id_zadanie());
			System.out.println("\t" + zadania.get(i).pobierz_data_zadanie().toString());
			System.out.println("\t" + zadania.get(i).pobierz_tytul_zadanie());
			System.out.println("\t" + zadania.get(i).pobierz_opis_zadanie());
			System.out.println("\t" + zadania.get(i).pobierz_priorytet_zadanie());
			System.out.println("\t" + zadania.get(i).pobierz_czy_wykonane());
			System.out.println();
		}
	}
	
	public void lista_przypisanych_grup_debug(int id_zadanie) {
		lista_przypisane_grupy(id_zadanie);
		
		System.out.println("Przypisane grupy");
		for(int i = 0; i < przypisane_grupy.size(); i++) {
			System.out.println("\t" + przypisane_grupy.get(i));
		}
		System.out.println();
	}
}
