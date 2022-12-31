/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mvias;

import bo.edu.uagrm.ficct.arbol.ExcepcionOrdenInvalido;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
/**
 *  
*/
public class ArbolMVias<K extends Comparable<K>, V> implements IArbolMVias<K, V> {

    protected NodoMVias<K, V> raiz;
    protected int orden;
    private static final int ORDEN_MINIMO = 3;

    public ArbolMVias(int orden) throws ExcepcionOrdenInvalido {
        if (orden < ORDEN_MINIMO) {
            throw new ExcepcionOrdenInvalido();
        }
        this.orden = orden;
    }

    public ArbolMVias() {
        this.orden = ORDEN_MINIMO;
    }

    @Override
    public void insertar(K clave, V valor) {

        if (clave == NodoMVias.datoVacio()) {
            System.err.println("Error: Nose permite claves nulas.");
            return;
        }
        if (valor == NodoMVias.datoVacio()) {
            System.err.println("Error: Nose permite valores nulas.");
            return;
        }
        if (NodoMVias.esNodoVacio(this.raiz)) {
            NodoMVias<K, V> nuevoNodo = new NodoMVias<>(this.orden, clave, valor);
            nuevoNodo.setClave(0, clave);
            nuevoNodo.setValor(0, valor);
        } else {
            NodoMVias nodoActual = this.raiz;
            while (!NodoMVias.esNodoVacio(nodoActual)) {
                int posicionClave = existeClaveEnNodo(nodoActual, clave);
                if (posicionClave != -1) {
                    nodoActual.setValor(posicionClave, valor);
                    return;
                } else {
                    if (nodoActual.esHoja()) {
                        if (nodoActual.estanClavesLLenas()) {
                            int posicionPorDondeBajar = this.porDondeBajar(nodoActual, clave);
                            NodoMVias<K, V> nuevoNodo = new NodoMVias<>(this.orden, clave, valor);
                            nodoActual.setHijo(posicionPorDondeBajar, nuevoNodo);
                            nodoActual = NodoMVias.nodoVacio();
                        } else {
                            this.insertarDatosOrdenadosEnNodo(nodoActual, clave, valor);
                            nodoActual = NodoMVias.nodoVacio();

                        }
                    } else {
                        //Sino es hoja buscamos por donde bajar
                        int posicionPorDondeBajar = this.porDondeBajar(nodoActual, clave);
                        if (nodoActual.esHijoVacio(posicionPorDondeBajar)) {
                            NodoMVias<K, V> nuevoNodo = new NodoMVias<>(this.orden, clave, valor);
                            nodoActual.setHijo(posicionPorDondeBajar, nuevoNodo);
                            nodoActual = NodoMVias.nodoVacio();
                        }
                        nodoActual = nodoActual.getHijo(posicionPorDondeBajar);
                    }

                }
            }

        }

    }

    protected void insertarDatosOrdenadosEnNodo(NodoMVias nodo, K clave, V valor) {
        
        for (int i = 0; i < nodo.cantidadClavesNoVacias(); i++) {
            K claveturno = (K) nodo.getClave(i);
            if (clave.compareTo(claveturno) < 0) {
                for (int j = i; j < nodo.cantidadClavesNoVacias() + 1; j++) {
                    K auxClave = (K) nodo.getClave(j);
                    V auxValor = (V) nodo.getValor(j);
                    nodo.setClave(j, clave);
                    nodo.setValor(j, valor);

                    clave = auxClave;
                    valor = auxValor;
                }
                return;
                //hay que desplazar
            }

        }
        nodo.setClave(nodo.cantidadClavesNoVacias() + 1, clave);
        nodo.setValor(nodo.cantidadClavesNoVacias() + 1, valor);

    }

    protected int porDondeBajar(NodoMVias nodo, K clave) {
        for (int i = 0; i < nodo.cantidadClavesNoVacias(); i++) {
          K claveTurno=(K) nodo.getClave(i);
            if (clave.compareTo(claveTurno)<0) {
                return i;
            }
        }
        return nodo.cantidadClavesNoVacias();
    }

    protected int existeClaveEnNodo(NodoMVias nodo, K claveBuscar) {
        for (int i = 0; i < nodo.cantidadClavesNoVacias(); i++) {
            if (claveBuscar.compareTo((K) nodo.getClave(i)) == 0) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean esVacio() {
        return NodoMVias.esNodoVacio(this.raiz);
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int nivel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int altura() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void vaciar() {
        this.raiz = NodoMVias.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean contiene(K claveABuscar) {
        return this.buscar(claveABuscar) != NodoMVias.datoVacio();
    }

    @Override
    public V buscar(K clave) {
        V valor = (V) NodoMVias.datoVacio();
        if (!this.esArbolVacio()) {
            Stack<NodoMVias<K, V>> pilaDeNodos = new Stack<>();
            pilaDeNodos.push(this.raiz);
            while (!pilaDeNodos.isEmpty()) {
                boolean CambioDeNodo = false;
                NodoMVias<K, V> nodoActual = pilaDeNodos.pop();
                for (int i = 0; i < nodoActual.cantidadClavesNoVacias(); i++) {
                    K claveActual = nodoActual.getClave(i);
                    if (clave.compareTo(claveActual) == 0) {
                        return nodoActual.getValor(i);
                    }
                    if (clave.compareTo(claveActual) < 0) {
                        nodoActual = nodoActual.getHijo(i);
                        CambioDeNodo = true;
                    }
                }
                if (!CambioDeNodo) {
                    nodoActual = nodoActual.getHijo(nodoActual.cantidadClavesNoVacias());
                }
            }
        }
        return valor;
    }
//     
//
//

    @Override
    public void eliminar(K claveAEliminar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<K> recorridoInOrden() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<K> recorridoPostOrden() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<K> recorridoPreOrden() {
        List<K> recorrido = new ArrayList<>();
        if (!this.esArbolVacio()) {
            Stack<NodoMVias<K, V>> pilaDeNodos = new Stack<>();
            pilaDeNodos.push(this.raiz);
            while (!pilaDeNodos.isEmpty()) {
                NodoMVias<K, V> nodoActual = pilaDeNodos.pop();

                if (!nodoActual.esHijoVacio(nodoActual.cantidadClavesNoVacias())) {
                    pilaDeNodos.push(nodoActual.getHijo(nodoActual.cantidadClavesNoVacias()));
                }

                for (int i = 0; i < nodoActual.cantidadClavesNoVacias(); i++) {
                    recorrido.add(nodoActual.getClave(i));
                    if (!nodoActual.esHijoVacio(i)) {
                        pilaDeNodos.push(nodoActual.getHijo(i));
                    }
                }

            }
        }
        return recorrido;
    }


@Override
public List<K> recorridoPorNiveles() {
        List<K> recorrido = new ArrayList<>();
        if (!this.esArbolVacio()) {
            Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while (!colaDeNodos.isEmpty()) {
                NodoMVias<K, V> nodoActual = colaDeNodos.poll();
                 
                for (int i = 0; i < nodoActual.cantidadClavesNoVacias(); i++) {
                    recorrido.add(nodoActual.getClave(i));
                    if (!nodoActual.esHijoVacio(i)) {
                        colaDeNodos.offer(nodoActual.getHijo(i));
                    }
                }

                if (!nodoActual.esHijoVacio(nodoActual.cantidadClavesNoVacias())) {
                    colaDeNodos.offer(nodoActual.getHijo(nodoActual.cantidadClavesNoVacias()));
                }

            }
        }
        return recorrido;
    }

}
