/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mvias;

import java.util.List;

/**
 *
 * @author hp
 */
public interface IArbolMVias<K, V> {

    public void insertar(K clave, V valor);

    boolean esVacio();

    int size();

    int nivel();

    int altura();

    void vaciar();

    boolean esArbolVacio();

    boolean contiene(K claveABuscar);

    V buscar(K clave);

    void eliminar(K claveAEliminar);

    List<K> recorridoInOrden();

    List<K> recorridoPostOrden();

    List<K> recorridoPreOrden();

    List<K> recorridoPorNiveles();

}
