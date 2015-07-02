/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;

/**
 *
 * @author Parra
 */
public class Datos implements Serializable {
    
    private Integer level;
    private Integer x;
    private Integer y;

    public Datos(int level_, int x_, int y_) {
        level = level_;
        x = x_;
        y = y_;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

