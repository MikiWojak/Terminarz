import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import terminarz.Terminarz;
import model.Grupa;
import model.Przypis;
import model.Zadanie;
import model.Zlozone;
import okienka.GruEdit;
import okienka.ZadEdit;

import java.awt.Font;

import javax.sound.midi.SysexMessage;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class Program{
	
	private Terminarz terminarz;				//po³¹czenie z BD
	private List<Zadanie>zadania;				//lista zadania
	private List<Grupa>grupy;
	private List<Przypis>przypisania;
	private List<Zlozone>zlozone;
	private List<String>przypisane_grupy;
	private JDialog edycja;
	
	private JFrame frmTerminarz;
	private JPanel zad_lista;
	private JPanel zad_szczegowy;
	private JLayeredPane warstwy;
	private JButton btn_zad_lista;
	private JButton btn_zad_szczegoly;
	private JLabel tytul_szczegoly;
	private JLabel tytul_lista_zad;
	private JList lista_zad;
	private JLabel opis_data_zad;
	private JLabel opis_tytul_zad;
	private JLabel opis_wykonane;
	private JLabel opis_opis_zad;
	private JLabel opis_priorytet_zad;
	private JLabel opis_id_zad;
	private JScrollPane scroll_lista_zad;
	private Choice wybor_grupa;
	private JLabel opis_grupy;
	private JPanel gru_panel;
	private JMenuBar menu;
	private JMenu mn_widok;
	private JMenuItem mn_it_zadania;
	private JMenuItem mn_it_grupy;
	private JLabel tytul_lista_gru;
	private JList lista_gru;
	private JScrollPane scroll_lista_gru;
	private JLabel opis_id_gru;
	private JLabel opis_nazwa_gru;
	private JLabel opis_opis_gru;
	private JTextPane tresc_id_gru;
	private JTextPane tresc_nazwa_gru;
	private JTextPane tresc_opis_gru;
	private JTextPane tresc_grupy;
	private JTextPane tresc_data_zad;
	private JTextPane tresc_tytul_zad;
	private JTextPane tresc_opis_zad;
	private JTextPane tresc_priorytet_zad;
	private JTextPane tresc_wykonane;
	private JTextPane tresc_id_zad;
	private JButton btn_dodaj_zad;
	private JLabel opis_filtr;
	private JButton btn_mod_zad;
	private JScrollPane scroll_opis_szczegoly_zad;
	private JButton btn_usun;
	private JButton btn_zrobione;
	private JButton btn_dodaj_gru;
	private JButton btn_mod_gru;
	private JButton btn_usun_gru;

	/**
	 * Create the application.
	 */
	public Program() {
		initialize();
		inicjalizacja_pol();
		
		//lista jest domyslnym okienkiem
		//tworzona PO inicjalizacji tabeli
		lista_rekordy_zadania();
		lista_rekordy_grupy();
		
		wybor_grupy();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTerminarz = new JFrame();
		frmTerminarz.setTitle("Terminarz");
		frmTerminarz.setBounds(100, 100, 1200, 700);
		frmTerminarz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTerminarz.getContentPane().setLayout(null);
		
		warstwy = new JLayeredPane();
		warstwy.setBounds(10, 39, 1160, 601);
		frmTerminarz.getContentPane().add(warstwy);
		warstwy.setLayout(new CardLayout(0, 0));
		
		zad_lista = new JPanel();
		warstwy.add(zad_lista, "name_762839293036754");
		zad_lista.setLayout(null);
		
		tytul_lista_zad = new JLabel("Lista zada\u0144");
		tytul_lista_zad.setHorizontalAlignment(SwingConstants.CENTER);
		tytul_lista_zad.setFont(new Font("Arial", Font.BOLD, 20));
		tytul_lista_zad.setBounds(12, 13, 1138, 40);
		zad_lista.add(tytul_lista_zad);
		
		scroll_lista_zad = new JScrollPane();
		scroll_lista_zad.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll_lista_zad.setBounds(12, 96, 1138, 440);
		zad_lista.add(scroll_lista_zad);
		
		lista_zad = new JList();
		scroll_lista_zad.setViewportView(lista_zad);
		lista_zad.setFont(new Font("Monospaced", Font.PLAIN, 20));
		lista_zad.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				przycisk_zrobione(lista_zad.getSelectedIndex());
			}
		});
		
		btn_zad_szczegoly = new JButton("Szczeg\u00F3\u0142y\r\n");
		btn_zad_szczegoly.setBounds(12, 549, 133, 39);
		zad_lista.add(btn_zad_szczegoly);
		btn_zad_szczegoly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lista_zad.getSelectedIndex() >= 0) {
					zmiana_panelu(zad_szczegowy);
					zadanie_szczegoly(lista_zad.getSelectedIndex());
				} else { wybierz_rekord(); }								//komunikat
			}
		});
		btn_zad_szczegoly.setFont(new Font("Arial", Font.BOLD, 20));
		
		wybor_grupa = new Choice();
		wybor_grupa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				lista_rekordy_zadania_wybrane();
			}
		});
		wybor_grupa.setFont(new Font("Arial", Font.PLAIN, 20));
		wybor_grupa.setBounds(795, 60, 355, 41);
		zad_lista.add(wybor_grupa);
		
		btn_dodaj_zad = new JButton("Dodaj");
		btn_dodaj_zad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edycja = new ZadEdit();
				//przerwa
				lista_rekordy_zadania();
			}
		});
		btn_dodaj_zad.setFont(new Font("Arial", Font.BOLD, 20));
		btn_dodaj_zad.setBounds(760, 549, 133, 39);
		zad_lista.add(btn_dodaj_zad);
		
		opis_filtr = new JLabel("Filtr grupy");
		opis_filtr.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_filtr.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_filtr.setBounds(12, 60, 777, 30);
		zad_lista.add(opis_filtr);
		
		btn_mod_zad = new JButton("Modyfikuj");
		btn_mod_zad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(lista_zad.getSelectedIndex() >= 0) {
					edycja = new ZadEdit(zadania.get(lista_zad.getSelectedIndex()));
					//przerwa
					lista_rekordy_zadania();
				} else { wybierz_rekord(); }
			}
		});
		btn_mod_zad.setFont(new Font("Arial", Font.BOLD, 20));
		btn_mod_zad.setBounds(905, 549, 133, 39);
		zad_lista.add(btn_mod_zad);
		
		btn_usun = new JButton("Usu\u0144");
		btn_usun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(lista_zad.getSelectedIndex() >= 0) {
					Object nazwy_opcji[] = {"Tak", "Nie"};
					int opcja = JOptionPane.showOptionDialog(
							null, 
							"Na pewno chcesz usun¹æ ten rekord?",
							"Pytanie",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							nazwy_opcji,
							nazwy_opcji[1]);
					if(opcja == 0) { usun_zadanie(); }
				} else { wybierz_rekord(); }
			}
		});
		btn_usun.setFont(new Font("Arial", Font.BOLD, 20));
		btn_usun.setBounds(1050, 549, 100, 39);
		zad_lista.add(btn_usun);
		
		btn_zrobione = new JButton("Zrobione");
		btn_zrobione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sel_index = lista_zad.getSelectedIndex();
				boolean status;
				if(sel_index >= 0) {
					status = zadania.get(sel_index).pobierz_czy_wykonane();
					czy_wykonane_zmiana(sel_index, status);
				} else { wybierz_rekord(); }	
			}
		});
		btn_zrobione.setFont(new Font("Arial", Font.BOLD, 20));
		btn_zrobione.setBounds(157, 549, 150, 39);
		zad_lista.add(btn_zrobione);
		
		zad_szczegowy = new JPanel();
		warstwy.add(zad_szczegowy, "name_762878183462982");
		zad_szczegowy.setLayout(null);
		
		tytul_szczegoly = new JLabel("Szczeg\u00F3\u0142y zadania");
		tytul_szczegoly.setHorizontalAlignment(SwingConstants.CENTER);
		tytul_szczegoly.setFont(new Font("Arial", Font.BOLD, 20));
		tytul_szczegoly.setBounds(12, 13, 1136, 41);
		zad_szczegowy.add(tytul_szczegoly);
		
		opis_data_zad = new JLabel("Data");
		opis_data_zad.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_data_zad.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_data_zad.setBounds(22, 110, 94, 30);
		zad_szczegowy.add(opis_data_zad);
		
		opis_tytul_zad = new JLabel("Tytu\u0142");
		opis_tytul_zad.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_tytul_zad.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_tytul_zad.setBounds(22, 153, 94, 30);
		zad_szczegowy.add(opis_tytul_zad);
		
		opis_opis_zad = new JLabel("Opis");
		opis_opis_zad.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_opis_zad.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_opis_zad.setBounds(22, 196, 94, 120);
		zad_szczegowy.add(opis_opis_zad);
		
		opis_priorytet_zad = new JLabel("Priorytet");
		opis_priorytet_zad.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_priorytet_zad.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_priorytet_zad.setBounds(22, 359, 94, 30);
		zad_szczegowy.add(opis_priorytet_zad);
		
		opis_wykonane = new JLabel("Wykonane");
		opis_wykonane.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_wykonane.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_wykonane.setBounds(22, 402, 94, 30);
		zad_szczegowy.add(opis_wykonane);
		
		btn_zad_lista = new JButton("Wr\u00F3\u0107");
		btn_zad_lista.setBounds(12, 549, 133, 39);
		zad_szczegowy.add(btn_zad_lista);
		btn_zad_lista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zmiana_panelu(zad_lista);
				zadania_szczegoly_reset();
			}
		});
		btn_zad_lista.setFont(new Font("Arial", Font.BOLD, 20));
		
		opis_id_zad = new JLabel("ID w BD");
		opis_id_zad.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_id_zad.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_id_zad.setBounds(22, 445, 94, 30);
		zad_szczegowy.add(opis_id_zad);
		
		opis_grupy = new JLabel("Grupy");
		opis_grupy.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_grupy.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_grupy.setBounds(22, 67, 94, 30);
		zad_szczegowy.add(opis_grupy);
		
		tresc_grupy = new JTextPane();
		tresc_grupy.setEditable(false);
		tresc_grupy.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_grupy.setBounds(128, 67, 1020, 30);
		zad_szczegowy.add(tresc_grupy);
		
		tresc_data_zad = new JTextPane();
		tresc_data_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_data_zad.setEditable(false);
		tresc_data_zad.setBounds(128, 110, 1020, 30);
		zad_szczegowy.add(tresc_data_zad);
		
		tresc_tytul_zad = new JTextPane();
		tresc_tytul_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_tytul_zad.setEditable(false);
		tresc_tytul_zad.setBounds(128, 153, 1020, 30);
		zad_szczegowy.add(tresc_tytul_zad);
		
		scroll_opis_szczegoly_zad = new JScrollPane();
		scroll_opis_szczegoly_zad.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll_opis_szczegoly_zad.setBounds(128, 196, 1020, 150);
		zad_szczegowy.add(scroll_opis_szczegoly_zad);
		
		tresc_opis_zad = new JTextPane();
		scroll_opis_szczegoly_zad.setViewportView(tresc_opis_zad);
		tresc_opis_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_opis_zad.setEditable(false);
		
		tresc_priorytet_zad = new JTextPane();
		tresc_priorytet_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_priorytet_zad.setEditable(false);
		tresc_priorytet_zad.setBounds(128, 359, 1020, 30);
		zad_szczegowy.add(tresc_priorytet_zad);
		
		tresc_wykonane = new JTextPane();
		tresc_wykonane.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_wykonane.setEditable(false);
		tresc_wykonane.setBounds(128, 402, 1020, 30);
		zad_szczegowy.add(tresc_wykonane);
		
		tresc_id_zad = new JTextPane();
		tresc_id_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_id_zad.setEditable(false);
		tresc_id_zad.setBounds(128, 445, 1020, 30);
		zad_szczegowy.add(tresc_id_zad);
		
		gru_panel = new JPanel();
		warstwy.add(gru_panel, "name_165903459099969");
		gru_panel.setLayout(null);
		
		tytul_lista_gru = new JLabel("Lista grup");
		tytul_lista_gru.setHorizontalAlignment(SwingConstants.CENTER);
		tytul_lista_gru.setFont(new Font("Arial", Font.BOLD, 20));
		tytul_lista_gru.setBounds(12, 13, 1136, 41);
		gru_panel.add(tytul_lista_gru);
		
		scroll_lista_gru = new JScrollPane();
		scroll_lista_gru.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll_lista_gru.setBounds(12, 67, 440, 521);
		gru_panel.add(scroll_lista_gru);
		
		lista_gru = new JList();
		scroll_lista_gru.setViewportView(lista_gru);
		lista_gru.setFont(new Font("Monospaced", Font.PLAIN, 20));
		lista_gru.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int index = lista_gru.getSelectedIndex();
				if(index >= 0) { grupa_szczegoly(index); }
			}
		});
		
		opis_id_gru = new JLabel("ID w BD");
		opis_id_gru.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_id_gru.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_id_gru.setBounds(464, 238, 94, 30);
		gru_panel.add(opis_id_gru);
		
		opis_nazwa_gru = new JLabel("Nazwa");
		opis_nazwa_gru.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_nazwa_gru.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_nazwa_gru.setBounds(464, 67, 94, 30);
		gru_panel.add(opis_nazwa_gru);
		
		opis_opis_gru = new JLabel("Opis");
		opis_opis_gru.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_opis_gru.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_opis_gru.setBounds(464, 105, 94, 120);
		gru_panel.add(opis_opis_gru);
		
		tresc_id_gru = new JTextPane();
		tresc_id_gru.setEditable(false);
		tresc_id_gru.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_id_gru.setBounds(570, 238, 578, 30);
		gru_panel.add(tresc_id_gru);
		
		tresc_nazwa_gru = new JTextPane();
		tresc_nazwa_gru.setEditable(false);
		tresc_nazwa_gru.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_nazwa_gru.setBounds(570, 67, 578, 30);
		gru_panel.add(tresc_nazwa_gru);
		
		tresc_opis_gru = new JTextPane();
		tresc_opis_gru.setEditable(false);
		tresc_opis_gru.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_opis_gru.setBounds(570, 105, 578, 120);
		gru_panel.add(tresc_opis_gru);
		
		btn_dodaj_gru = new JButton("Dodaj");
		btn_dodaj_gru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO: handle exception
				edycja = new GruEdit();
				//przerwa
				lista_rekordy_grupy();
			}
		});
		btn_dodaj_gru.setFont(new Font("Arial", Font.BOLD, 20));
		btn_dodaj_gru.setBounds(791, 549, 100, 39);
		gru_panel.add(btn_dodaj_gru);
		
		btn_mod_gru = new JButton("Modyfikuj");
		btn_mod_gru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: handle exception
				if(lista_gru.getSelectedIndex() >= 0) {
					edycja = new GruEdit(null);
					//przerwa
					lista_rekordy_grupy();
				} else { wybierz_rekord(); }
			}
		});
		btn_mod_gru.setFont(new Font("Arial", Font.BOLD, 20));
		btn_mod_gru.setBounds(903, 549, 133, 39);
		gru_panel.add(btn_mod_gru);
		
		btn_usun_gru = new JButton("Usu\u0144");
		btn_usun_gru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: handle exception
				if(lista_gru.getSelectedIndex() >= 0) {
					System.out.println("Usuwanie grupy...");
				} else { wybierz_rekord(); }
			}
		});
		btn_usun_gru.setFont(new Font("Arial", Font.BOLD, 20));
		btn_usun_gru.setBounds(1048, 549, 100, 39);
		gru_panel.add(btn_usun_gru);
		
		menu = new JMenuBar();
		menu.setBounds(0, 0, 1182, 26);
		frmTerminarz.getContentPane().add(menu);
		
		mn_widok = new JMenu("Widok");
		mn_widok.setFont(new Font("Arial", Font.PLAIN, 16));
		menu.add(mn_widok);
		
		mn_it_zadania = new JMenuItem("Zadania");
		mn_it_zadania.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zmiana_panelu(zad_lista);
				lista_rekordy_zadania();
				wybor_grupa.select(0);
			}
		});
		mn_it_zadania.setFont(new Font("Arial", Font.PLAIN, 16));
		mn_widok.add(mn_it_zadania);
		
		mn_it_grupy = new JMenuItem("Grupy");
		mn_it_grupy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zmiana_panelu(gru_panel);
				lista_rekordy_grupy();
				grupa_szczegoly_reset();
			}
		});
		mn_it_grupy.setFont(new Font("Arial", Font.PLAIN, 16));
		mn_widok.add(mn_it_grupy);
		
		frmTerminarz.setVisible(true);
	}

	private void inicjalizacja_pol() {				
		zadania = new LinkedList<Zadanie>();
		grupy = new LinkedList<Grupa>();
		przypisania = new LinkedList<Przypis>();
		zlozone = new LinkedList<Zlozone>();
		przypisane_grupy = new LinkedList<String>();
	}
	
	private void zmiana_panelu(JPanel panel) {
		warstwy.removeAll();
		warstwy.add(panel);
		warstwy.repaint();
		warstwy.revalidate();
	}
	
	private void lista_zad() {
		terminarz = new Terminarz();
		zadania = terminarz.lista_zadania();
		terminarz.zamknij_polaczenie();
	}

	private void lista_zad(int id_grupa) {
		terminarz = new Terminarz();
		zadania = terminarz.lista_zadania(id_grupa);
		terminarz.zamknij_polaczenie();
	}
	
	private void lista_rekordy_zadania() {
		lista_zad();
		lista_zad.setModel(rekordy_zadania());
	}
	
	
	private void lista_rekordy_zadania(int id_grupa) {
		lista_zad(id_grupa);
		lista_zad.setModel(rekordy_zadania());
	}
	
	private void lista_rekodry_zadania_brak_grupy() {
		terminarz = new Terminarz();
		zadania = terminarz.lista_zadania_bez_grupy();
		terminarz.zamknij_polaczenie();
		
		lista_zad.setModel(rekordy_zadania());
	}
	
	private void lista_przypisane_grupy(int id_zadanie) {
		//Pobranie listy grup
		terminarz = new Terminarz();
		przypisane_grupy = terminarz.lista_przypisanych_grup(id_zadanie);
		terminarz.zamknij_polaczenie();
	}
	
	//do listy zadañ w programie
	private DefaultListModel<Object> rekordy_zadania() {
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
	
	private void zadanie_szczegoly(int index) {
		//lista grup
		int id_zadanie = zadania.get(index).pobierz_id_zadanie();
		lista_przypisane_grupy(id_zadanie);
		String grupy = "";
		for(int i = 0; i < przypisane_grupy.size(); i++) {
			grupy = grupy + przypisane_grupy.get(i) + "\t";
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
	
	//zerowanie pól szczegó³ów zadania
	private void zadania_szczegoly_reset() {
		tresc_grupy.setText("");
		tresc_data_zad.setText("");
		tresc_tytul_zad.setText("");
		tresc_opis_zad.setText("");
		tresc_priorytet_zad.setText("");
		tresc_wykonane.setText("");
		tresc_id_zad.setText("");
	}
	
	private void grupa_szczegoly(int index) {
		tresc_id_gru.setText("" + grupy.get(index).pobierz_id_grupa());
		tresc_nazwa_gru.setText(grupy.get(index).pobierz_nazwa_grupa());
		tresc_opis_gru.setText(grupy.get(index).pobierz_opis_grupa());
	}
	
	//zerowanie pól szczegó³ów grupy
	private void grupa_szczegoly_reset() {
		tresc_id_gru.setText("");
		tresc_nazwa_gru.setText("");
		tresc_opis_gru.setText("");
	}
	
	private void lista_grupy() {
		terminarz = new Terminarz();
		grupy = terminarz.lista_grupy();
		terminarz.zamknij_polaczenie();
	}
	
	private void wybor_grupy() {
		//przygotowanie
		lista_grupy();
		int ilosc_grup = grupy.size();
		//wybór
		wybor_grupa.add("(brak filtru)");
		wybor_grupa.add("(brak grupy)");
		for(int i = 0; i < ilosc_grup; i++) { wybor_grupa.add(grupy.get(i).pobierz_nazwa_grupa()); }
	}
	
	private void lista_rekordy_grupy() {
		lista_grupy();
		lista_gru.setModel(rekordy_grupy());;
	}
	
	//do listy grup w programie
	private DefaultListModel<Object> rekordy_grupy() {
		DefaultListModel<Object>lista = new DefaultListModel<Object>();
		
		try {
			for(int i = 0; i < grupy.size(); i++) {
				lista.addElement(grupy.get(i).pobierz_nazwa_grupa());
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
					
		return lista;
	}
	

	private void wybierz_rekord() {
		JOptionPane.showMessageDialog(null, "Wybierz rekord z listy!", "Uwaga", JOptionPane.WARNING_MESSAGE);
	}
	
	private void usun_zadanie() {
		//usuniêcie
		terminarz = new Terminarz();
		terminarz.usun_zadanie(zadania.get(lista_zad.getSelectedIndex()).pobierz_id_zadanie());
		terminarz.zamknij_polaczenie();
		
		//odœwie¿enie listy
		lista_rekordy_zadania_wybrane();
	}
	
	//lista zadan w zaleznosci od wybranej grupy
	private void lista_rekordy_zadania_wybrane() {
		if(wybor_grupa.getSelectedIndex() == 0) {
			lista_rekordy_zadania();
		} else if (wybor_grupa.getSelectedIndex() == 1) {
			lista_rekodry_zadania_brak_grupy();
		} else if (wybor_grupa.getSelectedIndex() > 1) {
			int id_grupa = grupy.get(wybor_grupa.getSelectedIndex() - 2).pobierz_id_grupa();
			lista_rekordy_zadania(id_grupa);
		}
	}
	
	//DEBUG
	private void lista_zadania_debug() {
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
	
	private void lista_przypisanych_grup_debug(int id_zadanie) {
		lista_przypisane_grupy(id_zadanie);
		
		System.out.println("Przypisane grupy");
		for(int i = 0; i < przypisane_grupy.size(); i++) {
			System.out.println("\t" + przypisane_grupy.get(i));
		}
		System.out.println();
	}
	
	private void przycisk_zrobione(int index) {
		boolean czy_wykonane;
		if(index < 0) {czy_wykonane = false; }
		else { czy_wykonane = zadania.get(index).pobierz_czy_wykonane(); }
		
		if(!czy_wykonane) {
			btn_zrobione.setText("Zrobione");
		} else {
			btn_zrobione.setText("Niezrobione");
		}
	}
	
	private void czy_wykonane_zmiana(int index, boolean status) {
		//metoda w interfejsie BD
		terminarz = new Terminarz();
		terminarz.zadanie_zmiana_wykonane(zadania.get(index).pobierz_id_zadanie(), status);
		terminarz.zamknij_polaczenie();
		//odswiezenie listy
		lista_rekordy_zadania_wybrane();
	}
}
