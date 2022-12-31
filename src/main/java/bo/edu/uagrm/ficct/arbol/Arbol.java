/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package bo.edu.uagrm.ficct.arbol;

import java.awt.Color;
import java.awt.TextField;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Arbol {

    public static void main(String[] args) throws ExcepcionOrdenInvalido {
        IArbolBusqueda<Integer, String> arbol;

        System.out.println("\t\tOPCIONES:\n");
        System.out.println("\t 1. Arbol Binario\n");
        System.out.println("\t 2. Arbol AVL\n");
        System.out.println("\t 3. Arbol MVias\n");
        System.out.println("\t 0. Salir\n");
        Scanner a = new Scanner(System.in);

        boolean val = true;
        while (val) {
            System.out.println("\tElija una opcion: ");
            int var = a.nextInt();
            switch (var) {
                case 1 -> {
                    /* aqui se ejecutara todo el contexto de -Arbol binario de busqueda */
                    lienzo objLienzo = new lienzo();
                    arbol = new ArbolBinarioBusqueda<>();
                    Controlador control = new Controlador(objLienzo, (ArbolBinarioBusqueda<Integer, String>) arbol);

                    arbol.insertar(60, "aa");
                    arbol.insertar(40, "bb");
                    arbol.insertar(15, "cc");

                    arbol.insertar(14, "cc");

                    arbol.insertar(45, "dd");
                    arbol.insertar(28, "gg");
                    arbol.insertar(30, "hh");
                    arbol.insertar(20, "ii");
                    arbol.insertar(90, "jj");
                    arbol.insertar(70, "kk");
                    
                    
                    arbol.insertar(120, "mm");
                    arbol.insertar(95, "nn");
                    arbol.insertar(79, "oo");
                    arbol.insertar(80, "oo");
                    arbol.insertar(71, "kk");
                    
//                    arbol.insertar(75, "pp");
//                    arbol.insertar(73, "qq");
//                    arbol.insertar(44, "qq");
//                    arbol.insertar(46, "qq");
//                    arbol.insertar(69, "qq");
                    arbol.insertar(122, "qq");
                    
                    
                    
//                  
                    System.err.println("enque nivel: "   +arbol.enQueNivelEsta(60));
                    System.err.println("enque nivel: "   +arbol.enQueNivelEsta(120));
                    System.err.println("enque nivel: "   +arbol.enQueNivelEsta(15));
                    
                    System.err.println("enque nivel: "   +arbol.enQueNivelEsta(16));
                    
                    System.out.println("Cantidad de primos: "+arbol.cantidadDeNodosPrimos());
                    System.out.println(arbol.sizeRecursivo());
                    System.out.println(arbol.recorridoEnInOrden().toString());
//                     arbol.reflejo();
//                    System.out.println(arbol.recorridoEnInOrden().toString());

                    System.out.println("es Zurdo?: " + arbol.esZurdo());
                    System.out.println("altura del arbol recursivo: " + arbol.alturaRecursivo());
                    System.out.println("altura del arbol iterativo: " + arbol.altura());

                    System.out.println("cantidad de nodos despues del nivel:" + arbol.cantidadDeNodosDespuesDeN(0));
                    System.out.println("cantidad de nodos despues del nivel:" + arbol.cantidadDeNodosDespuesDeNIterativo(0));

                    System.out.println(arbol.estaBalanceado());
                    System.out.println(arbol.sizeRecursivo());
                    System.out.println("Cantidad de nodo que tiene solo Hijo Izquierdo: "
                            + arbol.cantidadSoloHijoIzq());
                    System.out.println("Esta Lleno: "
                            + arbol.lleno());
                    System.out.println("Cantidad de nodo que tiene solo Hijo Izquierdo en nivel: "
                            + arbol.cantidadSoloHijoIzqEnN(2));

                    ArbolBinarioBusqueda<Integer, String> arb = (ArbolBinarioBusqueda<Integer, String>) arbol;
                    System.out.println(arb.mostrarClavesDeNivel(5).toString());
                    System.out.println(arb.mostrarClavesAPartirDeNivel(2).toString());
                    System.out.println(arb.contarNodosEnNivel(31));

//                    arbol.insertar(41, "tt");
//                    arbol.insertar(47, "zz");
//                  arbol.insertar(14, "zz");
//                    arbol.insertar(61, "zz");
//                    arbol.insertar(44, "zz");
//                    arbol.insertar(48, "zz");
//                    arbol.insertar(62, "zz");
//                    
//-----------------------------------------------------------------------------
//                                    EJERCICIOS
//-----------------------------------------------------------------------------
                    control.iniciar();

                    JFrame frameNuevo = new JFrame();
                    frameNuevo.getContentPane().add(objLienzo);
                    frameNuevo.setSize(400, 200);
                    frameNuevo.setTitle("ESTRUCTURA DE DATO ARBOL");
                    frameNuevo.setVisible(true);
                    ArbolBinarioNoGenerico arbolNoGenerico = new ArbolBinarioNoGenerico();
                    arbolNoGenerico.insertar(60, "aa");
                    arbolNoGenerico.insertar(40, "bb");
                    arbolNoGenerico.insertar(15, "cc");
                    arbolNoGenerico.insertar(45, "dd");
                    arbolNoGenerico.insertar(28, "gg");
                    arbolNoGenerico.insertar(30, "hh");
                    arbolNoGenerico.insertar(20, "ii");
                    arbolNoGenerico.insertar(90, "jj");
                    arbolNoGenerico.insertar(70, "kk");
                    arbolNoGenerico.insertar(120, "mm");
                    arbolNoGenerico.insertar(95, "nn");
                    arbolNoGenerico.insertar(79, "oo");
                    arbolNoGenerico.insertar(75, "pp");
                    arbolNoGenerico.insertar(73, "qq");

                    System.out.println(" ----------------------------------------------------");
                    System.out.println(" ----------------------------------------------------");
                    System.out.println("La Suma De las Claves Pares: " + arbolNoGenerico.sumaParesR());

                    System.out.println("La Suma De las Claves ImPares: " + arbolNoGenerico.sumaImParesR());

                    System.out.println(" ----------------------------------------------------");
                    System.out.println(" ----------------------------------------------------");

                    System.out.print("LA ALTURA DEL AARBOL ES:" + arbol.altura() + "\n");
                    System.out.println(" ----------------------------------------------------");
                    System.out.println(" ----------------------------------------------------");

                    System.out.print("\n la altura del arbol es:" + arbol.altura());
                    System.out.println("\n el nivel " + arbol.nivel());
                    System.out.println(" -------------------RECORRIDO EN INORDEN---------------------------------");
                    System.out.println(arbol.recorridoEnInOrden());
                    System.out.println(" ----------------------------------------------------");
                    System.out.println(" -------------------RECORRIDO POR NIVELES---------------------------------");
                    System.out.println(arbol.recorridoPorNivelesOrden());
                    System.out.println(" -------------------RECORRIDO EN PRE-ORDEN---------------------------------");
                    System.out.println(arbol.recorridoEnPreOrden());
                    System.out.println(" -------------------RECORRIDO EN POSORDEN---------------------------------");
                    System.err.println(arbol.recorridoEnPosOrden());
                    System.out.println(" ----------------------------------------------------");
                    System.out.println("\n\tEL NIVEL DEL ARBOL ES:" + arbol.nivel());
                    System.out.println(" ----------------------------------------------------");

                    System.out.println(" ----------------------------------------------------");

                    System.out.print("la altura del arbol es:" + arbol.altura());
                    System.out.println("\n el nivel " + arbol.nivel());
                    System.out.println(" -------------------RECORRIDO EN INORDEN---------------------------------");
                    System.out.println(arbol.recorridoEnInOrden());
                    System.out.println(" ----------------------------------------------------");
                    System.out.println(" -------------------RECORRIDO POR NIVELES---------------------------------");
                    System.out.println(arbol.recorridoPorNivelesOrden());
                    System.out.println(" -------------------RECORRIDO EN PRE-ORDEN---------------------------------");
                    System.out.println(arbol.recorridoEnPreOrden());
                    System.out.println(" -------------------RECORRIDO EN POSORDEN---------------------------------");
                    System.out.println(arbol.recorridoEnPosOrden());
                    System.out.println(" ----------------------------------------------------");
                    System.out.println("\n\tEL NIVEL DEL ARBOL ES:" + arbol.nivel());
                    System.out.println(" ----------------------------------------------------");
                    System.out.println(" ----------------------------------------------------");
                    System.out.println(" ----------------------------------------------------");
                    System.out.println(" ----------------------------------------------------");
                }
                case 2 ->
                    /* aqui se ejecutara todo el contexto de -Arbol AVL */
                    System.out.println("\t\t ARBOL AVL \n");
                case 3 -> {
                    /* aqui se ejecutara todo el contexto de -Arbol Mvias de busqueda */
                    System.out.println("\t\t ARBOL Mvias de busqueda \n");
                    arbol = new ArbolMVias<>(3);
                    //prueba del arbol

                    JPanel panelNuevo = new JPanel();
                    panelNuevo.setBackground(Color.decode("12122"));

                    JFrame frameNuevo = new JFrame();

                    frameNuevo.setSize(400, 200);
                    frameNuevo.setTitle("ESTRUCTURA DE DATO ARBOL");

                    TextField texto = new TextField();
                    texto.setText("Esto es un texto");
                    panelNuevo.add(texto);

                    arbol.insertar(120, "aa");
                    arbol.insertar(200, "bb");
                    arbol.insertar(80, "cc");
                    arbol.insertar(150, "dd");
                    arbol.insertar(130, "gg");
                    arbol.insertar(50, "ii");
                    arbol.insertar(70, "ii");
                    arbol.insertar(72, "ii");
                    arbol.insertar(75, "ii");
                    arbol.insertar(98, "ii");
                    arbol.insertar(100, "ii");
                    arbol.insertar(140, "jj");
                    arbol.insertar(170, "kk");
                    arbol.insertar(134, "mm");
                    arbol.insertar(160, "qq");
                    arbol.insertar(190, "pp");
                    arbol.insertar(400, "nn");
                    arbol.insertar(560, "oo");
                    //----------------------------------------------------
                    ArbolMVias<Integer, String> arbol2 = new ArbolMVias<>(3);

                    arbol2.insertar(60, "aa");
                    arbol2.insertar(40, "bb");
                    arbol2.insertar(15, "cc");
                    arbol2.insertar(45, "dd");
                    arbol2.insertar(28, "gg");
                    arbol2.insertar(30, "hh");
                    arbol2.insertar(20, "ii");
                    arbol2.insertar(90, "jj");
                    arbol2.insertar(70, "kk");
                    arbol2.insertar(120, "mm");
                    arbol2.insertar(73, "qq");
                    arbol2.insertar(75, "pp");
                    arbol2.insertar(95, "nn");
                    arbol2.insertar(79, "oo");

                    System.out.println("muestra sin son iguales\n");
//                    System.out.println("Es:"+arbol2.similares((ArbolMVias<Integer, String>)arbol));
                    //----------------------------------------------------
                    texto.setText(arbol.recorridoPorNivelesOrden().toString());
                    System.out.println("Altura recursivo: " + arbol.alturaRecursivo());
                    System.out.println(arbol.buscarRecursivo(400));
                    System.out.println("size Recursivo: " + arbol.sizeRecursivo());
                    System.out.println("size iterativo: " + arbol.size());

                    System.out.println("RECORRIDO POR NIVELES : " + arbol.recorridoPorNivelesOrden());
                    System.out.println("RECORRIDO INORDEN : " + arbol.recorridoEnInOrden().toString());
                    System.out.println("RECORRIDO PREORDEN: " + arbol.recorridoEnPreOrden().toString());

                    frameNuevo.add(panelNuevo);

                    frameNuevo.show(true);
                }
                case 0 -> {
                    val = false;
                    System.out.println("\t\t\tProceso finalizado\n\n");
                    break;
                }
                default ->
                    System.out.println("\tError: No existe esa opcion\n");

            }
        }
        System.out.println("Sesion Finalizado");
    }
}
