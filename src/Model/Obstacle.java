package Model;

public class Obstacle extends Carre {

	public Obstacle(Coordinate coordinate, TypeObstacle type) {
		super(coordinate);
		this.type=type;
	}
	public Obstacle(Coordinate coordinate) {
		super(coordinate);
		this.type=TypeObstacle.Simple;
	}
	
	public boolean isDestructible(){
		return (type.equals(TypeObstacle.Simple) || type.equals(TypeObstacle.SimpleDestructeur));
	}
	
	public boolean isLave()
	{
		return (type.equals(TypeObstacle.Lave));
	}
	private  TypeObstacle type;

	public TypeObstacle getType() {
		return type;
	}
	
	

}
