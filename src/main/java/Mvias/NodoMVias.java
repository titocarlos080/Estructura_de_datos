/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mvias;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author hp
 */
public class NodoMVias<K, V> {

    private List<K> listaDeClaves;
    private List<V> listaDeValores;
    private List<NodoMVias<K, V>> listaDeHijos;

    public NodoMVias(int Orden) {
        listaDeHijos = new LinkedList<>();
        listaDeClaves = new LinkedList<>();
        listaDeValores = new LinkedList<>();
        for (int i = 0; i < Orden - 1; i++) {
            listaDeHijos.add(NodoMVias.nodoVacio());
            listaDeClaves.add((K) NodoMVias.datoVacio());
            listaDeValores.add((V) NodoMVias.datoVacio());

        }
        listaDeHijos.add(NodoMVias.nodoVacio());
    }

    public NodoMVias(int orden, K primerClave, V primerValor) {
        this(orden);
        this.listaDeClaves.set(0, primerClave);
        this.listaDeValores.set(0, primerValor);

    }

    public static NodoMVias nodoVacio() {
        return null;
    }

    public static Object datoVacio() {
        return null;
    }

    public void setClave(int posicicion, K clave) {
        this.listaDeClaves.set(posicicion, clave);
    }

    public K getClave(int posicicion) {
        return this.listaDeClaves.get(posicicion);
    }

    public V getValor(int posicicion) {
        return this.listaDeValores.get(posicicion);
    }

    public void setValor(int posicicion, V valor) {
        this.listaDeValores.set(posicicion, valor);
    }

    public void setHijo(int posicion, NodoMVias<K, V> nodo) {
        this.listaDeHijos.set(posicion, nodo);
    }

    public NodoMVias<K, V> getHijo(int posicion) {
        return this.listaDeHijos.get(posicion);
    }

    public static boolean esNodoVacio(NodoMVias nodo) {
        return nodo == NodoMVias.nodoVacio();
    }

    public boolean esClaveVacia(int posicion) {
        return this.listaDeClaves.get(posicion) == NodoMVias.datoVacio();
    }

    public boolean esValorVacio(int posicion) {
        return this.listaDeValores.get(posicion) == NodoMVias.datoVacio();
    }

    public boolean esHijoVacio(int posicion) {
        return this.listaDeHijos.get(posicion) == NodoMVias.nodoVacio();
    }

    public boolean esHoja() {
        for (int i = 0; i < this.listaDeHijos.size(); i++) {
            if (!this.esHijoVacio(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean estanClavesLLenas() {
        for (int i = 0; i < this.listaDeClaves.size(); i++) {
            if (this.esClaveVacia(i)) {
                return false;
            }
        }
        return true;
    }

    public int cantidadClavesNoVacias() {
        int cantidad = 0;
        for (int i = 0; i < this.listaDeClaves.size(); i++) {
            if (!this.esClaveVacia(i)) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public boolean estanDatosLlenos(){
        for (K listaDeClave : listaDeClaves) {
            if (listaDeClave==NodoMVias.datoVacio()) {
                return false;
            }
        }
        return true;
    }
    public int cantidadHijosVacias() {
        int cantidad = 0;
        for (int i = 0; i < this.listaDeHijos.size(); i++) {
            if (this.esClaveVacia(i)) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public int cantidadHijosNoVacias() {
        int cantidad = 0;
        for (int i = 0; i < this.listaDeHijos.size(); i++) {
            if (!this.esClaveVacia(i)) {
                cantidad++;
            }
        }
        return cantidad;
    }
   //hay claves no vacias
    //  return nroClavesNoVacios!=0
}
 