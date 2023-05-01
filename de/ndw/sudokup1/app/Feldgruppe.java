package de.ndw.sudokup1.app;

public class Feldgruppe {
	private int nr;
	private Feld[] felder;
	
	public Feldgruppe() {
		this.felder = new Feld[9];
	}
	
	public int getNr() {
		return this.nr;
	}
	
	public void setNr(int neueNr) {
		this.nr = neueNr;
	}
	
	public Feld getFeld(int index) {
		return this.felder[index];
	}
	
	public void setFeld(int index, Feld feld) {
		this.felder[index] = feld;
	}
	
	public boolean istVorhanden(int wert) {
		for(Feld feld : this.felder) {
			if(feld.getWert() == wert) {
				return true;
			}
		}
		return false;
	}
}
