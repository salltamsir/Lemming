package Model;


import java.util.ArrayList;
import java.util.List;

import State.State;




public class Lemming extends Carre {
	
	private Game game;
	private State state ;
	private ArrayList<Observer> observers;
	private Direction direction;
	private int changeTime;
	private int FallTime;
	private Direction previousDirection;
	
	
	
	public Lemming(Coordinate  coordinate, Game game){
		super(coordinate);
		this.game= game;
		observers=new ArrayList();

		state=State.NormalState;
		direction= Direction.Right;
		previousDirection=Direction.Right;
	}
	
	
	
	
	public void register(Observer o){
		observers.add(o);
	}
	public void unregister(Observer o){
		observers.remove(o);
	}
	
	public void notifyObservers(ArrayList<Event> events) {
		for (Observer observer : observers) {
			observer.notify(events, this);
		}
	}
	
	public boolean hitSomething(Coordinate c){
		
		if(this.getCoordinate().getX()+this.getCoordinate().getLargeur()== c.getX() && this.getCoordinate().getY()==c.getY()){
			if(this.getDirection().equals(Direction.Right))
			return true;
		}
		if(this.getCoordinate().getX()-this.getCoordinate().getLargeur()== c.getX() && this.getCoordinate().getY()==c.getY()){
			if(this.getDirection().equals(Direction.Left))
			return true;
		}
	
		return false;
		
	}
	
	public void move(int x, int y){
		this.getCoordinate().setX(this.getCoordinate().getX()+x);
		this.getCoordinate().setY(this.getCoordinate().getY()+y);
	}
	public void jump(){
		if(this.getDirection().equals(Direction.Right)){
	
			this.getCoordinate().setX(this.getCoordinate().getX()+this.getCoordinate().getLargeur());
			this.getCoordinate().setY(this.getCoordinate().getY()-this.getCoordinate().getLongueur());

		}
		else
			if(this.getDirection().equals(Direction.Left)){
				this.getCoordinate().setX(this.getCoordinate().getX()-this.getCoordinate().getLargeur());
				this.getCoordinate().setY(this.getCoordinate().getY()-this.getCoordinate().getLongueur());
				
			}
	}
	
	
	
	
	
	
	
	
	
	public Direction getDirection(){
		return direction;
	}
	
	public void setDirection(Direction direction){
		if(direction.equals(Direction.Right) || direction.equals(Direction.Left))
			previousDirection=direction;
		this.direction=direction;
	}
	
	public State getState(){
		return state;
	}
	public void setState(State state){
		this.state=state;
		state.onEnter(this);
	}
	
	public Game getGame(){
		return game;
	}




	public int getChangeTime() {
		return changeTime;
	}


	public void setChangeTime(int changeTime) {
		this.changeTime = changeTime;
	}




	public int getFallTime() {
		return FallTime;
	}




	public void setFallTime(int fallTime) {
		FallTime = fallTime;
	}
	
	public void setDirectionAndState(Direction direction, State state){
		this.direction=direction;
		this.state=state;
		state.onEnter(this);
	}




	public Direction getPreviousDirection() {
		return previousDirection;
	}




	public void setPreviousDirection(Direction previousDirection) {
		this.previousDirection = previousDirection;
	}
	
	
	

}
