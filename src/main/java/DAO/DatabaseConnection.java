package DAO;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//Clase encargada de gestionar la conexión con la base de datos MySQL
public class DatabaseConnection {

	 	// Instancia estática de la conexión, compartida por toda la aplicación
		public static Connection instance = null;
		 // URL de conexión JDBC a la base de datos 'gestorempleados' en el puerto 3360
		private static final String JDBC_URL = "jdbc:mysql://localhost:3360/gestorempleados";
		
		
		
		public static Connection getConnection(){
			try {
				// Establece la conexión usando DriverManager con usuario 'root' y sin contraseña
				instance = DriverManager.getConnection(JDBC_URL,"root","");
				System.out.println("Connection to BD ready");
				
			}catch (SQLException e) {
				// Imprime el error si ocurre una excepción relacionada con SQL
				e.printStackTrace();
			}catch(Exception ex) {
				// Imprime cualquier otro tipo de error no específico
				ex.printStackTrace();
			}
			// Devuelve la conexión
			return instance;
			
		}
		
		public static boolean isConnectionReady() {
			try {
				// Verifica si la conexión sigue siendo válida con un timeout de 10 segundos
				return instance.isValid(10);
			}catch(SQLException e) {
				// En caso de error, imprime la traza y devuelve false
				e.printStackTrace();
				return false;
			}
		}

}


