package pong.gui;

public class Player {
	private String name;
	private int score;
	private Racket racket;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void incScore() {
		if(++score>=Pong.getSCORE_WIN()){
			Pong.gameOver(name);
		}
		else{
			Pong.goal();
		}
	}
	public Racket getRacket() {
		return racket;
	}
	public void setRacket(Racket racket) {
		this.racket = racket;
	}
	
	public Player(String name, Racket racket){
		this.name = name;
		this.racket = racket;
		score = 0;
	}
}
