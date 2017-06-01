/*
 * Player.java
 *
 * Created on 15 de Novembro de 2006, 10:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package combate.model;

import java.awt.Color;

/**
 *
 * @author 
 */
public class Player {
    
    /* Player's name */
    public String name;
    
    /* Player's color */
    public Color color;
    
    
    /** Creates a new instance of Player */
    public Player() {
        this("Player",null);
    }
    
    /** Creates a new instance of Player */
    public Player( String name,Color color) {
        this.setName(name);
        this.setColor(color);
    }


    public Color getColor() {
        return color;
    }
    
    public String getName() {
        return name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
