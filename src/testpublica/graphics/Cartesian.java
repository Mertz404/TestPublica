/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpublica.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import testpublica.containers.MyJPanel;
import testpublica.data.Jogos;
import testpublica.util.trace;

/**
 *
 * @author Marcelo
 */
public class Cartesian extends MyJPanel{
    Graphics2D g2d;
    Jogos jogos;
    JScrollPane scrollPane;
    public Cartesian (Jogos jogos, Dimension dim){
        this.jogos = jogos;
        this.setSize(dim);
        g2d = (Graphics2D) this.getGraphics();
        new trace ("Cartesian created");
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, getHeight()-5, getWidth()-5, getHeight()-5);
        g2d.drawLine(5, 0, 5, getHeight());
        int posY, posX, max = 0, min = 1000;
        for (int cont = 0; cont <jogos.lenght; cont++){
            posX = 5+(jogos.getJogo(cont).jogo*6);
            posY = (getHeight() - 10)-(jogos.getJogo(cont).placar);
            g2d.drawOval(posX-2, posY-2, 4, 4);
            if (max > this.getHeight()-10){
                new trace("O valor " + jogos.getJogo(cont).placar + " excedeu os limites do gráfico - adicionar barra de rolagem vertical");
            }
            if (5+jogos.getJogo(cont).jogo*6 > this.getWidth()){
                new trace("A quantidade de Jogos cadastrados (" + jogos.getJogo(cont).jogo + ") excedeu os limites do gráfico - adicionar barra de rolagem horizontal");
            }
            if (max < jogos.getJogo(cont).placar){
                max = jogos.getJogo(cont).placar;
            }
            if (min > jogos.getJogo(cont).placar){
                min = jogos.getJogo(cont).placar;
            }
        }
        //g2d.draw(s);
        g2d.setColor(Color.red);
        g2d.drawLine(0, (getHeight() - 10)-min, this.getWidth(), (getHeight() - 10)-min);
        g2d.setColor(Color.blue);
        g2d.drawLine(0, (getHeight() - 10)-max, this.getWidth(), (getHeight() - 10)-max);
        
    }
    
    public void drawNextPoint(Jogos jgs){
        repaint();
    }
    

}
