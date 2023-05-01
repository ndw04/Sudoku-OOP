package de.ndw.sudokup1;

import java.util.Scanner;

import de.ndw.sudokup1.app.InputParser;
import de.ndw.sudokup1.app.Sudoku;

public class SudokuApp {
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("Bitte Sudoku eingeben:");
			Sudoku s = InputParser.fromScanner(scan, true);
			s.solveSudoku();
			if(!s.isGeloest()) {
				System.out.println("Sudoku konnte nicht gelöst werden");
			} else {
				s.ausgeben();
			}
		}
	}
}
