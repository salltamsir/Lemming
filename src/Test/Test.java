package Test;

import java.util.ArrayList;
import java.util.List;

import Graphique.Fenetre;
import Model.Coordinate;
import Model.Direction;
import Model.Escalier;
import Model.Event;
import Model.Game;
import Model.Lemming;
import Model.Observer;
import Model.Obstacle;
import Model.TypeObstacle;
import State.State;

public class Test {
	
	private static final int SPEED = 500;
	
	

	public static void main(String[] args) {
	    ArrayList<Lemming> deletedLemmings = new ArrayList();
		Escalier e = new Escalier(new Coordinate (0,100,20,20) );
		Escalier e1 = new Escalier(new Coordinate (20,100,20,20) );
		Escalier e3 = new Escalier(new Coordinate (40,100,20,20) );
		Escalier e4 = new Escalier(new Coordinate (60,100,20,20) );
		Escalier e5 = new Escalier(new Coordinate (80,100,20,20) );
		Escalier e6 = new Escalier(new Coordinate (100,100,20,20) );
		Escalier e7 = new Escalier(new Coordinate (80,80,20,20) );
		Escalier e8 = new Escalier(new Coordinate (40,80,20,20) );
		
		Fenetre fen = new Fenetre();
		
		Game game = new Game(fen);
		
		game.getListeEscalier().add(e);
		game.getListeEscalier().add(e1);
		game.getListeEscalier().add(e3);
		game.getListeEscalier().add(e4);
		game.getListeEscalier().add(e4);
		game.getListeEscalier().add(e5);
		game.getListeEscalier().add(e6);
		//game.getListeEscalier().add(e7);
		game.getListeEscalier().add(e8);
		
		game.getListeObstacle().add(new Obstacle(new Coordinate(100,80,20,20), TypeObstacle.Simple));
		
		Lemming l = new Lemming (new Coordinate(0,100,20,20), game);
		Lemming l0 = new Lemming (new Coordinate(0,100,20,20), game);
		
		Lemming l1 = new Lemming (new Coordinate (60,80,20,20), game);
		l1.setDirection(Direction.Right);
		ArrayList<Lemming> listeLemming = new ArrayList();
	//	listeLemming.add(l);
		l1.setState(State.TunnelierState);
		listeLemming.add(l1);
	//listeLemming.add(l0);

		
		game.setListeLemming(listeLemming);


		
		
		
		fen.addGame(game);
		//game.setFenetre(fen);


	

		for (Lemming lem : game.getListeLemming()){
		/*	lem.register(new Observer() {
				
				@Override
				public void notify(List<Event> events, Lemming lem) {
					System.out.println(events);
					
				}
			}); */
			
			lem.register(fen);
			lem.register(game);
			
		}
		try{
			Thread.sleep(SPEED);
		}
		catch (InterruptedException ex){
			ex.printStackTrace();
		}

		while(true){
		
			game.getDeletedLemmings(deletedLemmings);
			game.deleteLemmings(deletedLemmings);
			
			for(Lemming lemming : game.getListeLemming()){
				
				lemming.getState().move(lemming);
			
				try{
					Thread.sleep(SPEED);
				}
				catch (InterruptedException ex){
					ex.printStackTrace();
				}
				
			}

		
		}
		


	}

}
