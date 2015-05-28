package model;

import java.awt.Dimension;
import java.awt.geom.Point2D;

/**
 * Квадратная форма.
 * @author Tenk0Kugen
 */
public class Rectangle extends Form{
    
    private Dimension _size;
    private Point2D.Double _coord;
    
    public Rectangle(Point2D.Double coord, Dimension size){
        
        _coord = coord;
        _size = size;
    }
    
    /**
     * Возвращает размер объекта в пикселях.
     * @return Dimension
     */
    public Dimension getSize(){

        return _size;
    }
    
    /**
     * Задает размер объекта в пикселях.
     * @param dim
     */
    public void setSize(Dimension dim) {

        if (dim == null) {
            throw new NullPointerException();
        }
        _size = dim;
    }
    
    public double getHeight(){

        return _size.getHeight();
    }
    
    public double getWidth(){

        return _size.getWidth();
    }
    
    public Point2D.Double getCoordinates(){
        
        return _coord;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        return _size.width == ((Rectangle)obj).getSize().width && 
                _size.height == ((Rectangle)obj).getSize().height && 
                _coord == ((Rectangle)obj).getCoordinates();
    }
}
