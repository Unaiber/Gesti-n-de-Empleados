package DAO;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {

		public static Connection instance = null;
		private static final String JDBC_URL = "jdbc:mysql://localhost:3360/gestorempleados";
		
		
		
		public static Connection getConnection(){
			try {
				instance = DriverManager.getConnection(JDBC_URL,"root","");
				System.out.println("Connection to BD ready");
				
			}catch (SQLException e) {
				e.printStackTrace();
				//throw new NullPointerException();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			return instance;
			
		}
		
		public static boolean isConnectionReady() {
			try {
				return instance.isValid(10);
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

public static void main(String[] args) {

	Connection hola = getConnection();	
	System.out.println(isConnectionReady());	
	
	String obtain_empleados_query = "Select * from empleados;";
	try {
		PreparedStatement ps = hola.prepareStatement(obtain_empleados_query);
		//Para cuando haces un SELECT
		ps.executeQuery();
		//Para cuando modificas tabla
		//ps.executeUpdate(); 
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	
}


}




