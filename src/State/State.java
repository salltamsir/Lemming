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
			 
			 if(l.getDirection().equals(Direction.Up) && l.getGame().endOfClimb(l)){
				 l.setDirection(l.getPreviousDirection());
			 } 
			 else
			 
			 if(l.getGame().isEmpty(l.getCoordinate())){
				 l.setDirection(Direction.Down);
				 l.setFallTime(l.getFallTime()+1);
			}
			 else
				 
				 if(l.getDirection().equals(Direction.Down)){
					 l.setDirection(l.getPreviousDirection());
				 	 l.setFallTime(0);
				 }
			 
			else
			 
				if(l.getGame().hitBloqueur(l)){
					l.setDirection(l.getDirection().oppose());
				}
			 
			else
				if(l.getGame().hitEscalier(l)){
					if(l.getGame().isClimbable(l.getGame().getHitEscalier(l))){
						l.setDirection(Direction.Up);
					}
					l.setDirection(l.getDirection().oppose());
			    }
			 
			else
					if(l.getGame().hitObstacle(l)){

						l.setDirection(l.getDirection().oppose());
				    }
			 
			 
		    l.move(l.getDirection().getX(), l.getDirection().getY());
			ArrayList<Event> events = new ArrayList<>();
			events.add(new Event(l.getCoordinate(), l.getDirection()));
			l.notifyObservers(events);
			

			
		}

		@Override
		public void onEnter(Lemming l) {
			// TODO Auto-generated method stub
			
		}
		
	},
	
	CharpentierState{

		@Override
		public
		void move(Lemming l) {
			// TODO Auto-generated method stub
			l.setChangeTime(l.getChangeTime()+1);
			if(l.getGame().isEmpty(l.getCoordinate())==false && l.getGame().isBuildable(l.getCoordinate())){
				l.getGame().addEscalier(l.getCoordinate());
				l.jump();
				ArrayList<Event> events = new ArrayList<>();
				events.add(new Event(l.getCoordinate(), l.getDirection()));
				l.notifyObservers(events);
				 
				
	
			}
			else
				l.setState(NormalState);
		
	
			
		}

		@Override
		public void onEnter(Lemming l) {
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

		@Override
		public void onEnter(Lemming l) {
			// TODO Auto-generated method stub
			
		}
		
	},
	
	TunnelierState{

		@Override
		public
		void move(Lemming l) {
			// TODO Auto-generated method stub
			if(l.getDirection().equals(Direction.Up )|| l.getDirection().equals(Direction.Down))
				l.setState(NormalState);
			else{
				l.getGame().removeObstacle(l.getCoordinate());
				NormalState.move(l);
			}
			
			
		}

		@Override
		public void onEnter(Lemming l) {
			// TODO Auto-generated method stub
			
		}
		
	},
	
	BombeurState{

		@Override
		public void move(Lemming l) {
			// TODO Auto-generated method stub
			if(l.getChangeTime()==3){
				
			}
			NormalState.move(l);
			l.setChangeTime(l.getChangeTime()+1);
			
		}

		@Override
		public void onEnter(Lemming l) {
			// TODO Auto-generated method stub
			
		}
		
		
	},
	
	ForreurState{

		@Override
		public
		void move(Lemming l) {
			// TODO Auto-generated method stub
			if( l.getGame().removeEscalier(l.getCoordinate())==1){
				l.setFallTime(0);
			}
			 if(l.getGame().isEmpty(l.getCoordinate())){
				 l.setDirection(Direction.Down);
				 l.setFallTime(l.getFallTime()+1);
			} 
			 l.move(l.getDirection().getX(), l.getDirection().getY());
			ArrayList<Event> events = new ArrayList<>();
			events.add(new Event(l.getCoordinate(), l.getDirection()));
			l.notifyObservers(events);
			
			l.setChangeTime(l.getChangeTime()+1);
			
		}

		@Override
		public void onEnter(Lemming l) {
			// TODO Auto-generated method stub
			
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
				 
			 if(l.getGame().isEmpty(l.getCoordinate()) && !l.getDirection().equals(Direction.Up)){
				 l.setDirection(Direction.Down);
				 l.setFallTime(l.getFallTime()+1);
			}
			 else{
				 if(l.getDirection().equals(Direction.Down)){
					 l.setDirection(l.getPreviousDirection());
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

		@Override
		public void onEnter(Lemming l) {
			// TODO Auto-generated method stub
			
		}
		
	},
	ParachutisteState{

		@Override
		public
		void move(Lemming l) {
			// TODO Auto-generated method stub

			 
			if(l.getDirection().equals(Direction.Down)) {
				if(! l.getGame().isEmpty(l.getCoordinate())){
					l.setDirectionAndState(Direction.Right,NormalState);
				}
				else
				l.move(l.getDirection().getX()/2, l.getDirection().getY()/2);
			}
			else
				l.setDirectionAndState(Direction.Right,NormalState);
				
			ArrayList<Event> events = new ArrayList<>();
			events.add(new Event(l.getCoordinate(), l.getDirection()));
			l.notifyObservers(events);
			
		}

		@Override
		public void onEnter(Lemming l) {
			// TODO Auto-generated method stub
			l.setFallTime(0);
			
		}
		
	};
	
	public abstract void move (Lemming l);
	public abstract void onEnter(Lemming l);
	
	

}
