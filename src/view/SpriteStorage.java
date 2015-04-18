package view;

import java.awt.geom.Point2D;
import model.Speed2D;

/**
 *
 * @author vadim
 */
public interface SpriteStorage {
    public Speed2D getSpeed();
    public void setSpeed(Speed2D speed);
    public Point2D.Double getPosition();
    public void setPosition (Point2D.Double position);
    public IngameObjectView getObjectView();
    public void setObjectView(IngameObjectView view);
    
}
