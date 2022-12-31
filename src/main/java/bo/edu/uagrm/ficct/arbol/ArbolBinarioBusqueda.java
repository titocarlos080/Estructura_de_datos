package bo.edu.uagrm.ficct.arbol;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author hp
 */
public class ArbolBinarioBusqueda<K extends Comparable<K>, V> implements IArbolBusqueda<K, V> {

    protected NodoBinario<K, V> raiz;

    public ArbolBinarioBusqueda() {
    }

    @Override
    public void insertar(K clave, V valor) {
        if (clave == null) {
            System.out.println("Error: no se permite clave nula");
            return;
        }
        if (valor == null) {
            System.out.println("Error: no se permite valor nulo");
            return;
        }
        if (this.raiz == null) {
            NodoBinario<K, V> nuevoNodo = new NodoBinario<>();
            nuevoNodo.setClave(clave);
            nuevoNodo.setValor(valor);
            this.raiz = nuevoNodo;
        } else {

            NodoBinario<K, V> nodoActual = this.raiz;
            NodoBinario<K, V> nodoAnterior = NodoBinario.nodoVacio();
            while (nodoActual != NodoBinario.nodoVacio()) {
                nodoAnterior = nodoActual;
                K claveActual = nodoActual.getClave();
                if (clave.compareTo(claveActual) < 0) {
                    nodoActual = nodoActual.getHijoIzquierdo();
                } else if (clave.compareTo(claveActual) > 0) {
                    nodoActual = nodoActual.getHijoDerecho();
                } else {
                    nodoActual.setClave(clave);
                    nodoActual.setValor(valor);
                    return;
                }

            }//TERMINAMOS EL CICLO Y TENEMOS LA DIRECION DONDE AGREGAR, QUE ESTA EN nodoActual;
            nodoActual = new NodoBinario<>();

            nodoActual.setClave(clave);
            nodoActual.setValor(valor);
            K claveDeNodoAnterior = nodoAnterior.getClave();

            if (clave.compareTo(claveDeNodoAnterior) < 0) {

                nodoAnterior.setHijoIzquierdo(nodoActual);
            } else {
                nodoAnterior.setHijoDerecho(nodoActual);
            }
        }
    }
///RECONSTRUCION DE NUEVO ARBOL APARTIR DE LAS LISTAS RECORRIDAS

    public void ArbolBinarioBusqueda(boolean esPreOrden, List<K> llavesRecInOrden, List<V> valoresRecInOrden,
            List<K> llaveRecNoInOrden, List<V> valoresRecNoInOrden) {

        if (llavesRecInOrden == null || llaveRecNoInOrden == null) {
            throw new IllegalArgumentException("Las listas de claves no puede ser nula");
            /* ilegal*/
        }
        if (llavesRecInOrden.size() != llaveRecNoInOrden.size()) {
            throw new IllegalArgumentException("las lista no debeb tener diferentes tama;os");
        }
        if (esPreOrden) {
            recostruirConPreOrden(llavesRecInOrden, valoresRecInOrden, llaveRecNoInOrden, valoresRecNoInOrden);
        } else {
            recostruirConPosOrden(llavesRecInOrden, valoresRecInOrden, llaveRecNoInOrden, valoresRecNoInOrden);
        }
    }
//-----------------------------------------------------------------------------------------------------------------------------------

