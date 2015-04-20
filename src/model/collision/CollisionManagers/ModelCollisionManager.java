package model.collision.CollisionManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import model.Direction;
import model.IngameObject;
import model.collision.CollidedObject;
import model.collision.SpecialBehaviours;

/**
 * Содержит поведение объектов при столкновении.
 * @author Tenk0Kugen
 */
public class ModelCollisionManager {
    
    void collisionOcured(HashMap storage){
        Iterator i = storage.entrySet().iterator();
	while (i.hasNext()) {
            Map.Entry<CollidedObject, ArrayList<CollidedObject>> entry = (Map.Entry)i.next();
            ArrayList<CollidedObject> others = entry.getValue();
            CollidedObject current  = entry.getKey();
            Iterator j = others.iterator();
            while (j.hasNext()) {
                CollidedObject other  = (CollidedObject)j.next();
                current.object().processCollision(current, other);
            }
	}
    }
    
    void boundsCollisionOcured(IngameObject obj, Direction direction){
        if (direction.equals(Direction.east()) || direction.equals(Direction.west())) {
            obj.setSpeed(obj.getSpeed().flipHorizontal());
        } else {
            obj.setSpeed(obj.getSpeed().flipVertical());
        }
    }
    
    private HashMap DeepCopyStorage(HashMap storage){
        //TODO
        return null;
    }
    
    private void fixBallPosition(){
        //TODO
    }
}
