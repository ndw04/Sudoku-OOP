package de.ndw.sudokup1.app;

public class Sudoku {
	private Feldgruppe[] quadranten;
	private Feldgruppe[] zeilen;
	private Feldgruppe[] spalten;
	
	public void init() {
		this.quadranten = new Feldgruppe[9];
		this.zeilen = new Feldgruppe[9];
		this.spalten = new Feldgruppe[9];
		
		for(int i = 0; i < 9; ++i) {
			this.quadranten[i] = new Feldgruppe();
			this.quadranten[i].init();
			this.zeilen[i] = new Feldgruppe();
			this.zeilen[i].init();
			this.spalten[i] = new Feldgruppe();
			this.spalten[i].init();
		}
		
		for(int i = 0; i < 81; ++i) {
			Feld feld = new Feld();
			int row = i / 9;
            int col = i % 9;
            Feldgruppe zeile = this.zeilen[row];
            Feldgruppe spalte = this.spalten[col];
            Feldgruppe quadrant = this.quadranten[this.getQuadrant(row, col)];
			zeile.setFeld(col, feld);
			spalte.setFeld(row, feld);  
            quadrant.setFeld(this.getQuadrantIndex(row, col), feld);
            feld.init(zeile, spalte, quadrant);
		}
	}
	
	public void validateLayout() {
		for(int i = 0; i < 81; ++i) {
			int row = i / 9;
            int col = i % 9;
            Feld f = this.spalten[col].getFeld(row);
            assert this.spalten[col].getFeld(row) == f;
            assert this.zeilen[row].getFeld(col) == f;
            assert this.quadranten[this.getQuadrant(row, col)].getFeld(this.getQuadrantIndex(row, col)) == f;
		}
	}
	
	private void ausgebenSeperator() {
		for(int i = 0; i < 3; i++) {
			System.out.print("+ ");
			// schreibe einen block an abstandszeichen
			for(int j = 0; j < 3; j++) System.out.print("- ");     
		}
		System.out.println("+");
	}
	
	public void ausgeben() {
		for(int j = 0; j < 9; ++j) {
			Feldgruppe gruppe = this.zeilen[j];
			if(j % 3 == 0) ausgebenSeperator();
			for(int i = 0; i < 9; ++i) {
				if(i == 0) System.out.print("| ");
				else if(i % 3 == 0) System.out.print("| ");
				int wert = gruppe.getFeld(i).getWert();
				System.out.print(wert == -1 ? ". " : wert + " ");
			}
			System.out.println("|");
		}
		ausgebenSeperator();
	}
	
	public boolean setWert(int zeile, int spalte, int wert) {
		if(zeile > this.zeilen.length || spalte > this.spalten.length) return false;
		if(this.zeilen[zeile].istVorhanden(wert)) return false;
		if(this.spalten[spalte].istVorhanden(wert)) return false;
		if(this.quadranten[this.getQuadrant(zeile, spalte)].istVorhanden(wert)) return false;
		this.zeilen[zeile].getFeld(spalte).setWert(wert);
		return true;
	}
	
	private int getQuadrantIndex(int zeile, int spalte) {
		return zeile % 3 * 3 + spalte % 3;
	}
	
	private int getQuadrant(int zeile, int spalte) {
		return (zeile / 3) * 3 + (spalte / 3);
	}
	
	private boolean istKoordinate(int zeile, int spalte) {
		if(zeile > this.zeilen.length || zeile < 0) return false;
		if(spalte > this.spalten.length || spalte < 0) return false;
		return true;
	}
}
