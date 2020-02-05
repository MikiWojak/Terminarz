import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

public class Program {
	
	private Terminarz terminarz;				//po³¹czenie z BD
	private List<Zadanie>zadania;				//lista zadania
	private List<Grupa>grupy;
	private List<Przypis>przypisania;
	private List<Zlozone>zlozone;
	private ZadLista zad_lista;
	private ZadSzczegoly zad_szczegowy;
	
	private JFrame frame;

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
		
		zad_lista = new ZadLista();
		zad_lista.setBounds(0, 0, 1111, 592);
		frame.getContentPane().add(zad_lista);
		
		/*
		zad_szczegowy = new ZadSzczegoly();
		zad_szczegowy.setBounds(0, 0, 1111, 592);
		frame.getContentPane().add(zad_szczegowy);
		*/
		
		frame.setVisible(true);
	}
	
	public void inicjalizacja_pol() {				
		zadania = new LinkedList<Zadanie>();
		grupy = new LinkedList<Grupa>();
		przypisania = new LinkedList<Przypis>();
		zlozone = new LinkedList<Zlozone>();
	}

}
