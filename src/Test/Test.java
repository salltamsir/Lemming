package Test;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Graphique.Fenetre;
import Model.Coordinate;
import Model.CoordinateException;
import Model.Direction;

import Model.Obstacle;
import Model.Event;
import Model.Game;
import Model.Lemming;
import Model.Observer;
import Model.Obstacle;
import Model.TypeObstacle;
import State.State;

public class Test {
	
	private static final int SPEED = 500;
	private static final int LONGUEUR=20;
	private static int  LARGEUR=20;
	
	public static void makeCircuit(Game game, int x, int y, int nb){
		for(int i=0;i<nb;++i ){
			game.getListeObstacle().add(new Obstacle(new Coordinate(x,y,LARGEUR,LONGUEUR)));
			x+=20;
		}
	}
	
	

	public static void main(String[] args)  {
	    ArrayList<Lemming> deletedLemmings = new ArrayList();
	    ArrayList<Coordinate> coordinateObstacle= new ArrayList();
		 

	   
		Game game = new Game();
		Fenetre fen = new Fenetre(game.getWidth(),game.getHeight());
		makeCircuit(game,60,200,20);makeCircuit(game,120,220,13);makeCircuit(game,140,240,11);
		makeCircuit(game,140,260,11);makeCircuit(game,140,280,11);makeCircuit(game,600,340,12);
		makeCircuit(game,740,320,1);makeCircuit(game,740,300,1);
		makeCircuit(game,820,320,1);makeCircuit(game,820,300,1);
		makeCircuit(game,200,300,1);
		
		Obstacle o = new Obstacle(game.getLaveCoordinat(),TypeObstacle.Lave);
		game.getListeObstacle().add(o);
		
		fen.addGame(game);
		
	
		Lemming l = new Lemming (new Coordinate(60,0,LONGUEUR,LARGEUR), game);
		Lemming l0 = new Lemming (new Coordinate(60,-60,LONGUEUR,LARGEUR), game);
		Lemming l1 = new Lemming (new Coordinate (60,-140,LONGUEUR,LARGEUR), game);
		Lemming l2 = new Lemming (new Coordinate (60,-180,LONGUEUR,LARGEUR), game);
		Lemming l3 = new Lemming (new Coordinate (60,-240,LONGUEUR,LARGEUR), game);
		ArrayList<Lemming> listeLemming = new ArrayList();
		listeLemming.add(l0);
		listeLemming.add(l);
		listeLemming.add(l1);
		listeLemming.add(l2);
		listeLemming.add(l3);
		game.setListeLemming(listeLemming);
		
		

		for (Lemming lem : game.getListeLemming()){

			lem.register(fen);
			lem.register(game);
			
		}
		try{
			Thread.sleep(SPEED);
		}
		catch (InterruptedException ex){
			ex.printStackTrace();
		}

		while(game.isAlive()){
		
			game.getDeletedLemmings(deletedLemmings);
			game.deleteLemmings(deletedLemmings);
			
			for(Lemming lemming : game.getListeLemming()){
				

					lemming.getState().move(lemming);
			
					try{
						Thread.sleep(SPEED/game.getListeLemming().size());
					}
					catch (InterruptedException ex){
					ex.printStackTrace();
					}
			
				
			}

		
		}
		JOptionPane jop1 = new JOptionPane();
		if(game.win())
			jop1.showMessageDialog(null, "Vous avez gagne : "+game.getSaved()+" sauves", "Termine", JOptionPane.INFORMATION_MESSAGE);
		else
			jop1.showMessageDialog(null, "Vous avez perdu", "Termine", JOptionPane.ERROR_MESSAGE);
		


	}

}