    private NodoBinario<K, V> recostruirConPosOrden(List<K> llavesRecInOrden, List<V> valoresRecInOrden,
            List<K> llavesRecPosOrden, List<V> valoresRecPosOrden) {
        if (llavesRecInOrden.isEmpty() || llavesRecPosOrden.isEmpty()) {
            return NodoBinario.nodoVacio();
        }

        int posicionenturnoPosOrden = llavesRecPosOrden.size() - 1;
        K claveNodoActual = llavesRecPosOrden.get(posicionenturnoPosOrden);
        V valorNodoActual = valoresRecPosOrden.get(posicionenturnoPosOrden);
        NodoBinario<K, V> nodoActual = new NodoBinario<>();
        nodoActual.setClave(claveNodoActual);
        nodoActual.setValor(valorNodoActual);
        //PARA LA IZQUIERDA
        int posicionEnInOrden = obtenerPosicion(llavesRecInOrden, llavesRecPosOrden.get(posicionenturnoPosOrden));
        //sublista inorden
        List<K> recorridoClavesInOrdenIzq = llavesRecInOrden.subList(0, posicionEnInOrden);
        List<V> recorridoValoresInOrdenIzq = valoresRecInOrden.subList(0, posicionEnInOrden);
        //sublista posorden
        List<K> recorridoClavesPosOrdenIzq = llavesRecPosOrden.subList(0, posicionEnInOrden);
        List<V> recorridoValoresPosOrdenIzq = valoresRecPosOrden.subList(0, posicionEnInOrden);
        //------------------------------------------------------------------------------------------------------------
        NodoBinario<K, V> hijoIzqDeNodoActual = recostruirConPosOrden(recorridoClavesInOrdenIzq, recorridoValoresInOrdenIzq,
                recorridoClavesPosOrdenIzq, recorridoValoresPosOrdenIzq);
        nodoActual.setHijoIzquierdo(hijoIzqDeNodoActual);

        //PARA LA DERECHA
        //sublista inorden
        List<K> recorridoClavesInOrdenDer = llavesRecInOrden.subList(posicionEnInOrden + 1, llavesRecInOrden.size() - 1);
        List<V> recorridoValoresInOrdenDer = valoresRecInOrden.subList(posicionEnInOrden + 1, valoresRecInOrden.size() - 1);
        //sublista posorden
        List<K> recorridoClavesPosOrdenDer = llavesRecPosOrden.subList(posicionEnInOrden + 1, posicionenturnoPosOrden - 1);
        List<V> recorridoValoresPosOrdenDer = valoresRecPosOrden.subList(posicionEnInOrden + 1, posicionenturnoPosOrden - 1);

        //---------------------------------------------------------------------------------------
        NodoBinario<K, V> hijoDerDeNodoActual = recostruirConPosOrden(recorridoClavesInOrdenDer, recorridoValoresInOrdenDer,
                recorridoClavesPosOrdenDer, recorridoValoresPosOrdenDer);
        nodoActual.setHijoDerecho(hijoDerDeNodoActual);

        return nodoActual;

    }

    private NodoBinario<K, V> recostruirConPreOrden(List<K> llavesRecInOrden, List<V> valoresRecInOrden,
            List<K> llavesRecPreOrden, List<V> valoresRecPreOrden) {
        if (llavesRecInOrden.isEmpty() || llavesRecPreOrden.isEmpty()) {
            return NodoBinario.nodoVacio();
        }

        int posicionEnInOrden = obtenerPosicion(llavesRecInOrden, llavesRecPreOrden.get(0));
        return null;

    }

    private int obtenerPosicion(List<K> llaveRecInOrden, K clave) {
        int posicion;
        for (posicion = 0; posicion <= llaveRecInOrden.size() - 1; posicion++) {
            if (llaveRecInOrden.get(posicion).compareTo(clave) == 0) {
                return posicion;
            }
            posicion++;
        }

        return posicion;
    }

    //CONTAR HOJAS
    //  TRIMAP
    @Override
    public V eliminar(K clave) {
        if (clave == null) {
            System.out.println("Error: No se permite clave null");
            return null;
        }
        V valorAEliminar = buscar(clave);
        if (valorAEliminar == null) {
            System.err.println("Error: No existe la clave: [" + clave + "]");
            return null;
        }
        eliminar(this.raiz, clave);
        return valorAEliminar;

    }

    private NodoBinario<K, V> eliminar(NodoBinario<K, V> nodoActual, K clave) {
        K claveActual = nodoActual.getClave();
        if (clave.compareTo(claveActual) < 0) {
            NodoBinario<K, V> supuestoHijoIzquierdo = eliminar(nodoActual.getHijoIzquierdo(), clave);
            nodoActual.setHijoIzquierdo(supuestoHijoIzquierdo);
            return nodoActual;
        }
        if (clave.compareTo(claveActual) > 0) {
            NodoBinario<K, V> supuestoHijoDerecho = eliminar(nodoActual.getHijoDerecho(), clave);
            nodoActual.setHijoIzquierdo(supuestoHijoDerecho);
            return nodoActual;
        }
        //CASO 1. NODO A ELIMINAR ES HOJA
        if (nodoActual.esHoja()) {
            return NodoBinario.nodoVacio();
        }
        //CASO 2. TIENE UNO DE LOS HIJOS Y NO AMBOS-->HIJO IZQUIERDO
        if (nodoActual.esHijoIzquierdoVacio() && !nodoActual.esHijoDerechoVacio()) {
            return nodoActual.getHijoIzquierdo();
        }
        //     TIENE UNO DE LOS HIJOS Y NO AMBOS-->HIJO DERECHO
        if (!nodoActual.esHijoIzquierdoVacio() && nodoActual.esHijoDerechoVacio()) {
            return nodoActual.getHijoDerecho();
        }
        //CASO 3. TIENE AMBOS HIJO
        /*En este caso buscamos el sucesor--->  con su hijoDerecho recorrido InOrden*/
        NodoBinario<K, V> nodoSucesor = obtenerSucesor(nodoActual.getHijoDerecho());
        nodoActual.setClave(nodoSucesor.getClave());
        nodoActual.setValor(nodoSucesor.getValor());
        return nodoActual;
    }

