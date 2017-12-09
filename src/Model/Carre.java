package Model;

public abstract class Carre  {
	
	private Coordinate coordinate;
	private boolean isDeleted;
	
	Carre(Coordinate coordinate){
		this.coordinate=coordinate;
	}
	
	
	
	public Coordinate getCoordinate(){
		return coordinate;
	}



	public boolean isDeleted() {
		return isDeleted;
	}



	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	


}
