package Model;



public class Event {



	private Coordinate coordinate;
	private Direction direction;

	public Event(Coordinate coordinate, Direction direction) {
		this.coordinate = coordinate;
		this.direction=direction;
	}



	public Coordinate getCoordinate() {
		return coordinate;
	}
	public Direction getDirection(){
		return direction;
	}

	@Override
	public String toString() {
		
		return "direction " +this.direction+" ( x " + coordinate.getX() + ", y" + coordinate.getY() + " )";
	}

}
