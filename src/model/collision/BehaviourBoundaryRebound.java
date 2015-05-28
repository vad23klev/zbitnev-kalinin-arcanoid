/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.collision;

import model.Direction;
import model.IngameObject;
import model.ball.Ball;

/**
 *
 * @author vadim
 */
public class BehaviourBoundaryRebound extends CollisionBehaviour {
    /**
	 * Экзмепляр синглтона.
	 */
	private static BehaviourBoundaryRebound _instance = null;
	
	protected BehaviourBoundaryRebound() {
	}
        
        /**
	 * Возвращает экземпляр поведения упрогого отскока.
	 * @return
	 */
	public static BehaviourBoundaryRebound getInstance() {
		
            if (_instance == null) {
                    _instance = new BehaviourBoundaryRebound();
            }

            return _instance;
	}
	
        @Override
	public void invoke(CollidedObject from, CollidedObject to) {
            IngameObject toobj = to.object();
            if (toobj instanceof Ball) {
                if (to.collisionSide()  == CollidedObject.SIDE_LEFT || to.collisionSide()  == CollidedObject.SIDE_RIGHT) {
                    toobj.setSpeed(toobj.getSpeed().flipHorizontal());
                } else if (to.collisionSide()  == CollidedObject.SIDE_TOP) {
                    toobj.setSpeed(toobj.getSpeed().flipVertical());
                } else {
                    toobj.destroy();
                }
            }
	}
    
}
