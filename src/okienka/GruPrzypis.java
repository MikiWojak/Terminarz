package okienka;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Zadanie;
import model.Grupa;

import java.awt.Font;
import java.awt.Dialog.ModalityType;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Choice;

public class GruPrzypis extends JDialog {
	private Zadanie zadanie;
	private List<Grupa>grupy;
	private boolean czyUsuwanie;
	
	private JButton btnPotwierdz;
	private JButton btnAnuluj;
	private JLabel tytulDodaj;
	private Choice choice;
	private JLabel tytulZad;
	private JLabel trescTytul;
	private JLabel trescData;
	private JPanel panel;
	
	//dodawanie grupy do zadania
	/**
	 * @wbp.parser.constructor
	 */
	public GruPrzypis(Zadanie zadanie) {
		initComp();
		setTitle("Dodaj grupê");
		
		this.zadanie = zadanie;
		czyUsuwanie = false;
		
		initFinal();
	}
	
	//usuwanie grupy z zadania
	public GruPrzypis(Zadanie zadanie, boolean czyUsuwanie) {
		initComp();
		usuwanieWyglad();
		
		this.zadanie = zadanie;
		this.czyUsuwanie = true;
		
		initFinal();
	}
	
	private void initComp() {
		setBounds(100, 100, 700, 325);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 682, 278);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		btnPotwierdz = new JButton("Dodaj");
		btnPotwierdz.setFont(new Font("Arial", Font.BOLD, 20));
		btnPotwierdz.setBounds(461, 235, 100, 30);
		panel.add(btnPotwierdz);
		
		btnAnuluj = new JButton("Anuluj");
		btnAnuluj.setFont(new Font("Arial", Font.BOLD, 20));
		btnAnuluj.setBounds(570, 235, 100, 30);
		panel.add(btnAnuluj);
		
		tytulDodaj = new JLabel("Dodaj grup\u0119 do zadania");
		tytulDodaj.setHorizontalAlignment(SwingConstants.CENTER);
		tytulDodaj.setFont(new Font("Arial", Font.BOLD, 20));
		tytulDodaj.setBounds(12, 13, 658, 30);
		panel.add(tytulDodaj);
		
		choice = new Choice();
		choice.setFont(new Font("Arial", Font.BOLD, 20));
		choice.setBounds(12, 49, 658, 30);
		panel.add(choice);
		
		tytulZad = new JLabel("Zadanie w skr\u00F3cie");
		tytulZad.setHorizontalAlignment(SwingConstants.CENTER);
		tytulZad.setFont(new Font("Arial", Font.BOLD, 20));
		tytulZad.setBounds(12, 84, 658, 30);
		panel.add(tytulZad);
		
		trescTytul = new JLabel("Tytu\u0142");
		trescTytul.setHorizontalAlignment(SwingConstants.CENTER);
		trescTytul.setFont(new Font("Arial", Font.PLAIN, 20));
		trescTytul.setBounds(12, 127, 658, 30);
		panel.add(trescTytul);
		
		trescData = new JLabel("Data");
		trescData.setHorizontalAlignment(SwingConstants.CENTER);
		trescData.setFont(new Font("Arial", Font.PLAIN, 20));
		trescData.setBounds(12, 170, 658, 30);
		panel.add(trescData);
	}
	
	//dla usuwania zadania
	private void usuwanieWyglad() {
		setTitle("Usuñ grupê");
		tytulDodaj.setText("Usuñ grupê z zadania");
		btnPotwierdz.setText("Usuñ");
	}
	
	//koniec inicjowania okienka
	private void initFinal() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}
}