    private NodoBinario<K, V> obtenerSucesor(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoAnterior = NodoBinario.nodoVacio();
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijoIzquierdo();
        }
        return nodoAnterior;
    }

    @Override
    public V buscar(K clave) {
        V valor = null;
        if (!this.esArbolVacio()) {
            Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
            pilaDeNodos.push(this.raiz);
            while (!pilaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoActual = pilaDeNodos.pop();
                K claveActual = nodoActual.getClave();
                if (clave.compareTo(claveActual) == 0) {
                    return nodoActual.getValor();
                }
                if (!nodoActual.esHijoIzquierdoVacio()) {
                    pilaDeNodos.push(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esHijoDerechoVacio()) {
                    pilaDeNodos.push(nodoActual.getHijoDerecho());
                }
            }
        }
        return valor;
    }

    @Override
    public boolean contiene(K clave) {
        if (!this.esArbolVacio()) {
            Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
            pilaDeNodos.push(this.raiz);
            while (!pilaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoActual = pilaDeNodos.pop();
                K claveActual = nodoActual.getClave();
                if (clave.compareTo(claveActual) == 0) {
                    return true;
                }
                if (!nodoActual.esHijoDerechoVacio()) {
                    pilaDeNodos.push(nodoActual.getHijoDerecho());
                }
                if (!nodoActual.esHijoIzquierdoVacio()) {
                    pilaDeNodos.push(nodoActual.getHijoIzquierdo());
                }

            }
        }
        return false;
    }

    @Override
    public int size() {
        int nroDeNodos = 0;
        if (!this.esArbolVacio()) {
            Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while (!colaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                nroDeNodos++;
                if (!nodoActual.esHijoIzquierdoVacio()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                } else if (nodoActual.esHijoDerechoVacio()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }

            }
        }
        return nroDeNodos;
    }

    @Override
    public int altura() {
        return altura(this.raiz);
    }

    protected int altura(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int alturaHijoIzquierdo = altura(nodoActual.getHijoIzquierdo());
        int alturaHijoDerecho = altura(nodoActual.getHijoDerecho());
        int dif = alturaHijoIzquierdo - alturaHijoDerecho;
        if (dif <= 0) {
            return alturaHijoDerecho + 1;
        } else {

            return alturaHijoIzquierdo + 1;
        }

    }

    @Override
    public void vaciar() {
        this.raiz = NodoBinario.nodoVacio();//nodo vacio retorna null
    }

    @Override
    public int nivel() {
        int nivel = -1;
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.add(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            int i = 0;
            int longitud = colaDeNodos.size();
            while (i < longitud) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                if (!nodoActual.esHijoIzquierdoVacio()) {
                    colaDeNodos.add(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esHijoDerechoVacio()) {
                    colaDeNodos.add(nodoActual.getHijoDerecho());
                }

                i++;
            }
            nivel++;
        }
        return nivel;
    }

    @Override
    public List<K> recorridoEnInOrden() {
        List<K> recorrido = new ArrayList<>();
        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
        apilarParaInOrden(pilaDeNodos, this.raiz);
        while (!pilaDeNodos.isEmpty()) {
            NodoBinario<K, V> nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            apilarParaInOrden(pilaDeNodos, nodoActual.getHijoDerecho());
        }
        return recorrido;
    }
//funcion auxiliar para apilar en recorrido InOrden

    private void apilarParaInOrden(Stack pilaDeNodos, NodoBinario<K, V> nodoActual) {
        while (nodoActual != NodoBinario.nodoVacio()) {
            pilaDeNodos.add(nodoActual);
            nodoActual = nodoActual.getHijoIzquierdo();
        }
    }

    @Override
    public List<K> recorridoEnPreOrden() {
        List<K> recorrido = new ArrayList<>();
        if (this.raiz != null) {
            Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<NodoBinario<K, V>>();
            pilaDeNodos.push(this.raiz);
            while (!pilaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoActual = pilaDeNodos.pop();
                recorrido.add(nodoActual.getClave());

                if (!nodoActual.esHijoDerechoVacio()) {
                    pilaDeNodos.push(nodoActual.getHijoDerecho());
                }
                if (!nodoActual.esHijoIzquierdoVacio()) {
                    pilaDeNodos.push(nodoActual.getHijoIzquierdo());
                }
            }
        }
        return recorrido;
    }

    @Override
    public List<K> recorridoEnPosOrden() {
        List<K> recorrido = new ArrayList<>();
        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
        apilarParaPosOrden(pilaDeNodos, this.raiz);
        while (!pilaDeNodos.isEmpty()) {
            NodoBinario<K, V> nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            if (!pilaDeNodos.isEmpty()) {
                NodoBinario<K, V> tope = pilaDeNodos.peek();
                if (!tope.esHijoDerechoVacio() && tope.getHijoDerecho() != nodoActual) {
                    apilarParaPosOrden(pilaDeNodos, tope.getHijoDerecho());
                }
            }
        }
        return recorrido;
    }

    private void apilarParaPosOrden(Stack< NodoBinario<K, V>> pilaDeNodos, NodoBinario<K, V> nodoActual) {
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            pilaDeNodos.add(nodoActual);
            if (!nodoActual.esHijoIzquierdoVacio()) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else {
                nodoActual = nodoActual.getHijoDerecho();
            }

        }
    }

    @Override
    public List<K> recorridoPorNivelesOrden() {
        List<K> recorrido = new ArrayList<>();
        if (!this.esArbolVacio()) {
            Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while (!colaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                recorrido.add(nodoActual.getClave());
                if (!nodoActual.esHijoIzquierdoVacio()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esHijoDerechoVacio()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }

            }
        }
        return recorrido;
    }

