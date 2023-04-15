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
            Feldgruppe quadrant = this.quadranten[(row / 3) * 3 + (col / 3)];
			zeile.setFeld(col, feld);
			spalte.setFeld(row, feld);  
            quadrant.setFeld(row % 3 * 3 + col % 3, feld);
            feld.init(zeile, spalte, quadrant);
		}
	}
	
	public void ausgeben() {
		for(Feldgruppe gruppe : this.zeilen) {
			for(int i = 0; i < 9; ++i) {
				int wert = gruppe.getFeld(i).getWert();
				System.out.print(wert == -1 ? ". " : wert + " ");
			}
			System.out.println("");
		}
	}
	
	public boolean setWert(int zeile, int spalte, int wert) {
		if(zeile > this.zeilen.length || spalte > this.spalten.length) return false;
		if(this.zeilen[zeile].istVorhanden(wert)) return false;
		if(this.spalten[spalte].istVorhanden(wert)) return false;
		if(this.quadranten[(zeile / 3) * 3 + (spalte / 3)].istVorhanden(wert)) return false;
		this.zeilen[zeile].getFeld(spalte).setWert(wert);
		return true;
	}
	
	private boolean istKoordinate(int zeile, int spalte) {
		if(zeile > this.zeilen.length || zeile < 0) return false;
		if(spalte > this.spalten.length || spalte < 0) return false;
		return true;
	}
}
