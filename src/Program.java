import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import terminarz.Terminarz;
import model.Grupa;
import model.Przypis;
import model.Zadanie;
import model.Zlozone;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;

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
		frame.setBounds(100, 100, 1111, 592);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lbl_nazwa_grupa = new JLabel("New label");
		lbl_nazwa_grupa.setFont(new Font("Arial", Font.PLAIN, 24));
		lbl_nazwa_grupa.setBounds(12, 13, 1069, 37);
		frame.getContentPane().add(lbl_nazwa_grupa);
		
		lbl_opis_grupa = new JLabel("New label");
		lbl_opis_grupa.setFont(new Font("Arial", Font.PLAIN, 24));
		lbl_opis_grupa.setBounds(12, 63, 1069, 37);
		frame.getContentPane().add(lbl_opis_grupa);
		
		lbl_tytul_zadanie = new JLabel("New label");
		lbl_tytul_zadanie.setFont(new Font("Arial", Font.PLAIN, 24));
		lbl_tytul_zadanie.setBounds(12, 113, 1069, 37);
		frame.getContentPane().add(lbl_tytul_zadanie);
		
		lbl_data_zadanie = new JLabel("New label");
		lbl_data_zadanie.setFont(new Font("Arial", Font.PLAIN, 24));
		lbl_data_zadanie.setBounds(12, 163, 1069, 37);
		frame.getContentPane().add(lbl_data_zadanie);
		
		lbl_opis_zadanie = new JLabel("New label");
		lbl_opis_zadanie.setFont(new Font("Arial", Font.PLAIN, 24));
		lbl_opis_zadanie.setBounds(12, 213, 1069, 37);
		frame.getContentPane().add(lbl_opis_zadanie);
		
		lbl_priorytet_zadanie = new JLabel("New label");
		lbl_priorytet_zadanie.setFont(new Font("Arial", Font.PLAIN, 24));
		lbl_priorytet_zadanie.setBounds(12, 263, 1069, 37);
		frame.getContentPane().add(lbl_priorytet_zadanie);
		
		lbl_czy_wykonane = new JLabel("New label");
		lbl_czy_wykonane.setFont(new Font("Arial", Font.PLAIN, 24));
		lbl_czy_wykonane.setBounds(12, 313, 1069, 37);
		frame.getContentPane().add(lbl_czy_wykonane);
		
		Aktualizuj = new JButton("Aktualizuj");
		Aktualizuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aktualizuj_dane();
			}
		});
		Aktualizuj.setFont(new Font("Arial", Font.PLAIN, 24));
		Aktualizuj.setBounds(457, 363, 222, 37);
		frame.getContentPane().add(Aktualizuj);
		
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
		
		zlozone = terminarz.lista_zlozone();
		
		lbl_nazwa_grupa.setText(zlozone.get(0).pobierz_nazwa_grupa());
				
		terminarz.zamknij_polaczenie();
	}
}
