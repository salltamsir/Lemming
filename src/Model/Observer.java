package Model;

import java.util.List;


public interface Observer {
	public void notify(List<Event> events, Lemming l);

}
