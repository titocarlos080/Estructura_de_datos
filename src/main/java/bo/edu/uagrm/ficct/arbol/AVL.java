/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.arbol;

/**
 *
 * @author hp
 */
public class AVL<K extends Comparable<K>, V> extends ArbolBinarioBusqueda<K, V> {

    private static final byte LIMITE_MAX = 1;

    public void insertar(K claveAInsertar, V valorAsociado) {
        if (claveAInsertar == null) {
            throw new IllegalArgumentException("No se permite clave nulas");
        }
        if (valorAsociado == null) {
            throw new IllegalArgumentException("No se permitev valores nulos");
        }
        this.raiz = insertar(this.raiz, claveAInsertar, valorAsociado);

    }

    public NodoBinario<K, V> insertar(NodoBinario<K, V> nodoActual, K claveAInsertar, V valorasociado) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            NodoBinario<K, V> nuevoNodo = new NodoBinario<>(claveAInsertar, valorasociado);
            return nuevoNodo;
        }
        K claveActual = nodoActual.getClave();
        if (claveAInsertar.compareTo(claveActual) < 0) {
            NodoBinario<K, V> supuestoNuevoHijoIzq = insertar(nodoActual.getHijoIzquierdo(), claveAInsertar, valorasociado);
            nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzq);
            return valancear(nodoActual);
        }
        if (claveAInsertar.compareTo(claveActual) > 0) {

            NodoBinario<K, V> supuestoNuevoHijoIzq = insertar(nodoActual.getHijoDerecho(), claveAInsertar, valorasociado);
            nodoActual.setHijoDerecho(supuestoNuevoHijoIzq);
            return valancear(nodoActual);
        }
        nodoActual.setClave(claveActual);
        nodoActual.setValor(valorasociado);
        return nodoActual;
    }

    public NodoBinario<K, V> valancear(NodoBinario<K, V> NodoProblematico) {
        int alturaDeHijoIzquierdo = altura(NodoProblematico.getHijoIzquierdo());
        int alturaDeHijoDerecho = altura(NodoProblematico.getHijoDerecho());
        int diferencia = alturaDeHijoIzquierdo - alturaDeHijoDerecho;

        if (diferencia > LIMITE_MAX) {
            //quiere decir que hijo izquierdo es mas largo
            NodoBinario<K, V> hijoIzqDelNodoProblematico = NodoProblematico.getHijoIzquierdo();
            alturaDeHijoIzquierdo = altura(hijoIzqDelNodoProblematico.getHijoIzquierdo());
            alturaDeHijoDerecho = altura(hijoIzqDelNodoProblematico.getHijoDerecho());
            if (alturaDeHijoDerecho > alturaDeHijoIzquierdo) {
                return rotacionDobleDerecha(NodoProblematico);
            }
            return rotacionSimpleDerecha(NodoProblematico);
        } else {
            if (diferencia > -LIMITE_MAX) {
                //quiere decir que hijo Derecho es mas largo
                NodoBinario<K, V> hijoDerDelNodoProblematico = NodoProblematico.getHijoDerecho();
                alturaDeHijoIzquierdo = altura(hijoDerDelNodoProblematico.getHijoIzquierdo());
                alturaDeHijoDerecho = altura(hijoDerDelNodoProblematico.getHijoDerecho());
                if (alturaDeHijoDerecho > alturaDeHijoIzquierdo) {
                    return rotacionDobleIzquierda(NodoProblematico);
                }
                return rotacionSimpleIzquierda(NodoProblematico);
            }

        }

        return NodoProblematico;
    }

    private NodoBinario<K, V> rotacionSimpleDerecha(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoQueRota = nodoActual.getHijoIzquierdo();
        nodoActual.setHijoIzquierdo(nodoQueRota.getHijoDerecho());
        nodoQueRota.setHijoDerecho(nodoActual);
        return nodoQueRota;
    }

    private NodoBinario<K, V> rotacionDobleDerecha(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoQueRota = rotacionSimpleIzquierda(nodoActual.getHijoIzquierdo());
        nodoActual.setHijoIzquierdo(nodoQueRota);
        return rotacionSimpleDerecha(nodoActual);
    }

    private NodoBinario<K, V> rotacionSimpleIzquierda(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoQueRota = nodoActual.getHijoDerecho();
        nodoActual.setHijoDerecho(nodoQueRota.getHijoIzquierdo());
        nodoQueRota.setHijoIzquierdo(nodoActual);
        return nodoQueRota;
    }

    private NodoBinario<K, V> rotacionDobleIzquierda(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoQueRota = rotacionSimpleDerecha(nodoActual.getHijoDerecho());
        nodoActual.setHijoDerecho(nodoQueRota);
        return rotacionSimpleIzquierda(nodoActual);
    }
    
    public void Eliminar(K claveAEliminar){
     }

}
