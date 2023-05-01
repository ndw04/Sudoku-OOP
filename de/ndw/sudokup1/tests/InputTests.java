package de.ndw.sudokup1.tests;

import de.ndw.sudokup1.app.InputParser;
import de.ndw.sudokup1.app.Sudoku;

public class InputTests {

	public static void main(String[] args) {
		try {
			Sudoku s = InputParser.parse("1 ".repeat(81), false);
			System.out.println("Test 1 successful");
			s.ausgeben();
		} catch(Exception e) {
			System.out.println("Test 1 failed: " + e.getMessage());
		}
		
		try {
			Sudoku s = InputParser.parse("", false);
			System.out.println("Test 2 successful");
			s.ausgeben();
		} catch(Exception e) {
			System.out.println("Test 2 failed: " + e.getMessage());
		}
		
		try {
			Sudoku s = InputParser.parse("a b ckw wdlwd 121", false);
			System.out.println("Test 3 successful");
			s.ausgeben();
		} catch(Exception e) {
			System.out.println("Test 3 failed: " + e.getMessage());
		}
		
		try {
			Sudoku s = InputParser.parse("1 2 3 4 5 6 7 8 4 4 4 4 4", true);
			System.out.println("Test 4 successful");
			s.ausgeben();
		} catch(Exception e) {
			System.out.println("Test 4 failed: " + e.getMessage());
		}
	}
}
