package modelo;

import java.sql.SQLException;

import DAO.EmpleadosDAO;

public class Empleado {

	
	 	private int id;
	    private String nombre;
	    private String email;
	    private int salario;
	    
	    
		public Empleado() {

		}


		public Empleado(String nombre, String email, int salario) {
			this.nombre = nombre;
			this.email = email;
			this.salario = salario;
		}
		
		


		public Empleado(int id, String nombre, String email, int salario) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.email = email;
			this.salario = salario;
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
		

		
		
		@Override
		public String toString() {
			return "Empleado [id=" + id + ", nombre=" + nombre + ", email=" + email + ", salario=" + salario + "]";
		}


		public void insertar() throws SQLException {
			
			EmpleadosDAO.getConection().insertar(this);
			// TODO Auto-generated method stub
			
		}

	}
			

	    

	

