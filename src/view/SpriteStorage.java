/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public Point2D.Float getPosition();
    public void setPosition (Point2D.Float position);
    
}
