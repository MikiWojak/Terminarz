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
		int program_szerokosc = 1000;
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
		panel.setBounds(0, 0, 982, 553);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		tytul_artykulu = new JLabel("Tytu\u0142 artyku\u0142u");
		tytul_artykulu.setHorizontalAlignment(SwingConstants.CENTER);
		tytul_artykulu.setFont(new Font("Arial", Font.BOLD, 20));
		tytul_artykulu.setBounds(424, 12, 546, 30);
		panel.add(tytul_artykulu);
		
		btn_zamknij = new JButton("Zamknij");
		btn_zamknij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btn_zamknij.setFont(new Font("Arial", Font.BOLD, 20));
		btn_zamknij.setBounds(830, 510, 140, 30);
		panel.add(btn_zamknij);
		
		scroll_tematy = new JScrollPane();
		scroll_tematy.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_tematy.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll_tematy.setBounds(12, 13, 400, 527);
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
		scroll_tresc.setBounds(424, 58, 546, 439);
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
		lista_tematow.add("Wst�p");											//0
		lista_tematow.add("Widok");											//1
		lista_tematow.add("Zadanie");										//2
		lista_tematow.add("Pomoc");											//3
		lista_tematow.add("Lista zada�");									//4
		lista_tematow.add("Filtr grup");									//5
		lista_tematow.add("Szczeg�y zadania");								//6
		lista_tematow.add("Oznacz jako zrobione/niezrobione");				//7
		lista_tematow.add("Dodawanie, modyfikacja i usuwanie zada�");		//8
		lista_tematow.add("Przypisywanie zada� do grup");					//9
		lista_tematow.add("Lista grup");									//10
		lista_tematow.add("Szczeg�y grupy");								//11
		lista_tematow.add("Dodawanie, modyfikacja i usuwanie grup");		//12
		lista_tematow.add("Plik z danymi");									//13
		lista_tematow.add("Rozwi�zywanie problem�w");						//14
		
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
					+ "\n\nTytu�: Miko�aj �arnowski"
					+ "\n\nProgram ma u�atwi� zarz�dzanie zadaniami, planami, spotkaniami, itp.";
		case 1:		//Widok
			return "Znajduje si� w menu na g�rnej belce. Umo�liwia prze��czenie si� pomi�dzy list� zada� a list� grup."
					+ "\n\nOpcje:"
					+ "\n- Zadania;"
					+ "\n- Grupy.";
		case 2:		//Zadanie
			return "Znajduje si� w menu na g�rnej belce. S� tam operacje na zadaniach. Jest w��czone tylko gdy jest w��czona lista zada�."
					+ "\n\nOpcje:"
					+ "\n- Dodaj;"
					+ "\n- Modyfikuj;"
					+ "\n- Usu�;"
					+ "\n- Dodaj do grupy;"
					+ "\n- Usu� z grupy.";
		case 3:		//Pomoc
			return "Znajduje si� w menu na g�rnej belce. Uruchamia okno pomocy, w kt�rym si� teraz znajdujesz."
					+ "\n\nZnajdziesz tu troch� informacji o tym, jak pos�ugiwa� si� programem.";
		case 4:		//Lista zada�
			return "Na li�cie zada� prezentowane s� takie informacje:"
					+ "\n- czy zadanie jest zrobione ('*' - tak, ' ' - nie);"
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
		case 8:		//Dodawanie, modyfikacja i usuwanie zada�
			return "Dodawanie zadania"
					+ "\nZadanie -> Dodaj"
					+ "\nUzupe�niasz pola danymi. Wprowadzasz dat�, u�ywaj�c odpowiednio: roku, miesi�ca i dnia. Zapisujesz nowe zadanie."
					+ "\n\nModyfikacja zadania"
					+ "\nZadanie -> Modyfikuj"
					+ "\nWybierasz jedno zadanie. Modyfikujesz b�d� poprawiasz wybrane zadanie. Potwierdzasz wprowadzone zmiany."
					+ "\n\nUsuni�cie zadania"
					+ "\nZadanie -> Usu�"
					+ "\nWybierasz jedno zadanie. Potwierdzasz ch�� jego usuni�cia.";
		case 9:		//Przypisywanie zada� do grup
			return "Program umo�liwia przypisanie zadania do co najmniej jednej grupy. Mo�na je tak�e przypisa� do wi�kszej liczby grup."
					+ "\n\nPrzypisanie zadania do grupy"
					+ "\nZadanie -> Dodaj do grupy"
					+ "\nWybierasz grup�. Sprawdzasz, czy tytu� zadania i jego data si� zgadzaj�. Potwierdzasz przypisanie zadania do grupy."
					+ "\nUwaga!"
					+ "\nPo wyczerpaniu wolnych grup dalsze przypisywanie grup do takiego zadania b�dzie niemo�liwe."
					+ "\n\nUsuni�cie zadania z grupy"
					+ "\nZadanie -> Usu� z grupy"
					+ "\nWybierasz grup�. Sprawdzasz, czy tytu� zadania i jego data si� zgadzaj�. Potwierdzasz usuni�cie zadania z grupy."
					+ "\nUwaga!"
					+ "\nUsuni�cie grup z zadania, kt�re nie zosta�o przypisane do �adnej grupy, b�dzie niemo�liwe.";
		case 10:	//Lista grup
			return "Lista stworzonych przez u�ytkownika grup. Na li�cie wy�wietla si� jedynie nazwa grupy. Grupy s� posortowane alfabetycznie.";
		case 11:	//Szczeg�y grupy
			return "Po wybraniu grupy z listy wy�wietl� si� jej szczeg�y:"
					+ "\n- nazwa grupy;"
					+ "\n- opis grupy.";
		case 12:	//Dodawanie, modyfikacja i usuwanie grup
			return "Przyciski do ww. operacji znajduj� si� pod szczeg�ami zadania."
					+ "\n\nDodawanie grupy"
					+ "\nPrzycisk Dodaj"
					+ "\nWprowadzasz dane. Zapisujesz now� grup�."
					+ "\n\nModyfikacja grupy"
					+ "\nPrzycisk Modyfikuj"
					+ "\nWybierasz jedn� grup�. Wprowadzasz lub modyfikujesz jej dane. Potwierdzasz wprowadzone zmiany."
					+ "\n\nUsuni�cie grupy"
					+ "\nPrzycisk Usu�"
					+ "\nWybierasz jedn� grup�. Potwierdzasz ch�� jej usuni�cia.";
		case 13:	//Plik z danymi
			return "Plik z danymi to plik bazy danych. Ma nazw� 'terminarz.db'. Znajduje si� w tym samym folderze, co plik uruchomieniowy programu."
					+ "\n\nJe�li go nie ma, jest tworzony nowy, pusty plik danych."
					+ "\n\nJe�li za� jest, to z niego s� wczytywane dane."
					+ "\n\nUwaga!"
					+ "\nUsuni�cie tego pliku powoduje utrat� zapisanych w nich danych!"
					+ "\n\nZaleca si� wykonywanie kopii zapasowej pliku 'terminarz.db' w celu ochrony zapisanych w nich danych.";
		case 14:	//Rozwi�zywanie problem�w
			return "Na wi�kszo�� typowych problem�w wystarczy ponowne uruchomienie funkcji lub ca�ego programu."
					+ "\n\nDo�o�y�em wszelkich stara�, �eby program dzia�a� prawid�owo. Nie wykluczam jednak obecno�ci b��d�w, kt�re mog� wyj�� na jaw podczas u�ytkowania programu.";
		default:
			return "";
		}
	}
}
