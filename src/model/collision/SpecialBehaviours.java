package model.collision;

import java.util.ArrayList;

public class SpecialBehaviours {

    /**
     * Если флаг установлен, то поведение применяется не только при столкновении с объектом данного класса, но и его потомками.
     */
    public boolean flagCheckDerived = false;
    
    public ArrayList<CollisionBehaviour> behaviours = new ArrayList<>();
    
    public SpecialBehaviours() {
        
    }
    
    public SpecialBehaviours(CollisionBehaviour b) {
        
        if (b == null) {
            throw new NullPointerException();
        }
        behaviours.add(b);
    }
}
