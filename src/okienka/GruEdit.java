package okienka;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GruEdit extends JDialog {
	private boolean czy_modyfikacja;
	
	private JPanel panel;
	//dodanie grupy
	public GruEdit() {
		initComp();
		setTitle("Dodaj grupê");
		czy_modyfikacja = false;
		initFinal();
	}
	
	//elementy w okienku
	private void initComp() {
		setBounds(100, 100, 650, 300);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 632, 253);
		getContentPane().add(panel);
		panel.setLayout(null);
	}
	
	//koniec inicjowania okienka
	private void initFinal() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}
}
