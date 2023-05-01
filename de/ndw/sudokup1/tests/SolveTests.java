package de.ndw.sudokup1.tests;

import de.ndw.sudokup1.app.InputParser;
import de.ndw.sudokup1.app.Sudoku;

public class SolveTests {
	public static void main(String[] args) throws Exception {
		Sudoku s = InputParser.parse("_ _ 4 _ 5 _ _ _ _\n"
		+ "9 _ _ 7 3 4 6 _ _\n"
		+ "_ _ 3 _ 2 1 _ 4 9\n"
		+ "_ 3 5 _ 9 _ 4 8 _\n"
		+ "_ 9 _ _ _ _ _ 3 _\n"
		+ "_ 7 6 _ 1 _ 9 2 _\n"
		+ "3 1 _ 9 7 _ 2 _ _\n"
		+ "_ _ 9 1 8 2 _ _ 3\n"
		+ "_ _ _ _ 6 _ 1 _ _\n", true);
		
		s.solveSudoku();
		
		s.ausgeben();
		
		System.out.println(s.isGeloest());
		
		Sudoku s1 = InputParser.parse("_ ".repeat(81), true);
				
		s1.solveSudoku();
				
		s1.ausgeben();
		System.out.println(s.isGeloest());
	}
}
