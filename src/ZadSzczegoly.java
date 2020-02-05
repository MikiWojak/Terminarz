import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;

public class ZadSzczegoly extends JPanel {
	private JButton button;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;

	/**
	 * Create the panel.
	 */
	public ZadSzczegoly() {
		setLayout(null);
		
		button = new JButton("Aktualizuj");
		button.setFont(new Font("Arial", Font.PLAIN, 24));
		button.setBounds(457, 363, 222, 37);
		add(button);
		
		label = new JLabel("New label");
		label.setFont(new Font("Arial", Font.PLAIN, 24));
		label.setBounds(12, 313, 1069, 37);
		add(label);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("Arial", Font.PLAIN, 24));
		label_1.setBounds(12, 263, 1069, 37);
		add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("Arial", Font.PLAIN, 24));
		label_2.setBounds(12, 213, 1069, 37);
		add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setFont(new Font("Arial", Font.PLAIN, 24));
		label_3.setBounds(12, 163, 1069, 37);
		add(label_3);
		
		label_4 = new JLabel("New label");
		label_4.setFont(new Font("Arial", Font.PLAIN, 24));
		label_4.setBounds(12, 113, 1069, 37);
		add(label_4);
		
		label_5 = new JLabel("New label");
		label_5.setFont(new Font("Arial", Font.PLAIN, 24));
		label_5.setBounds(12, 63, 1069, 37);
		add(label_5);
		
		label_6 = new JLabel("New label");
		label_6.setFont(new Font("Arial", Font.PLAIN, 24));
		label_6.setBounds(12, 13, 1069, 37);
		add(label_6);

	}
}
