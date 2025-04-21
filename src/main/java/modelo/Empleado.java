package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import DAO.EmpleadosDAO;

public class Empleado {

	
	 	private int id;
	    private String nombre;
	    private String email;
	    private int salario;
	    private int departamento_id;
	    private String departamento;
	    
	    
		public Empleado() {

		}


		public Empleado(String nombre, String email, int salario, int departamento_id) {
			
			this.nombre = nombre;
			this.email = email;
			this.salario = salario;
			this.departamento_id = departamento_id;
		}
		
		


		public Empleado(int id,String nombre, String email, int salario, String departamento) {
			this.id = id;
			this.nombre = nombre;
			this.email = email;
			this.salario = salario;
			this.departamento = departamento;
		}
		
		

		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getNombre() {
			return nombre;
		}


		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public int getSalario() {
			return salario;
		}


		public void setSalario(int salario) {
			this.salario = salario;
		}


		public int getDepartamento_id() {
			return departamento_id;
		}


		public void setDepartamento_id(int departamento_id) {
			this.departamento_id = departamento_id;
		}


		public String getDepartamento() {
			return departamento;
		}


		public void setDepartamento(String departamento) {
			this.departamento = departamento;
		}

		

		
		public void insertar() throws SQLException {
			
			EmpleadosDAO.getConection().insertar(this);
			
			
		}
		
		public void actualizar() throws SQLException {
			
			EmpleadosDAO.getConection().actualizar(this);
			
		}
		
		public Empleado obtenerEmpleado(int id) throws SQLException {
			
			Empleado e = EmpleadosDAO.getConection().obtenerEmpleado(id);
			System.out.println(e.toString());
			return e;
			
		}

		public String recibirEmpleados(int id) throws SQLException {
			
			Empleado e = EmpleadosDAO.getConection().obtenerEmpleado(id);
			
			
			this.id = e.getId();
			this.nombre = e.getNombre();
			this.email = e.getEmail();
			this.salario = e.getSalario();
			this.departamento = e.getDepartamento();
			
			
			Gson gson = new Gson();
			String json = gson.toJson(this);
			return json;
	}
			



		public String dameJson(int id) throws SQLException {
	
	Gson gson = new Gson();
	String json = gson.toJson(this.obtenerEmpleado(id));
	return json;
	
	
}
		public void borrar(int id) throws SQLException {	
	
			int filas = EmpleadosDAO.getConection().borrar(id);
	
}


		@Override
		public String toString() {
			return "Empleado [id=" + id + ", nombre=" + nombre + ", email=" + email + ", salario=" + salario
					+ ", departamento_id=" + departamento_id + ", departamento=" + departamento + "]";
		}
		
}

