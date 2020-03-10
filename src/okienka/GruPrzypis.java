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
	
	private JButton btnDodaj;
	private JButton btnAnuluj;
	private JLabel lblNewLabel;
	private Choice choice;
	
	public GruPrzypis(Zadanie zadanie) {
		initComp();
		setTitle("Dodaj grupê");
		
		initFinal();
	}
	
	private void initComp() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 432, 253);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		btnDodaj = new JButton("Dodaj");
		btnDodaj.setFont(new Font("Arial", Font.BOLD, 20));
		btnDodaj.setBounds(214, 210, 100, 30);
		panel.add(btnDodaj);
		
		btnAnuluj = new JButton("Anuluj");
		btnAnuluj.setFont(new Font("Arial", Font.BOLD, 20));
		btnAnuluj.setBounds(323, 210, 100, 30);
		panel.add(btnAnuluj);
		
		lblNewLabel = new JLabel("Dodaj grup\u0119 do zadania");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 13, 408, 30);
		panel.add(lblNewLabel);
		
		choice = new Choice();
		choice.setFont(new Font("Arial", Font.BOLD, 20));
		choice.setBounds(12, 49, 408, 30);
		panel.add(choice);
	}
	
	//koniec inicjowania okienka
	private void initFinal() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}
}
