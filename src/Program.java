import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import terminarz.Terminarz;
import model.Grupa;
import model.Przypis;
import model.Zadanie;
import okienka.GruEdit;
import okienka.GruPrzypis;
import okienka.Pomoc;
import okienka.ZadEdit;

import java.awt.Font;
import java.awt.Toolkit;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Color;

public class Program{
	
	private Terminarz terminarz;				//po³¹czenie z BD
	private List<Zadanie>zadania;				//lista zadania
	private List<Grupa>grupy;
	private List<Przypis>przypisania;
	private List<String>przypisane_grupy;
	private JDialog edycja;
	private JDialog doGrupy;
	private JDialog pomoc;
	
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
	private JLabel opis_nazwa_gru;
	private JLabel opis_opis_gru;
	private JTextPane tresc_opis_gru;
	private JTextPane tresc_opis_zad;
	private JLabel opis_filtr;
	private JScrollPane scroll_opis_szczegoly_zad;
	private JButton btn_zrobione;
	private JButton btn_dodaj_gru;
	private JButton btn_mod_gru;
	private JButton btn_usun_gru;
	private JMenu mn_zadanie;
	private JMenuItem mntm_mod_zad;
	private JMenuItem mntm_dod_zad;
	private JMenuItem mntm_usun_zad;
	private JMenuItem mntm_dod_do_gru;
	private JMenuItem mntm_usun_z_gru;
	private JScrollPane scroll_opis_gru;
	private Choice tresc_grupy;
	private JMenu mnNewMenu;
	private JTextField tresc_nazwa_gru;
	private JLabel tresc_tytul_zad;
	private JLabel tresc_data_zad;
	private JLabel tresc_priorytet_zad;
	private JLabel tresc_wykonane;

