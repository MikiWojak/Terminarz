package okienka;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import terminarz.Terminarz;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Choice;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class ZadEdit extends JDialog {
	private Terminarz terminarz;
	private Date data;
	
	private JLabel tytul;
	private JPanel panel;
	private JLabel opis_dzien;
	private JLabel opis_miesiac;
	private JLabel opis_rok;
	private JLabel opis_tytul;
	private JLabel opis_opis;
	private JLabel opis_priorytet;
	private JLabel opis_wykonane;
	private JTextPane tresc_tytul;
	private JTextPane tresc_opis;
	private Choice tresc_priorytet;
	private Choice tresc_wykonane;
	private JButton btn_dodaj;
	private JButton btn_anuluj;
	private Choice tresc_rok;
	private Choice tresc_miesiac;
	private Choice tresc_dzien;

	public ZadEdit() {
		initComp();
		setTitle("Dodaj zadanie");
		initFinal();
	}
	
	public void initComp() {
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
		opis_dzien.setBounds(12, 135, 150, 30);
		panel.add(opis_dzien);
		
		opis_miesiac = new JLabel("Miesi\u0105c");
		opis_miesiac.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_miesiac.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_miesiac.setBounds(12, 92, 150, 30);
		panel.add(opis_miesiac);
		
		opis_rok = new JLabel("Rok");
		opis_rok.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_rok.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_rok.setBounds(12, 56, 150, 30);
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
		priorytet_wartosci();										//watroœci dla priorytetu
		panel.add(tresc_priorytet);
		
		tresc_wykonane = new Choice();
		tresc_wykonane.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_wykonane.setBounds(174, 434, 798, 30);
		wykonane_wartosci();										//wartoœci dla wykonania
		panel.add(tresc_wykonane);
		
		btn_anuluj = new JButton("Anuluj");
		btn_anuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_anuluj.setFont(new Font("Arial", Font.PLAIN, 20));
		btn_anuluj.setBounds(870, 510, 100, 30);
		panel.add(btn_anuluj);
		
		btn_dodaj = new JButton("Dodaj\r\n");
		btn_dodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//wstaw_zadanie();
				//dispose();
				
				dni_wartosci();
			}
		});
		btn_dodaj.setFont(new Font("Arial", Font.PLAIN, 20));
		btn_dodaj.setBounds(758, 510, 100, 30);
		panel.add(btn_dodaj);
		
		tresc_rok = new Choice();
		tresc_rok.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_rok.setBounds(168, 49, 798, 30);
		rok_wartosci();												//rok
		panel.add(tresc_rok);
		
		tresc_miesiac = new Choice();
		tresc_miesiac.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_miesiac.setBounds(168, 92, 798, 30);
		miesiac_wartosci();											//miesi¹c
		panel.add(tresc_miesiac);
		
		tresc_dzien = new Choice();
		tresc_dzien.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_dzien.setBounds(168, 135, 798, 30);
		panel.add(tresc_dzien);
	}
	
	public void rok_wartosci() {
		String data_teraz = LocalDate.now().toString();
		String rok_str = data_teraz.substring(0, 4);
		int rok = Integer.parseInt(rok_str);
		rok += 10;
		
		for(int i = 0; i < 15; i++) {
			tresc_rok.add("" + rok);
			rok--;
		}
		
		tresc_rok.select(10);
	}
	
	public void miesiac_wartosci() {
		String temp;
		
		for(int i = 1; i <= 12; i++) {
			if(i < 10) {temp = "0" + i; }
			else {temp = "" + i; }
			
			tresc_miesiac.add("" + temp);
		}
	}
	
	public void dni_wartosci() {
		int rok = Integer.parseInt(tresc_rok.getSelectedItem());
		int miesiac = Integer.parseInt(tresc_miesiac.getSelectedItem());
		
		System.out.println(rok + "\t" + miesiac);
	}
	
	public boolean czy_rok_przestepny(int rok) {
		//ród³o: http://www.algorytm.org/przetwarzanie-dat/wyznaczanie-lat-przestepnych.html
		if((rok % 4 == 0 && rok % 100 != 0) || rok % 400 == 0) { return true; }
		else { return false; }
	}
	
	public void priorytet_wartosci() {
		tresc_priorytet.add("pilne");
		tresc_priorytet.add("wa¿ne");
		tresc_priorytet.add("niewa¿ne");
		tresc_priorytet.add("nieistotne");
	}
	
	public void wykonane_wartosci() {
		tresc_wykonane.add("nie");
		tresc_wykonane.add("tak");
	}
	
	//koniec inicjowania okienka
	public void initFinal() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}
	
	public void wstaw_zadanie() {
		/*
		String data = tresc_rok.getText() + "-" + tresc_miesiac.getText() + "-" + tresc_dzien.getText();
		//System.out.println(data);
		
		boolean czy_wykonane;
		if(tresc_wykonane.getSelectedItem().equals("nie")) { czy_wykonane = false; }
		else { czy_wykonane = true; }
		//System.out.println(czy_wykonane);
		
		terminarz = new Terminarz();
		terminarz.wstaw_zadanie(
				Date.valueOf(data), 
				tresc_tytul.getText(), 
				tresc_opis.getText(), 
				tresc_priorytet.getSelectedItem(), 
				czy_wykonane);
		terminarz.zamknij_polaczenie();
		*/
	}
	
	//DEBUG
	public void data_rok() {
		String data = LocalDate.now().toString();
		System.out.println(data);
		
		String rok_str = data.substring(0, 4);
		System.out.println(rok_str);
		
		int rok = Integer.parseInt(rok_str);
		System.out.println(rok + "\t" + (rok - 20));
	}
}
