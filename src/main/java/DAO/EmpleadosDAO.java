package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Empleado;

//Clase DAO para gestionar operaciones CRUD sobre empleados
public class EmpleadosDAO {
	
	// Atributo de conexión a la base de datos
	public Connection con;
	
	// Constructor que obtiene la conexión desde la clase DatabaseConnection
	public EmpleadosDAO () throws SQLException {
		this.con = DatabaseConnection.getConnection();		
	}

	// Método estático que devuelve una nueva instancia del DAO
	public static EmpleadosDAO getConection () throws SQLException {
		EmpleadosDAO ed = new EmpleadosDAO();
		return ed;
	}
	
	// Nuevo constructor para test con conexión personalizada (ej: SQLite)
	public EmpleadosDAO(Connection connection) {
		this.con = connection;
	}
	
	// Inserta un nuevo empleado en la base de datos
	public void insertar(Empleado e) {
		String sql = "INSERT INTO empleados (nombre,email,salario,departamento_id) VALUES(?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, e.getNombre());
			ps.setString(2, e.getEmail());
			ps.setInt(3, e.getSalario());
			ps.setInt(4, e.getDepartamento_id());
			ps.executeUpdate(); // Ejecuta la inserción
			ps.close();
		} catch (SQLException c) {
			c.printStackTrace(); // Imprime error si falla la inserción
		}
	}

	// Actualiza un empleado existente y devuelve el número de filas afectadas
	public int actualizar(Empleado e) throws SQLException {
		int filas = 0;
		String sql = "UPDATE empleados SET nombre = ?, email = ?, salario = ?, departamento_id = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, e.getNombre());
		ps.setString(2, e.getEmail());
		ps.setDouble(3, e.getSalario());
		ps.setInt(4, e.getDepartamento_id());
		ps.setInt(5, e.getId());
		filas = ps.executeUpdate(); // Ejecuta actualización
		ps.close();
		return filas;
	}

	// Elimina un empleado según su ID y devuelve las filas eliminadas
	public int borrar(int id) throws SQLException {
		int filas = 0;
		String sql = "DELETE FROM empleados WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		filas = ps.executeUpdate(); // Ejecuta eliminación
		ps.close();
		return filas;
	}

	// Recupera todos los empleados desde la base de datos y los devuelve en una lista
	public ArrayList<Empleado> obtenerEmpleados() throws SQLException {
		ArrayList<Empleado> lista = null;
		String sql = "SELECT * FROM empleados";
		try (PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				if (lista == null) {
					lista = new ArrayList<Empleado>();
				}
				// Se asume que "departamento_id" se guarda como String, aunque es un int
				lista.add(new Empleado(
					rs.getInt("id"),
					rs.getString("nombre"),
					rs.getString("email"),
					rs.getInt("salario"),
					rs.getString("departamento_id")
				));
			}
			return lista;
		}
	}

	// Devuelve todos los empleados convertidos a formato JSON
	public String toJson () throws SQLException {
		String json = "";
		Gson gson = new Gson();
		json = gson.toJson(this.obtenerEmpleados());
		return json;
	}

	// Obtiene un empleado a partir de su ID desde la vista 'vista_empleado'
	public Empleado obtenerEmpleado (int id) throws SQLException {
		String sql = "SELECT * FROM empleados WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next(); // Avanza a la primera fila del resultado

		// Crea un nuevo objeto Empleado con los datos obtenidos
		Empleado e = new Empleado(
			rs.getInt(1),
			rs.getString(2),
			rs.getString(3),
			rs.getInt(4),
			rs.getString(5)
		);
		
		return e;
	}
	
}