///---------------------------------------------------------------------------------------------------------------
    public int cantidadDeNodosConSusDosHijos() {
        int cantidad = 0;
        if (!this.esArbolVacio()) {
            Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while (!colaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                if (!nodoActual.esHijoDerechoVacio() && !nodoActual.esHijoIzquierdoVacio()) {
                    cantidad++;
                }
                if (!nodoActual.esHijoIzquierdoVacio()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esHijoDerechoVacio()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }

            }
        }
        return cantidad;
    }

///---------------------------------------------------------------------------------------------------------------
    /*metodo que retorna a que nivel pertenece un nodo*/
    private int nivelDeUnaClave(K clave) {
        int nivel = 0;
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.add(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            int i = 0;
            int longitud = colaDeNodos.size();
            while (i < longitud) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                if (nodoActual.getClave().compareTo(clave) == 0) {
                    return i;
                }
                if (!nodoActual.esHijoIzquierdoVacio()) {
                    colaDeNodos.add(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esHijoDerechoVacio()) {
                    colaDeNodos.add(nodoActual.getHijoDerecho());
                }

                i++;
            }
            nivel++;
        }
        return nivel;
    }

    /*metodo que retorna la cantidad de nodos que tenga sus dos hijo en un nivel N*/
    public int cantidadDeNodosConSusDosHijos(int nivel) {
        int cantidad = 0;
        if (!this.esArbolVacio()) {
            Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while (!colaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                if (nivel == this.nivelDeUnaClave(nodoActual.getClave())) {
                    if (!nodoActual.esHijoDerechoVacio() && !nodoActual.esHijoDerechoVacio()) {
                        cantidad++;
                    }

                }
                if (!nodoActual.esHijoIzquierdoVacio()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esHijoDerechoVacio()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }

            }
        }
        return cantidad;

    }

    public int cantidadDeNodosCompletos() {
        return cantidadDeNodosCompletosV(this.raiz);

    }

    private int cantidadDeNodosCompletosV(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int cantidadIzq = cantidadDeNodosCompletosV(nodoActual.getHijoIzquierdo());
        int cantidadDer = cantidadDeNodosCompletosV(nodoActual.getHijoDerecho());

        if (!nodoActual.esHijoDerechoVacio() && !nodoActual.esHijoIzquierdoVacio()) {
            return cantidadDer + cantidadIzq + 1;
        }
        return cantidadDer + cantidadIzq;

    }

    public int nroDeNodosCompletosEnNivelN(int nivelObjetivo) {

        return nroDeNodosCompletosEnNivelN(this.raiz, nivelObjetivo, 0);
    }

    private int nroDeNodosCompletosEnNivelN(NodoBinario<K, V> nodoActual, int nivelObjeto, int nivelActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        if (nivelActual < nivelObjeto) {
            return nroDeNodosCompletosEnNivelN(nodoActual.getHijoDerecho(), nivelObjeto, nivelActual + 1)
                    + nroDeNodosCompletosEnNivelN(nodoActual.getHijoIzquierdo(), nivelObjeto, nivelActual + 1);
        }
        if (nivelObjeto == nivelActual) {

            if (!nodoActual.esHijoDerechoVacio() && !nodoActual.esHijoIzquierdoVacio()) {
                return 1;
            }
        }
        return 0;
    }


    /*
  
     */
