package modelo;

import java.sql.SQLException;

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
		
		


		public Empleado(String nombre, String email, int salario, String departamento) {
			super();
			this.nombre = nombre;
			this.email = email;
			this.salario = salario;
			this.departamento = departamento;
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

		

		@Override
		public String toString() {
			return "Empleado [id=" + id + ", nombre=" + nombre + ", email=" + email + ", salario=" + salario
					+ ", departamento_id=" + departamento_id + ", departamento=" + departamento + "]";
		}


		public void insertar() throws SQLException {
			
			EmpleadosDAO.getConection().insertar(this);
			// TODO Auto-generated method stub
			
		}

	}
			

	    

	

