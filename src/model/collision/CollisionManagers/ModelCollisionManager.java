package model.collision.CollisionManagers;

import com.golden.gamedev.object.collision.CollisionShape;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import model.Direction;
import model.IngameObject;
import model.Bounds;
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
            boolean isOnOneLine = false;
            if (others.size() > 1) {
                isOnOneLine = true;
                for(int k = 1; k < others.size(); k++) {
                    if(others.get(k).object().getPosition().x != others.get(0).object().getPosition().x 
                            && others.get(k).object().getPosition().y != others.get(0).object().getPosition().y) {
                        isOnOneLine = false;
                    }
                }
            }
            CollidedObject current  = entry.getKey();
            Iterator j = others.iterator();
            while (j.hasNext()) {
                CollidedObject other  = (CollidedObject)j.next();
                if(!isOnOneLine) {
                    current.object().processCollision(other, current.collisionSide(), current.collisionShape());
                }
                other.object().processCollision(current, other.collisionSide(), other.collisionShape());
            }
            if(isOnOneLine) {
                j = others.iterator();
                current.object().processCollision((CollidedObject)j.next(), current.collisionSide(), current.collisionShape());
            }
	}
    }
    
    void boundsCollisionOcured(IngameObject obj, int collisionSide, Shape collisionShape){
        obj.processCollision(new CollidedObject(new Bounds(), new Point2D.Double(0, 0), collisionSide, collisionShape), collisionSide, collisionShape);
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
