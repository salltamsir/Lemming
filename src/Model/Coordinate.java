package Model;

public class Coordinate {
	
	private int x;
	private int y;
	private int largeur;
	private int longueur;
	
	public Coordinate(int x, int y, int largeur, int longueur) {
		super();
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this.longueur = longueur;
	}
	

	public Coordinate(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getLargeur() {
		return largeur;
	}
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	public int getLongueur() {
		return longueur;
	}
	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
	

}
