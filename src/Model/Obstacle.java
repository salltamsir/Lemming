package Model;

public class Obstacle extends Carre {

	public Obstacle(Coordinate coordinate, TypeObstacle type) {
		super(coordinate);
		this.type=type;
		// TODO Auto-generated constructor stub
	}
	private TypeObstacle type;
	
	public TypeObstacle getType(){
		return type;
	}
	
	
	

}
