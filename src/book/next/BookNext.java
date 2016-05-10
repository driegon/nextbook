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
    
    private static int ObtenerAñoNac(int id)
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
        
        resultado = bdd.consulta("SELECT DISTINCT l.titulo, c.id_libro FROM carretilla c " +
        "INNER JOIN libro l on (c.id_libro = l.id) " +
        "WHERE estatus = 1 " +
        "AND c.id_usuario IN " +
        "    (select u.id from usuario u " +
        "    where u.id not in ("+ id + ") /*usuario consultado*/ " +
        "    and YEAR(u.fecha_nacimiento) BETWEEN " 
                + (AñoNac - 5) + " and " + (AñoNac + 5)
                + ") /*rango +-5 de la fecha del usuario*/ " +
        "AND c.id_libro not IN " +
        "    (SELECT ca.id_libro from carretilla ca " +
        "    where ca.id_usuario in ("+ id + ")) /*se descartan los que tiene en carretilla*/" +
        "ORDER by c.leido DESC, c.punteo DESC LIMIT 5");
               
        int contador = 0;
        
        try{
            while (resultado.next()) {
                Libros[contador] = resultado.getString("id_libro") + "," + resultado.getString("titulo");                
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
                
        conexion bdd = new conexion();
        ResultSet resultado = null;
        
        resultado = bdd.consulta(
        "SELECT T.titulo, T.id FROM (\n" +
        "	\n" +
        "	SELECT DISTINCT l.* \n" +
        "	FROM libro AS l\n" +
        "	INNER JOIN categoria_libro as cl2 ON (l.id = cl2.id_libro)\n" +
        "	WHERE cl2.id_categoria IN (\n" +
        "		SELECT DISTINCT cl.id_categoria		\n" +
        "		FROM recomendacion as r\n" +
        "		INNER JOIN categoria_libro as cl ON (r.id_libro = cl.id_libro)\n" +
        "		WHERE r.id_usuario in ("+ id + ") \n" +
        "		ORDER BY r.fec_transac DESC	\n" +
        "	)\n" +
        "\n" +
        "	UNION\n" +
        "\n" +
        "	\n" +
        "	SELECT DISTINCT l.* \n" +
        "	FROM libro AS l\n" +
        "	INNER JOIN palabraclave_libro as pl2 ON (l.id = pl2.id_libro)\n" +
        "	WHERE pl2.id_palabraclave IN (\n" +
        "		SELECT DISTINCT pl.id_palabraclave\n" +
        "		FROM recomendacion as r\n" +
        "		INNER JOIN palabraclave_libro as pl ON (r.id_libro = pl.id_libro)\n" +
        "		WHERE r.id_usuario in ("+ id + ") \n" +
        "		ORDER BY r.fec_transac DESC	\n" +
        "	)\n" +
        ") AS T\n" +
        "WHERE T.id not IN\n" +
        "    (SELECT ca.id_libro from carretilla ca\n" +
        "    where ca.id_usuario in ("+ id + ")) /*se descartan los que tiene en carretilla*/\n" +
        "ORDER BY T.punteo DESC\n" +
        "limit 5");
                     
        int contador = 0;
        
        try{
            while (resultado.next()) {
                Libros[contador] = resultado.getString("id") + "," + resultado.getString("titulo");                
                contador++;                
            }
            resultado.close();
        }catch(SQLException ex)
        {
            System.out.println("SQLException: "+ ex.getMessage());        
        }
        
        return Libros;
    }
    
    public static String[] RecomendacionPorAprendizaje(int id)
    {
        //Aprendizaje: ya tiene leidos, ver libros leidos de la persona, a partir de categoria 
        //y palabras clave buscar todos los libros y order by punteo libro y fecha leido
        String[] Libros = new String[5];
                
        conexion bdd = new conexion();
        ResultSet resultado = null;
        
        resultado = bdd.consulta(
        "SELECT T.titulo, T.id FROM (\n" +
        "	\n" +
        "	SELECT DISTINCT l.* \n" +
        "	FROM libro AS l\n" +
        "	INNER JOIN categoria_libro as cl2 ON (l.id = cl2.id_libro)\n" +
        "	WHERE cl2.id_categoria IN (\n" +
        "		SELECT DISTINCT cl.id_categoria\n" +
        "		FROM carretilla as c\n" +
        "		INNER JOIN categoria_libro as cl ON (c.id_libro = cl.id_libro)\n" +
        "		WHERE c.id_usuario in ("+ id + ") \n" +
        "		ORDER BY c.leido, c.punteo DESC\n" +
        "	)\n" +
        "\n" +
        "	UNION\n" +
        "\n" +
        "	\n" +
        "	SELECT DISTINCT l.* \n" +
        "	FROM libro AS l\n" +
        "	INNER JOIN palabraclave_libro as pl2 ON (l.id = pl2.id_libro)\n" +
        "	WHERE pl2.id_palabraclave IN (\n" +
        "		SELECT DISTINCT pl.id_palabraclave\n" +
        "		FROM carretilla as c\n" +
        "		INNER JOIN palabraclave_libro as pl ON (c.id_libro = pl.id_libro)\n" +
        "		WHERE c.id_usuario in ("+ id + ") \n" +
        "		ORDER BY c.leido, c.punteo DESC\n" +
        "	)\n" +
        ") AS T\n" +
        "WHERE T.id not IN\n" +
        "    (SELECT ca.id_libro from carretilla ca\n" +
        "    where ca.id_usuario in ("+ id + ")) /*se descartan los que tiene en carretilla*/\n" +
        "ORDER BY T.punteo DESC\n" +
        "limit 5");
                     
        int contador = 0;
        
        try{
            while (resultado.next()) {
                Libros[contador] = resultado.getString("id") + "," + resultado.getString("titulo");                
                contador++;                
            }
            resultado.close();
        }catch(SQLException ex)
        {
            System.out.println("SQLException: "+ ex.getMessage());        
        }
        
        return Libros;
    }
    
    public static String[] TraerCarretilla(int id)
    {   //Carretilla: muestra los 5 libros mas recientes agregados a la carretilla y sin haberse leído
        String[] Libros = new String[5];
        
        conexion bdd = new conexion();
        ResultSet resultado = null;
        
        resultado = bdd.consulta("SELECT l.titulo, c.id FROM carretilla c " +
        "INNER JOIN libro l on (c.id_libro = l.id) " +
        "WHERE id_usuario = " + id + " and leido = 0 and estatus = 1 " +
        "ORDER by c.id DESC LIMIT 5 ");
        
        int contador = 0;
        
        try{
            while (resultado.next()) {
                Libros[contador] = resultado.getString("id") + "," + resultado.getString("titulo");
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
