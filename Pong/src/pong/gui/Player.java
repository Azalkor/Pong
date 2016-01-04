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
	public void setScore(int score) {
		this.score = score;
	}
	public void incScore() {
		if(++score>=Pong.getSCORE_WIN()){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			Pong.goal();
			for(Player p : Pong.getPlayers()){
				p.setScore(0);
			}
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
