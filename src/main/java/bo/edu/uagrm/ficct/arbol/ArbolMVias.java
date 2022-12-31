/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.arbol;

import bo.edu.uagrm.ficct.arbol.ExcepcionOrdenInvalido;

import java.security.SignatureException;
import java.text.spi.NumberFormatProvider;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
// import java.util.logging.Level;
// import java.util.logging.Logger;

/**
 *
 * @author hp
 * @param <K>
 * @param <V>
 */
public class ArbolMVias<K extends Comparable<K>, V> implements IArbolBusqueda<K, V> {

    protected NodoMVias<K, V> raiz;
    protected int orden;
    private static final int ORDEN_MINIMO = 3;
    private static final int POSICION_INAVALIDO = -1;

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
            this.raiz = nuevoNodo;

        } else {

            NodoMVias<K, V> nodoActual = this.raiz;
            while (!NodoMVias.esNodoVacio(nodoActual)) {
                int posicionClave = existeClaveEnNodo(nodoActual, clave);
                if (posicionClave != POSICION_INAVALIDO) {
                    nodoActual.setValor(posicionClave, valor);
                    nodoActual.setClave(posicionClave, clave);
                    return;
                } else {
                    if (nodoActual.esHoja()) {// Puede que sea hoja
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
                        // Sino es hoja buscamos por donde bajar
                        int posicionPorDondeBajar = this.porDondeBajar(nodoActual, clave);
                        if (nodoActual.esHijoVacio(posicionPorDondeBajar)) {
                            NodoMVias<K, V> nuevoNodo = new NodoMVias<>(this.orden, clave, valor);
                            nodoActual.setHijo(posicionPorDondeBajar, nuevoNodo);
                            nodoActual = NodoMVias.nodoVacio();
                        } else {
                            nodoActual = nodoActual.getHijo(posicionPorDondeBajar);

                        }
                    }

                }
            }

        }

    }

    protected void insertarDatosOrdenadosEnNodo(NodoMVias<K, V> nodoActual, K clave, V valor) {

        int j = 0;
        while (j < nodoActual.cantidadClavesNoVacias()) {
            K claveTurno = (K) nodoActual.getClave(j);
            if (clave.compareTo(claveTurno) < 0) {

                break;
            }
            j++;
        }

        for (int i = nodoActual.cantidadClavesNoVacias(); i > j; i--) {

            K claveTurno = (K) nodoActual.getClave(i - 1);
            V valorTurno = (V) nodoActual.getValor(i - 1);
            nodoActual.setClave(i, claveTurno);
            nodoActual.setValor(i, valorTurno);
        }
        nodoActual.setClave(j, clave);
        nodoActual.setValor(j, valor);

    }

    protected int porDondeBajar(NodoMVias<K, V> nodo, K clave) {
        for (int i = 0; i < nodo.cantidadClavesNoVacias(); i++) {
            K claveTurno = (K) nodo.getClave(i);
            if (clave.compareTo(claveTurno) < 0) {
                return i;
            }
        }
        return nodo.cantidadClavesNoVacias();
    }

    protected int existeClaveEnNodo(NodoMVias<K, V> nodo, K claveBuscar) {
        for (int i = 0; i < nodo.cantidadClavesNoVacias(); i++) {
            if (claveBuscar.compareTo((K) nodo.getClave(i)) == 0) {
                return i;
            }
        }

        return POSICION_INAVALIDO;
    }

    @Override
    public V eliminar(K clave) {

        return null;
    }

    @Override
    public V buscar(K clave) {
        V valor = (V) NodoMVias.datoVacio();
        if (!this.esArbolVacio()) {
            Stack<NodoMVias<K, V>> pilaDeNodos = new Stack<>();
            pilaDeNodos.push(this.raiz);
            while (!pilaDeNodos.isEmpty()) {
                NodoMVias<K, V> nodoActual = pilaDeNodos.pop();
                boolean cambiodeNodo = false;
                for (int i = 0; i < nodoActual.cantidadClavesNoVacias(); i++) {
                    K claveActual = nodoActual.getClave(i);
                    if (clave.compareTo(claveActual) == 0) {
                        return (V) nodoActual.getClave(i);
                    }
                    if (clave.compareTo(claveActual) < 0) {
                        pilaDeNodos.push(nodoActual.getHijo(i));
                        cambiodeNodo = true;
                    }

                }
                if (!cambiodeNodo) {
                    nodoActual = nodoActual.getHijo(nodoActual.cantidadClavesNoVacias());
                }
            }
        }
        return valor;
    }

    public V buscarRecursivo(K clave) {
        return buscarRecursivo(this.raiz, clave);
    }

    private V buscarRecursivo(NodoMVias<K, V> nodoActual, K clave) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return (V) NodoMVias.datoVacio();
        }
        V valor = (V) NodoMVias.datoVacio();

        for (int i = 0; i < nodoActual.cantidadClavesNoVacias(); i++) {
            if (clave.compareTo(nodoActual.getClave(i)) == 0) {
                return nodoActual.getValor(i);

            }
            if (clave.compareTo(nodoActual.getClave(i)) < 0) {
                valor = buscarRecursivo(nodoActual.getHijo(i), clave);
            }
        }
        if (clave.compareTo(nodoActual.getClave(nodoActual.cantidadClavesNoVacias() - 1)) > 0) {
            valor = buscarRecursivo(nodoActual.getHijo(nodoActual.cantidadClavesNoVacias()), clave);
        }
        return valor;
    }

    @Override
    public boolean contiene(K clave) {
        return buscar(clave) != NodoMVias.datoVacio();
    }

    @Override
    public int size() {
        int size = 0;
        Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            NodoMVias<K, V> nodoActual = colaDeNodos.poll();
            size++;
            for (int i = 0; i < nodoActual.cantidadClavesNoVacias(); i++) {
                if (!nodoActual.esHijoVacio(i)) {
                    colaDeNodos.offer(nodoActual.getHijo(i));
                }
            }
            if (!nodoActual.esHijoVacio(nodoActual.cantidadClavesNoVacias())) {
                colaDeNodos.offer(nodoActual.getHijo(nodoActual.cantidadClavesNoVacias()));
            }
        }
        return size;
    }

    public int sizeRecursivo() {
        return sizeRecursivo(this.raiz);
    }

    private int sizeRecursivo(NodoMVias<K, V> nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        if (nodoActual.esHoja()) {
            return 1;
        }
        int size = 0;
        for (int i = 0; i < nodoActual.cantidadClavesNoVacias(); i++) {
            size += sizeRecursivo(nodoActual.getHijo(i));
        }
        if (!nodoActual.esHijoVacio(nodoActual.cantidadClavesNoVacias())) {
            size += sizeRecursivo(nodoActual.getHijo(nodoActual.cantidadClavesNoVacias()));
        }
        return size + 1;
    }

    @Override
    public int altura() {
        return nivel() + 1;
    }

    //// METODO QUE DEBUELVA LA CANTIDAD DE HIJOS VACIOS EN UN ARBOL
    protected int nroDeHijosVacios() {
        int cantidad = 0;
        Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            NodoMVias<K, V> nodoActual = colaDeNodos.poll();
            cantidad = cantidad + nodoActual.cantidadHijosVacias();
            for (int i = 0; i < nodoActual.cantidadClavesNoVacias(); i++) {
                if (!nodoActual.esHijoVacio(i)) {
                    colaDeNodos.offer(nodoActual.getHijo(i));
                }
            }
            if (!nodoActual.esHijoVacio(nodoActual.cantidadClavesNoVacias())) {
                colaDeNodos.offer(nodoActual.getHijo(nodoActual.cantidadClavesNoVacias()));
            }
        }
        return cantidad;
    }

    @Override
    public void vaciar() {
        this.raiz = NodoMVias.nodoVacio();
    }

    @Override
    public int alturaRecursivo() {
        return alturaRecursivo(this.raiz);
    }

    private int alturaRecursivo(NodoMVias<K, V> nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        if (nodoActual.esHoja()) {
            return 1;
        }
        int alturaMayor = 0;
        for (int i = 0; i < orden; i++) {
            int alturaActual = alturaRecursivo(nodoActual.getHijo(i));
            if (alturaActual > alturaMayor) {
                alturaMayor = alturaActual;
            }
        }

        return alturaMayor + 1;
    }

    @Override
    public boolean esArbolVacio() {
        return this.raiz == NodoMVias.nodoVacio();
    }

    @Override
    public int nivel() {
        int nivel = -1;
        Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            NodoMVias<K, V> nodoActual = colaDeNodos.poll();
            int longitudDeColaActual = colaDeNodos.size();
            int pos = 0;
            while (pos < longitudDeColaActual) {
                for (int i = 0; i < nodoActual.cantidadClavesNoVacias(); i++) {
                    if (!nodoActual.esHijoVacio(i)) {
                        colaDeNodos.offer(nodoActual.getHijo(i));
                    }
                }
                if (!nodoActual.esHijoVacio(nodoActual.cantidadClavesNoVacias())) {
                    colaDeNodos.offer(nodoActual.getHijo(nodoActual.cantidadClavesNoVacias()));
                }
                pos++;
            }
            nivel++;
        }
        return nivel;
    }
    // 4,5,6,7

    @Override
    public List<K> recorridoEnInOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnInOrden(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnInOrden(NodoMVias<K, V> nodoActual, List<K> recorrido) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }
        for (int i = 0; i < nodoActual.cantidadClavesNoVacias(); i++) {
            recorridoEnInOrden(nodoActual.getHijo(i), recorrido);
            recorrido.add(nodoActual.getClave(i));
        }
        if (!nodoActual.esHijoVacio(nodoActual.cantidadClavesNoVacias())) {
            recorridoEnInOrden(nodoActual.getHijo(nodoActual.cantidadClavesNoVacias()), recorrido);
        }
    }

    @Override
    public List<K> recorridoEnPreOrden() {
        List<K> recorrido = new ArrayList<>();

        recorridoEnPreOrden(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnPreOrden(NodoMVias<K, V> nodoActual, List<K> recorrido) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }
        for (int i = 0; i < nodoActual.cantidadClavesNoVacias(); i++) {
            recorrido.add(nodoActual.getClave(i));
            recorridoEnPreOrden(nodoActual.getHijo(i), recorrido);
        }
        if (!nodoActual.esHijoVacio(nodoActual.cantidadClavesNoVacias())) {
            recorridoEnPreOrden(nodoActual.getHijo(nodoActual.cantidadClavesNoVacias()), recorrido);
        }
    }

    @Override
    public List<K> recorridoEnPosOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnPosOrden(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnPosOrden(NodoMVias<K, V> nodoActual, List<K> recorrido) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }
        recorridoEnPosOrden(nodoActual.getHijo(0), recorrido);
        for (int i = 0; i < nodoActual.cantidadClavesNoVacias(); i++) {
            recorridoEnPosOrden(nodoActual.getHijo(i + 1), recorrido);
            recorrido.add(nodoActual.getClave(i));
        }

    }

    @Override
    public List<K> recorridoPorNivelesOrden() {
        List<K> recorrido = new ArrayList<>();
        Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();

        colaDeNodos.offer(this.raiz);
        System.out.println(raiz.cantidadClavesNoVacias());
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

        return recorrido;
    }

    @Override
    public List<V> recorridoValoresEnInOrden() {
        List<V> recorrido = new ArrayList<>();

        return recorrido;
    }

    @Override
    public List<V> recorridoValoresEnPreOrden() {
        List<V> recorrido = new ArrayList<>();

        return recorrido; // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<V> recorridoValoresEnPosOrden() {
        List<V> recorrido = new ArrayList<>();

        return recorrido; // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<V> recorridoValoresPorNivelesOrden() {
        List<V> recorrido = new ArrayList<>();

        return recorrido; // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    public V eliminar2(K claveAEliminar) {
        if (claveAEliminar == null) {
            throw new NullPointerException("calve a eliminar no puede ser nula");
        }
        V valor = this.buscar(claveAEliminar);

        if (valor == null) {
            throw new NullPointerException("calve a eliminar no puede ser nula");

        }

        this.raiz = eliminarAmigo(this.raiz, claveAEliminar);
        return valor;
    }

    // cantidad de nodos hojas
    public int cantidadDeNodoHojas() {

        Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
        int cantidadDeHojas = 0;
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            NodoMVias<K, V> nodoActual = colaDeNodos.poll();
            if (nodoActual.esHoja()) {
                cantidadDeHojas++;
            }
            for (int i = 0; i < nodoActual.cantidadClavesNoVacias(); i++) {
                if (!nodoActual.esHijoVacio(i)) {
                    colaDeNodos.offer(nodoActual.getHijo(i));
                }
            }
            if (!nodoActual.esHijoVacio(nodoActual.cantidadClavesNoVacias())) {
                colaDeNodos.offer(nodoActual.getHijo(nodoActual.cantidadClavesNoVacias()));
            }

        }

        return cantidadDeHojas;
    }

    public boolean similares(ArbolMVias<K, V> arbol2) {
        Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();

        Queue<NodoMVias<K, V>> colaDeNodos2 = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        colaDeNodos2.offer(arbol2.raiz);

        while (!colaDeNodos.isEmpty() && !colaDeNodos2.isEmpty()) {
            NodoMVias<K, V> nodoActual = colaDeNodos.poll();
            NodoMVias<K, V> nodoActual2 = colaDeNodos2.poll();
            if (nodoActual.cantidadClavesNoVacias() == nodoActual2.cantidadClavesNoVacias()) {
                for (int i = 0; i < orden - 1; i++) {

                    if (!nodoActual.esHijoVacio(i) && !nodoActual2.esHijoVacio(i)) {
                        colaDeNodos.offer(nodoActual.getHijo(i));
                        colaDeNodos2.offer(nodoActual2.getHijo(i));
                    }
                    if (nodoActual.esHijoVacio(i) != nodoActual2.esHijoVacio(i)) {
                        return false;
                    }
                }

                if (!nodoActual.esHijoVacio(orden - 1) && !nodoActual2.esHijoVacio(orden - 1)) {
                    colaDeNodos.offer(nodoActual.getHijo(orden - 1));
                    colaDeNodos2.offer(nodoActual2.getHijo(orden - 1));
                }
                if (nodoActual.esHijoVacio(orden - 1) != nodoActual2.esHijoVacio(orden - 1)) {
                    return false;
                }
            }

        }
        return true;
    }

    // cantidad de nodos no hojas
    public int cantidadDeNodoNoHojas() {

        Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
        int cantidadDeHojas = 0;
        colaDeNodos.add(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            NodoMVias<K, V> nodoActual = colaDeNodos.poll();
            if (!nodoActual.esHoja()) {
                cantidadDeHojas++;
            }
            for (int i = 0; i < nodoActual.cantidadClavesNoVacias(); i++) {
                if (!nodoActual.esHijoVacio(i)) {
                    colaDeNodos.add(nodoActual.getHijo(i));
                }
            }
            if (!nodoActual.esHijoVacio(nodoActual.cantidadClavesNoVacias())) {
                colaDeNodos.add(nodoActual.getHijo(nodoActual.cantidadClavesNoVacias()));
            }

        }

        return cantidadDeHojas;
    }
    // [4,5,6] => 3 orden 4
    // | | | |

    // metodo que devuelva verdadero si los nodos de nivel N tienen todos sus hijos
    // diferentes de vacios
    // METODO QUE DEVUELVA VERDADERO SI UN ARBOL  ESTA BALANCEADO
    public boolean tienenHijosDiferentesDeVacio(int nivel) {
        return tienenHijosDiferentesDeVacio(this.raiz, nivel, 0);
    }

    private boolean tienenHijosDiferentesDeVacio(NodoMVias<K, V> nodoActual, int nivel, int nivelActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return false;
        }
        if (nivelActual < nivel) {

            for (int i = 0; i <= nodoActual.cantidadClavesNoVacias(); i++) {
                if (!nodoActual.esHijoVacio(i)) {
                    tienenHijosDiferentesDeVacio(nodoActual.getHijo(i), nivel, nivelActual + 1);
                }
            }
        } else if (nivelActual == nivel) {
            for (int i = 0; i <= nodoActual.cantidadClavesNoVacias(); i++) {
                if (nodoActual.esHijoVacio(i)) {
                    return false;
                }

            }
        }
        return true;
    }

    private NodoMVias<K, V> eliminarAmigo(NodoMVias<K, V> nodoActual, K claveAEliminar) {
        for (int i = 0; i < nodoActual.cantidadClavesNoVacias(); i++) {
            /* ver si es un hoja */

        }
        return null;
    }

    /*  OTROS METODOS DE PARACTICA*/
    //contar la altura a partir de un Nivel
    /*
     
     */
    public int alturaAPartirDeN(int nivel) {
        return alturaAPartirDeN(this.raiz, nivel, 0);
    }

    private int alturaAPartirDeN(NodoMVias<K, V> nodoActual, int nivel, int nivelActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        if (nivelActual < nivel) {
            for (int i = 0; i <= nodoActual.cantidadClavesNoVacias(); i++) {
                alturaAPartirDeN(nodoActual.getHijo(i), nivel, nivelActual + 1);
            }
        }
        int alturaMayor = 0;
        for (int i = 0; i <= nodoActual.cantidadClavesNoVacias(); i++) {
            int alturaActual = alturaAPartirDeN(nodoActual.getHijo(i), nivel, nivelActual + 1);
            if (alturaActual > alturaMayor) {
                alturaMayor = alturaActual;
            }
        }
        return alturaMayor + 1;
    }

    @Override
    public int cantidadSoloHijoIzq() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int cantidadSoloHijoIzqEnN(int nivel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean lleno() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean estaBalanceado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int cantidadDeNodosDespuesDeN(int nivel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int cantidadDeNodosDespuesDeNIterativo(int nivel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean esZurdo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void reflejo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int cantidadDeNodosPrimos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Para un árbol mvias de búsqueda implementar un método que reciba una
     * clave, la busque en el árbol, en caso de encontrar la llave que retorne
     * en que nivel está. Que retorne -1 en caso de no estar la clave en el
     * árbol. La implementación debe ser recursiva.
     *
     */
    public int enQueNivelEsta(K clave) {
        return enQueNivelEsta(this.raiz, clave, 0);
    }

    private int enQueNivelEsta(NodoMVias<K, V> nodoActual, K clave, int nivelActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return -1;
        }
        int n = -1;
        for (int i = 0; i < nodoActual.cantidadClavesNoVacias(); i++) {
            n = enQueNivelEsta(nodoActual.getHijo(i), clave, nivelActual + 1);
            if (nodoActual.getClave(i).compareTo(clave) == 0) {
                return nivelActual;
            }
        }
        n = enQueNivelEsta(nodoActual.getHijo(nodoActual.cantidadClavesNoVacias()), clave, nivelActual + 1);
        return n;
    }

}
