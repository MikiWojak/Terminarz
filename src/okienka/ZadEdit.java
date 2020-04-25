package okienka;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Zadanie;
import terminarz.Terminarz;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Choice;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;

public class ZadEdit extends JDialog {
	private Terminarz terminarz;
	private Zadanie zadanie;
	private boolean czy_modyfikacja;
	
	private JLabel tytul;
	private JPanel panel;
	private JLabel opis_dzien;
	private JLabel opis_miesiac;
	private JLabel opis_rok;
	private JLabel opis_tytul;
	private JLabel opis_opis;
	private JLabel opis_priorytet;
	private JLabel opis_wykonane;
	private JTextPane tresc_opis;
	private Choice tresc_priorytet;
	private Choice tresc_wykonane;
	private JButton btn_dodaj;
	private JButton btn_anuluj;
	private Choice tresc_rok;
	private Choice tresc_miesiac;
	private Choice tresc_dzien;
	private JScrollPane scroll_opis_zad;
	private JTextField tresc_tytul;
	
	//dodanie zadania
	public ZadEdit() {
		initComp();
		setTitle("Dodaj zadanie");
		
		czy_modyfikacja = false;
		
		initFinal();
	}
	
	//modyfikacja zadania
	public ZadEdit(Zadanie zadanie) {
		initComp();
		modyfikacja_wyglad();
		
		czy_modyfikacja = true;
		this.zadanie = zadanie;
		zadanie_dane();
		
		initFinal();
	}
	
