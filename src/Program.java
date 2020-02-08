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

public class Program {
	
	private Terminarz terminarz;				//po³¹czenie z BD
	private List<Zadanie>zadania;				//lista zadania
	private List<Grupa>grupy;
	private List<Przypis>przypisania;
	private List<Zlozone>zlozone;
	
	private JFrame frame;
	private JPanel zad_lista;
	private JPanel zad_szczegowy;
	private JLayeredPane warstwy;
	private JButton btn_zad_lista;
	private JButton btn_zad_szczegoly;
	private JLabel tytul_szczegoly;
	private JLabel tytul_lista;
	private JList lista;
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

	/**
	 * Create the application.
	 */
	public Program() {
		initialize();
		inicjalizacja_pol();
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
		warstwy.setBounds(12, 13, 1058, 575);
		frame.getContentPane().add(warstwy);
		warstwy.setLayout(new CardLayout(0, 0));
		
		zad_lista = new JPanel();
		warstwy.add(zad_lista, "name_762839293036754");
		zad_lista.setLayout(null);
		
		tytul_lista = new JLabel("Lista");
		tytul_lista.setHorizontalAlignment(SwingConstants.CENTER);
		tytul_lista.setFont(new Font("Arial", Font.PLAIN, 20));
		tytul_lista.setBounds(12, 13, 1034, 41);
		zad_lista.add(tytul_lista);
		
		lista = new JList();
		lista.setFont(new Font("Monospaced", Font.PLAIN, 20));
		lista.setBounds(22, 67, 1024, 440);
		zad_lista.add(lista);
		
		btn_zad_szczegoly = new JButton("Szczeg\u00F3\u0142y\r\n");
		btn_zad_szczegoly.setBounds(12, 523, 133, 39);
		zad_lista.add(btn_zad_szczegoly);
		btn_zad_szczegoly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zmiana_panelu(zad_szczegowy);
			}
		});
		btn_zad_szczegoly.setFont(new Font("Arial", Font.PLAIN, 20));
		
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
		opis_data_zad.setBounds(22, 67, 94, 25);
		zad_szczegowy.add(opis_data_zad);
		
		opis_tytul_zad = new JLabel("Tytu\u0142");
		opis_tytul_zad.setVerticalAlignment(SwingConstants.TOP);
		opis_tytul_zad.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_tytul_zad.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_tytul_zad.setBounds(22, 105, 94, 25);
		zad_szczegowy.add(opis_tytul_zad);
		
		opis_opis_zad = new JLabel("Opis");
		opis_opis_zad.setVerticalAlignment(SwingConstants.TOP);
		opis_opis_zad.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_opis_zad.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_opis_zad.setBounds(22, 143, 94, 25);
		zad_szczegowy.add(opis_opis_zad);
		
		opis_priorytet_zad = new JLabel("Priorytet");
		opis_priorytet_zad.setVerticalAlignment(SwingConstants.TOP);
		opis_priorytet_zad.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_priorytet_zad.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_priorytet_zad.setBounds(22, 263, 94, 25);
		zad_szczegowy.add(opis_priorytet_zad);
		
		opis_wykonane = new JLabel("Wykonane");
		opis_wykonane.setVerticalAlignment(SwingConstants.TOP);
		opis_wykonane.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_wykonane.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_wykonane.setBounds(22, 301, 94, 25);
		zad_szczegowy.add(opis_wykonane);
		
		tresc_data_zad = new JLabel("Data zadania");
		tresc_data_zad.setVerticalAlignment(SwingConstants.TOP);
		tresc_data_zad.setHorizontalAlignment(SwingConstants.LEFT);
		tresc_data_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_data_zad.setBounds(128, 67, 918, 25);
		zad_szczegowy.add(tresc_data_zad);
		
		tresc_tytul_zad = new JLabel("Tytu\u0142 zadania");
		tresc_tytul_zad.setVerticalAlignment(SwingConstants.TOP);
		tresc_tytul_zad.setHorizontalAlignment(SwingConstants.LEFT);
		tresc_tytul_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_tytul_zad.setBounds(128, 105, 918, 25);
		zad_szczegowy.add(tresc_tytul_zad);
		
		tresc_opis_zad = new JLabel("Opis zadania");
		tresc_opis_zad.setVerticalAlignment(SwingConstants.TOP);
		tresc_opis_zad.setHorizontalAlignment(SwingConstants.LEFT);
		tresc_opis_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_opis_zad.setBounds(128, 143, 918, 100);
		zad_szczegowy.add(tresc_opis_zad);
		
		tresc_priorytet_zad = new JLabel("Priorytet zadania");
		tresc_priorytet_zad.setVerticalAlignment(SwingConstants.TOP);
		tresc_priorytet_zad.setHorizontalAlignment(SwingConstants.LEFT);
		tresc_priorytet_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_priorytet_zad.setBounds(128, 263, 918, 25);
		zad_szczegowy.add(tresc_priorytet_zad);
		
		tresc_wykonane = new JLabel("Czy wykonane zadanie");
		tresc_wykonane.setVerticalAlignment(SwingConstants.TOP);
		tresc_wykonane.setHorizontalAlignment(SwingConstants.LEFT);
		tresc_wykonane.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_wykonane.setBounds(128, 301, 918, 25);
		zad_szczegowy.add(tresc_wykonane);
		
		btn_zad_lista = new JButton("Wr\u00F3\u0107");
		btn_zad_lista.setBounds(12, 523, 133, 39);
		zad_szczegowy.add(btn_zad_lista);
		btn_zad_lista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zmiana_panelu(zad_lista);
				//lista_rekordy();
			}
		});
		btn_zad_lista.setFont(new Font("Arial", Font.PLAIN, 20));
		
		opis_id_zad = new JLabel("ID");
		opis_id_zad.setVerticalAlignment(SwingConstants.TOP);
		opis_id_zad.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_id_zad.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_id_zad.setBounds(22, 339, 94, 25);
		zad_szczegowy.add(opis_id_zad);
		
		tresc_id_zad = new JLabel("ID zadania");
		tresc_id_zad.setVerticalAlignment(SwingConstants.TOP);
		tresc_id_zad.setHorizontalAlignment(SwingConstants.LEFT);
		tresc_id_zad.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_id_zad.setBounds(128, 339, 918, 25);
		zad_szczegowy.add(tresc_id_zad);
		
		frame.setVisible(true);
		
		//lista jest domyslnym okienkiem
		lista_rekordy();
	}
	
	public void inicjalizacja_pol() {				
		zadania = new LinkedList<Zadanie>();
		grupy = new LinkedList<Grupa>();
		przypisania = new LinkedList<Przypis>();
		zlozone = new LinkedList<Zlozone>();
	}
	
	public void zmiana_panelu(JPanel panel) {
		warstwy.removeAll();
		warstwy.add(panel);
		warstwy.repaint();
		warstwy.revalidate();
	}
	
	public void lista_rekordy() {
		terminarz = new Terminarz();
		zadania = terminarz.lista_zadania();
		terminarz.zamknij_polaczenie();
		
		lista.setModel(lista_zadania());
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
		
		String czy_wykonane;
		if (zlozone.get(0).pobierz_czy_wykonane()) { czy_wykonane = "nie"; }
		else { czy_wykonane = "tak"; }
		/*
		lbl_tytul_zadanie.setText(zlozone.get(0).pobierz_tytul_zadanie());
		lbl_data_zadanie.setText(zlozone.get(0).pobierz_data_zadanie().toString());
		lbl_opis_zadanie.setText(zlozone.get(0).pobierz_opis_zadanie());
		lbl_priorytet_zadanie.setText(zlozone.get(0).pobierz_priorytet_zadanie());
		lbl_czy_wykonane.setText(czy_wykonane);
		*/
	}
}
