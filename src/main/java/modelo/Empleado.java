package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import DAO.EmpleadosDAO;

//Clase que representa a un empleado y operaciones CRUD asociadas.
public class Empleado {

	 // Atributos de la clase Empleado que almacenan los datos de un empleado
	 	private int id; // Identificador único del empleado
	    private String nombre; // Nombre del empleado
	    private String email; // Correo electrónico del empleado
	    private int salario; // Salario del empleado
	    private int departamento_id; // Identificador del departamento al que pertenece el empleado
	    private String departamento; // Nombre del departamento al que pertenece el empleado
	    
	    // Constructor por defecto
		public Empleado() {

		}

		// Constructor con parámetros para crear un empleado, utilizado para las operaciones insertar y actualizar.
		public Empleado(String nombre, String email, int salario, int departamento_id) {
			
			this.nombre = nombre;
			this.email = email;
			this.salario = salario;
			this.departamento_id = departamento_id;
		}
		
		

		 // Constructor con parámetros para crear un empleado, utilizado para las operaciones leer y borrar.
		public Empleado(int id,String nombre, String email, int salario, String departamento) {
			this.id = id;
			this.nombre = nombre;
			this.email = email;
			this.salario = salario;
			this.departamento = departamento;
		}
		
		
		// Métodos getter y setter para los atributos de la clase Empleado
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

		

		// Método para insertar un nuevo empleado en la base de datos utilizando EmpleadosDAO
		public void insertar() throws SQLException {
			
			EmpleadosDAO.getConection().insertar(this);
			
			
		}
		// Método para actualizar la información de un empleado en la base de datos utilizando EmpleadosDAO
		public void actualizar() throws SQLException {
			
			EmpleadosDAO.getConection().actualizar(this);
			
		}
		// Método para obtener un empleado de la base de datos mediante su id
		public Empleado obtenerEmpleado(int id) throws SQLException {
			
			Empleado e = EmpleadosDAO.getConection().obtenerEmpleado(id);
			System.out.println(e.toString());
			return e;
			
		}
		// Método que recibe un empleado de la base de datos y lo convierte a formato JSON usando Gson
		public String recibirEmpleados(int id) throws SQLException {
			
			Empleado e = EmpleadosDAO.getConection().obtenerEmpleado(id);
			
			// Asigna los valores del empleado obtenido a los atributos de la instancia actual
			this.id = e.getId();
			this.nombre = e.getNombre();
			this.email = e.getEmail();
			this.salario = e.getSalario();
			this.departamento = e.getDepartamento();
			
			// Convierte el objeto empleado a JSON y lo devuelve
			Gson gson = new Gson();
			String json = gson.toJson(this);
			return json;
	}
			


		// Método que devuelve un empleado en formato JSON basado en su id
		public String dameJson(int id) throws SQLException {
	
			Gson gson = new Gson();
			// Convierte el empleado obtenido en formato JSON y lo retorna
			String json = gson.toJson(this.obtenerEmpleado(id));
			return json;
	
	
}
		// Método para eliminar un empleado de la base de datos mediante su id
		public void borrar(int id) throws SQLException {	
	
			int filas = EmpleadosDAO.getConection().borrar(id);
		}
}

