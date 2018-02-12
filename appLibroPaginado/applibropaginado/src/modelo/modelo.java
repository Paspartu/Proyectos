package modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBBDD;


public class modelo {
	// atributos
    private final Connection conexion;

    
    /**
     * Constructor
     * @param conexion conexión a la BBDD
     * @param controlador
     */
    public modelo(Connection conexion1) {
        conexion = conexion1;
       
    }
    
    public Object[][] getTituloPorAutor(int nPagina){
        
        int registros = 0;
        
        try{
           PreparedStatement pstm = conexion.prepareStatement( "SELECT count(*) as total FROM libros");
            try (ResultSet res = pstm.executeQuery()) {
                res.next();
                registros = res.getInt("total");
            }
        }catch(SQLException e){
           System.err.println( e.getMessage() ); 
        }
      //se crea una matriz con tantas filas y columnas que necesite
      //columnas que tiene la tabla
     // Object[][] data = new Object[registros][2]; 
        Object[][] data = new Object[2][2]; 
        try{
        	/*SELECT libros.titulo, autores.nombre
        	FROM libros
        	JOIN autores
        	ON libros.id_autor=autores.id_autor
        	ORDER BY autores.nombre;*/
        	
            //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
           PreparedStatement pstm = conexion.prepareStatement("SELECT autores.nombre,libros.titulo FROM autores, libros WHERE  libros.id_autor=autores.id_autor ORDER BY autores.nombre, libros.titulo  LIMIT 2 OFFSET "+nPagina*2);
            try (ResultSet res = pstm.executeQuery()) {
                int i=0;
                while(res.next()){
                	data[i][0] = res.getString( "nombre" );
        			data[i][1] = res.getString( "titulo" );
                	/*if(i==(nPagina*2))
                	{	
                		for(int e=0;e<2;e++)
                		{
                			data[e][0] = res.getString( "nombre" );
                			data[e][1] = res.getString( "titulo" );  
                		}
                	}*/
                    i++;
                }}
         
           }catch(SQLException e){
              System.err.println( e.getMessage() );
          }
        conexion.close();
          return data;
      }
/*
public Object[] getAutores() {
int registros = 0;
        
        try{
           PreparedStatement pstm = conexion.prepareStatement( "SELECT count(*) as total FROM autores");
            try (ResultSet res = pstm.executeQuery()) {
                res.next();
                registros = res.getInt("total");
            }
        }catch(SQLException e){
           System.err.println( e.getMessage() ); 
        }
      //se crea una matriz con tantas filas y columnas que necesite
      //columnas que tiene la tabla
      Object[] data = new Object[registros]; 
        try{
       
            //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
           PreparedStatement pstm = conexion.prepareStatement("SELECT nombre FROM autores ORDER BY nombre");
            try (ResultSet res = pstm.executeQuery()) {
                int i=0;
                while(res.next()){
                    data[i] = res.getString( "nombre" );           
                    i++;
                }}
         
           }catch(SQLException e){
              System.err.println( e.getMessage() );
          }
          return data;	
		
	
	}*/

	public int getNumeroRegistros() {
		// TODO Auto-generated method stub
		
	        
	        int registros = 0;
	        
	        try{
	           PreparedStatement pstm = conexion.prepareStatement( "SELECT count(*) as total FROM libros");
	            try (ResultSet res = pstm.executeQuery()) {
	                res.next();
	                registros = res.getInt("total");
	            }
	        }catch(SQLException e){
	           System.err.println( e.getMessage() ); 
	        }
	        return registros;
	}
    
    
}
