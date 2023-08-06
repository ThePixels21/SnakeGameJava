/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author Pixels
 */
public class Juego implements KeyListener{
    public static final int ANCHO = 30;
    public static final int ALTO = 30;
    public static final int DIMENSION = 20;
    
    private Snake snake;
    private Comida comida;
    
    private Graficos graficos;
    private JFrame ventana;
    
    public Juego(){
        this.snake = new Snake();
        this.comida = new Comida(snake);
        this.graficos = new Graficos(this);
        ventana = new JFrame();
        graficos.setPreferredSize(new Dimension(ANCHO * DIMENSION, ALTO * DIMENSION));
        ventana.add(graficos);
        
        ventana.setTitle("Snake");
        ventana.pack();
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Finalizar el proceso al presionar la X
        ventana.setLocationRelativeTo(null);
    }
    
    public void start(){
        graficos.estado = "CORRIENDO";
        graficos.getScore().setVisible(true);
    }
    
    public void restart(){
        this.snake.reset();
        graficos.estado = "CORRIENDO";
        graficos.getScore().setText("Puntaje: 0");
        graficos.getScore().setVisible(true);
    }
    
    public void actualizar(){
        if(graficos.estado.equals("CORRIENDO")){
            if(revisarColisionComida()){
                snake.crecer();
                comida.randomSpawn(snake);
                graficos.getScore().setText("Puntaje: " + (snake.getCuerpo().size()-3));
            }else if(revisarColisionPared() || revisarColisionCuerpo()){
                graficos.estado = "FINAL";
                graficos.getScore().setVisible(false);
            }else {
                snake.moverse();
            }
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int codigoTecla = e.getKeyCode();
        if (graficos.estado.equals("CORRIENDO")) {
            switch (codigoTecla) {
                case KeyEvent.VK_W:
                    snake.arriba();
                    break;
                case KeyEvent.VK_S:
                    snake.abajo();
                    break;
                case KeyEvent.VK_A:
                    snake.izquierda();
                    break;
                case KeyEvent.VK_D:
                    snake.derecha();
                    break;
                case KeyEvent.VK_ENTER:
                    if(graficos.getTiempo().isRunning()){
                        graficos.getTiempo().stop();
                    }else{
                        graficos.getTiempo().start();
                    }
                    break;
            }
        }else if(graficos.estado.equals("FINAL")){
            this.restart();
        }else{
            this.start();
        }
    }
    
    private boolean revisarColisionPared(){
        if(snake.getXCabeza() < 0 || snake.getXCabeza() >= (ANCHO * DIMENSION) 
                || snake.getYCabeza() < 0 || snake.getYCabeza() >= (ALTO * DIMENSION)){
            return true;
        }
        return false;
    }
    
    private boolean revisarColisionComida(){
        if(snake.getXCabeza() == comida.getX() && snake.getYCabeza() == comida.getY()){
            return true;
        }
        return false;
    }
    
    private boolean revisarColisionCuerpo(){
        for (int i = 1; i < snake.getCuerpo().size(); i++) {
            if(snake.getXCabeza() == snake.getCuerpo().get(i).x
                    && snake.getYCabeza() == snake.getCuerpo().get(i).y){
                return true;
            }
        }
        return false;
    }
    
    public Snake getSnake() {
        return snake;
    }

    public Comida getComida() {
        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