///---------------------------------------------------------------------------------------------------------------
// METODOS RECURSIVOS
    public List<K> recorridoInOrdenV2() {
        List<K> recorrido = new ArrayList<>();
        recorridoInOrdenV2(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoInOrdenV2(NodoBinario<K, V> nodoActual, List<K> recorrido) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }
        recorridoInOrdenV2(nodoActual.getHijoIzquierdo(), recorrido);

        recorrido.add(nodoActual.getClave());
        recorridoInOrdenV2(nodoActual.getHijoDerecho(), recorrido);
    }

    public List<K> recorridoPreOrdenV2() {
        List<K> recorrido = new ArrayList<>();
        recorrerPreOrden(recorrido, this.raiz);
        return recorrido;
    }

    private void recorrerPreOrden(List<K> recorrido, NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }
        recorrido.add(nodoActual.getClave());
        recorrerPreOrden(recorrido, nodoActual.getHijoIzquierdo());
        recorrerPreOrden(recorrido, nodoActual.getHijoDerecho());
    }
// 

    public int sizeV2() {
        return sizeV2(this.raiz);

    }

    private int sizeV2(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int nroDeNodosPorIzq = sizeV2(nodoActual.getHijoIzquierdo());
        int nroDeNodosPorDer = sizeV2(nodoActual.getHijoDerecho());
        return nroDeNodosPorIzq + nroDeNodosPorDer + 1;
    }

    public int alturaV2() {
        return sizeV2(this.raiz);

    }

    private int alturaV2(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int nroDeNodosPorIzq = alturaV2(nodoActual.getHijoIzquierdo());
        int nroDeNodosPorDer = alturaV2(nodoActual.getHijoDerecho());
        return nroDeNodosPorIzq > nroDeNodosPorDer ? nroDeNodosPorIzq + 1 : nroDeNodosPorDer + 1;
    }
    // como hacer iterativo? 

    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(this.raiz);
    }
///---------------------------------------------------------------------------------------------------------------
///---------------------------------------------------------------------------------------------------------------
///RECORRIDO PARA OBTENER SUS VALORES
///---------------------------------------------------------------------------------------------------------------
///---------------------------------------------------------------------------------------------------------------

    @Override
    public List<V> recorridoValoresEnInOrden() {
        List<V> recorrido = new ArrayList<>();
        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
        apilarInOrdenValores(pilaDeNodos, this.raiz);
        while (!pilaDeNodos.isEmpty()) {
            NodoBinario<K, V> nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getValor());
            apilarInOrdenValores(pilaDeNodos, nodoActual.getHijoDerecho());

        }
        return recorrido;
    }

    private void apilarInOrdenValores(Stack<NodoBinario<K, V>> pilaDeNodos, NodoBinario<K, V> nodoActual) {
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            pilaDeNodos.push(nodoActual);
            nodoActual = nodoActual.getHijoIzquierdo();
        }
    }

    @Override
    public List<V> recorridoValoresEnPreOrden() {
        List<V> recorrido = new ArrayList<>();
        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
        pilaDeNodos.push(this.raiz);
        while (!pilaDeNodos.isEmpty()) {
            NodoBinario<K, V> nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getValor());
            if (!nodoActual.esHijoDerechoVacio()) {
                pilaDeNodos.push(nodoActual.getHijoDerecho());
            }
            if (!nodoActual.esHijoIzquierdoVacio()) {
                pilaDeNodos.push(nodoActual.getHijoIzquierdo());
            }
        }
        return recorrido;
    }

    @Override
    public List<V> recorridoValoresEnPosOrden() {
        List<V> recorrido = new ArrayList<>();
        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
        apilarPosOrdenValores(pilaDeNodos, this.raiz);
        while (!pilaDeNodos.isEmpty()) {
            NodoBinario<K, V> nodoActual = pilaDeNodos.peek();
            if (!NodoBinario.esNodoVacio(nodoActual.getHijoDerecho())) {
                boolean bannderaSiYaExiste = false;
                for (V v : recorrido) {
                    if (v == nodoActual.getHijoDerecho().getValor()) {
                        bannderaSiYaExiste = true;
                        break;
                    }
                }
                if (bannderaSiYaExiste) {
                    recorrido.add(nodoActual.getValor());

                } else {
                    apilarPosOrdenValores(pilaDeNodos, nodoActual.getHijoDerecho());

                }

            } else {
                recorrido.add(nodoActual.getValor());

            }

        }
        return recorrido;
    }

    private boolean contieneValor(List<V> recorrido, V valor) {

        for (V v : recorrido) {
            if (v == valor) {
                return true;
            }
        }
        return false;
    }

    private void apilarPosOrdenValores(Stack<NodoBinario<K, V>> pilaDeNodos, NodoBinario<K, V> nodoActual) {
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            pilaDeNodos.push(nodoActual);
            nodoActual = nodoActual.getHijoIzquierdo();
        }
    }

    @Override
    public List<V> recorridoValoresPorNivelesOrden() {
        List<V> recorrido = new ArrayList<>();
        if (!this.esArbolVacio()) {
            Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while (!colaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                recorrido.add(nodoActual.getValor());
                if (!nodoActual.esHijoIzquierdoVacio()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esHijoDerechoVacio()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }

            }
        }
        return recorrido;
    }
