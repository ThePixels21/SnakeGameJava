/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Pixels
 */
public class Snake {

    private ArrayList<Rectangle> cuerpo;

    private int ancho = Juego.ANCHO;
    private int alto = Juego.ALTO;
    private int dimension = Juego.DIMENSION;

    private String movimiento; //NO, ARRIBA, ABAJO, IZQUIERDA, DERECHA

    public Snake() {
        cuerpo = new ArrayList();
        initCuerpo();
        movimiento = "NO";
    }

    //Inicializar el cuerpo de la snake
    private void initCuerpo() {
        Rectangle temp = new Rectangle(dimension, dimension);
        temp.setLocation((ancho / 2) * dimension, (alto / 2) * dimension);
        cuerpo.add(temp);

        temp = new Rectangle(dimension, dimension);
        temp.setLocation(((ancho / 2) - 1) * dimension, (alto / 2) * dimension);
        cuerpo.add(temp);

        temp = new Rectangle(dimension, dimension);
        temp.setLocation(((ancho / 2) - 2) * dimension, (alto / 2) * dimension);
        cuerpo.add(temp);
        
    }
    
    public void reset(){
        cuerpo.clear();
        initCuerpo();
        this.movimiento = "NO";
    }

    public void moverse() {
        if (!movimiento.equals("NO")) {
            Rectangle cabeza = cuerpo.get(0);
            Rectangle temp = new Rectangle(dimension, dimension);
            switch (movimiento) {
                case "ARRIBA":
                    temp.setLocation(cabeza.x, cabeza.y - dimension);
                    break;
                case "ABAJO":
                    temp.setLocation(cabeza.x, cabeza.y + dimension);
                    break;
                case "IZQUIERDA":
                    temp.setLocation(cabeza.x - dimension, cabeza.y);
                    break;
                case "DERECHA":
                    temp.setLocation(cabeza.x + dimension, cabeza.y);
                    break;
            }
            cuerpo.add(0, temp);
            cuerpo.remove(cuerpo.size() - 1);
        }
    }

    public void crecer() {
        Rectangle cabeza = cuerpo.get(0);
        Rectangle temp = new Rectangle(dimension, dimension);
        switch (movimiento) {
            case "ARRIBA":
                temp.setLocation(cabeza.x, cabeza.y - dimension);
                break;
            case "ABAJO":
                temp.setLocation(cabeza.x, cabeza.y + dimension);
                break;
            case "IZQUIERDA":
                temp.setLocation(cabeza.x - dimension, cabeza.y);
                break;
            case "DERECHA":
                temp.setLocation(cabeza.x + dimension, cabeza.y);
                break;
        }
        cuerpo.add(0, temp);
    }

    //Movimientos
    public void arriba() {
        if (!movimiento.equals("ABAJO")) {
            this.movimiento = "ARRIBA";
        }
    }

    public void abajo() {
        if (!movimiento.equals("ARRIBA")) {
            this.movimiento = "ABAJO";
        }
    }

    public void izquierda() {
        if (!movimiento.equals("DERECHA") && !movimiento.equals("NO")) {
            this.movimiento = "IZQUIERDA";
        }
    }

    public void derecha() {
        if (!movimiento.equals("IZQUIERDA")) {
            this.movimiento = "DERECHA";
        }
    }
    
    public int getXCabeza(){
        return cuerpo.get(0).x;
    }
    
    public int getYCabeza(){
        return cuerpo.get(0).y;
    }

    public ArrayList<Rectangle> getCuerpo() {
        return cuerpo;
    }
    
}
