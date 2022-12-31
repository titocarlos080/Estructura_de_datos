/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.arbol;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicGraphicsUtils;

/**
 *
 * @author hp
 */
public class lienzo extends JPanel {

    private ArbolBinarioBusqueda arbol;
    public static final int DIAMETRO = 30;
    public static final int RADIO = DIAMETRO / 2;
    public static final int ANCHO = 30;

    public void setArbol(ArbolBinarioBusqueda arbol) {
        this.arbol = arbol;
        repaint();

    }

    public void paint(Graphics g) {
        super.paint(g);
        pintar(g, getWidth() / 2, 20, arbol.raiz);

    }
   
    public void pintar(Graphics g, int x, int y, NodoBinario nodoActual) {
            if (!NodoBinario.esNodoVacio(nodoActual)) {
                
            int EXTRA =  NodoBinario.nodosCompletos(nodoActual)*(ANCHO / 2);
                  
             g.drawOval(x, y, DIAMETRO, DIAMETRO);
              g.drawString(nodoActual.getClave().toString(), x+RADIO , y+RADIO );
            if (!nodoActual.esHijoIzquierdoVacio()) {
                g.drawLine(x+RADIO, y+RADIO, x-ANCHO-EXTRA+RADIO, y+ANCHO+RADIO);
                

            }
            if (!nodoActual.esHijoDerechoVacio()) {
                g.drawLine(x+RADIO, y+RADIO, x+ANCHO+EXTRA+RADIO, y+ANCHO+RADIO);
                   
            }
            pintar(g, x-ANCHO-EXTRA, y+ANCHO, nodoActual.getHijoIzquierdo());
            pintar(g, x+ANCHO+EXTRA, y+ANCHO, nodoActual.getHijoDerecho());

        }

       
        }
    

}
