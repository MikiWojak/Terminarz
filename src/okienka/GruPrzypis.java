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
import java.awt.Toolkit;
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
		setTitle("Dodaj grup�");
		
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
		this.czyUsuwanie = true;	//bez wzgl�du na warto�� drugiego parametru
		wybraneGrupy();
		zadanieWskrocie();
		
		initFinal();
	}
	
	private void initComp() {
		//wymiary programu
		int program_szerokosc = 725;
		int program_wysokosc = 325;
		int system_szerokosc = Toolkit.getDefaultToolkit().getScreenSize().width;
		int system_wysokosc = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(
				(system_szerokosc - program_szerokosc) / 2,
				(system_wysokosc - program_wysokosc) / 2,
				program_szerokosc,
				program_wysokosc);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 707, 278);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		btnPotwierdz = new JButton("Dodaj");
		btnPotwierdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(czyUsuwanie) { usunGrupe(); }			//usuwanie grupy
				else { dodajGrupe(); }						//dodawanie grupy
				dispose();
			}
		});
		btnPotwierdz.setFont(new Font("Arial", Font.BOLD, 20));
		btnPotwierdz.setBounds(483, 235, 100, 30);
		panel.add(btnPotwierdz);
		
		btnAnuluj = new JButton("Anuluj");
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnuluj.setFont(new Font("Arial", Font.BOLD, 20));
		btnAnuluj.setBounds(595, 235, 100, 30);
		panel.add(btnAnuluj);
		
		tytulDodaj = new JLabel("Wybierz grup�, kt�r� chcesz doda� do zadania");
		tytulDodaj.setHorizontalAlignment(SwingConstants.CENTER);
		tytulDodaj.setFont(new Font("Arial", Font.BOLD, 20));
		tytulDodaj.setBounds(12, 13, 683, 30);
		panel.add(tytulDodaj);
		
		wyborGrupy = new Choice();
		wyborGrupy.setFont(new Font("Arial", Font.PLAIN, 20));
		wyborGrupy.setBounds(12, 49, 683, 30);
		panel.add(wyborGrupy);
		
		tytulZad = new JLabel("Zadanie w skr\u00F3cie");
		tytulZad.setHorizontalAlignment(SwingConstants.CENTER);
		tytulZad.setFont(new Font("Arial", Font.BOLD, 20));
		tytulZad.setBounds(12, 84, 683, 30);
		panel.add(tytulZad);
		
		trescTytul = new JLabel("Tytu\u0142");
		trescTytul.setHorizontalAlignment(SwingConstants.CENTER);
		trescTytul.setFont(new Font("Arial", Font.PLAIN, 20));
		trescTytul.setBounds(12, 127, 683, 30);
		panel.add(trescTytul);
		
		trescData = new JLabel("Data");
		trescData.setHorizontalAlignment(SwingConstants.CENTER);
		trescData.setFont(new Font("Arial", Font.PLAIN, 20));
		trescData.setBounds(12, 170, 683, 30);
		panel.add(trescData);
	}
	
	//dla usuwania zadania
	private void usuwanieWyglad() {
		setTitle("Usu� grup�");
		tytulDodaj.setText("Wybierz grup�, kt�r� chcesz usun�� z zadania");
		btnPotwierdz.setText("Usu�");
	}
	
	//zadanie w skr�cie - dane
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
		
		//brak grup do usuni�cia
		if(grupy.size() == 0) {
			JOptionPane.showMessageDialog(null, "Brak grup do usuni�cia!", "Uwaga!", JOptionPane.WARNING_MESSAGE);
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
		terminarz = new Terminarz();
		terminarz.wstaw_przypisanie(
				grupy.get(wyborGrupy.getSelectedIndex()).pobierz_id_grupa(), 
				zadanie.pobierz_id_zadanie());
		terminarz.zamknij_polaczenie();
	}
	
	private void usunGrupe() {
		terminarz = new Terminarz();
		terminarz.usun_przypisanie(
				grupy.get(wyborGrupy.getSelectedIndex()).pobierz_id_grupa(), 
				zadanie.pobierz_id_zadanie());
		terminarz.zamknij_polaczenie();
	}
}
