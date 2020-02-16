package okienka;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Choice;
import javax.swing.JToggleButton;

public class ZadEdit extends JDialog {
	private JLabel tytul;
	private JPanel panel;
	private JLabel opis_dzien;
	private JLabel opis_miesiac;
	private JLabel opis_rok;
	private JLabel opis_tytul;
	private JLabel opis_opis;
	private JLabel opis_priorytet;
	private JLabel opis_wykonane;
	private JTextPane tresc_dzien;
	private JTextPane tresc_miesiac;
	private JTextPane tresc_rok;
	private JTextPane tresc_tytul;
	private JTextPane tresc_opis;
	private Choice tresc_priorytet;
	private Choice tresc_wykonane;
	private JButton btn_dodaj;
	private JButton btn_anuluj;

	public ZadEdit() {
		init();
	}
	
	public void init() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 982, 553);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		tytul = new JLabel("Dodaj zadanie");
		tytul.setHorizontalAlignment(SwingConstants.CENTER);
		tytul.setFont(new Font("Arial", Font.PLAIN, 20));
		tytul.setBounds(12, 13, 958, 30);
		panel.add(tytul);
		
		opis_dzien = new JLabel("Dzie\u0144");
		opis_dzien.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_dzien.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_dzien.setBounds(12, 56, 150, 30);
		panel.add(opis_dzien);
		
		opis_miesiac = new JLabel("Miesi\u0105c");
		opis_miesiac.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_miesiac.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_miesiac.setBounds(12, 99, 150, 30);
		panel.add(opis_miesiac);
		
		opis_rok = new JLabel("Rok");
		opis_rok.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_rok.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_rok.setBounds(12, 142, 150, 30);
		panel.add(opis_rok);
		
		opis_tytul = new JLabel("Tytu\u0142");
		opis_tytul.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_tytul.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_tytul.setBounds(12, 185, 150, 30);
		panel.add(opis_tytul);
		
		opis_opis = new JLabel("Opis");
		opis_opis.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_opis.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_opis.setBounds(12, 228, 150, 150);
		panel.add(opis_opis);
		
		opis_priorytet = new JLabel("Priorytet");
		opis_priorytet.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_priorytet.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_priorytet.setBounds(12, 391, 150, 30);
		panel.add(opis_priorytet);
		
		opis_wykonane = new JLabel("Czy wykonane?");
		opis_wykonane.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_wykonane.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_wykonane.setBounds(12, 434, 150, 30);
		panel.add(opis_wykonane);
		
		tresc_dzien = new JTextPane();
		tresc_dzien.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_dzien.setBounds(174, 56, 796, 30);
		panel.add(tresc_dzien);
		
		tresc_miesiac = new JTextPane();
		tresc_miesiac.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_miesiac.setBounds(174, 99, 796, 30);
		panel.add(tresc_miesiac);
		
		tresc_rok = new JTextPane();
		tresc_rok.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_rok.setBounds(174, 142, 796, 30);
		panel.add(tresc_rok);
		
		tresc_tytul = new JTextPane();
		tresc_tytul.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_tytul.setBounds(174, 185, 796, 30);
		panel.add(tresc_tytul);
		
		tresc_opis = new JTextPane();
		tresc_opis.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_opis.setBounds(174, 228, 796, 150);
		panel.add(tresc_opis);
		
		tresc_priorytet = new Choice();
		tresc_priorytet.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_priorytet.setBounds(174, 391, 798, 30);
		panel.add(tresc_priorytet);
		
		tresc_wykonane = new Choice();
		tresc_wykonane.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_wykonane.setBounds(174, 434, 798, 30);
		panel.add(tresc_wykonane);
		
		btn_anuluj = new JButton("Anuluj");
		btn_anuluj.setFont(new Font("Arial", Font.PLAIN, 20));
		btn_anuluj.setBounds(870, 510, 100, 30);
		panel.add(btn_anuluj);
		
		btn_dodaj = new JButton("Dodaj\r\n");
		btn_dodaj.setFont(new Font("Arial", Font.PLAIN, 20));
		btn_dodaj.setBounds(758, 510, 100, 30);
		panel.add(btn_dodaj);
	}
}
