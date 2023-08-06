/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;

import java.awt.Rectangle;

/**
 *
 * @author Pixels
 */
public class Comida {
    
    private int x;
    private int y;
    
    public Comida(Snake snake){
        this.randomSpawn(snake);
    }
    
    public void randomSpawn(Snake snake){
        boolean enSnake;
        do {      
            enSnake = false;
            this.x = (int) (Math.random() * Juego.ANCHO) * Juego.DIMENSION;
            this.y = (int) (Math.random() * Juego.ALTO) * Juego.DIMENSION;
            for (Rectangle rectangle : snake.getCuerpo()) {
                if(rectangle.x == x && rectangle.y == y){
                    enSnake = true;
                    break;
                }
            }
        } while (enSnake);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}
