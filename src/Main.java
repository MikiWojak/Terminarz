import java.awt.EventQueue;
import java.sql.Date;
import java.util.List;

import model.Zadanie;
import terminarz.Terminarz;
import model.Grupa;
import model.Przypis;

public class Main {
	// TODO: handle exception
	public static void main(String[] args) {
		//uruchomienie programu okienkowego
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program window = new Program();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
