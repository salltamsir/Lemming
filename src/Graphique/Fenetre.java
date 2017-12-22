package Graphique;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Obstacle;
import Model.Event;
import Model.Game;
import Model.Lemming;
import Model.Observer;
import Model.Obstacle;



public class Fenetre extends JFrame implements Observer{


	private Game game;
	JButton [] bouton = new JButton[7];
	private String choosenType=null;

	
	
	private JComponent component = new JComponent() {

		@Override
		protected void paintComponent(Graphics g) {

			g.setColor(Color.GRAY);
			g.fillRect(game.getExitCoodinate().getX(), game.getExitCoodinate().getY(), game.getExitCoodinate().getLargeur(), game.getExitCoodinate().getLongueur());
			g.fillRect(game.getEnterCoordinate().getX(), game.getEnterCoordinate().getY(), game.getEnterCoordinate().getLargeur(), game.getEnterCoordinate().getLongueur());
			g.setColor(Color.BLUE);
			for (Lemming l: game.getListeLemming()){

				g.fillOval(l.getCoordinate().getX(),l.getCoordinate().getY(),l.getCoordinate().getLargeur(),l.getCoordinate().getLongueur());
			}
	
			g.setColor(Color.BLACK);
			
			for (Obstacle o: game.getListeObstacle()){
				
				if(o.isDestructible()) 
					g.setColor(Color.BLACK);
				else if(o.isLave())
					g.setColor(Color.RED);
				else
					g.setColor(Color.GRAY);
				g.fillRect(o.getCoordinate().getX(),o.getCoordinate().getY(),o.getCoordinate().getLargeur(),o.getCoordinate().getLongueur());
			}
			
			
			}
	};
	
	public Fenetre(int width, int height){
		
	    JPanel boutonPane = new JPanel();
	    bouton[0] = new JButton("Forreur");
	    bouton[1]= new JButton("Bloqueur");
	    bouton[2] = new JButton("Bombeur");
	    bouton[3] = new JButton("Charpentier");
	    bouton[4] = new JButton("Tunnelier");
	    bouton[5]= new JButton("Parachutiste");
	    bouton[6]=new JButton("Grimpeur");
	    this.addListener();
	    boutonPane.add(bouton[0]);
	    boutonPane.add(bouton[1]);
	    boutonPane.add(bouton[2]);
	    boutonPane.add(bouton[3]);
	    boutonPane.add(bouton[4]);
	    boutonPane.add(bouton[5]);
	    boutonPane.add(bouton[6]);
		component.add(boutonPane);
		this.setTitle("Lemmings");
		this.setSize(width,height);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.getContentPane().add(boutonPane, BorderLayout.NORTH);
	    this.getContentPane().add(component, BorderLayout.CENTER);
		this.setVisible(true);
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) throws NullPointerException {

				final Point pos = component.getMousePosition();
				try{
				  game.changeType(choosenType, pos.x, pos.y);
				}
				catch (Exception ex){
					
				}
				
			}
		});

	}
	
	
	public void addGame(Game game){
		this.game=game;
	}

	
	public void addListener(){
		for (int i=0;i<this.bouton.length;i++){
			bouton[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					choosenType=( (JButton)e.getSource()).getText();
					//System.out.println(( (JButton)e.getSource()).getText());
					
				}
			});
			
		}
	}
	




	@Override
	public void notify(List<Event> events, Lemming l) {
		repaint();
		
	}



}