	/**
	 * Create the application.
	 */
	public Program() {
		// TODO: handle exception
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
		//wymiary programu
		int program_szerokosc = 1200;
		int program_wysokosc = 650;
		int system_szerokosc = Toolkit.getDefaultToolkit().getScreenSize().width;
		int system_wysokosc = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		frmTerminarz = new JFrame();
		frmTerminarz.setTitle("Terminarz");
		frmTerminarz.setBounds(
				(system_szerokosc - program_szerokosc) / 2, 
				(system_wysokosc - program_wysokosc) / 2,
				program_szerokosc,
				program_wysokosc);
		frmTerminarz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTerminarz.setResizable(false);
		frmTerminarz.getContentPane().setLayout(null);
		
		warstwy = new JLayeredPane();
		warstwy.setBounds(10, 39, 1160, 551);
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
		scroll_lista_zad.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_lista_zad.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll_lista_zad.setBounds(12, 96, 1138, 390);
		zad_lista.add(scroll_lista_zad);
		
		lista_zad = new JList();
		scroll_lista_zad.setViewportView(lista_zad);
		lista_zad.setFont(new Font("Monospaced", Font.PLAIN, 20));
		lista_zad.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				przycisk_zrobione(lista_zad.getSelectedIndex());
			}
		});
		
		btn_zad_szczegoly = new JButton("Szczeg\u00F3\u0142y\r\n zadania");
		btn_zad_szczegoly.setBounds(12, 499, 220, 39);
		zad_lista.add(btn_zad_szczegoly);
		btn_zad_szczegoly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lista_zad.getSelectedIndex() >= 0) {
					zmiana_panelu(zad_szczegowy);
					zadanie_szczegoly(lista_zad.getSelectedIndex());
					mn_zadanie.setEnabled(false);
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
		
		opis_filtr = new JLabel("Filtr grupy");
		opis_filtr.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_filtr.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_filtr.setBounds(12, 60, 777, 30);
		zad_lista.add(opis_filtr);
		
		btn_zrobione = new JButton("Oznacz jako zrobione");
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
		btn_zrobione.setBounds(880, 499, 270, 39);
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
		btn_zad_lista.setBounds(12, 499, 133, 39);
		zad_szczegowy.add(btn_zad_lista);
		btn_zad_lista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zmiana_panelu(zad_lista);
				zadania_szczegoly_reset();
				mn_zadanie.setEnabled(true);
			}
		});
		btn_zad_lista.setFont(new Font("Arial", Font.BOLD, 20));
		
		opis_grupy = new JLabel("Grupy");
		opis_grupy.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_grupy.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_grupy.setBounds(22, 67, 94, 30);
		zad_szczegowy.add(opis_grupy);
		
		scroll_opis_szczegoly_zad = new JScrollPane();
		scroll_opis_szczegoly_zad.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_opis_szczegoly_zad.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll_opis_szczegoly_zad.setBounds(128, 196, 1020, 150);
		zad_szczegowy.add(scroll_opis_szczegoly_zad);
		
		tresc_opis_zad = new JTextPane();
		scroll_opis_szczegoly_zad.setViewportView(tresc_opis_zad);
		tresc_opis_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_opis_zad.setEditable(false);
		
		tresc_grupy = new Choice();
		tresc_grupy.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_grupy.setBounds(128, 67, 1020, 30);
		zad_szczegowy.add(tresc_grupy);
		
		tresc_tytul_zad = new JLabel("");
		tresc_tytul_zad.setOpaque(true);
		tresc_tytul_zad.setBackground(Color.WHITE);
		tresc_tytul_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_tytul_zad.setBounds(128, 153, 1020, 30);
		zad_szczegowy.add(tresc_tytul_zad);
		
		tresc_data_zad = new JLabel("");
		tresc_data_zad.setOpaque(true);
		tresc_data_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_data_zad.setBackground(Color.WHITE);
		tresc_data_zad.setBounds(128, 110, 1020, 30);
		zad_szczegowy.add(tresc_data_zad);
		
		tresc_priorytet_zad = new JLabel("");
		tresc_priorytet_zad.setOpaque(true);
		tresc_priorytet_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_priorytet_zad.setBackground(Color.WHITE);
		tresc_priorytet_zad.setBounds(128, 359, 1020, 30);
		zad_szczegowy.add(tresc_priorytet_zad);
		
		tresc_wykonane = new JLabel("");
		tresc_wykonane.setOpaque(true);
		tresc_wykonane.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_wykonane.setBackground(Color.WHITE);
		tresc_wykonane.setBounds(128, 402, 1020, 30);
		zad_szczegowy.add(tresc_wykonane);
		
		gru_panel = new JPanel();
		warstwy.add(gru_panel, "name_165903459099969");
		gru_panel.setLayout(null);
		
		tytul_lista_gru = new JLabel("Lista grup");
		tytul_lista_gru.setHorizontalAlignment(SwingConstants.CENTER);
		tytul_lista_gru.setFont(new Font("Arial", Font.BOLD, 20));
		tytul_lista_gru.setBounds(12, 13, 1136, 41);
		gru_panel.add(tytul_lista_gru);
		
		scroll_lista_gru = new JScrollPane();
		scroll_lista_gru.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_lista_gru.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll_lista_gru.setBounds(12, 67, 440, 471);
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
		
		scroll_opis_gru = new JScrollPane();
		scroll_opis_gru.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_opis_gru.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll_opis_gru.setBounds(570, 105, 578, 120);
		gru_panel.add(scroll_opis_gru);
		
		tresc_opis_gru = new JTextPane();
		scroll_opis_gru.setViewportView(tresc_opis_gru);
		tresc_opis_gru.setEditable(false);
		tresc_opis_gru.setFont(new Font("Arial", Font.PLAIN, 20));
		
		btn_dodaj_gru = new JButton("Dodaj");
		btn_dodaj_gru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				edycja = new GruEdit();
				//przerwa
				odswiez_wybor_grupy();
			}
		});
		btn_dodaj_gru.setFont(new Font("Arial", Font.BOLD, 20));
		btn_dodaj_gru.setBounds(791, 499, 100, 39);
		gru_panel.add(btn_dodaj_gru);
		
		btn_mod_gru = new JButton("Modyfikuj");
		btn_mod_gru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lista_gru.getSelectedIndex() >= 0) {
					edycja = new GruEdit(grupy.get(lista_gru.getSelectedIndex()));
					//przerwa
					odswiez_wybor_grupy();
				} else { wybierz_rekord(); }
			}
		});
		btn_mod_gru.setFont(new Font("Arial", Font.BOLD, 20));
		btn_mod_gru.setBounds(903, 499, 133, 39);
		gru_panel.add(btn_mod_gru);
		
		btn_usun_gru = new JButton("Usu\u0144");
		btn_usun_gru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lista_gru.getSelectedIndex() >= 0) {
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
					if(opcja == 0) { usun_grupe(); }
				} else { wybierz_rekord(); }
			}
		});
		btn_usun_gru.setFont(new Font("Arial", Font.BOLD, 20));
		btn_usun_gru.setBounds(1048, 499, 100, 39);
		gru_panel.add(btn_usun_gru);
		
		tresc_nazwa_gru = new JTextField();
		tresc_nazwa_gru.setBackground(Color.WHITE);
		tresc_nazwa_gru.setEditable(false);
		tresc_nazwa_gru.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_nazwa_gru.setBounds(570, 67, 578, 30);
		gru_panel.add(tresc_nazwa_gru);
		tresc_nazwa_gru.setColumns(10);
		
		menu = new JMenuBar();
		menu.setBounds(0, 0, 1194, 26);
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
				
				mn_it_grupy.setEnabled(true);
				mn_it_zadania.setEnabled(false);
				mn_zadanie.setEnabled(true);
			}
		});
		mn_it_zadania.setFont(new Font("Arial", Font.PLAIN, 16));
		mn_it_zadania.setEnabled(false);
		mn_widok.add(mn_it_zadania);
		
		mn_it_grupy = new JMenuItem("Grupy");
		mn_it_grupy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zmiana_panelu(gru_panel);
				lista_rekordy_grupy();
				grupa_szczegoly_reset();
				
				mn_it_grupy.setEnabled(false);
				mn_it_zadania.setEnabled(true);
				mn_zadanie.setEnabled(false);
			}
		});
		mn_it_grupy.setFont(new Font("Arial", Font.PLAIN, 16));
		mn_widok.add(mn_it_grupy);
		
		mn_zadanie = new JMenu("Zadanie");
		mn_zadanie.setFont(new Font("Arial", Font.PLAIN, 16));
		menu.add(mn_zadanie);
		
		mntm_dod_zad = new JMenuItem("Dodaj");
		mntm_dod_zad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				edycja = new ZadEdit();
				//przerwa
				lista_rekordy_zadania_wybrane();
			}
		});
		mntm_dod_zad.setFont(new Font("Arial", Font.PLAIN, 16));
		mn_zadanie.add(mntm_dod_zad);
		
		mntm_mod_zad = new JMenuItem("Modyfikuj");
		mntm_mod_zad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lista_zad.getSelectedIndex() >= 0) {
					edycja = new ZadEdit(zadania.get(lista_zad.getSelectedIndex()));
					//przerwa
					lista_rekordy_zadania_wybrane();
				} else { wybierz_rekord(); }
			}
		});
		mntm_mod_zad.setFont(new Font("Arial", Font.PLAIN, 16));
		mn_zadanie.add(mntm_mod_zad);
		
		mntm_usun_zad = new JMenuItem("Usu\u0144");
		mntm_usun_zad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		mntm_usun_zad.setFont(new Font("Arial", Font.PLAIN, 16));
		mn_zadanie.add(mntm_usun_zad);
		
		mntm_dod_do_gru = new JMenuItem("Dodaj do grupy");
		mntm_dod_do_gru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sel_index = lista_zad.getSelectedIndex();
				if(sel_index >= 0) {
					doGrupy = new GruPrzypis(zadania.get(sel_index));
					//przerwa
					lista_rekordy_zadania_wybrane();
				} else { wybierz_rekord(); }
			}
		});
		mntm_dod_do_gru.setFont(new Font("Arial", Font.PLAIN, 16));
		mn_zadanie.add(mntm_dod_do_gru);
		
		mntm_usun_z_gru = new JMenuItem("Usu\u0144 z grupy");
		mntm_usun_z_gru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sel_index = lista_zad.getSelectedIndex();
				if(sel_index >= 0) {
					doGrupy = new GruPrzypis(zadania.get(sel_index), true);
					//przerwa
					lista_rekordy_zadania_wybrane();
				} else { wybierz_rekord(); }
			}
		});
		mntm_usun_z_gru.setFont(new Font("Arial", Font.PLAIN, 16));
		mn_zadanie.add(mntm_usun_z_gru);
		
		mnNewMenu = new JMenu("Pomoc");
		mnNewMenu.setFont(new Font("Arial", Font.PLAIN, 16));
		mnNewMenu.addMouseListener(new MouseAdapter() {
			// TODO: handle exception
			public void mouseClicked(MouseEvent arg0) {
				pomoc = new Pomoc();
			}
		});
		menu.add(mnNewMenu);
		
		frmTerminarz.setVisible(true);
	}

	private void inicjalizacja_pol() {				
		zadania = new LinkedList<Zadanie>();
		grupy = new LinkedList<Grupa>();
		przypisania = new LinkedList<Przypis>();
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
		for(int i = 0; i < przypisane_grupy.size(); i++) {
			tresc_grupy.add(przypisane_grupy.get(i));
		}
		
		//czy wykonane
		String czy_wykonane;
		if (zadania.get(index).pobierz_czy_wykonane()) { czy_wykonane = "tak"; }
		else { czy_wykonane = "nie"; }
		
		//wyœwietlenie szczegó³ów
		tresc_data_zad.setText(zadania.get(index).pobierz_data_zadanie().toString());
		tresc_tytul_zad.setText(zadania.get(index).pobierz_tytul_zadanie());
		tresc_opis_zad.setText(zadania.get(index).pobierz_opis_zadanie());
		tresc_priorytet_zad.setText(zadania.get(index).pobierz_priorytet_zadanie());
		tresc_wykonane.setText(czy_wykonane);
	}
	
	//zerowanie pól szczegó³ów zadania
	private void zadania_szczegoly_reset() {
		tresc_grupy.removeAll();
		tresc_data_zad.setText("");
		tresc_tytul_zad.setText("");
		tresc_opis_zad.setText("");
		tresc_priorytet_zad.setText("");
		tresc_wykonane.setText("");
	}
	
	private void grupa_szczegoly(int index) {
		tresc_nazwa_gru.setText(grupy.get(index).pobierz_nazwa_grupa());
		tresc_opis_gru.setText(grupy.get(index).pobierz_opis_grupa());
	}
	
	//zerowanie pól szczegó³ów grupy
	private void grupa_szczegoly_reset() {
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
	
	private void usun_grupe() {
		terminarz = new Terminarz();
		terminarz.usun_grupe(grupy.get(lista_gru.getSelectedIndex()).pobierz_id_grupa());
		terminarz.zamknij_polaczenie();
		
		//odœwie¿enie list
		odswiez_wybor_grupy();
	}
	
	//odœwie¿enie wyboru listy po modyfikacji grupy
	private void odswiez_wybor_grupy() {
		lista_rekordy_grupy();
		grupa_szczegoly_reset();
		wybor_grupa.removeAll();
		wybor_grupy();
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
	
	private void przycisk_zrobione(int index) {
		boolean czy_wykonane;
		if(index < 0) {czy_wykonane = false; }
		else { czy_wykonane = zadania.get(index).pobierz_czy_wykonane(); }
		
		if(!czy_wykonane) {
			btn_zrobione.setText("Oznacz jako zrobione");
		} else {
			btn_zrobione.setText("Oznacz jako niezrobione");
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
