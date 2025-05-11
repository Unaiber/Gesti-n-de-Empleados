package modelo;

import java.sql.SQLException;

import DAO.UsuariosDAO;

/**
 * Clase que representa a un usuario del sistema. Contiene los atributos
 * necesarios para almacenar la información de un usuario y operaciones CRUD
 * asociadas con la base de datos a través de la clase UsuariosDAO.
 */
public class Usuario {

	// Atributos de la clase Usuario
	private int id; // Identificador único del usuario
	private String username; // Nombre de usuario del usuario
	private String password; // Contraseña del usuario
	private String rol; // Rol del usuario (ADMIN, RRHH, EMPLEADO, etc.)
	private String email; // Correo electrónico del usuario

	// Constructor por defecto
	public Usuario() {
	}

	/**
	 * Constructor con parámetros para crear un usuario nuevo. Este constructor se
	 * utiliza para insertar un nuevo usuario en la base de datos.
	 *
	 * @param username el nombre de usuario del usuario
	 * @param password la contraseña del usuario
	 * @param rol      el rol del usuario (ADMIN, RRHH, EMPLEADO, etc.)
	 * @param email    el correo electrónico del usuario
	 */
	public Usuario(String username, String password, String rol, String email) {
		this.username = username;
		this.password = password;
		this.rol = rol;
		this.email = email;
	}

	/**
	 * Constructor con parámetros para crear un usuario con los datos recuperados de
	 * la base de datos. Este constructor se utiliza para recuperar información de
	 * un usuario existente.
	 *
	 * @param id       el identificador único del usuario
	 * @param username el nombre de usuario del usuario
	 * @param password la contraseña del usuario
	 * @param email    el correo electrónico del usuario
	 * @param rol      el rol del usuario
	 */
	public Usuario(int id, String username, String password, String email, String rol) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.rol = rol;
		this.email = email;
	}

	// Métodos getter y setter para los atributos de la clase Usuario

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

	/**
	 * Obtiene la información de un usuario desde la base de datos mediante su
	 * nombre de usuario. Llama al método obtenerUsername del DAO de Usuarios.
	 *
	 * @throws SQLException si ocurre un error en la base de datos durante la
	 *                      consulta
	 */
	public void obtenerUsername() throws SQLException {
		UsuariosDAO.getConection();
		UsuariosDAO.obtenerUsername("username", "password");
	}

	/**
	 * Inserta un nuevo usuario en la base de datos utilizando el DAO de Usuarios.
	 * Llama al método insertarUsuario del DAO para realizar la operación de
	 * inserción.
	 *
	 * @throws SQLException si ocurre un error en la base de datos durante la
	 *                      inserción
	 */
	public void insertarUsuario() throws SQLException {
		UsuariosDAO.getConection().insertarUsuario(this);
	}
}
