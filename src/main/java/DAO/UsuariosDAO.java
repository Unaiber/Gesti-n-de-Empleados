package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

import modelo.Usuario;

public class UsuariosDAO {
	
	public  static Connection con;
	
	public UsuariosDAO () throws SQLException {
		
		UsuariosDAO.con = DatabaseConnection.getConnection();
	}
	
	
	public static UsuariosDAO getConection () throws SQLException {
	
		UsuariosDAO ud = new UsuariosDAO();
		
		return ud;
		
	}
	
	public static Usuario obtenerUsername (String username, String password) throws SQLException{
		String sql = "SELECT * FROM usuarios WHERE username = ?";
		
			
		 try (PreparedStatement ps = con.prepareStatement(sql)) {
			 	con = DatabaseConnection.getConnection();
	            ps.setString(1, username);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    // Recuperar el hash de la contraseña almacenado en la base de datos
	                    String storedHash = rs.getString("password");
	                    // Verificar la contraseña proporcionada con el hash almacenado
	                    if (BCrypt.checkpw(password, storedHash)) {
	                        // La contraseña es correcta, crear el objeto Usuario
	                        Usuario us = new Usuario();
	                        us.setUsername(rs.getString("username"));
	                        us.setEmail(rs.getString("email"));
	                        us.setPassword(storedHash);  // Opción: puedes almacenar solo el hash aquí
	                        us.setRol(rs.getString("rol"));
	                        return us;
	                    } else {
	                        // Contraseña incorrecta
	                        return null;
	                    }
	                }
	            }
	        }
	        return null;
	        
	    }
	
	public void insertarUsuario(Usuario u) {
		
		String sql = "INSERT INTO usuarios (username,password,rol,email)" + "VALUES(?,?,?,?)";
		String hashedPassword = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			con = DatabaseConnection.getConnection();
			ps.setString(1, u.getUsername());
			ps.setString(2, hashedPassword);
			ps.setString(3,u.getRol());
			ps.setString(4, u.getEmail());
			ps.executeUpdate();
			
			}
		
		catch (SQLException c) {
			
			c.printStackTrace();
			
		}
			
	}
	}


