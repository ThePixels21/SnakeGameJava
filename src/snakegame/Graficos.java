/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Pixels
 */
public class Graficos extends JPanel implements ActionListener{

    private Timer tiempo = new Timer(100, this);
    public String estado; //INICIO, CORRIENDO, FINAL
    
    private Juego juego;
    private Snake snake;
    private Comida comida;
    
    private JLabel score;
    
    public Graficos(Juego juego){
        tiempo.start();
        estado = "INICIO";
        this.juego = juego;
        this.snake = juego.getSnake();
        this.comida = juego.getComida();
        this.score = new JLabel();
        this.score.setText("Puntaje: 0");
        this.score.setForeground(Color.gray);
        this.score.setBounds(0, 0, 40, 20);
        this.score.setVisible(false);
        this.add(score);
        
        this.addKeyListener(juego); //Añadir keyListener al juego
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }
    
    //Pintar componente
    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        //Pintar fondo
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, Juego.ANCHO * Juego.DIMENSION, Juego.ALTO * Juego.DIMENSION); //Pintar rectángulo en el fondo

        if (estado.equals("INICIO")) {
            g2d.setColor(Color.white);
            g2d.drawString("Presiona una tecla", ((Juego.ANCHO * Juego.DIMENSION)/2) -40, ((Juego.ALTO * Juego.DIMENSION)/2)-20);
        } else if (estado.equals("CORRIENDO")) {
            //Pintar comida
            g2d.setColor(Color.red);
            g2d.fillOval(comida.getX(), comida.getY(), Juego.DIMENSION, Juego.DIMENSION);

            //Pintar snake
            g2d.setColor(new Color(0, 192, 7));
            g2d.fill(snake.getCuerpo().get(0)); //Cabeza
            g2d.setColor(Color.green);
            for (int i = 1; i < snake.getCuerpo().size(); i++) {
                g2d.fill(snake.getCuerpo().get(i));
            }
        } else if(estado.equals("FINAL")){
            g2d.setColor(Color.white);
            g2d.drawString("Tu puntaje: " + (snake.getCuerpo().size() - 3), ((Juego.ANCHO * Juego.DIMENSION)/2) -40, ((Juego.ALTO * Juego.DIMENSION)/2)-20);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        juego.actualizar();
    }

    public JLabel getScore() {
        return score;
    }

    public Timer getTiempo() {
        return tiempo;
    }
    
}