	private void initComp() {
		//wymiary programu
		int program_szerokosc = 1000;
		int program_wysokosc = 500;
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
		panel.setBounds(0, 0, 994, 465);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		tytul = new JLabel("Dodaj zadanie");
		tytul.setHorizontalAlignment(SwingConstants.CENTER);
		tytul.setFont(new Font("Arial", Font.BOLD, 20));
		tytul.setBounds(12, 13, 970, 30);
		panel.add(tytul);
		
		opis_dzien = new JLabel("Dzie\u0144");
		opis_dzien.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_dzien.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_dzien.setBounds(476, 49, 150, 30);
		panel.add(opis_dzien);
		
		opis_miesiac = new JLabel("Miesi\u0105c");
		opis_miesiac.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_miesiac.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_miesiac.setBounds(254, 49, 150, 30);
		panel.add(opis_miesiac);
		
		opis_rok = new JLabel("Rok");
		opis_rok.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_rok.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_rok.setBounds(12, 56, 150, 30);
		panel.add(opis_rok);
		
		opis_tytul = new JLabel("Tytu\u0142");
		opis_tytul.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_tytul.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_tytul.setBounds(10, 92, 150, 30);
		panel.add(opis_tytul);
		
		opis_opis = new JLabel("Opis");
		opis_opis.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_opis.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_opis.setBounds(10, 135, 150, 150);
		panel.add(opis_opis);
		
		opis_priorytet = new JLabel("Priorytet");
		opis_priorytet.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_priorytet.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_priorytet.setBounds(10, 298, 150, 30);
		panel.add(opis_priorytet);
		
		opis_wykonane = new JLabel("Czy wykonane?");
		opis_wykonane.setHorizontalAlignment(SwingConstants.RIGHT);
		opis_wykonane.setFont(new Font("Arial", Font.ITALIC, 20));
		opis_wykonane.setBounds(10, 341, 150, 30);
		panel.add(opis_wykonane);
		
		scroll_opis_zad = new JScrollPane();
		scroll_opis_zad.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_opis_zad.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll_opis_zad.setBounds(172, 135, 810, 150);
		panel.add(scroll_opis_zad);
		
		tresc_opis = new JTextPane();
		scroll_opis_zad.setViewportView(tresc_opis);
		tresc_opis.setFont(new Font("Arial", Font.PLAIN, 20));
		
		tresc_priorytet = new Choice();
		tresc_priorytet.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_priorytet.setBounds(172, 298, 810, 30);
		priorytet_wartosci();										//watroœci dla priorytetu
		panel.add(tresc_priorytet);
		
		tresc_wykonane = new Choice();
		tresc_wykonane.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_wykonane.setBounds(172, 341, 810, 30);
		wykonane_wartosci();										//wartoœci dla wykonania
		panel.add(tresc_wykonane);
		
		btn_anuluj = new JButton("Anuluj");
		btn_anuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_anuluj.setFont(new Font("Arial", Font.BOLD, 20));
		btn_anuluj.setBounds(882, 422, 100, 30);
		panel.add(btn_anuluj);
		
		btn_dodaj = new JButton("Dodaj\r\n");
		btn_dodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(czy_tytul_pusty()) {
					JOptionPane.showMessageDialog(null, "Tytu³ nie mo¿e byæ pusty!", "Uwaga!", JOptionPane.WARNING_MESSAGE);
				} else {
					if(czy_modyfikacja) {	//modyfikacja rekordu
						modyfikuj_zadanie();
					} else {				//dodanie rekordu
						wstaw_zadanie();
					}
					dispose();
				}
			}
		});
		btn_dodaj.setFont(new Font("Arial", Font.BOLD, 20));
		btn_dodaj.setBounds(730, 422, 140, 30);
		panel.add(btn_dodaj);
		
		tresc_rok = new Choice();
		tresc_rok.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_rok.setBounds(168, 49, 80, 30);
		tresc_rok.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				dni_wartosci();
			}
		});
		rok_wartosci();												//rok
		panel.add(tresc_rok);
		
		tresc_miesiac = new Choice();
		tresc_miesiac.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_miesiac.setBounds(410, 49, 60, 30);
		tresc_miesiac.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				dni_wartosci();
			}
		});
		miesiac_wartosci();											//miesi¹c
		panel.add(tresc_miesiac);
		
		tresc_dzien = new Choice();
		tresc_dzien.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_dzien.setBounds(632, 49, 60, 30);
		dni_wartosci(); 											//dzieñ
		panel.add(tresc_dzien);
		
		tresc_tytul = new JTextField();
		tresc_tytul.setFont(new Font("Arial", Font.PLAIN, 20));
		tresc_tytul.setBounds(172, 92, 810, 30);
		panel.add(tresc_tytul);
		tresc_tytul.setColumns(10);
	}
	
	private void rok_wartosci() {
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
	
	private void miesiac_wartosci() {
		String temp;
		
		for(int i = 1; i <= 12; i++) {
			if(i < 10) {temp = "0" + i; }
			else {temp = "" + i; }
			
			tresc_miesiac.add("" + temp);
		}
	}
	
	private void dni_wartosci() {
		tresc_dzien.removeAll();
		
		int rok = Integer.parseInt(tresc_rok.getSelectedItem());
		int miesiac = Integer.parseInt(tresc_miesiac.getSelectedItem());
		int dni;
		String temp;
		
		switch(miesiac) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			dni = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			dni = 30;
			break;
		case 2:
			if(czy_rok_przestepny(rok)) { dni = 29; }
			else { dni = 28; }
			break;
		default:
			dni = 31;
			break;
		}
		
		for(int i = 1; i <= dni; i++) {
			if(i < 10) { temp = "0" + i; }
			else { temp = "" + i; }
			tresc_dzien.add(temp);
		}
	}
	
	private boolean czy_rok_przestepny(int rok) {
		//ród³o: http://www.algorytm.org/przetwarzanie-dat/wyznaczanie-lat-przestepnych.html
		if((rok % 4 == 0 && rok % 100 != 0) || rok % 400 == 0) { return true; }
		else { return false; }
	}
	
	private void priorytet_wartosci() {
		tresc_priorytet.add("pilne");
		tresc_priorytet.add("wa¿ne");
		tresc_priorytet.add("niewa¿ne");
		tresc_priorytet.add("nieistotne");
	}
	
	private void wykonane_wartosci() {
		tresc_wykonane.add("nie");
		tresc_wykonane.add("tak");
	}
	
	private void modyfikacja_wyglad() {
		setTitle("Modyfikuj zadanie");
		btn_dodaj.setText("Modyfikuj");
		tytul.setText("Modyfikuj zadanie");
	}
	
	//koniec inicjowania okienka
	private void initFinal() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}
	
	private String data_pelna() {
		return tresc_rok.getSelectedItem() + "-" + tresc_miesiac.getSelectedItem() + "-" + tresc_dzien.getSelectedItem();
	}
	
	private boolean czy_wykonane_bool() {
		if(tresc_wykonane.getSelectedItem().equals("nie")) { return false; }
		else { return true; }
	}
	
	private void wstaw_zadanie() {
		terminarz = new Terminarz();
		terminarz.wstaw_zadanie(
				Date.valueOf(data_pelna()), 
				tresc_tytul.getText(), 
				tresc_opis.getText(), 
				tresc_priorytet.getSelectedItem(), 
				czy_wykonane_bool());
		terminarz.zamknij_polaczenie();
	}
	
	private boolean czy_tytul_pusty() {
		if(tresc_tytul.getText().equals("")) { return true; }
		else { return false; }
	}
	
	private void modyfikuj_zadanie() {
		terminarz = new Terminarz();
		terminarz.modyfikuj_zadanie(
				zadanie.pobierz_id_zadanie(), 
				Date.valueOf(data_pelna()),
				tresc_tytul.getText(),
				tresc_opis.getText(),
				tresc_priorytet.getSelectedItem(),
				czy_wykonane_bool());
		terminarz.zamknij_polaczenie();
	}
	
	//DEBUG
	private void data_rok() {
		String data = LocalDate.now().toString();
		System.out.println(data);
		
		String rok_str = data.substring(0, 4);
		System.out.println(rok_str);
		
		int rok = Integer.parseInt(rok_str);
		System.out.println(rok + "\t" + (rok - 20));
	}
	
	private void zadanie_dane() {
		String data = zadanie.pobierz_data_zadanie().toString();
		String rok = data.substring(0, 4);
		String miesiac = data.substring(5, 7);
		String dzien = data.substring(8, 10);
		
		String czy_wykonane;
		if (zadanie.pobierz_czy_wykonane()) { czy_wykonane = "tak"; }
		else { czy_wykonane = "nie"; }
		
		tresc_rok.select(rok);
		tresc_miesiac.select(miesiac);
		tresc_dzien.select(dzien);
		tresc_tytul.setText(zadanie.pobierz_tytul_zadanie());
		tresc_opis.setText(zadanie.pobierz_opis_zadanie());
		tresc_priorytet.select(zadanie.pobierz_priorytet_zadanie());
		tresc_wykonane.select(czy_wykonane);
	}
	
	/**
	 * DEBUG
	 * Sprawdzenie czy dni podczas modyfikacji s¹ prawid³owe
	 */
	public int dni_wartosci_debug() {
		int ilosc = tresc_dzien.getItemCount();
		System.out.println(tresc_dzien.getItemCount());
		int dzien = Integer.parseInt(tresc_dzien.getItem(ilosc - 1));
		
		return dzien;
	}
}
