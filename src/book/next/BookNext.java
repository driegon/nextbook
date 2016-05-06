/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.next;

import basededatos.conexion;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
    public static int ObtenerAñoNac(int id)
    {
        conexion bdd = new conexion();
        ResultSet resultado = null;
        int AñoNac = 0;
                
        resultado = bdd.consulta("SELECT year(fecha_nacimiento) as fecha FROM usuario WHERE id = " + id);
        
        try{
            while (resultado.next()){                                
                AñoNac = resultado.getInt("fecha");
            }
            resultado.close();
        }catch(SQLException ex)
        {
            System.out.println("SQLException: "+ ex.getMessage());        
        }
        
        return AñoNac;
    }
    
    public static String[] RecomendacionPrimaria(int id)
    {   //Recomendacion Primaria: usuario nuevo y sin consultas, 
        //a partir de la edad se hacen las busquedas order by leido y punteo
        String[] Libros = new String[5];
        int AñoNac = 0;      
        AñoNac = ObtenerAñoNac(id);
        
        conexion bdd = new conexion();
        ResultSet resultado = null;
        
        resultado = bdd.consulta("SELECT l.titulo FROM carretilla c " +
        "INNER JOIN libro l on (c.id_libro = l.id) " +
        "WHERE estatus = 1 " +
        "AND c.id_usuario IN " +
        "    (select u.id from usuario u " +
        "    where u.id not in ("+ id + ") /*usuario consultado*/ " +
        "    and YEAR(u.fecha_nacimiento) BETWEEN " 
                + (AñoNac - 5) + " and " + (AñoNac + 5)
                + ") /*rango +-5 de la fecha del usuario*/ " +
        "ORDER by c.leído DESC, c.punteo DESC LIMIT 5");
               
        int contador = 0;
        
        try{
            while (resultado.next()) {
                Libros[contador] = resultado.getString("titulo");
                contador++;                
            }
            resultado.close();
        }catch(SQLException ex)
        {
            System.out.println("SQLException: "+ ex.getMessage());        
        }
        
        return Libros;
    }
    
    public static String[] RecomendacionPorSimilitud(int id)
    {
        String[] Libros = new String[5];
        
        Libros[0]= "Pinocho";
        Libros[1]= "Integrales";
        Libros[2]= "";
        Libros[3]= "";
        Libros[4]= "";
        
        return Libros;
    }
    
    public static String[] RecomendacionPorAprendizaje(int id)
    {
        String[] Libros = new String[5];
        
        Libros[0]= "La Bella y la Bestia";
        Libros[1]= "Fisica Fundamental";
        Libros[2]= "Gramatica";
        Libros[3]= "";
        Libros[4]= "";
        
        return Libros;
    }
    
    public static String[] TraerCarretilla(int id)
    {   //Carretilla: muestra los 5 libros mas recientes agregados a la carretilla y sin haberse leído
        String[] Libros = new String[5];
        
        conexion bdd = new conexion();
        ResultSet resultado = null;
        
        resultado = bdd.consulta("SELECT l.titulo FROM carretilla c " +
        "INNER JOIN libro l on (c.id_libro = l.id) " +
        "WHERE id_usuario = " + id + " and leído = 0 and estatus = 1 " +
        "ORDER by c.id DESC LIMIT 5 ");
        
        int contador = 0;
        
        try{
            while (resultado.next()) {
                Libros[contador] = resultado.getString("titulo");
                contador++;                
            }
            resultado.close();
        }catch(SQLException ex)
        {
            System.out.println("SQLException: "+ ex.getMessage());        
        }
        
        return Libros;
    }
    
}
