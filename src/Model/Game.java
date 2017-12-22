package Model;

import java.util.ArrayList;
import java.util.List;

import Graphique.Fenetre;
import State.State;

public class Game implements Observer{
	

	private static final Coordinate laveCoordinate = new Coordinate(0,400,900,100);
	private static final Coordinate EnterCoorinate = new Coordinate(60,0,50,50);
	private static final Coordinate ExitCoodinate = new Coordinate(840,310,40,50);
	private static final int WIDTH=900;
	private static final int  HEIGHT=600;


	private static int MAX_CHANGE_TIME=5;
	private static int MAX_FALL_TIME=5;
	private int saved=0;
	private ArrayList<Lemming> listeLemming;
	private ArrayList<Obstacle> listeObstacle;
	private Fenetre fenetre;
	
	public Game (){

		listeObstacle= new ArrayList();
		listeLemming= new ArrayList();	

	}
	
	public Game(ArrayList<Lemming> listeLemming){
		this.listeLemming= listeLemming;

	}

	public ArrayList<Lemming> getListeLemming() {
		return listeLemming;
	}
	


	public void setListeLemming(ArrayList<Lemming> listeLemming) {
		this.listeLemming = listeLemming;
	}


	
	

	public boolean isOut(Coordinate c){
		if(c.getX()<0 || c.getX()>WIDTH || c.getY()<0)
			return true;
		return false;
	}

	public boolean isEmpty(Coordinate coordinate, int offsetX, int offsetY){
		for(Obstacle obstacle : listeObstacle){
			if(obstacle.getCoordinate().getX()==coordinate.getX()+offsetX && obstacle.getCoordinate().getY()==coordinate.getY()+offsetY){
				return false;
			}
		}
		return true;
	}
	

	
	public int removeObstacle(Coordinate coordinate, int x, int y){
		int found=0;
		Obstacle obstacle=null;
		for (Obstacle e : listeObstacle){
			if(e.getCoordinate().getX()==coordinate.getX()+x && e.getCoordinate().getY()==coordinate.getY()+y && e.isDestructible()){
				obstacle=e;
				found=1;
				break;
			}		
		}
		listeObstacle.remove(obstacle);
		return found;	
	}

	
	public ArrayList<Lemming> getDeletedLemmings(ArrayList<Lemming> deletedLemmings){
		for (Lemming l: listeLemming ){
			if(l.isDeleted())
				deletedLemmings.add(l);
		}
		return deletedLemmings;
	}
	
	public void deleteLemmings(ArrayList<Lemming> deletedLemmings){
		for(Lemming lemming : deletedLemmings ){
			listeLemming.remove(lemming);
		}
	}
	
	public boolean hitBloqueur(Lemming l){
		for (Lemming lemming: listeLemming){
			if(lemming.getState().equals(State.BloqueurState)){
				if(l.hitSomething(lemming.getCoordinate())){
					return true;
				}
				
			}
		}
		return false;
	}
	
	public boolean hitObstacle(Lemming l){
		for(Obstacle obstacle : listeObstacle){
			if(l.hitSomething(obstacle.getCoordinate())){
				return true;
			}
		}
		return false;
	}
	

	public Obstacle getHitObstacle(Lemming l){
		for(Obstacle obstacle : listeObstacle){

			if(l.hitSomething(obstacle.getCoordinate())){
				return obstacle;
			}
		}
		return null;
	} 
	


	
	public boolean isClimbable(Coordinate coordinate){
		for(Obstacle obstacle : listeObstacle){

			if(obstacle.getCoordinate().getX()==coordinate.getX() && obstacle.getCoordinate().getY()+obstacle.getCoordinate().getLongueur()==coordinate.getY()){
				return false;
			}

		}
		return true;
	}
	
	public boolean endOfClimb(Coordinate c){
		for(Obstacle obstacle : listeObstacle){

			if(obstacle.getCoordinate().getX()==c.getX()+c.getLargeur() && obstacle.getCoordinate().getY()==c.getY()){
				return false;
			}
			else
			if(obstacle.getCoordinate().getX()+obstacle.getCoordinate().getLargeur()==c.getX()&& obstacle.getCoordinate().getY()==c.getY()){
				return false;
			}
		}
			return true;
		
	}
	
