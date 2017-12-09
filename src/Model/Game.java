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
	private Fenetre fenetre;
	
	public Game (Fenetre fenetre){
		this.fenetre=fenetre;
		listeEscalier= new ArrayList();
		
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
	public boolean isEmpty(Lemming l){
		for(Escalier escalier : listeEscalier){
			if(escalier.getCoordinate().getX()==l.getCoordinate().getX() && escalier.getCoordinate().getY()==l.getCoordinate().getY()+l.getCoordinate().getLongueur()){
				return false;
			}
		}
		
		return true;
	}
	
	public void remove(Coordinate coordinate){
		Escalier escalier=null;
		for (Escalier e : listeEscalier){
			if(e.getCoordinate().getX()==coordinate.getX() && e.getCoordinate().getY()==coordinate.getY()+coordinate.getLargeur()){
				escalier=e;
				break;
			}
				
		}
		listeEscalier.remove(escalier);
		
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
	

	@Override
	public void notify(List<Event> events, Lemming l) {

		
		if(l.getChangeTime()==MAX_CHANGE_TIME){
			l.setChangeTime(0);
			l.setState(State.NormalState);
		}
		else
			if(l.getFallTime()==MAX_FALL_TIME){
				l.setDeleted(true);
			}
	
	}
	

}
