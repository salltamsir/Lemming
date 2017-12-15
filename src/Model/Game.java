package Model;

import java.util.ArrayList;
import java.util.List;

import Graphique.Fenetre;
import State.State;

public class Game implements Observer{
	
	private static int MAX_CHANGE_TIME=5;
	private static int MAX_FALL_TIME=5;
	private ArrayList<Lemming> listeLemming;
	private ArrayList<Escalier> listeEscalier;
	private ArrayList<Obstacle> listeObstacle;
	private Fenetre fenetre;
	
	public Game (Fenetre fenetre){
		this.fenetre=fenetre;
		listeEscalier= new ArrayList();
		listeLemming= new ArrayList();
		listeObstacle= new ArrayList();
		
	}
	
	public Game(ArrayList<Lemming> listeLemming, Fenetre fenetre){
		this.listeLemming= listeLemming;
		this.fenetre=fenetre;
	}

	public ArrayList<Lemming> getListeLemming() {
		return listeLemming;
	}
	


	public void setListeLemming(ArrayList<Lemming> listeLemming) {
		this.listeLemming = listeLemming;
	}

	public ArrayList<Escalier> getListeEscalier() {
		return listeEscalier;
	}

	public void setListeEscalier(ArrayList<Escalier> listeEscalier) {
		this.listeEscalier = listeEscalier;
	}

	
	

	public boolean isOut(Coordinate c){
	if(c.getX()+c.getLargeur()>=fenetre.getWidth()-Direction.Right.getX()){

			return true;
		} 
		if(c.getX()<-1*(Direction.Left.getX()))
				return
				true;
		
		return false;
		
	}
	public boolean isEmpty(Coordinate coordinate){
		for(Escalier escalier : listeEscalier){
			if(escalier.getCoordinate().getX()==coordinate.getX() && escalier.getCoordinate().getY()==coordinate.getY()+coordinate.getLongueur()){
				return false;
			}
		}
		for(Obstacle obstacle : listeObstacle){
			if(obstacle.getCoordinate().getX()==coordinate.getX() && obstacle.getCoordinate().getY()==coordinate.getY()+coordinate.getLongueur()){
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isBuildable(Coordinate coordinate){
		for(Escalier escalier : listeEscalier){
			if(escalier.getCoordinate().getX()==coordinate.getX()+coordinate.getLargeur() && escalier.getCoordinate().getY()==coordinate.getY()){
				return false;
			}
		}
		for(Obstacle obstacle : listeObstacle){
			if(obstacle.getCoordinate().getX()==coordinate.getX()+coordinate.getLargeur() && obstacle.getCoordinate().getY()==coordinate.getY()){
				return false;
			}
		}
		
		return true;
	}
	
	public int removeEscalier(Coordinate coordinate){
		int found=0;
		Escalier escalier=null;
		for (Escalier e : listeEscalier){
			if(e.getCoordinate().getX()==coordinate.getX() && e.getCoordinate().getY()==coordinate.getY()+coordinate.getLargeur()){
				escalier=e;
				found=1;
				break;
			}		
		}
		listeEscalier.remove(escalier);
		return found;
		
	}
	
	public void removeObstacle(Coordinate coordinate){


		for (Obstacle o : listeObstacle){
			
			if(o.getCoordinate().getX()==coordinate.getX()+coordinate.getLargeur() && o.getCoordinate().getY()==coordinate.getY() ){
				System.out.println(o.getCoordinate().getX()+" et "+coordinate.getX()+" y "+o.getCoordinate().getY()+" et "+coordinate.getY());
				if(o.getType().equals(TypeObstacle.Simple)){
					listeObstacle.remove(o);
					break;
				}
			}		
		}
				
	
		
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
	
	public boolean hitEscalier(Lemming l){
		for(Escalier escalier : listeEscalier){

			if(l.hitSomething(escalier.getCoordinate())){
				return true;
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
	
	public Escalier getHitEscalier(Lemming l){
		for(Escalier escalier : listeEscalier){

			if(l.hitSomething(escalier.getCoordinate())){
				return escalier;
			}
		}
		return null;
	}
	
	public boolean isClimbable(Escalier e){
		for(Escalier escalier : listeEscalier){

			if(escalier.getCoordinate().getX()==e.getCoordinate().getX() && escalier.getCoordinate().getY()+escalier.getCoordinate().getLongueur()==e.getCoordinate().getY()){
				return false;
			}

		}
		return true;
	}
	
	public boolean endOfClimb(Lemming l){
		for(Escalier escalier : listeEscalier){

			if(escalier.getCoordinate().getX()==l.getCoordinate().getX()+l.getCoordinate().getLargeur() && escalier.getCoordinate().getY()==l.getCoordinate().getY()){
				return false;
			}
			else
			if(escalier.getCoordinate().getX()+escalier.getCoordinate().getLargeur()==l.getCoordinate().getX()&& escalier.getCoordinate().getY()==l.getCoordinate().getY()){
				return false;
			}
		}
			return true;
		
	}
	
	public void addEscalier(Coordinate coordinate){
		listeEscalier.add(new Escalier(new Coordinate(coordinate.getX()+coordinate.getLargeur(), coordinate.getY(), coordinate.getLargeur(), coordinate.getLongueur())));
	}
	
	public ArrayList<Obstacle> getNeighborObstacles(Coordinate coordinate){
		ArrayList<Obstacle> obs= new ArrayList();
		for(Obstacle o : listeObstacle){
			if(o.getCoordinate().getX()<= coordinate.getX()+3*coordinate.getLargeur() && o.getCoordinate().getY()==coordinate.getY() && o.getCoordinate().getX()>coordinate.getX()){
				obs.add(o);
			}
			else
				if(o.getCoordinate().getX()>= coordinate.getX()+3*coordinate.getLargeur() && o.getCoordinate().getY()==coordinate.getY() && o.getCoordinate().getX()<coordinate.getX()){
					obs.add(o);
				}
		}
		return obs;
		
	}
	
	public void removeNeighborObstacles(ArrayList<Obstacle> obs){
		for (Obstacle o: obs){
			listeObstacle.remove(obs);
		}
	}

	

	@Override
	public void notify(List<Event> events, Lemming l) {

		//System.out.println(l.getCoordinate().getX());
		if(l.getChangeTime()==MAX_CHANGE_TIME){
			l.setChangeTime(0);
			l.setState(State.NormalState);
		}
		else
			if(l.getFallTime()==MAX_FALL_TIME){
				l.setDeleted(true);
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
	

}
