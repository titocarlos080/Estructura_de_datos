/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.arbol;

import java.util.Stack;

/**
 *
 * @author hp
 */
public class ArbolBinarioNoGenerico extends ArbolBinarioBusqueda<Integer, String> {

    public int sumaPares() {
        int suma = 0;
        Stack<NodoBinario<Integer, String>> pilaDeNodos = new Stack<>();
        pilaDeNodos.add(this.raiz);
        while (!pilaDeNodos.isEmpty()) {
            NodoBinario<Integer, String> nodoActual = pilaDeNodos.pop();
            int clave = nodoActual.getClave();
            if (clave % 2 == 0) {
                suma += clave;

            }
            if (!nodoActual.esHijoDerechoVacio()) {
                pilaDeNodos.push(nodoActual.getHijoDerecho());
            }
            if (!nodoActual.esHijoIzquierdoVacio()) {
                pilaDeNodos.push(nodoActual.getHijoIzquierdo());
            }
        }

        return suma;
    }
    
    public int sumaParesR(){
    return sumaParesR(this.raiz);
    }
    private int sumaParesR(NodoBinario<Integer,String> nodoActual){
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        } 
        int sumaParesDerecho=sumaParesR(nodoActual.getHijoDerecho());
        int sumaParesIzquierdo=sumaParesR(nodoActual.getHijoIzquierdo());
        if ((nodoActual.getClave()%2)==0) {
            return nodoActual.getClave()+sumaParesDerecho+sumaParesIzquierdo;
        }else{
        return sumaParesDerecho+sumaParesIzquierdo; 
        }
        
    }
        public int sumaImParesR(){
    return sumaImParesR(this.raiz);
    }
    private int sumaImParesR(NodoBinario<Integer,String> nodoActual){
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        } 
        int sumaParesDerecho=sumaImParesR(nodoActual.getHijoDerecho());
        int sumaParesIzquierdo=sumaImParesR(nodoActual.getHijoIzquierdo());
        if ((nodoActual.getClave()%2)!=0) {
            return nodoActual.getClave()+sumaParesDerecho+sumaParesIzquierdo;
        }else{
        return sumaParesDerecho+sumaParesIzquierdo; 
        }
        
    }

}
