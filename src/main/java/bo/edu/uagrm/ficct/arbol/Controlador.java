/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.arbol;

/**
 *
 * @author hp
 */
public class Controlador  {
    private lienzo objLienzo; //VISTA
    private ArbolBinarioBusqueda  Arbol; //VISTA

    public Controlador(lienzo objLienzo, ArbolBinarioBusqueda  Arbol) {
        this.objLienzo = objLienzo;
        this.Arbol = Arbol;
    }
    public void iniciar(){
    objLienzo.setArbol(Arbol);
    }
    
}
