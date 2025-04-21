package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		String sql = "INSERT INTO empleados (nombre,email,salario,departamento_id)" + "VALUES(?,?,?,?)";
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


	public int actualizar(Empleado e) throws SQLException{
		   
		int filas = 0;
		String sql = "UPDATE empleados SET nombre = ?, email = ?, salario = ?, departamento_id = ? WHERE id = ?";
	    PreparedStatement ps = con.prepareStatement(sql); 

	        ps.setString(1, e.getNombre());
	        ps.setString(2, e.getEmail());
	        ps.setDouble(3, e.getSalario());
	        ps.setInt(4, e.getDepartamento_id());
	        ps.setInt(5,e.getId());
	       
	        filas = ps.executeUpdate();
	        ps.close();
	        return filas;

	

}
	
public int borrar(int id) throws SQLException {
		
		int filas =0;
		
		String sql = "DELETE FROM empleados WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		filas = ps.executeUpdate();
		ps.close();
		
		return filas;
}
	
	public ArrayList <Empleado> obtenerEmpleados() throws SQLException {
		ArrayList<Empleado> lista = null;
		String sql = "SELECT * FROM empleados";
		
		
	           try(  PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()){

	            while (rs.next()) {
	                
	            	if(lista == null) {
	            		lista = new ArrayList<Empleado>();
	            	}
	            	lista.add(new Empleado(rs.getInt("id"),rs.getString("nombre"),rs.getString("email"),rs.getInt("salario"),rs.getString("departamento_id")));
	                
	            }

	return lista;
	}
	

	}
	
	
	public String toJson () throws SQLException {
		
		String json = "";
		
		Gson gson = new Gson();
		
		json = gson.toJson(this.obtenerEmpleados());
		
		return json;
	}
	


	
	public Empleado obtenerEmpleado (int id) throws SQLException {
		
	
		
		String sql = "SELECT * FROM vista_empleado WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		Empleado e = new Empleado(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5));
		
		
		
		return e;
		
	}

	
	
}



