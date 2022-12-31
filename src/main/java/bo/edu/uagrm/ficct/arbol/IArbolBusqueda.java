package bo.edu.uagrm.ficct.arbol;


import java.util.List;
 


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author hp
 * @param <K>
 * @param <V>
 */
public interface IArbolBusqueda<K extends Comparable<K>,V> {
    void insertar(K clave,V valor);
        
    V eliminar(K clave) ;
    V buscar(K clave);
    V buscarRecursivo(K clave);
    boolean contiene(K clave); 
    int size();
    int sizeRecursivo();
    int altura();
    void vaciar();
    boolean esArbolVacio();
    int nivel();
    boolean estaBalanceado();
    boolean esZurdo();
    int cantidadDeNodosPrimos();
   int  enQueNivelEsta(K clave); 
      void reflejo();
    int cantidadDeNodosDespuesDeN(int nivel);
    int cantidadDeNodosDespuesDeNIterativo(int nivel);
    int alturaRecursivo();
int cantidadSoloHijoIzq();
int cantidadSoloHijoIzqEnN(int nivel);
boolean lleno();

   //claves
    List<K> recorridoEnInOrden();
    List<K> recorridoEnPreOrden();
    List<K> recorridoEnPosOrden();
    List<K> recorridoPorNivelesOrden();
    //valores
    List<V> recorridoValoresEnInOrden();
    List<V> recorridoValoresEnPreOrden();
    List<V> recorridoValoresEnPosOrden();
    List<V> recorridoValoresPorNivelesOrden();
    
    
}
