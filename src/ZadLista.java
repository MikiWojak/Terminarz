import javax.swing.JPanel;

import model.Zadanie;

import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class ZadLista extends JPanel {
	private List<Zadanie>zadania;
	
	private JList lista;
	
	
	public ZadLista() {
		setLayout(null);
		
		lista = new JList();
		lista.setBounds(12, 13, 753, 454);
		add(lista);
		
		inicjalizacja_pol();
		
		lista.setModel(lista_zadania());		//BAZA DANYCH!!!
	}
	
	public void inicjalizacja_pol() {
		zadania = new LinkedList<Zadanie>();
	}
	
	//do listy w programie
	public DefaultListModel<Object> lista_zadania() {
			DefaultListModel<Object>lista = new DefaultListModel<Object>();
			String czy_wykonane;
			try {
				for(int i = 0; i < zadania.size(); i++) {
					if(zadania.get(i).pobierz_czy_wykonane()) { czy_wykonane = "*"; }
					else { czy_wykonane = " "; }
					
					lista.addElement(czy_wykonane + "   " + zadania.get(i).pobierz_data_zadanie().toString() + "   " + zadania.get(i).pobierz_tytul_zadanie());
				}
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				return null;
			}
				
			return lista;
		}
}
