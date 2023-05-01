package de.ndw.sudokup1.app;

public class Sudoku {
	private Feldgruppe[] quadranten;
	private Feldgruppe[] zeilen;
	private Feldgruppe[] spalten;
	
	public Sudoku() {
		this.quadranten = new Feldgruppe[9];
		this.zeilen = new Feldgruppe[9];
		this.spalten = new Feldgruppe[9];
		
		for(int i = 0; i < 9; ++i) {
			this.zeilen[i] = new Feldgruppe();
			this.zeilen[i].setNr(i);
			this.spalten[i] = new Feldgruppe();
			this.spalten[i].setNr(i);
			this.quadranten[i] = new Feldgruppe();
			this.quadranten[i].setNr(i);
		}
		
		for(int i = 0; i < 81; ++i) {
			int row = i / 9;
            int col = i % 9;
            Feldgruppe zeile = this.zeilen[row];
            Feldgruppe spalte = this.spalten[col];
            Feldgruppe quadrant = this.quadranten[this.getQuadrant(row, col)];
			Feld feld = new Feld(zeile, spalte, quadrant);
			zeile.setFeld(col, feld);
			spalte.setFeld(row, feld);  
            quadrant.setFeld(this.getQuadrantIndex(row, col), feld);
		}
	}
	
	public void solveSudoku() {
		this._solveSudoku(0, 0);
	}
	
	private boolean _solveSudoku(int row, int col) {
		if(row == 9) return true;
		
		int nextRow = col == 8 ? row + 1 : row;
        int nextCol = col == 8 ? 0 : col + 1;
        
        if(this.getWert(row, col) != -1) {
        	return _solveSudoku(nextRow, nextCol);
        }
        
        for(int num = 1; num <= 9; ++num) {
        	if(setWert(row, col, num)) {
        		if(_solveSudoku(nextRow, nextCol)) return true;
        		
        		this.setWert(row, col, -1);
        	}
        }
        
        return false;
	}
	
	public boolean isGeloest() {
		for(int i = 0; i < 9; ++i) {
			for(int j = 0; j < 9; ++j) {
				int w = this.getWert(i, j);
				if(w == -1) return false;
				this.zeilen[i].getFeld(j).setWert(-1);
				if(this.zeilen[i].istVorhanden(w)) return false;
				if(this.spalten[j].istVorhanden(w)) return false;
				if(this.quadranten[this.getQuadrant(i, j)].istVorhanden(w)) return false;
				this.zeilen[i].getFeld(j).setWert(w);			
			}
		}
		return true;
	}
	
	public void validateLayout() {
		for(int i = 0; i < 81; ++i) {
			int row = i / 9;
            int col = i % 9;
            Feld f = this.spalten[col].getFeld(row);
            assert this.spalten[col].getFeld(row) == f;
            assert this.zeilen[row].getFeld(col) == f;
            assert this.quadranten[this.getQuadrant(row, col)].getFeld(this.getQuadrantIndex(row, col)) == f;
            int qid = this.getQuadrant(row, col);
            int qidx = this.getQuadrantIndex(row, col);
            System.out.printf("q(%d,%d)/%d = %d\n", row, col, qid, this.quadranten[qid].getFeld(qidx).getWert());
		}
	}
	
	private void ausgebenSeperator() {
		for(int i = 0; i < 3; i++) {
			System.out.print("+ ");
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
	
	public int getWert(int zeile, int spalte) {
		return this.zeilen[zeile].getFeld(spalte).getWert();
	}
	
	public boolean setWert(int zeile, int spalte, int wert) {
		if(!this.istKoordinateGueltig(zeile, spalte)) return false;
		if(wert == -1) {
			this.zeilen[zeile].getFeld(spalte).setWert(wert);
			return true;
		}
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
	
	private boolean istKoordinateGueltig(int zeile, int spalte) {
		if(zeile > this.zeilen.length || zeile < 0) return false;
		if(spalte > this.spalten.length || spalte < 0) return false;
		return true;
	}
}
