package model.collision.CollisionManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import model.Direction;
import model.IngameObject;
import model.collision.CollidedObject;

/**
 * Содержит поведение объектов при столкновении.
 * @author Tenk0Kugen
 */
public class ModelCollisionManager {
    
    void collisionOcured(HashMap storage){
        storage = this.removeCouplingFromStorage(storage);
        Iterator i = storage.entrySet().iterator();
	while (i.hasNext()) {
            Map.Entry<CollidedObject, ArrayList<CollidedObject>> entry = (Map.Entry)i.next();
            ArrayList<CollidedObject> others = entry.getValue();
            CollidedObject current  = entry.getKey();
            Iterator j = others.iterator();
            while (j.hasNext()) {
                CollidedObject other  = (CollidedObject)j.next();
                current.object().processCollision(current, other);
                other.object().processCollision(other, current);
            }
	}
    }
    
    void boundsCollisionOcured(IngameObject obj, Direction direction){
        if (direction.equals(Direction.east()) || direction.equals(Direction.west())) {
            obj.setSpeed(obj.getSpeed().flipHorizontal());
        } else if (direction.equals(Direction.north())) {
            obj.setSpeed(obj.getSpeed().flipVertical());
        } else {
            obj.destroy();
        }
    }
    
    private HashMap DeepCopyStorage(HashMap storage){
        //TODO
        return null;
    }
    
    private void fixBallPosition(){
        //TODO
    }
        /**
     * Просеять словарь столкновений и удалить дублирующиеся ассоциации
     * @param st Словарь столкновений
     */
    private HashMap<CollidedObject, ArrayList<CollidedObject>>
        removeCouplingFromStorage(HashMap<CollidedObject, ArrayList<CollidedObject>> st) {

        HashMap<CollidedObject, ArrayList<CollidedObject>> newst = new HashMap<>();

        for (CollidedObject key : st.keySet()) {
            for (CollidedObject val : st.get(key)) {
                // Если в словарь уже не добавлена "обратная" ассоциация
                if (!newst.containsKey(val) || !newst.get(val).contains(key)) {
                    if (!newst.containsKey(key)) {
                        newst.put(key, new ArrayList<CollidedObject>());
                    }
                    newst.get(key).add(val);
                }
            }
        }

        return newst;
    }
}
