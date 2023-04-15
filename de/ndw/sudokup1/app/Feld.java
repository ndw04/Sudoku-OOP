package de.ndw.sudokup1.app;

public class Feld {
	private int wert;
	private Feldgruppe zeile;
	private Feldgruppe spalte;
	private Feldgruppe quadrant;
	
	public void init(Feldgruppe neueZeile, Feldgruppe neueSpalte, Feldgruppe neuerQuadrant) {
		this.zeile = neueZeile;
		this.spalte = neueSpalte;
		this.quadrant = neuerQuadrant;
		this.wert = -1; // default
	}
	
	public int getWert() {
		return this.wert;
	}
	
	public void setWert(int wert) {
		this.wert = wert;
	}
}
