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
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;

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
				lista_rekordy();
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
		
		JLabel lblNewLabel = new JLabel("Data");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Arial", Font.ITALIC, 20));
		lblNewLabel.setBounds(22, 67, 94, 25);
		zad_szczegowy.add(lblNewLabel);
		
		JLabel lblTytul = new JLabel("Tytu\u0142");
		lblTytul.setVerticalAlignment(SwingConstants.TOP);
		lblTytul.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTytul.setFont(new Font("Arial", Font.ITALIC, 20));
		lblTytul.setBounds(22, 105, 94, 25);
		zad_szczegowy.add(lblTytul);
		
		JLabel lblOpis = new JLabel("Opis");
		lblOpis.setVerticalAlignment(SwingConstants.TOP);
		lblOpis.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOpis.setFont(new Font("Arial", Font.ITALIC, 20));
		lblOpis.setBounds(22, 143, 94, 25);
		zad_szczegowy.add(lblOpis);
		
		JLabel lblPriorytet = new JLabel("Priorytet");
		lblPriorytet.setVerticalAlignment(SwingConstants.TOP);
		lblPriorytet.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPriorytet.setFont(new Font("Arial", Font.ITALIC, 20));
		lblPriorytet.setBounds(22, 263, 94, 25);
		zad_szczegowy.add(lblPriorytet);
		
		JLabel lblWykonane = new JLabel("Wykonane");
		lblWykonane.setVerticalAlignment(SwingConstants.TOP);
		lblWykonane.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWykonane.setFont(new Font("Arial", Font.ITALIC, 20));
		lblWykonane.setBounds(22, 301, 94, 25);
		zad_szczegowy.add(lblWykonane);
		
		label = new JLabel("Data");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Arial", Font.PLAIN, 20));
		label.setBounds(128, 67, 918, 25);
		zad_szczegowy.add(label);
		
		label_1 = new JLabel("Data");
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Arial", Font.PLAIN, 20));
		label_1.setBounds(128, 105, 918, 25);
		zad_szczegowy.add(label_1);
		
		label_2 = new JLabel("Data");
		label_2.setVerticalAlignment(SwingConstants.TOP);
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Arial", Font.PLAIN, 20));
		label_2.setBounds(128, 143, 918, 100);
		zad_szczegowy.add(label_2);
		
		label_3 = new JLabel("Data");
		label_3.setVerticalAlignment(SwingConstants.TOP);
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Arial", Font.PLAIN, 20));
		label_3.setBounds(128, 263, 918, 25);
		zad_szczegowy.add(label_3);
		
		label_4 = new JLabel("Data");
		label_4.setVerticalAlignment(SwingConstants.TOP);
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setFont(new Font("Arial", Font.PLAIN, 20));
		label_4.setBounds(128, 301, 918, 25);
		zad_szczegowy.add(label_4);
		
		btn_zad_lista = new JButton("Wr\u00F3\u0107");
		btn_zad_lista.setBounds(12, 523, 133, 39);
		zad_szczegowy.add(btn_zad_lista);
		btn_zad_lista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zmiana_panelu(zad_lista);
			}
		});
		btn_zad_lista.setFont(new Font("Arial", Font.PLAIN, 20));
		
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
	
	/*
	public void aktualizuj_dane() {
		terminarz = new Terminarz();
		zlozone = terminarz.lista_zlozone();
		
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
	*/
}
