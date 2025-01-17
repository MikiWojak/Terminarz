package okienka;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Grupa;
import terminarz.Terminarz;

import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import java.awt.Color;

public class GruEdit extends JDialog {
	private Terminarz terminarz;
	private Grupa grupa;
	private boolean czy_modyfikacja;
	
	private JPanel panel;
	private JLabel opis_nazwa;
	private JLabel opis_opis;
	private JButton btn_anuluj;
	private JButton btn_mod;
	private JTextPane tresc_opis;
	private JLabel tytul;
	private JScrollPane scroll_opis_gru;
	private JTextField tresc_nazwa;
	//dodanie grupy
	public GruEdit() {
		initComp();
		setTitle("Dodaj grup�");
		czy_modyfikacja = false;
		initFinal();
	}
	
	public GruEdit(Grupa grupa) {
		initComp();
		modyfikacja_wyglad();
		this.grupa = grupa;
		czy_modyfikacja = true;
		grupa_dane();
		initFinal();
	}
	
	//elementy w okienku
	private void initComp() {
		//wymiary programu
		int program_szerokosc = 725;
		int program_wysokosc = 325;
		int system_szerokosc = Toolkit.getDefaultToolkit().getScreenSize().width;
		int system_wysokosc = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(
				(system_szerokosc - program_szerokosc) / 2,
				(system_wysokosc - program_wysokosc) / 2,
				program_szerokosc,
				program_wysokosc);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 719, 290);
		getContentPane().add(panel);
		panel.setLayout(null);
		
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
		
		scroll_opis_gru = new JScrollPane();
		scroll_opis_gru.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll_opis_gru.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_opis_gru.setBounds(118, 94, 589, 120);
		panel.add(scroll_opis_gru);
		
		tresc_opis = new JTextPane();
		scroll_opis_gru.setViewportView(tresc_opis);
		tresc_opis.setFont(new Font("Arial", Font.PLAIN, 20));
		
		btn_anuluj = new JButton("Anuluj");
		btn_anuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_anuluj.setFont(new Font("Arial", Font.BOLD, 20));
		btn_anuluj.setBounds(607, 238, 100, 39);
		panel.add(btn_anuluj);
		
		btn_mod = new JButton("Dodaj");
		btn_mod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(czy_tytul_pusty()) {
					JOptionPane.showMessageDialog(null, "Nazwa nie mo�e by� pusta!", "Uwaga!", JOptionPane.WARNING_MESSAGE);
				} else {
					if(czy_modyfikacja) { mod_grupe(); }
					else { wstaw_grupe(); }
					dispose();
				}
			}
		});
		btn_mod.setFont(new Font("Arial", Font.BOLD, 20));
		btn_mod.setBounds(460, 238, 135, 39);
		panel.add(btn_mod);
		
		tytul = new JLabel("Dodaj grup�");
		tytul.setHorizontalAlignment(SwingConstants.CENTER);
		tytul.setFont(new Font("Arial", Font.BOLD, 20));
		tytul.setBounds(12, 13, 695, 30);
		panel.add(tytul);
		
		tresc_nazwa = new JTextField();
		tresc_nazwa.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_nazwa.setColumns(10);
		tresc_nazwa.setBackground(Color.WHITE);
		tresc_nazwa.setBounds(118, 56, 589, 30);
		panel.add(tresc_nazwa);
	}

	protected void wstaw_grupe() {
		terminarz = new Terminarz();
		terminarz.wstaw_grupe(
				tresc_nazwa.getText(),
				tresc_opis.getText());
		terminarz.zamknij_polaczenie();
	}
	
	protected void mod_grupe() {
		terminarz = new Terminarz();
		terminarz.modyfikuj_grupa(
				grupa.pobierz_id_grupa(),
				tresc_nazwa.getText(),
				tresc_opis.getText());
		terminarz.zamknij_polaczenie();
	}

	private void modyfikacja_wyglad() {
		setTitle("Modyfikuj grup�");
		btn_mod.setText("Modyfikuj");
		tytul.setText("Modyfikuj grup�");
	}
	
	private void grupa_dane() {
		tresc_nazwa.setText(grupa.pobierz_nazwa_grupa());
		tresc_opis.setText(grupa.pobierz_opis_grupa());
	}
	
	//koniec inicjowania okienka
	private void initFinal() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}
	
	private boolean czy_tytul_pusty() {
		if(tresc_nazwa.getText().equals("")) { return true; }
		else { return false;}
	}
}
