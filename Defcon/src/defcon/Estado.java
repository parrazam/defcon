/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package defcon;

import java.awt.Point;
import javax.swing.JLabel;

/**
 *
 * @author Parra
 */
public class Estado{

    private Integer estadoActual;
    private JLabel indicador;

    public Estado(JLabel ind, Integer est) {
        indicador = ind;
        estadoActual = est;
        setEstado(estadoActual);
    }

    public void setEstado(Integer num) {
        estadoActual = num;
        switch (num) {
            case 5:
                indicador.setLocation(10, 60);
                break;
            case 4:
                indicador.setLocation(10, 130);
                break;
            case 3:
                indicador.setLocation(10, 200);
                break;
            case 2:
                indicador.setLocation(10, 270);
                break;
            case 1:
                indicador.setLocation(10, 340);
                break;
            default:
                break;
        }
    }

    public Point getPosicionEstado() {
        return indicador.getLocation();
    }

    public int getEstado() {
        return estadoActual;
    }

    public void setLocation(int x, int y) {
        int pos = 346 / 5;
        y = y / pos;
        switch (y) {
            case 0:
                setEstado(5);
                break;
            case 1:
                setEstado(4);
                break;
            case 2:
                setEstado(3);
                break;
            case 3:
                setEstado(2);
                break;
            case 4:
                setEstado(1);
                break;
        }
    }
}
