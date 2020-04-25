/**
 * 
 */
package TestCases;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import model.Zadanie;
import okienka.ZadEdit;

/**
 * @author Miki
 *
 */
class ZadaniaModData {
	
	
	/**
	 * Test method for {@link okienka.ZadEdit}.
	 */
	@Test
	void testDni_wartosci_debug() {
		ZadEdit zadEdit = new ZadEdit(new Zadanie(
				1, 
				Date.valueOf("2020-02-15"), 
				"Lorem ipsum", 
				"", 
				"pilne", 
				false));
		
		int oczekiwany = 29;
		int wynik = zadEdit.dni_wartosci_debug();
		
		assertEquals(oczekiwany, wynik);
		
		
	}

}
