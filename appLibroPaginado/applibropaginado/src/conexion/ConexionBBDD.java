package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBBDD {
	
	Connection conx;
	
	public ConexionBBDD(){
		conectar();
	}
	
	public void conectar(){
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://192.168.32.48/libros";
			Properties props = new Properties();
			props.setProperty("user", "usuario");
			props.setProperty("password", "usuaria");
			try {
				conx = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

    public Connection getConexion(){
    	return conx;
    }

}
