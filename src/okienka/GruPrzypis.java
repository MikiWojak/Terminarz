package okienka;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Zadanie;
import terminarz.Terminarz;
import model.Grupa;

import java.awt.Font;
import java.awt.Dialog.ModalityType;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GruPrzypis extends JDialog {
	private Zadanie zadanie;
	private List<Grupa>grupy;
	private boolean czyUsuwanie;
	private Terminarz terminarz;
	
	private JButton btnPotwierdz;
	private JButton btnAnuluj;
	private JLabel tytulDodaj;
	private Choice wyborGrupy;
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
		wszystkieGrupy();
		zadanieWskrocie();
		
		initFinal();
	}
	
	//usuwanie grupy z zadania
	public GruPrzypis(Zadanie zadanie, boolean czyUsuwanie) {
		initComp();
		usuwanieWyglad();
		
		this.zadanie = zadanie;
		this.czyUsuwanie = true;	//bez wzglêdu na wartoœæ drugiego parametru
		wybraneGrupy();
		zadanieWskrocie();
		
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
		btnPotwierdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO: handle exception
				if(czyUsuwanie) { usunGrupe(); }			//usuwanie grupy
				else { dodajGrupe(); }						//dodawanie grupy
				dispose();
			}
		});
		btnPotwierdz.setFont(new Font("Arial", Font.BOLD, 20));
		btnPotwierdz.setBounds(461, 235, 100, 30);
		panel.add(btnPotwierdz);
		
		btnAnuluj = new JButton("Anuluj");
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: handle exception
				
				dispose();
			}
		});
		btnAnuluj.setFont(new Font("Arial", Font.BOLD, 20));
		btnAnuluj.setBounds(570, 235, 100, 30);
		panel.add(btnAnuluj);
		
		tytulDodaj = new JLabel("Dodaj grup\u0119 do zadania");
		tytulDodaj.setHorizontalAlignment(SwingConstants.CENTER);
		tytulDodaj.setFont(new Font("Arial", Font.BOLD, 20));
		tytulDodaj.setBounds(12, 13, 658, 30);
		panel.add(tytulDodaj);
		
		wyborGrupy = new Choice();
		wyborGrupy.setFont(new Font("Arial", Font.PLAIN, 20));
		wyborGrupy.setBounds(12, 49, 658, 30);
		panel.add(wyborGrupy);
		
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
	
	//zadanie w skrócie - dane
	private void zadanieWskrocie() {
		trescTytul.setText(zadanie.pobierz_tytul_zadanie());
		trescData.setText(zadanie.pobierz_data_zadanie().toString());
	}
	
	private void wszystkieGrupy() {
		int ilosc_wynikow;
		terminarz = new Terminarz();
		
		ilosc_wynikow = terminarz.przypis_ilosc_przypisan(zadanie.pobierz_id_zadanie());
		if(ilosc_wynikow == 0) { grupy = terminarz.lista_grupy(); }							//wszystkie grupy
		else { grupy = terminarz.lista_brakujacych_grup(zadanie.pobierz_id_zadanie()); }	//nieprzypisane grupy
		
		terminarz.zamknij_polaczenie();
		
		//brak grup do przypisania
		if(grupy.size() == 0) {
			JOptionPane.showMessageDialog(null, "Brak grup do przypisania!", "Uwaga!", JOptionPane.WARNING_MESSAGE);
			btnPotwierdz.setEnabled(false);
		}
		
		for(int i = 0; i < grupy.size(); i++) {
			wyborGrupy.add(grupy.get(i).pobierz_nazwa_grupa());
		}
	}
	
	private void wybraneGrupy() {
		terminarz = new Terminarz();
		grupy = terminarz.lista_grupy(zadanie.pobierz_id_zadanie());
		terminarz.zamknij_polaczenie();
		
		//brak grup do usuniêcia
		if(grupy.size() == 0) {
			JOptionPane.showMessageDialog(null, "Brak grup do usuniêcia!", "Uwaga!", JOptionPane.WARNING_MESSAGE);
			btnPotwierdz.setEnabled(false);
		}
		
		for(int i = 0; i < grupy.size(); i++) {
			wyborGrupy.add(grupy.get(i).pobierz_nazwa_grupa());
		}
	}
	
	//koniec inicjowania okienka
	private void initFinal() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}
	
	private void dodajGrupe() {
		// TODO: handle exception
		terminarz = new Terminarz();
		terminarz.wstaw_przypisanie(
				grupy.get(wyborGrupy.getSelectedIndex()).pobierz_id_grupa(), 
				zadanie.pobierz_id_zadanie());
		terminarz.zamknij_polaczenie();
	}
	
	private void usunGrupe() {
		// TODO: handle exception
		terminarz = new Terminarz();
		terminarz.usun_przypisanie(
				grupy.get(wyborGrupy.getSelectedIndex()).pobierz_id_grupa(), 
				zadanie.pobierz_id_zadanie());
		terminarz.zamknij_polaczenie();
	}
}
