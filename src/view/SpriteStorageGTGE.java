/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import model.Speed2D;
import model.interaction.PositionChangeListener;
import model.interaction.SpeedChangeListener;

/**
 *
 * @author vadim
 */
public class SpriteStorageGTGE implements SpriteStorage {
    protected IngameObjectView _view;
    protected SpriteGTGE _sprite;

    public SpriteStorageGTGE(BufferedImage image) {
        if (image == null) {
	    throw new NullPointerException();
	}
        this._sprite = new SpriteGTGE();
        this._sprite.setImage(image);
    }
    
    public SpriteStorageGTGE() {
        this._sprite = new SpriteGTGE();
    }
    @Override
    public Speed2D getSpeed() {
        return new Speed2D(this._sprite.getHorizontalSpeed(),this._sprite.getVerticalSpeed());
    }

    @Override
    public void setSpeed(Speed2D speed) {
        if (speed == null) {
	    throw new NullPointerException();
	}
        this._sprite.setSpeed(speed.x(), speed.y());
    }

    @Override
    public Point2D.Double getPosition() {
        return new Point2D.Double(this._sprite.getX(), this._sprite.getY());
    }

    @Override
    public void setPosition(Point2D.Double position) {
        if (position == null) {
	    throw new NullPointerException();
	}
        this._sprite.setX(position.getX());
        this._sprite.setY(position.getY());
    }

    @Override
    public IngameObjectView getObjectView() {
        return this._view;
    }

    @Override
    public void setObjectView(IngameObjectView view) {
        if (view == null) {
	    throw new NullPointerException();
	}
        this._view = view;
    }
    public SpriteGTGE getSprite() {
        return _sprite;
    }
    
   /**
     * Необходимо использовать вместо прямого обращения к спрайту.
     * @param timeElapsed Прошедшее время.
     */
    public void update(long timeElapsed) {
    	_sprite.update(timeElapsed);
    }
    
     public void render(Graphics2D g) {
    	
    	_sprite.render(g);
    }
}
