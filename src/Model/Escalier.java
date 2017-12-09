package Model;

public class Escalier extends Carre {

	public Escalier(Coordinate coordinate) {
		super(coordinate);
	}
	
	public boolean isClimbable(Coordinate coordinate){
		if(this.getCoordinate().getX()== coordinate.getX() && this.getCoordinate().getY()==coordinate.getY()+coordinate.getLongueur())
			return false;
		
		return true;
	}

}
