/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.next;

/**
 *
 * @author Deisy
 */
public class BookNext {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public static String[] RecomendacionPrimaria()
    {
        String[] Libros = new String[5];
        
        Libros[0]= "El Principito";
        Libros[1]= "Algebra de Baldor";
        Libros[2]= "";
        Libros[3]= "";
        Libros[4]= "";
        
        return Libros;
    }
    
    public static String[] RecomendacionPorSimilitud()
    {
        String[] Libros = new String[5];
        
        Libros[0]= "Pinocho";
        Libros[1]= "Integrales";
        Libros[2]= "";
        Libros[3]= "";
        Libros[4]= "";
        
        return Libros;
    }
    
    public static String[] RecomendacionPorAprendizaje()
    {
        String[] Libros = new String[5];
        
        Libros[0]= "La Bella y la Bestia";
        Libros[1]= "Fisica Fundamental";
        Libros[2]= "Gramatica";
        Libros[3]= "";
        Libros[4]= "";
        
        return Libros;
    }
    
    public static String[] TraerCarretilla()
    {
        String[] Libros = new String[5];
        
        Libros[0]= "Harry Potter 1";
        Libros[1]= "El Aquimista";
        Libros[2]= "";
        Libros[3]= "";
        Libros[4]= "";
        
        return Libros;
    }
    
}
