package model.swarm;

import model.GameField;
import model.IngameObject;

/**
 * Модель роя.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class Swarm extends IngameObject {

	public Swarm(GameField field) {
            
            if (field == null) {
	        throw new NullPointerException();
	    }
	    this._field = field;
	}
	
	public void devour(CanBeInSwarm obj) {
	    
	}

}
