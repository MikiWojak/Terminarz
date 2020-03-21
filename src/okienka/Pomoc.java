package okienka;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Dialog.ModalityType;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Pomoc extends JDialog {
	private JPanel panel;
	
	public Pomoc() {
		initComp();
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
		
		JLabel lblTytuArtykuu = new JLabel("Tytu\u0142 artyku\u0142u");
		lblTytuArtykuu.setHorizontalAlignment(SwingConstants.CENTER);
		lblTytuArtykuu.setFont(new Font("Arial", Font.BOLD, 20));
		lblTytuArtykuu.setBounds(324, 13, 446, 30);
		panel.add(lblTytuArtykuu);
		
		JButton btnZamknij = new JButton("Zamknij");
		btnZamknij.setFont(new Font("Arial", Font.BOLD, 20));
		btnZamknij.setBounds(630, 510, 140, 30);
		panel.add(btnZamknij);
		
		JList list = new JList();
		list.setFont(new Font("Arial", Font.PLAIN, 20));
		list.setBounds(12, 13, 300, 527);
		panel.add(list);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(324, 58, 446, 439);
		panel.add(scrollPane);
		
		JTextPane txtpnLoremIpsumDolor = new JTextPane();
		txtpnLoremIpsumDolor.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent ultricies felis ac viverra imperdiet. Donec rhoncus metus a mauris sollicitudin faucibus. Vestibulum metus augue, porta nec faucibus ut, tempus at lorem. Sed molestie purus in porttitor volutpat. Phasellus dolor nulla, porta nec lorem nec, pulvinar dapibus odio. Sed feugiat convallis imperdiet. Ut eget sem libero. Suspendisse eget nunc eros.\r\n\r\nPraesent commodo mi blandit ipsum fermentum, id hendrerit lectus convallis. Fusce congue convallis tincidunt. Suspendisse metus eros, porta quis placerat non, tincidunt id augue. Sed rhoncus auctor ipsum quis consectetur. Aenean a felis magna. Nunc a mauris vehicula, egestas ligula non, varius quam. Sed dictum euismod justo quis fringilla. Suspendisse volutpat auctor tellus vel sodales. Nunc sit amet cursus nunc. Cras dolor quam, rhoncus vitae nisl non, lacinia imperdiet lacus. Vivamus vel rhoncus felis, consequat ultrices elit. Vestibulum eget purus quam. Proin erat ligula, commodo in justo quis, fringilla sollicitudin eros. Proin dictum tortor at vestibulum elementum.");
		scrollPane.setViewportView(txtpnLoremIpsumDolor);
		txtpnLoremIpsumDolor.setFont(new Font("Arial", Font.PLAIN, 20));
	}
	
	//koniec inicjowania okienka
	private void initFinal() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}
}
