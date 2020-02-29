package okienka;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Grupa;

import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GruEdit extends JDialog {
	private boolean czy_modyfikacja;
	
	private JPanel panel;
	private JTextPane tresc_nazwa;
	private JLabel opis_nazwa;
	private JLabel opis_opis;
	private JButton btn_anuluj;
	private JButton btn_mod;
	private JTextPane tresc_opis;
	private JLabel tytul;
	//dodanie grupy
	public GruEdit() {
		initComp();
		setTitle("Dodaj grupê");
		czy_modyfikacja = false;
		initFinal();
	}
	
	public GruEdit(Grupa grupa) {
		initComp();
		modyfikacja_wyglad();
		czy_modyfikacja = true;
		initFinal();
	}
	
	//elementy w okienku
	private void initComp() {
		setBounds(100, 100, 725, 325);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 706, 278);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		tresc_nazwa = new JTextPane();
		tresc_nazwa.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_nazwa.setEditable(false);
		tresc_nazwa.setBounds(118, 56, 578, 30);
		panel.add(tresc_nazwa);
		
		opis_nazwa = new JLabel("Nazwa");
		opis_nazwa.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_nazwa.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_nazwa.setBounds(12, 56, 94, 30);
		panel.add(opis_nazwa);
		
		opis_opis = new JLabel("Opis");
		opis_opis.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_opis.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_opis.setBounds(12, 94, 94, 120);
		panel.add(opis_opis);
		
		tresc_opis = new JTextPane();
		tresc_opis.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_opis.setEditable(false);
		tresc_opis.setBounds(118, 94, 578, 120);
		panel.add(tresc_opis);
		
		btn_anuluj = new JButton("Anuluj");
		btn_anuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_anuluj.setFont(new Font("Arial", Font.BOLD, 20));
		btn_anuluj.setBounds(596, 227, 100, 39);
		panel.add(btn_anuluj);
		
		btn_mod = new JButton("Dodaj");
		btn_mod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_mod.setFont(new Font("Arial", Font.BOLD, 20));
		btn_mod.setBounds(449, 227, 135, 39);
		panel.add(btn_mod);
		
		tytul = new JLabel("Dodaj zadanie");
		tytul.setHorizontalAlignment(SwingConstants.CENTER);
		tytul.setFont(new Font("Arial", Font.BOLD, 20));
		tytul.setBounds(12, 13, 682, 30);
		panel.add(tytul);
	}
	
	private void modyfikacja_wyglad() {
		setTitle("Modyfikuj zadanie");
		btn_mod.setText("Modyfikuj");
		tytul.setText("Modyfikuj zadanie");
	}
	
	//koniec inicjowania okienka
	private void initFinal() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}
}
