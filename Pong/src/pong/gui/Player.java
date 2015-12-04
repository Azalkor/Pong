package pong.gui;

public class Player {
	private String nom;
	private Racket racket;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Racket getRacket() {
		return racket;
	}
	public void setRacket(Racket racket) {
		this.racket = racket;
	}
	public Player(String nom, Racket racket){
		this.nom = nom;
		this.racket = racket;
	}
}
