package de.ndw.sudokup1.app;

import java.util.Scanner;

public class InputParser {
	private int at = 0;
	private String buffer;
	
	public static Sudoku fromScanner(Scanner scanner, boolean ignoreInvalid) throws Exception {
		String in = "";
		for(int i = 0; i < 9; ++i) {
			in += scanner.nextLine() + "\n";
		}
		return InputParser.parse(in, ignoreInvalid);
	}
	
	public static Sudoku parse(String buffer, boolean ignoreInvalid) throws Exception {
		Sudoku sudoku = new Sudoku();
		
		InputParser parser = new InputParser();
		parser.buffer = buffer;
		
		int skipped = 0;
		
		for(int i = 0; i < 9; ++i) {
			for(int j = 0; j < 9; ++j) {
				if(ignoreInvalid) {
					try {
						if(!sudoku.setWert(i, j, parser.next())) ++skipped;
					} catch(Exception e) {
						++skipped;
						//System.err.println(e.getMessage());
					}
				} else {
					sudoku.setWert(i, j, parser.next());
				}
			}
		}
		
		System.out.println("Skipped entries: " + skipped);
		
		return sudoku;
	}
	
	public int next() throws Exception {
		this.at += 2;
		if(this.buffer.length() - (this.at - 2) < 2) throw new Exception("Unable to parse String, input did not match expected length");
		char c1 = this.buffer.charAt(this.at - 2), c2 = this.buffer.charAt(this.at - 1);		
		int out = Character.compare(c1, '_') == 0 ? -1 : Integer.parseInt(String.valueOf(c1));
		if(out < -1 || out == 0 || out > 9) throw new Exception("Unable to parse char, expected '_' or number 1 - 9, found '" + c1 + "'");
		if(Character.compare(c2, ' ') != 0 && Character.compare(c2, '\n') != 0) throw new Exception("Unable to parse char, found char '" + c2 + "', expected ' ' or '\\n'");
		return out;
	}
}
