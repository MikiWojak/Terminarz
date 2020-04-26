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
	 * Test modyfikacji zadania: stycze� - 31
	 */
	@Test
	void styczen() {
		ZadEdit zadEdit = new ZadEdit(new Zadanie(
				1, 
				Date.valueOf("2020-01-15"), 
				"Lorem ipsum", 
				"", 
				"pilne", 
				false), false);
		
		int oczekiwany = 31;
		int wynik = zadEdit.dni_wartosci_debug();
		
		assertEquals(oczekiwany, wynik);
	}
	
	/**
	 * Test modyfikacji zadania: luty parzysty 29
	 */
	@Test
	void luty_p() {
		ZadEdit zadEdit = new ZadEdit(new Zadanie(
				1, 
				Date.valueOf("2020-02-15"), 
				"Lorem ipsum", 
				"", 
				"pilne", 
				false), false);
		
		int oczekiwany = 29;
		int wynik = zadEdit.dni_wartosci_debug();
		
		assertEquals(oczekiwany, wynik);
	}
	
	/**
	 * Test modyfikacji zadania: luty nieparzysty 28
	 */
	@Test
	void luty_np() {
		ZadEdit zadEdit = new ZadEdit(new Zadanie(
				1, 
				Date.valueOf("2018-02-15"), 
				"Lorem ipsum", 
				"", 
				"pilne", 
				false), false);
		
		int oczekiwany = 28;
		int wynik = zadEdit.dni_wartosci_debug();
		
		assertEquals(oczekiwany, wynik);
	}
	
	/**
	 * Test modyfikacji zadania: marzec - 31
	 */
	@Test
	void marzec() {
		ZadEdit zadEdit = new ZadEdit(new Zadanie(
				1, 
				Date.valueOf("2020-03-15"), 
				"Lorem ipsum", 
				"", 
				"pilne", 
				false), false);
		
		int oczekiwany = 31;
		int wynik = zadEdit.dni_wartosci_debug();
		
		assertEquals(oczekiwany, wynik);
	}
	
	/**
	 * Test modyfikacji zadania: kwiecie� - 30
	 */
	@Test
	void kwiecien() {
		ZadEdit zadEdit = new ZadEdit(new Zadanie(
				1, 
				Date.valueOf("2020-04-15"), 
				"Lorem ipsum", 
				"", 
				"pilne", 
				false), false);
		
		int oczekiwany = 30;
		int wynik = zadEdit.dni_wartosci_debug();
		
		assertEquals(oczekiwany, wynik);
	}
	
	/**
	 * Test modyfikacji zadania: maj - 31
	 */
	@Test
	void maj() {
		ZadEdit zadEdit = new ZadEdit(new Zadanie(
				1, 
				Date.valueOf("2020-05-15"), 
				"Lorem ipsum", 
				"", 
				"pilne", 
				false), false);
		
		int oczekiwany = 31;
		int wynik = zadEdit.dni_wartosci_debug();
		
		assertEquals(oczekiwany, wynik);
	}
	
	/**
	 * Test modyfikacji zadania: czerwiec - 30
	 */
	@Test
	void czerwiec() {
		ZadEdit zadEdit = new ZadEdit(new Zadanie(
				1, 
				Date.valueOf("2020-06-15"), 
				"Lorem ipsum", 
				"", 
				"pilne", 
				false), false);
		
		int oczekiwany = 30;
		int wynik = zadEdit.dni_wartosci_debug();
		
		assertEquals(oczekiwany, wynik);
	}
	
	/**
	 * Test modyfikacji zadania: lipiec - 31
	 */
	@Test
	void lipiec() {
		ZadEdit zadEdit = new ZadEdit(new Zadanie(
				1, 
				Date.valueOf("2020-07-15"), 
				"Lorem ipsum", 
				"", 
				"pilne", 
				false), false);
		
		int oczekiwany = 31;
		int wynik = zadEdit.dni_wartosci_debug();
		
		assertEquals(oczekiwany, wynik);
	}
	
	/**
	 * Test modyfikacji zadania: sierpie� - 31
	 */
	@Test
	void sierpien() {
		ZadEdit zadEdit = new ZadEdit(new Zadanie(
				1, 
				Date.valueOf("2020-08-15"), 
				"Lorem ipsum", 
				"", 
				"pilne", 
				false), false);
		
		int oczekiwany = 31;
		int wynik = zadEdit.dni_wartosci_debug();
		
		assertEquals(oczekiwany, wynik);
	}
	
	/**
	 * Test modyfikacji zadania: wrzesie� - 30
	 */
	@Test
	void wrzesien() {
		ZadEdit zadEdit = new ZadEdit(new Zadanie(
				1, 
				Date.valueOf("2020-09-15"), 
				"Lorem ipsum", 
				"", 
				"pilne", 
				false), false);
		
		int oczekiwany = 30;
		int wynik = zadEdit.dni_wartosci_debug();
		
		assertEquals(oczekiwany, wynik);
	}
	
	/**
	 * Test modyfikacji zadania: pa�dziernik - 31
	 */
	@Test
	void pazdziernik() {
		ZadEdit zadEdit = new ZadEdit(new Zadanie(
				1, 
				Date.valueOf("2020-10-15"), 
				"Lorem ipsum", 
				"", 
				"pilne", 
				false), false);
		
		int oczekiwany = 31;
		int wynik = zadEdit.dni_wartosci_debug();
		
		assertEquals(oczekiwany, wynik);
	}
	
	/**
	 * Test modyfikacji zadania: listopad - 30
	 */
	@Test
	void listopad() {
		ZadEdit zadEdit = new ZadEdit(new Zadanie(
				1, 
				Date.valueOf("2020-11-15"), 
				"Lorem ipsum", 
				"", 
				"pilne", 
				false), false);
		
		int oczekiwany = 30;
		int wynik = zadEdit.dni_wartosci_debug();
		
		assertEquals(oczekiwany, wynik);
	}
	
	/**
	 * Test modyfikacji zadania: grudzie� - 31
	 */
	@Test
	void grudzie�() {
		ZadEdit zadEdit = new ZadEdit(new Zadanie(
				1, 
				Date.valueOf("2020-12-15"), 
				"Lorem ipsum", 
				"", 
				"pilne", 
				false), false);
		
		int oczekiwany = 31;
		int wynik = zadEdit.dni_wartosci_debug();
		
		assertEquals(oczekiwany, wynik);
	}

}
