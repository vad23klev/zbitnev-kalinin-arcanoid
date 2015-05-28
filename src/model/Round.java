package model;

import java.awt.geom.Point2D;

/**
 * Круглая форма.
 * @author Tenk0Kugen
 */
public class Round extends Form{
    
    private double _radius;
    private Point2D.Double _center;
    
    public Round(Point2D.Double cent, Double rad){
        
        _radius = rad;
        _center = cent;
    }
    
    /**
     * Получить радиус объекта в пикселях.
     * @return Радиус.
     */
    public double getRadius(){
        
        return _radius;
    }
    
    /**
     * Задает радиус объекта в пикселях.
     * @param radius
     */
    public void setRadius(double radius){
        
        _radius = radius;
    }
    
    /**
     * Получить координаты центра объекта.
     * @return Координаты центра.
     */
    public Point2D.Double getCenter(){
        
        return _center;
    }
    
    /**
     * Установить координаты центра объекта.
     * @param cent
     */
    public void setCenter(Point2D.Double cent){
        
        _center = cent;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        return _radius == ((Round)obj).getRadius() && 
                _center == ((Round)obj).getCenter();
    }
}
