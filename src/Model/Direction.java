package Model;


public enum Direction {
	Static(0,0),
	Up(0,-20),
	Down(0,20),
	Left(-20,0),
	Right(20,0);
	
	private int x,y;
	
	Direction(int x, int y){
		this.x=x;
		this.y=y;
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
	
	public Direction oppose(){
		if(this.equals(Direction.Right))
			return Direction.Left;
		if(this.equals(Direction.Left))
			return Direction.Right;
		return this;
	}

	
	

}

