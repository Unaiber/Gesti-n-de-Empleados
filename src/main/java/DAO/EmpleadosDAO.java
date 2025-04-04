package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import modelo.Empleado;

public class EmpleadosDAO {
	
	public Connection con;
	
	public EmpleadosDAO () throws SQLException {
	
		this.con = DatabaseConnection.getConnection();		
	}
	//Singleton
	public static EmpleadosDAO getConection () throws SQLException {
	
		EmpleadosDAO ed = new EmpleadosDAO();
		
		return ed;
	}
	
	public void insertar(Empleado e) {
		String sql = "INSERT INTO empleados (nombre,email,salario, departamento_id)" + "VALUES(?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, e.getNombre());
			ps.setString(2, e.getEmail());
			ps.setInt(3, e.getSalario());
			ps.setInt(4,e.getDepartamento_id());
			ps.executeUpdate();
			
			ps.close();
			}
		
		catch (SQLException c) {
			
			c.printStackTrace();
			
			}
	}
	
	
	

	public ArrayList <Empleado> obtenerEmpleados() throws SQLException {
		ArrayList<Empleado> lista = null;
		String sql = "SELECT * FROM vista_empleado";
		
		
	           try(  PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()){

	            while (rs.next()) {
	                
	            	if(lista == null) {
	            		lista = new ArrayList<Empleado>();
	            	}
	            	lista.add(new Empleado(rs.getString("nombre"),rs.getString("email"),rs.getInt("salario"),rs.getString("departamento")));
	                
	            }
		
		
	ps.close();	
	return lista;
	}
	

	}
	
	
	public String toJson () throws SQLException {
		
		String json = "";
		
		Gson gson = new Gson();
		
		json = gson.toJson(this.obtenerEmpleados());
		
		return json;
	}
	
	
}
	
	




