package modelo;

import java.sql.SQLException;

import DAO.UsuariosDAO;

public class Usuario {
	
	private int id;
	private String username;
	private String password;
	private String rol;
	private String email;
	
	
	public Usuario() {
	
	}
	
	public Usuario(String username, String password, String rol, String email) {
		this.username = username;
		this.password = password;
		this.rol = rol;
		this.email = email;
	}

	public Usuario(int id, String username, String password, String email, String rol) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.rol = rol;
		this.email = email;
	}


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

	

	@Override
	public String toString() {
		return "Usuarios [id=" + id + ", username=" + username + ", password=" + password + ", rol=" + rol + ", email="
				+ email + "]";
	}


	public void obtenerUsername() throws SQLException {
		
		UsuariosDAO.getConection();
		UsuariosDAO.obtenerUsername("username","password");
		
		
	}

	public void insertarUsuario() throws SQLException {

		UsuariosDAO.getConection().insertarUsuario(this);
		
	}
	
	

}
