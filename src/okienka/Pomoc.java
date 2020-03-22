package okienka;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;
import java.awt.Dialog.ModalityType;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pomoc extends JDialog {
	private List<String>lista_tematow;
	
	private JPanel panel;
	private JLabel tytul_artykulu;
	private JButton btn_zamknij;
	private JScrollPane scroll_tematy;
	private JList tematy;
	private JScrollPane scroll_tresc;
	private JTextPane tresc_artykulu;
	
	public Pomoc() {
		initComp();
		
		lista_tematow = new LinkedList<String>();
		utworz_liste_tematow();
		
		tytul_artykulu.setText(lista_tematow.get(0));
		tresc_artykulu.setText(ustaw_tresc_artykulu(0));
		
		initFinal();
	}
	
	private void initComp() {
		//wymiary programu
		int program_szerokosc = 800;
		int program_wysokosc = 600;
		int system_szerokosc = Toolkit.getDefaultToolkit().getScreenSize().width;
		int system_wysokosc = Toolkit.getDefaultToolkit().getScreenSize().height;
				
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(
				(system_szerokosc - program_szerokosc) / 2,
				(system_wysokosc - program_wysokosc) / 2,
				program_szerokosc,
				program_wysokosc);
		getContentPane().setLayout(null);
		setTitle("Pomoc");
		
		panel = new JPanel();
		panel.setBounds(0, 0, 782, 553);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		tytul_artykulu = new JLabel("Tytu\u0142 artyku\u0142u");
		tytul_artykulu.setHorizontalAlignment(SwingConstants.CENTER);
		tytul_artykulu.setFont(new Font("Arial", Font.BOLD, 20));
		tytul_artykulu.setBounds(324, 13, 446, 30);
		panel.add(tytul_artykulu);
		
		btn_zamknij = new JButton("Zamknij");
		btn_zamknij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btn_zamknij.setFont(new Font("Arial", Font.BOLD, 20));
		btn_zamknij.setBounds(630, 510, 140, 30);
		panel.add(btn_zamknij);
		
		scroll_tematy = new JScrollPane();
		scroll_tematy.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_tematy.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll_tematy.setBounds(12, 13, 300, 527);
		panel.add(scroll_tematy);
		
		tematy = new JList();
		scroll_tematy.setViewportView(tematy);
		tematy.setFont(new Font("Arial", Font.PLAIN, 20));
		tematy.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				tresc_artykulu.setText(ustaw_tresc_artykulu(tematy.getSelectedIndex()));
				tytul_artykulu.setText(tematy.getSelectedValue().toString());
			}
		});
		
		scroll_tresc = new JScrollPane();
		scroll_tresc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_tresc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll_tresc.setBounds(324, 58, 446, 439);
		panel.add(scroll_tresc);
		
		tresc_artykulu = new JTextPane();
		tresc_artykulu.setEditable(false);
		scroll_tresc.setViewportView(tresc_artykulu);
		tresc_artykulu.setFont(new Font("Arial", Font.PLAIN, 20));
	}
	
	//koniec inicjowania okienka
	private void initFinal() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}
	
	//lista temat�w
	private void utworz_liste_tematow() {
		lista_tematow.add("Wst�p");
		lista_tematow.add("Widok");
		lista_tematow.add("Zadanie");
		lista_tematow.add("Pomoc");
		lista_tematow.add("Lista zada�");
		lista_tematow.add("Filtr grup");
		lista_tematow.add("Szczeg�y zadania");
		lista_tematow.add("Oznacz jako zrobione/niezrobione");
		
		tematy.setModel(rekordy_tematy());
	}
	
	private DefaultListModel<Object> rekordy_tematy() {
		DefaultListModel<Object> lista = new DefaultListModel<Object>();
		
		try {
			for(int i = 0; i < lista_tematow.size(); i++) {
				lista.addElement(lista_tematow.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
		return lista;		
	}
	
	//tre�� artyku�u
	private String ustaw_tresc_artykulu(int index) {
		switch(index) {
		case 0:		//Wst�p
			return "Terminarz"
					+ "\n\nTytu�: Miko�aj �arnowski";
		case 1:		//Widok
			return "Menu znajduj�ce si� na g�rnej belce. Umo�liwia prze��czenie si� pomi�dzy list� zada� a list� grup."
					+ "\n\nOpcje:"
					+ "\n- Zadania;"
					+ "\n- Grupy.";
		case 2:		//Zadanie
			return "Menu znajduj�ce si� na g�rnej belce. S� tam operacje na zadnaiach. Jest w��czone tylko gdy jest w��czona lista zada�."
					+ "\n\nOpcje:"
					+ "\n- Dodaj;"
					+ "\n- Modyfikuj;"
					+ "\n- Usu�;"
					+ "\n- Dodaj do grupy;"
					+ "\n- Usu� z grupy.";
		case 3:		//Pomoc
			return "";
		case 4:		//Lista zada�
			return "Na li�cie zada� prezentowane s� takie informacje:"
					+ "\n- czy zadanie jest zrobione (* lub jej brak);"
					+ "\n- data zadania (rrrr-mm-dd);"
					+ "\n- tytu� zadania."
					+ "\n\nKolejno�� sortowania rekord�w jest taka sama. Dzi�ki temu na g�rze znajduj� si� zadania: niewykonane i o najbli�szym terminie.";
		case 5:		//Filtr grup
			return "Nad list� znajduje si� filtr grup. Grupy maj� za zadanie u�atwi� organizacj� i zarz�dzanie zadaniami."
					+ "\n\nOpr�cz grup utworzonych przez u�ytkownika s� dwie specjalne:"
					+ "\n- brak filtru - wszystkie zadania;"
					+ "\n- brak grupy - zadania bez przypisanej grupy, tam l�duj� nowo utworzone zadania.";
		case 6:		//Szczeg�y zadania
			return "Ten przycisk znajduje si� pod list� zada� po lewej stronie. Umo�liwia wy�wietlenie szczeg��w zadania, takich jak:"
					+ "\n- lista grup przypisanych do zadania;"
					+ "\n- opis zadania;"
					+ "\n- priorytet zadania.";
		case 7:		//Oznacz jako zrobione/niezrobione
			return "Ten przycisk znajduje si� pod list� zada� po prawej stronie. Pozwala na szybkie oznaczenie niezrobionego zadania jako zrobione i na odwr�t - zrobionego jako niezrobione.";
		case 8:
			return "";
		default:
			return "";
		}
	}
}
