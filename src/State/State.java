package State;

import Model.Direction;
import Model.Event;
import java.util.ArrayList;
import java.util.List;

import Model.Lemming;


public enum State {
	
	NormalState{
		
		public void move(Lemming l){

			 if(l.getGame().isOut(l.getCoordinate())){
				l.setDirection(l.getDirection().oppose());
			 } 
			 
			 if(l.getGame().isEmpty(l)){
				 l.setDirection(Direction.Down);
				 l.setFallTime(l.getFallTime()+1);
			}
			 else{
				 if(l.getDirection().equals(Direction.Down))
					 l.setDirection(Direction.Right);
				 l.setFallTime(0);
			 }
			 
				if(l.getGame().hitBloqueur(l)){
					l.setDirection(l.getDirection().oppose());
				}
				else{
					if(l.getGame().hitEscalier(l)){
						if(l.getGame().isClimbable(l.getGame().getHitEscalier(l))){
							l.jump();
						}
						l.setDirection(l.getDirection().oppose());
						
					}
				}
			 
			 
		    l.move(l.getDirection().getX(), l.getDirection().getY());
			ArrayList<Event> events = new ArrayList<>();
			events.add(new Event(l.getCoordinate(), l.getDirection()));
			l.notifyObservers(events);
			

			
		}
		
	},
	
	CharpentierState{

		@Override
		public
		void move(Lemming l) {
			// TODO Auto-generated method stub

			
		}
		
	},
	
	BloqueurState{

		@Override
		public
		void move(Lemming l) {
			// TODO Auto-generated method stub

			ArrayList<Event> events = new ArrayList<>();
			events.add(new Event(l.getCoordinate(), l.getDirection()));
			l.notifyObservers(events);
			
			
		}
		
	},
	
	TunnelierState{

		@Override
		public
		void move(Lemming l) {
			// TODO Auto-generated method stub
			
		}
		
	},
	
	ForreurState{

		@Override
		public
		void move(Lemming l) {
			// TODO Auto-generated method stub
			l.getGame().remove(l.getCoordinate());
			
			 if(l.getGame().isEmpty(l)){
				 l.setDirection(Direction.Down);
				 l.setFallTime(l.getFallTime()+1);
			}
			 
			 l.move(l.getDirection().getX(), l.getDirection().getY());
			

			ArrayList<Event> events = new ArrayList<>();
			events.add(new Event(l.getCoordinate(), l.getDirection()));
			l.notifyObservers(events);
			
			l.setChangeTime(l.getChangeTime()+1);
			
		}
		
	},
	
	GrimpeurState{
	    public  void move(Lemming l){
	    	
			 if(l.getGame().isOut(l.getCoordinate())){
				l.setDirection(l.getDirection().oppose());
			 } 
			 else
			 if(l.getDirection().equals(Direction.Up) && l.getGame().endOfClimb(l)){
				 l.setDirectionAndState(Direction.Right, NormalState);
			 }
			 else
				 
			 if(l.getGame().isEmpty(l) && !l.getDirection().equals(Direction.Up)){
				 l.setDirection(Direction.Down);
				 l.setFallTime(l.getFallTime()+1);
			}
			 else{
				 if(l.getDirection().equals(Direction.Down)){
					 l.setDirection(Direction.Right);
				 	l.setFallTime(0);
				 }
			 }
			 
				if(l.getGame().hitBloqueur(l)){
					l.setDirection(l.getDirection().oppose());
				}
				else{
					if(l.getGame().hitEscalier(l)){
						l.setDirection(Direction.Up);
					}
				}
			 
			 
		    l.move(l.getDirection().getX(), l.getDirection().getY());
			ArrayList<Event> events = new ArrayList<>();
			events.add(new Event(l.getCoordinate(), l.getDirection()));
			l.notifyObservers(events);
			
		}
		
	},
	ParachutisteState{

		@Override
		public
		void move(Lemming l) {
			// TODO Auto-generated method stub
			 if(l.getGame().isEmpty(l)){
				 l.setDirection(Direction.Down);
				 l.setFallTime(0);
			}
			 else{
				 if(l.getDirection().equals(Direction.Down)){
					 l.setDirectionAndState(Direction.Right, NormalState);
				 }
				 
			 }
			 
		    l.move(l.getDirection().getX(), l.getDirection().getY());
				
			ArrayList<Event> events = new ArrayList<>();
			events.add(new Event(l.getCoordinate(), l.getDirection()));
			l.notifyObservers(events);
			
		}
		
	};
	
	public abstract void move (Lemming l);
	
	

}
