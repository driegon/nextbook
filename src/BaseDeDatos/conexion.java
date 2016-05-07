/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;
import java.sql.*;

/**
 *
 * @author digut
 */
public class conexion {
   private Connection con;
   
   /**
    * Realiza la conexión a la BDD
    */
   private void conectar()
   {
       con = null;
       try
       {
           Class.forName("com.mysql.jdbc.Driver").newInstance();
           System.out.println("Registro exitoso");
       }
       catch(Exception e)
       {
           System.out.println(e.toString());
       }
       
       try{
           con = DriverManager.getConnection("jdbc:mysql://localhost/next.book?user=root&password=");
       } catch (SQLException ex){
           System.out.println("SQLException: "+ ex.getMessage());
       }
   }
   
   /**
    * Realiza el cierre de la conexión abierta. 
    */
   public void cerrarConexion()
   {
       try{
            con.close();
        }catch(SQLException ex)
        {
            System.out.println("SQLException: "+ ex.getMessage());        
        }
   }
   
   /**  
    * Permite realizar consultas del tipo SELECT a la base. Retorna un ResultSet que se puede
    * recorrer con un WHILE de su funcion NEXT(). Si tiene algun problema lo imprime a la linea de comandos.
    * @param query Contiene el SELECT que se desea ejecutar. 
    * @return 
    */
   public ResultSet consulta(String query)
   {
       con = null;
       ResultSet rs = null;
       Statement cmd = null;
       
       conectar();
       
       try{
           cmd = con.createStatement();
           rs = cmd.executeQuery(query);
           /* // De esta manera puedes recorrer los resultados del query: 
            while (rs.next()) {
                 String nombre = rs.getString("nombre") + " " + rs.getString("apellido") ;
                 System.out.println(nombre);
             }*/
       }
       catch (SQLException ex)
        {
            System.out.println("SQLException: "+ ex.getMessage());
       }
       
       return rs;
   }
   
   /**
    * Permite realizar UPDATE / DELETE / INSERT a la base de datos. Retorna cadena vacía si no 
    * hubo problemas durante la ejecución. De lo contrario retorna el error en un STRING.
    * @param query Contiene el query de la sentencia a ejecutar. 
    * @return 
    */
   public String ejecutarQuery(String query)
   {
       String resultado = "";
       Statement cmd = null;
       con = null;
       
       conectar();
       
       try{
           cmd = con.createStatement();
           cmd.executeUpdate(query);
           
           //con.close();
       }
       catch (SQLException ex)
       {
            resultado = "SQLException: "+ ex.getMessage();
       }
       
       return resultado;
   }
}

