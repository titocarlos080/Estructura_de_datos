package bo.edu.uagrm.ficct.arbol;

 

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 /*
   * Nodo Binario Generico a utilizarse en implementaciones
  donde K-> es una clave; V--> es tipo de valor

 */
/**
 *
 * @author Tito Carlos Gutierrez
 */
public class NodoBinario<K, V> {

    private NodoBinario<K, V> hijoIzquierdo;
    private K clave;
    private V valor;
    private NodoBinario<K, V> hijoDerecho;
 
    public NodoBinario() {
    }

    public NodoBinario(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public NodoBinario<K, V> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoBinario<K, V> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public K getClave() {
        return clave;
    }

    public void setClave(K clave) {
        this.clave = clave;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    public NodoBinario<K, V> getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(NodoBinario<K, V> hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public static NodoBinario nodoVacio() {

        return null;
    }
    public static int nodosCompletos(NodoBinario nodo){
        
        if (NodoBinario.esNodoVacio(nodo)) {
            return 1;
        }
        int cantDer=nodosCompletos(nodo.getHijoDerecho());
      int cantIzq=nodosCompletos(nodo.getHijoIzquierdo());
      return cantDer+cantIzq;
    }
    public static boolean esNodoVacio(NodoBinario elNodo) {
    
        return elNodo == NodoBinario.nodoVacio();
    }

    public boolean esHijoIzquierdoVacio() {
        return NodoBinario.esNodoVacio(this.getHijoIzquierdo());
    }

    public boolean esHijoDerechoVacio() {
        return NodoBinario.esNodoVacio(this.getHijoDerecho());
    }

    public boolean esHoja() {
        return esHijoIzquierdoVacio() && esHijoDerechoVacio();
    }
    public boolean esCompleto(){
        return !esHijoDerechoVacio()&& !esHijoIzquierdoVacio();
    }
    
}