///---------------------------------------------------------------------------------------------------------------
///---------------------------------------------------------------------------------------------------------------

    /*
    *mostrar las claves del nivel N
     */
    public List<K> mostrarClavesDeNivel(int nivel) {
        List<K> recorrido = new ArrayList<>();
        mostrarClavesDeNivel(this.raiz, recorrido, nivel, 0);
        return recorrido;
    }

    private void mostrarClavesDeNivel(NodoBinario<K, V> nodoActual, List<K> recorrido, int nivel, int nivelActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {

        } else {
            if (nivelActual < nivel) {
                mostrarClavesDeNivel(nodoActual.getHijoIzquierdo(), recorrido, nivel, nivelActual + 1);
                mostrarClavesDeNivel(nodoActual.getHijoDerecho(), recorrido, nivel, nivelActual + 1);
            } else if (nivelActual == nivel) {
                recorrido.add(nodoActual.getClave());
            }

        }
    }

    /*
    *mostrar las claves del a partir del nivel N
     */
    public List<K> mostrarClavesAPartirDeNivel(int nivel) {
        List<K> recorrido = new ArrayList<>();
        mostrarClavesAPartirDeNivel(this.raiz, recorrido, nivel, 0);
        return recorrido;

    }

    private void mostrarClavesAPartirDeNivel(NodoBinario<K, V> nodoActual, List<K> recorrido, int nivel, int nivelActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {

        } else {
            if (nivelActual < nivel) {
                mostrarClavesAPartirDeNivel(nodoActual.getHijoIzquierdo(), recorrido, nivel, nivelActual + 1);
                mostrarClavesAPartirDeNivel(nodoActual.getHijoDerecho(), recorrido, nivel, nivelActual + 1);
            } else if (nivelActual >= nivel) {

                mostrarClavesAPartirDeNivel(nodoActual.getHijoIzquierdo(), recorrido, nivel, nivelActual + 1);
                mostrarClavesAPartirDeNivel(nodoActual.getHijoDerecho(), recorrido, nivel, nivelActual + 1);
                recorrido.add(nodoActual.getClave());
            }

        }
    }

    /*
     * contar cuantos nodos hay en un Nivel
     */
    public int contarNodosEnNivel(int nivel) {
        return contarNodosEnNivel(this.raiz, nivel, 0);
    }

    private int contarNodosEnNivel(NodoBinario<K, V> nodoActual, int nivel, int nivelActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int cantidad = 0;
        if (nivelActual < nivel) {
            cantidad += contarNodosEnNivel(nodoActual.getHijoIzquierdo(), nivel, nivelActual + 1);
            cantidad += contarNodosEnNivel(nodoActual.getHijoDerecho(), nivel, nivelActual + 1);
        } else if (nivelActual == nivel) {
            if (!NodoBinario.esNodoVacio(nodoActual)) {
                cantidad++;
            }
        }
        return cantidad;

    }

    public int cantidadSoloHijoIzq() {
        return cantidadSoloHijoIzq(this.raiz);
    }

    private int cantidadSoloHijoIzq(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        if (nodoActual.esHoja()) {
            return 0;
        }

        int cantIzq = cantidadSoloHijoIzq(nodoActual.getHijoIzquierdo());
        int cantDer = cantidadSoloHijoIzq(nodoActual.getHijoDerecho());

        if (!nodoActual.esHijoIzquierdoVacio() && nodoActual.esHijoDerechoVacio()) {
            return cantIzq + 1;
        } else {

            return cantDer + cantIzq;
        }
    }

    @Override
    public int cantidadSoloHijoIzqEnN(int nivel) {
        return cantidadSoloHijoIzqEnN(raiz, nivel, 0);
    }

    private int cantidadSoloHijoIzqEnN(NodoBinario<K, V> nodoActual, int nivel, int nivelActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        if (nodoActual.esHoja()) {
            return 0;
        }
        int cantIzq = 0;
        int cantDer = 0;
        if (nivelActual < nivel) {
            cantIzq = cantidadSoloHijoIzqEnN(nodoActual.getHijoIzquierdo(), nivel, nivelActual + 1);

            cantDer = cantidadSoloHijoIzqEnN(nodoActual.getHijoDerecho(), nivel, nivelActual + 1);
        } else if (nivelActual == nivel) {
            if (!nodoActual.esHijoIzquierdoVacio() && nodoActual.esHijoDerechoVacio()) {
                return cantIzq + 1;
            }

        }
        return cantDer + cantIzq;

    }

    @Override
    public boolean lleno() {

        return lleno(raiz.getHijoIzquierdo(), raiz.getHijoDerecho());
    }

    private boolean lleno(NodoBinario<K, V> hijoIzq, NodoBinario<K, V> hijoDer) {
        if (NodoBinario.esNodoVacio(hijoIzq)) {
            if (NodoBinario.esNodoVacio(hijoDer)) {
                return true;
            }
            return false;
        }
        if (hijoDer.esHoja()) {
            if (hijoIzq.esHoja()) {
                return true;
            }
            return false;
        }
        if (!(hijoIzq.esCompleto() && hijoDer.esCompleto())) {
            return false;
        }
        boolean ladoIzq = lleno(hijoIzq.getHijoIzquierdo(), hijoIzq.getHijoDerecho());
        boolean ladoDer = lleno(hijoDer.getHijoIzquierdo(), hijoDer.getHijoDerecho());

        return ladoDer && ladoIzq;
    }

    public boolean estaBalanceado() {
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            NodoBinario<K, V> nodoActual = colaDeNodos.poll();
            int alturaIzquierdo = this.altura(nodoActual.getHijoIzquierdo());
            int alturaDerecho = this.altura(nodoActual.getHijoDerecho());
            int diferencia = alturaDerecho - alturaIzquierdo;
            if (diferencia > 1 || diferencia < -1) {
                return false;
            }
            if (!nodoActual.esHijoIzquierdoVacio()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if (!nodoActual.esHijoDerechoVacio()) {
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }

        }
        return true;
    }

    @Override
    public V buscarRecursivo(K clave) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int sizeRecursivo() {
        return sizeRecursivo(this.raiz);
    }

    private int sizeRecursivo(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        if (nodoActual.esHoja()) {
            return 1;
        }
        int n = 0;
        n += sizeRecursivo(nodoActual.getHijoIzquierdo());
        n += sizeRecursivo(nodoActual.getHijoDerecho());
        return n + 1;
    }

    public int cantidadDeNodosDespuesDeN(int nivel) {
        return cantidadDeNodosDespuesDeN(this.raiz, nivel, 0);
    }

    private int cantidadDeNodosDespuesDeN(NodoBinario<K, V> nodoActual, int nivel, int nivelActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int cant = 0;
        if (nivelActual <= nivel) {
            cant += cantidadDeNodosDespuesDeN(nodoActual.getHijoIzquierdo(), nivel, nivelActual + 1);
            cant += cantidadDeNodosDespuesDeN(nodoActual.getHijoDerecho(), nivel, nivelActual + 1);

        }
        if (nivelActual > nivel) {

            if (!NodoBinario.esNodoVacio(nodoActual)) {
                cant++;
            }
            cant += cantidadDeNodosDespuesDeN(nodoActual.getHijoIzquierdo(), nivel, nivelActual + 1);
            cant += cantidadDeNodosDespuesDeN(nodoActual.getHijoDerecho(), nivel, nivelActual + 1);

        }
        return cant;
    }

    public int cantidadDeNodosDespuesDeNIterativo(int nivel) {
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        int cantidad = 0;
        colaDeNodos.offer(this.raiz);
        int nivelActual = 0;
        boolean sw = false;
        while (!colaDeNodos.isEmpty() && !sw) {
            int n = colaDeNodos.size();

            for (int i = 0; i < n; i++) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                if (!nodoActual.esHijoIzquierdoVacio()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esHijoDerechoVacio()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
            }
            nivelActual++;
            if (nivelActual > nivel) {
                sw = true;
            }

        }
        while (!colaDeNodos.isEmpty()) {
            NodoBinario<K, V> nodoActual = colaDeNodos.poll();
            cantidad++;
            if (!nodoActual.esHijoIzquierdoVacio()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if (!nodoActual.esHijoDerechoVacio()) {
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }
        }
        return cantidad;

    }

    @Override
    public int alturaRecursivo() {
        return altuaraResursivo(this.raiz);
    }

    private int altuaraResursivo(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        if (nodoActual.esHoja()) {
            return 1;
        }
        int alturaIzq = altuaraResursivo(nodoActual.getHijoIzquierdo());
        int alturaDer = altuaraResursivo(nodoActual.getHijoDerecho());

        if (alturaIzq > alturaDer) {
            return alturaIzq + 1;
        }
        return alturaDer + 1;

    }

    /**
     * verificar si un arbol es zurdo
     */
    public boolean esZurdo() {
        if (this.raiz.esHoja() || NodoBinario.esNodoVacio(this.raiz)) {
            return true;
        }

        return esZurdo(this.raiz);
    }

    private boolean esZurdo(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return true;
        }

        if (!nodoActual.esHoja()) {
            int alturaIzq = sizeRecursivo(nodoActual.getHijoIzquierdo());
            int alturaDer = sizeRecursivo(nodoActual.getHijoDerecho());
            System.out.println(nodoActual.getClave() + "\t ->  I:" + alturaIzq + "      D:" + alturaDer);
            if (!(alturaIzq > alturaDer)) {
                return false;
            }
        }

        boolean ladoI = esZurdo(nodoActual.getHijoIzquierdo());
        boolean ladoD = esZurdo(nodoActual.getHijoDerecho());

        return ladoI && ladoD;
    }

    /**
     * **
     *
     * cantidad de nodos primos
     *
     */
    public int cantidadDeNodosPrimos() {
        return cantidadDeNodosPrimos(this.raiz);
    }

    private int cantidadDeNodosPrimos(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        if (nodoActual.esHoja()) {
            return 0;
        }
        int cant = 0;

        NodoBinario<K, V> padreI = nodoActual.getHijoIzquierdo();
        NodoBinario<K, V> padreD = nodoActual.getHijoDerecho();

        if (!NodoBinario.esNodoVacio(padreI) && !NodoBinario.esNodoVacio(padreD)) {
             if(!(padreI.esHoja() || padreD.esHoja())){
                 cant = cant + cantidadDeHijo(padreI)+ cantidadDeHijo(padreD);  
             }
           
      
        }
        
        cant += cantidadDeNodosPrimos(nodoActual.getHijoIzquierdo());
        cant += cantidadDeNodosPrimos(nodoActual.getHijoDerecho());

        return cant;
    }

    private int cantidadDeHijo(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        } else if (nodoActual.esHoja()) {
            return 0;
        }

        else if (!nodoActual.esHijoDerechoVacio() && !nodoActual.esHijoIzquierdoVacio()) {
            return 2;
        }   
        return 1;
         
    }

    /**
     * *
     *
     * reflejo de un arbol
     */
    public void reflejo() {
        reflejo(this.raiz);

    }

    private void reflejo(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }
        if (nodoActual.esHoja()) {
            return;
        }
        NodoBinario<K, V> hijoIzq = nodoActual.getHijoIzquierdo();
        nodoActual.setHijoIzquierdo(nodoActual.getHijoDerecho());
        nodoActual.setHijoDerecho(hijoIzq);

        reflejo(nodoActual.getHijoIzquierdo());
        reflejo(nodoActual.getHijoDerecho());

    }
    
    public int enQueNivelEsta(K clave) {
        return enQueNivelEsta(this.raiz, clave, 0);
    }

    private int enQueNivelEsta(NodoBinario<K, V> nodoActual, K clave, int nivelActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return -1;
        }
       
        System.out.println("nivel: "+nivelActual+ "Clave"+nodoActual.getClave());
        
        int n=-1;
        if (clave.compareTo(nodoActual.getClave())==0) {
            return nivelActual;
        }else if (        clave.compareTo(nodoActual.getClave())<0) {
           n=      enQueNivelEsta(nodoActual.getHijoIzquierdo(), clave, nivelActual + 1);
            
        }else{
          n=   enQueNivelEsta(nodoActual.getHijoDerecho(), clave, nivelActual + 1);
           
        }
 
        
  
        
      
       
      return n;
    } 

}
