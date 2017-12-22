package Model;


import java.util.ArrayList;
import java.util.List;

import State.State;




public class Lemming extends Carre {
	
	private Game game;
	private State state ;
	private State previousState;
	private ArrayList<Observer> observers;
	private Direction direction;
	private int changeTime;
	private int FallTime;
	private Direction previousDirection;
	
	
	
	public Lemming(Coordinate  coordinate, Game game){
		super(coordinate);
		this.game= game;
		observers=new ArrayList();
		state=State.ParachutisteState;
		previousState=state;
		direction= Direction.Down;
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
	
	
			this.getCoordinate().setX(this.getCoordinate().getX()+this.getDirection().getX());
			this.getCoordinate().setY(this.getCoordinate().getY()-this.getCoordinate().getLongueur());

	}
	
	public boolean isOut(){
		return (this.game.isOut(this.coordinate) && !direction.equals(Direction.Down));
	}
	
	public boolean isChangeable(State state){
		return this.game.isChangeable(this, state);
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
		if(isChangeable(state)){
			previousState=this.state;
			this.state=state;
			state.onEnter(this);
		}
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
		setDirection(direction);
		setState(state);
		state.onEnter(this);
	}

	



	public Direction getPreviousDirection() {
		return previousDirection;
	}
	public State getPreviousState(){
		return previousState;
	}



	


	
	
	

}
