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
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Program {
	
	private Terminarz terminarz;				//połączenie z BD
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
		warstwy.setBounds(12, 13, 1058, 527);
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
		
		zad_szczegowy = new JPanel();
		warstwy.add(zad_szczegowy, "name_762878183462982");
		zad_szczegowy.setLayout(null);
		
		tytul_szczegoly = new JLabel("Szczeg\u00F3\u0142y");
		tytul_szczegoly.setHorizontalAlignment(SwingConstants.CENTER);
		tytul_szczegoly.setFont(new Font("Arial", Font.PLAIN, 20));
		tytul_szczegoly.setBounds(12, 13, 1034, 41);
		zad_szczegowy.add(tytul_szczegoly);
		
		btn_zad_lista = new JButton("Lista");
		btn_zad_lista.setFont(new Font("Arial", Font.PLAIN, 20));
		btn_zad_lista.setBounds(12, 601, 133, 39);
		frame.getContentPane().add(btn_zad_lista);
		
		btn_zad_szczegoly = new JButton("Szczeg\u00F3\u0142y\r\n");
		btn_zad_szczegoly.setFont(new Font("Arial", Font.PLAIN, 20));
		btn_zad_szczegoly.setBounds(157, 601, 133, 39);
		frame.getContentPane().add(btn_zad_szczegoly);
		
		frame.setVisible(true);
	}
	
	public void inicjalizacja_pol() {				
		zadania = new LinkedList<Zadanie>();
		grupy = new LinkedList<Grupa>();
		przypisania = new LinkedList<Przypis>();
		zlozone = new LinkedList<Zlozone>();
	}
}
