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
		lista_tematow.add("Wstêp");
		lista_tematow.add("Akapit 1");
		lista_tematow.add("Akapit 2");
		lista_tematow.add("Akapit 3");
		lista_tematow.add("Akapit 4");
		lista_tematow.add("Akapit 5");
		
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
			return "Lorem ipsum dolor sit amet, consectetur adipiscing elit.Praesent ultricies felis ac viverra imperdiet. Donec rhoncus metus a mauris sollicitudin faucibus. Vestibulum metus augue, porta nec faucibus ut, tempus at lorem. Sed molestie purus in porttitor volutpat. Phasellus dolor nulla, porta nec lorem nec, pulvinar dapibus odio. Sed feugiat convallis imperdiet. Ut eget sem libero. Suspendisse eget nunc eros."
					+ "\n\nUt elementum sapien vitae purus faucibus venenatis. In elementum arcu et sapien fermentum pellentesque. Maecenas nec venenatis ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean sit amet arcu dapibus, blandit nulla at, iaculis mi. Donec eu mollis elit. Nulla facilisi.";
		case 1:		//Akapit 1
			return "Praesent commodo mi blandit ipsum fermentum, id hendrerit lectus convallis. Fusce congue convallis tincidunt. Suspendisse metus eros, porta quis placerat non, tincidunt id augue. Sed rhoncus auctor ipsum quis consectetur. Aenean a felis magna. Nunc a mauris vehicula, egestas ligula non, varius quam. Sed dictum euismod justo quis fringilla. Suspendisse volutpat auctor tellus vel sodales. Nunc sit amet cursus nunc. Cras dolor quam, rhoncus vitae nisl non, lacinia imperdiet lacus. Vivamus vel rhoncus felis, consequat ultrices elit. Vestibulum eget purus quam. Proin erat ligula, commodo in justo quis, fringilla sollicitudin eros. Proin dictum tortor at vestibulum elementum."
					+ "\n\nQuisque urna lorem, scelerisque non felis et, venenatis eleifend ex. Donec et eros ac dolor gravida dictum. Aenean eros nibh, bibendum eget velit ut, condimentum rutrum augue. Vestibulum iaculis nisl eu sodales pretium. Nam tellus elit, rhoncus nec mauris non, tempor pretium ex. In eget ultricies erat. Duis purus ipsum, laoreet tempus turpis in, rutrum varius nibh. Nulla ut ultricies orci. Quisque ut porta justo. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris sodales dignissim erat ut mollis. Morbi neque magna, dignissim vitae mollis sed, sodales ac odio. Morbi ac euismod urna. Nam imperdiet odio eu orci ornare tempor. Nulla rutrum pellentesque risus, malesuada convallis leo efficitur quis. Duis maximus dignissim metus.";
		case 2:		//Akapit 2
			return "In risus erat, vulputate eu ultricies vitae, ultricies vitae dolor. Maecenas ultricies ex vel enim facilisis, nec rhoncus neque ultrices. Fusce mattis nibh dapibus libero placerat, nec rutrum nibh auctor. Maecenas pellentesque diam nec laoreet tempor. Pellentesque scelerisque ante non tempus efficitur. Sed ut mi vel arcu faucibus molestie sit amet eu nunc. Vestibulum lobortis, metus nec consequat ornare, mauris ex auctor dui, in mattis eros ex sit amet ante. Donec dictum volutpat ultricies. Aliquam posuere lorem dui, vitae venenatis magna pellentesque ut. Fusce fringilla lobortis dapibus."
					+ "\n\nQuisque commodo mollis ligula quis vulputate. Aenean bibendum, ante eget ultrices bibendum, diam urna venenatis nisi, nec ultrices purus nibh ac massa. Ut et risus bibendum, ornare dolor quis, imperdiet arcu. Quisque semper molestie lectus lacinia ultrices. Sed eu dolor justo. Praesent scelerisque, mauris eget efficitur facilisis, elit lacus tincidunt purus, a finibus ante lorem maximus augue. Maecenas sagittis, metus a semper laoreet, ligula sem varius nisl, sed dignissim lectus diam id felis. Cras vitae lorem ligula. Duis ornare risus nec neque semper viverra. Aliquam quis rhoncus quam. Fusce eget elit eget velit pharetra hendrerit nec et eros. Sed sed nulla hendrerit, consectetur ipsum non, sodales risus. Suspendisse id aliquet nibh. Vestibulum nec sapien erat.";
		case 3:		//Akapit 3
			return "Curabitur risus ipsum, interdum ut lectus sed, aliquam mattis nulla. Donec quis dui non velit dictum gravida a eget lacus. Maecenas fringilla vitae purus sit amet placerat. In gravida convallis orci nec auctor. Donec orci nulla, varius sed suscipit a, posuere et velit. Nunc vel metus sodales, tincidunt sem eget, gravida justo. Maecenas condimentum nunc ultricies ligula facilisis, nec pulvinar nunc viverra. Mauris rutrum hendrerit laoreet. Proin vitae iaculis ligula. Pellentesque finibus, neque eu dictum fermentum, arcu justo efficitur erat, vel mattis magna nisi hendrerit tortor. Aliquam in rutrum lacus. Praesent condimentum vitae enim eu pharetra."
					+ "\n\nInteger mattis turpis mi. In leo lorem, finibus in ligula a, laoreet feugiat lectus. Aliquam fermentum mollis iaculis. Phasellus non cursus dui. In ut ornare mi. Mauris condimentum magna eget leo dignissim faucibus. Nunc varius mollis massa, in convallis nisi consequat id. Sed nec volutpat libero.";
		case 4:		//Akapit 4
			return "Proin interdum vitae sapien id consequat. Duis in maximus lectus. Sed vitae enim rutrum, molestie nisi eget, finibus nisi. Nam a est non ante aliquam egestas nec a justo. Nulla eget arcu et quam elementum pharetra. Ut vitae enim justo. In erat justo, dictum sed augue nec, dignissim egestas ex. Sed et orci et nisi blandit consectetur. Suspendisse commodo interdum nibh."
					+ "Mauris ac molestie nibh. Suspendisse potenti. Donec eu commodo magna, quis eleifend nunc. Integer pharetra ligula massa, id mattis erat malesuada sit amet. Fusce feugiat neque non consequat aliquet. Vivamus porttitor luctus nisl, at mattis nisl scelerisque eu. Suspendisse ligula sapien, interdum nec lacus in, dignissim vehicula elit. Ut commodo efficitur purus in feugiat. Suspendisse in vehicula purus, eu sollicitudin sapien. Praesent pellentesque mollis risus vel congue. Aliquam pharetra rhoncus quam a facilisis. Nam eget euismod erat, ac pellentesque diam. Nam massa lectus, suscipit sit amet vestibulum eu, ullamcorper id nibh. Curabitur lobortis efficitur risus vel sollicitudin. Praesent elementum magna non turpis molestie imperdiet.";
		case 5:		//Akapit 5
			return "Curabitur urna nisl, mattis sit amet diam sit amet, egestas dignissim neque. Donec et dolor ac velit posuere sollicitudin vulputate quis odio. Aliquam in sapien consequat, sodales massa eu, sodales nulla. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Phasellus ullamcorper massa quam, non volutpat sem venenatis ut. Praesent porttitor sagittis convallis. Nullam ultricies ipsum eros, at placerat dolor convallis at. Vestibulum laoreet viverra arcu, at viverra ipsum vehicula nec. Curabitur pharetra imperdiet tortor, id ultrices quam eleifend eu. Fusce interdum porttitor eleifend. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris gravida ac velit ac venenatis. Aliquam consequat at massa quis euismod. Nam suscipit a velit sit amet rhoncus. Nunc euismod porta varius."
					+ "Duis ipsum metus, rutrum quis hendrerit a, ultrices nec mi. Nulla facilisi. Maecenas ut eleifend nibh. Morbi ut dignissim nulla. Curabitur vel iaculis elit. Nunc ut lectus lobortis, ornare augue sed, consequat felis. Sed id mi risus. Duis elementum, tortor et laoreet varius, nibh est faucibus diam, eu rutrum velit nibh a elit. Pellentesque in cursus urna. Donec eu dolor tempor, faucibus elit nec, volutpat elit. Suspendisse potenti.";
		default:
			return "";
		}
	}
}
