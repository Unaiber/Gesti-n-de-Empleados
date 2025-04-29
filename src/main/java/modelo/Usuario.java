package modelo;

import java.sql.SQLException;

import DAO.UsuariosDAO;

//Clase que representa a un empleado y operaciones CRUD asociadas.
public class Usuario {
	
	// Atributos de la clase Usuario que almacenan los datos de un usuario
	private int id; // Identificador único del usuario
	private String username; // Nombre de usuario del usuario
	private String password; // Correo electrónico del usuario
	private String rol; // Rol del usuario
	private String email; // Correo electrónico del usuario
	
	// Constructor por defecto
	public Usuario() {
	
	}
	// Constructor con parámetros para crear un usuario, utilizado para la operación insertarUsuario.
	public Usuario(String username, String password, String rol, String email) {
		this.username = username;
		this.password = password;
		this.rol = rol;
		this.email = email;
	}
	// Constructor con parámetros para crear un usuario, utilizado para la operación obtenerUsuario.
	public Usuario(int id, String username, String password, String email, String rol) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.rol = rol;
		this.email = email;
	}

	// Métodos getter y setter para los atributos de la clase Empleado
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	// Método para obtener la información de un usuario de la base de datos utilizando UsuariosDAO
	public void obtenerUsername() throws SQLException {
		
		UsuariosDAO.getConection();
		UsuariosDAO.obtenerUsername("username","password");
		
		
	}
	// Método para insertar la información de un usuario en la base de datos utilizando UsuariosDAO
	public void insertarUsuario() throws SQLException {

		UsuariosDAO.getConection().insertarUsuario(this);
		
	}
}
