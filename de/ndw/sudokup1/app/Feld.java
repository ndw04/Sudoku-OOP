package de.ndw.sudokup1.app;

public class Feld {
	private int wert;
	private Feldgruppe zeile;
	private Feldgruppe spalte;
	private Feldgruppe quadrant;
	
	public Feld(Feldgruppe zeile, Feldgruppe spalte, Feldgruppe quadrant) {
		this.zeile = zeile;
		this.spalte = spalte;
		this.quadrant = quadrant;
		this.wert = -1;
	}
	
	public int getWert() {
		return this.wert;
	}
	
	public void setWert(int wert) {
		this.wert = wert;
	}
}
