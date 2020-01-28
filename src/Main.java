import java.awt.EventQueue;
import java.sql.Date;

import model.Zadanie;
import model.Grupa;
import model.Przypis;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Zadanie zadanie = new Zadanie(1, Date.valueOf("2020-01-29"), "Stanowiska egzaminacyjne", "", "nie takie wa¿ne", false);
	}
}
