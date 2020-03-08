package okienka;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GruPrzypis extends JDialog {
	public GruPrzypis() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 432, 253);
		getContentPane().add(panel);
	}
}