	public void addEscalier(Coordinate coordinate){
		listeObstacle.add(new Obstacle(new Coordinate(coordinate.getX()+coordinate.getLargeur(), coordinate.getY(), coordinate.getLargeur(), coordinate.getLongueur()),TypeObstacle.Simple));
	}
	
	public ArrayList<Obstacle> getNeighborObstacles(Coordinate coordinate){
		ArrayList<Obstacle> obs= new ArrayList();

		Coordinate leftRange = new Coordinate(coordinate.getX()-(3*coordinate.getLargeur()),coordinate.getY(), 3*coordinate.getLargeur(),coordinate.getLongueur());
		Coordinate rightRange = new Coordinate(coordinate.getX()+coordinate.getLargeur(),coordinate.getY(), 3*coordinate.getLargeur(),coordinate.getLongueur());
		for(Obstacle o : listeObstacle){
			if(leftRange.contains(o.getCoordinate().getX(), o.getCoordinate().getY()) || rightRange.contains((o.getCoordinate().getX()), o.getCoordinate().getY()) && o.isDestructible()){
				obs.add(o);
				
			}
		}
	
		return obs;
		
	}
	
	public void removeNeighborObstacles(ArrayList<Obstacle> obs){
		for (Obstacle o: obs){
			listeObstacle.remove(o);

		} 
	}

	public boolean isAlive(){
		
		if (listeLemming.size()+ saved<0)
			return false;;
		return true;
	}
	
	public Boolean win(){
		if(saved>=3)
			return true;
		return false;
	}
	
	public boolean isChangeable(Lemming l , State state){
		if(state.equals(l.getState()))
			return false;
		if(state.equals(State.CharpentierState))
			return l.getDirection().equals(Direction.Right);
		if(state.equals(State.ParachutisteState))
			return l.getDirection().equals(Direction.Down);
		return (l.getDirection().equals(Direction.Right) || l.getDirection().equals(Direction.Left));
	}
	
	
	//Methode pour changer le state d'un lemmin cliqué
	public void changeType(String state, int x, int y){
	  if(state!=null){
		for (Lemming l : listeLemming){
			//System.out.println(l.getCoordinate().contains(x, y));
			if(l.getCoordinate().contains(x, y)){
				System.out.println(l.getState()+" is my state");
					l.setState(State.valueOf(state+"State"));
				break;
			}
			
		}
	  }
	}
	

	@Override
	public void notify(List<Event> events, Lemming l) {


		if(ExitCoodinate.contains(l.getCoordinate().getX(), l.getCoordinate().getY())){
			l.setDeleted(true);
			++saved;
			System.out.println(saved);
		}
		else
			if(laveCoordinate.contains(l.getCoordinate().getX(), l.getCoordinate().getY()) && l.isOut()){
				l.setDeleted(true);

			}
			else
				
		if(l.getFallTime()==MAX_FALL_TIME){
			l.setDeleted(true);
		}
		else
		
		if(l.getChangeTime()==MAX_CHANGE_TIME){
			l.setChangeTime(0);
			l.setState(State.NormalState);
		}
	

		else
			if(l.getState().equals(State.BombeurState) && l.getChangeTime()==3){
				l.setDeleted(true);
				removeNeighborObstacles(getNeighborObstacles(l.getCoordinate()));
			}
	
	} 

	public ArrayList<Obstacle> getListeObstacle() {
		return listeObstacle;
	}

	public void setListeObstacle(ArrayList<Obstacle> listeObstacle) {
		this.listeObstacle = listeObstacle;
	}

	public static Coordinate getLaveCoordinat() {
		return laveCoordinate;
	}

	public static Coordinate getEnterCoordinate() {
		return EnterCoorinate;
	}

	public static Coordinate getExitCoodinate() {
		return ExitCoodinate;
	}
	
	public int getSaved(){
		return saved;
	}
	public int getWidth(){
		return WIDTH;
	}
	public int getHeight(){
		return HEIGHT;
	}


	

}
