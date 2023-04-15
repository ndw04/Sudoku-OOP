package de.ndw.sudokup1;

import de.ndw.sudokup1.app.Sudoku;

public class SudokuApp {
	public static void main(String[] args) {
		// neues sudoku
		Sudoku s = new Sudoku();
		s.init();
		
		// beispiel sudoku
		s.setWert(0, 1, 3);
		s.setWert(1, 3, 1);
		s.setWert(1, 4, 9);
		s.setWert(1, 5, 5);
		s.setWert(2, 2, 8);
		s.setWert(2, 7, 6);
		s.setWert(3, 0, 8);
		s.setWert(3, 4, 6);
		s.setWert(4, 0, 4);
		s.setWert(4, 3, 8);
		s.setWert(4, 8, 1);
		s.setWert(5, 4, 2);
		s.setWert(6, 1, 6);
		s.setWert(6, 6, 2);
		s.setWert(6, 7, 8);
		s.setWert(7, 3, 4);
		s.setWert(7, 4, 1);
		s.setWert(7, 5, 9);
		s.setWert(7, 8, 5);
		s.setWert(8, 7, 7);
		
		// zusätzlicher Wert
		s.setWert(1, 1, 2);
		
		// zusätzlicher Wert zeile vorhanden
		s.setWert(0, 0, 3);
		
		// zusätzlicher Wert spalte vorhanden
		s.setWert(1, 0, 8);
		
		// zusätzlicher Wert quadrant vorhanden
		s.setWert(0, 8, 6);
		
		// im terminal ausgeben
		s.ausgeben();
	}
}
