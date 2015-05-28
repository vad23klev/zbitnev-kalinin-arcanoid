/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interaction;

/**
 *
 * @author vadim
 */
public interface CollisionEventListener {
    /**
     * Произошла коллизия между игровыми объектами.
     * @param storage Ключ -- столкнувшийся объект, Значение -- список объектов, с которыми он
     * столкнулся.
    */
    void collisionOccured();
    
}
