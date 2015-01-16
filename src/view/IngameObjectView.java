package view;

import java.awt.geom.Point2D.Float;

import model.Speed2D;
import model.interaction.GenericEventListener;
import model.interaction.PositionChangeListener;
import model.interaction.SpeedChangeListener;

/**
 * Представление отдельного игрового объекта
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class IngameObjectView
		implements PositionChangeListener, SpeedChangeListener {

    /**
     * Необходимо использовать вместо прямого обращения к спрайту.
     * @param timeElapsed Прошедшее время.
     */
    public void update(int timeElapsed) {
        
    }
    
	@Override
	public void positionChanged(Float newpos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void speedChanged(Speed2D newspeed) {
		// TODO Auto-generated method stub
		
	}
}
