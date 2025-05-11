package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexión a la base de datos. Proporciona métodos para
 * obtener una conexión y verificar su estado. La conexión es única y estática,
 * compartida por toda la aplicación.
 */
public class DatabaseConnection {

	// Instancia estática de la conexión, compartida por toda la aplicación
	public static Connection instance = null;

	// URL de conexión JDBC a la base de datos 'gestorempleados' en el puerto 3360
	private static final String JDBC_URL = "jdbc:mysql://localhost:3360/gestorempleados";

	/**
	 * Obtiene la conexión a la base de datos. Si la conexión no está establecida,
	 * se crea una nueva instancia.
	 * 
	 * @return La instancia de conexión a la base de datos.
	 */
	public static Connection getConnection() {
		try {
			// Establece la conexión usando DriverManager con usuario 'root' y sin
			// contraseña
			instance = DriverManager.getConnection(JDBC_URL, "root", "");
			System.out.println("Connection to BD ready");
		} catch (SQLException e) {
			// Imprime el error si ocurre una excepción relacionada con SQL
			e.printStackTrace();
		} catch (Exception ex) {
			// Imprime cualquier otro tipo de error no específico
			ex.printStackTrace();
		}
		// Devuelve la conexión
		return instance;
	}

	/**
	 * Verifica si la conexión a la base de datos es válida. Se valida la conexión
	 * con un timeout de 10 segundos.
	 * 
	 * @return true si la conexión es válida, false si no lo es.
	 */
	public static boolean isConnectionReady() {
		try {
			// Verifica si la conexión sigue siendo válida con un timeout de 10 segundos
			return instance.isValid(10);
		} catch (SQLException e) {
			// En caso de error, imprime la traza y devuelve false
			e.printStackTrace();
			return false;
		}
	}
}
