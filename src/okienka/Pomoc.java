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
	
	//lista tematów
	private void utworz_liste_tematow() {
		lista_tematow.add("Wstêp");											//0
		lista_tematow.add("Widok");											//1
		lista_tematow.add("Zadanie");										//2
		lista_tematow.add("Pomoc");											//3
		lista_tematow.add("Lista zadañ");									//4
		lista_tematow.add("Filtr grup");									//5
		lista_tematow.add("Szczegó³y zadania");								//6
		lista_tematow.add("Oznacz jako zrobione/niezrobione");				//7
		lista_tematow.add("Dodawanie, modyfikacja i usuwanie zadañ");		//8
		lista_tematow.add("Przypisywanie zadañ do grup");					//9
		lista_tematow.add("Lista grup");									//10
		lista_tematow.add("Szczegó³y grupy");								//11
		lista_tematow.add("Dodawanie, modyfikacja i usuwanie grup");		//12
		lista_tematow.add("Plik z danymi");									//13
		lista_tematow.add("Rozwi¹zywanie problemów");						//14
		
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
	
	//treœæ artyku³u
	private String ustaw_tresc_artykulu(int index) {
		switch(index) {
		case 0:		//Wstêp
			return "Terminarz"
					+ "\n\nTytu³: Miko³aj ¯arnowski"
					+ "\n\nProgram ma u³atwiæ zarz¹dzanie zadaniami, planami, spotkaniami, itp.";
		case 1:		//Widok
			return "Znajduje siê w menu na górnej belce. Umo¿liwia prze³¹czenie siê pomiêdzy list¹ zadañ a list¹ grup."
					+ "\n\nOpcje:"
					+ "\n- Zadania;"
					+ "\n- Grupy.";
		case 2:		//Zadanie
			return "Znajduje siê w menu na górnej belce. S¹ tam operacje na zadaniach. Jest w³¹czone tylko gdy jest w³¹czona lista zadañ."
					+ "\n\nOpcje:"
					+ "\n- Dodaj;"
					+ "\n- Modyfikuj;"
					+ "\n- Usuñ;"
					+ "\n- Dodaj do grupy;"
					+ "\n- Usuñ z grupy.";
		case 3:		//Pomoc
			return "Znajduje siê w menu na górnej belce. Uruchamia okno pomocy, w którym siê teraz znajdujesz."
					+ "\n\nZnajdziesz tu trochê informacji o tym, jak pos³ugiwaæ siê programem.";
		case 4:		//Lista zadañ
			return "Na liœcie zadañ prezentowane s¹ takie informacje:"
					+ "\n- czy zadanie jest zrobione ('*' - tak, ' ' - nie);"
					+ "\n- data zadania (rrrr-mm-dd);"
					+ "\n- tytu³ zadania."
					+ "\n\nKolejnoœæ sortowania rekordów jest taka sama. Dziêki temu na górze znajduj¹ siê zadania: niewykonane i o najbli¿szym terminie.";
		case 5:		//Filtr grup
			return "Nad list¹ znajduje siê filtr grup. Grupy maj¹ za zadanie u³atwiæ organizacjê i zarz¹dzanie zadaniami."
					+ "\n\nOprócz grup utworzonych przez u¿ytkownika s¹ dwie specjalne:"
					+ "\n- brak filtru - wszystkie zadania;"
					+ "\n- brak grupy - zadania bez przypisanej grupy, tam l¹duj¹ nowo utworzone zadania.";
		case 6:		//Szczegó³y zadania
			return "Ten przycisk znajduje siê pod list¹ zadañ po lewej stronie. Umo¿liwia wyœwietlenie szczegó³ów zadania, takich jak:"
					+ "\n- lista grup przypisanych do zadania;"
					+ "\n- opis zadania;"
					+ "\n- priorytet zadania.";
		case 7:		//Oznacz jako zrobione/niezrobione
			return "Ten przycisk znajduje siê pod list¹ zadañ po prawej stronie. Pozwala na szybkie oznaczenie niezrobionego zadania jako zrobione i na odwrót - zrobionego jako niezrobione.";
		case 8:		//Dodawanie, modyfikacja i usuwanie zadañ
			return "Dodawanie zadania"
					+ "\nZadanie -> Dodaj"
					+ "\nUzupe³niasz pola danymi. Wprowadzasz datê, u¿ywaj¹c odpowiednio: roku, miesi¹ca i dnia. Zapisujesz nowe zadanie."
					+ "\n\nModyfikacja zadania"
					+ "\nZadanie -> Modyfikuj"
					+ "\nWybierasz jedno zadanie. Modyfikujesz b¹dŸ poprawiasz wybrane zadanie. Potwierdzasz wprowadzone zmiany."
					+ "\n\nUsuniêcie zadania"
					+ "\nZadanie -> Usuñ"
					+ "\nWybierasz jedno zadanie. Potwierdzasz chêæ jego usuniêcia.";
		case 9:		//Przypisywanie zadañ do grup
			return "Program umo¿liwia przypisanie zadania do co najmniej jednej grupy. Mo¿na je tak¿e przypisaæ do wiêkszej liczby grup."
					+ "\n\nPrzypisanie zadania do grupy"
					+ "\nZadanie -> Dodaj do grupy"
					+ "\nWybierasz grupê. Sprawdzasz, czy tytu³ zadania i jego data siê zgadzaj¹. Potwierdzasz przypisanie zadania do grupy."
					+ "\nUwaga!"
					+ "\nPo wyczerpaniu wolnych grup dalsze przypisywanie grup do takiego zadania bêdzie niemo¿liwe."
					+ "\n\nUsuniêcie zadania z grupy"
					+ "\nZadanie -> Usuñ z grupy"
					+ "\nWybierasz grupê. Sprawdzasz, czy tytu³ zadania i jego data siê zgadzaj¹. Potwierdzasz usuniêcie zadania z grupy."
					+ "\nUwaga!"
					+ "\nUsuniêcie grup z zadania, które nie zosta³o przypisane do ¿adnej grupy, bêdzie niemo¿liwe.";
		case 10:	//Lista grup
			return "Lista stworzonych przez u¿ytkownika grup. Na liœcie wyœwietla siê jedynie nazwa grupy. Grupy s¹ posortowane alfabetycznie.";
		case 11:	//Szczegó³y grupy
			return "Po wybraniu grupy z listy wyœwietl¹ siê jej szczegó³y:"
					+ "\n- nazwa grupy;"
					+ "\n- opis grupy.";
		case 12:	//Dodawanie, modyfikacja i usuwanie grup
			return "Przyciski do ww. operacji znajduj¹ siê pod szczegó³ami zadania."
					+ "\n\nDodawanie grupy"
					+ "\nPrzycisk Dodaj"
					+ "\nWprowadzasz dane. Zapisujesz now¹ grupê."
					+ "\n\nModyfikacja grupy"
					+ "\nPrzycisk Modyfikuj"
					+ "\nWybierasz jedn¹ grupê. Wprowadzasz lub modyfikujesz jej dane. Potwierdzasz wprowadzone zmiany."
					+ "\n\nUsuniêcie grupy"
					+ "\nPrzycisk Usuñ"
					+ "\nWybierasz jedn¹ grupê. Potwierdzasz chêæ jej usuniêcia.";
		case 13:	//Plik z danymi
			return "Plik z danymi to plik bazy danych. Ma nazwê 'terminarz.db'. Znajduje siê w tym samym folderze, co plik uruchomieniowy programu."
					+ "\n\nJeœli go nie ma, jest tworzony nowy, pusty plik danych."
					+ "\n\nJeœli zaœ jest, to z niego s¹ wczytywane dane."
					+ "\n\nUwaga!"
					+ "\nUsuniêcie tego pliku powoduje utratê zapisanych w nich danych!"
					+ "\n\nZaleca siê wykonywanie kopii zapasowej pliku 'terminarz.db' w celu ochrony zapisanych w nich danych.";
		case 14:	//Rozwi¹zywanie problemów
			return "Na wiêkszoœæ typowych problemów wystarczy ponowne uruchomienie funkcji lub ca³ego programu."
					+ "\n\nDo³o¿y³em wszelkich starañ, ¿eby program dzia³a³ prawid³owo. Nie wykluczam jednak obecnoœci b³êdów, które mog¹ wyjœæ na jaw podczas u¿ytkowania programu.";
		default:
			return "";
		}
	}
}
