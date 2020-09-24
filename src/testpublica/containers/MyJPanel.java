/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpublica.containers;

import java.awt.Component;
import javax.swing.JPanel;
import testpublica.data.Jogo;
import testpublica.data.Jogos;

/**
 *
 * @author Marcelo
 */
public class MyJPanel extends JPanel {
    public MyJPanel() {

    }

    public Component getComponentByName(String name) {
        int compCont = this.getComponentCount();
        String compName = "";
        for (int cont = 0; cont < compCont; cont++) {
            compName = this.getComponent(cont).getName();
            if (compName.equals(name)) {
                return this.getComponent(cont);
            }
        }
        String str = "Componente não encontrado";
        throw new IllegalArgumentException(str);
    }

}
