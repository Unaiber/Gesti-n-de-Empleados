package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

import modelo.Usuario;

/**
 * Clase DAO para realizar operaciones con la tabla 'usuarios' en la base de datos.
 * Proporciona métodos para autenticar usuarios, insertar nuevos usuarios y obtener la conexión.
 */
public class UsuariosDAO {
    
    // Conexión compartida (estática) a la base de datos
    public static Connection con;

    /**
     * Constructor que obtiene una conexión desde la clase DatabaseConnection.
     * 
     * @throws SQLException si ocurre un error al obtener la conexión
     */
    public UsuariosDAO () throws SQLException {
        UsuariosDAO.con = DatabaseConnection.getConnection();
    }

    /**
     * Método estático que devuelve una nueva instancia del DAO.
     * Funciona como un "factory method" para crear una nueva instancia de UsuariosDAO.
     * 
     * @return una nueva instancia de UsuariosDAO
     * @throws SQLException si ocurre un error al obtener la conexión
     */
    public static UsuariosDAO getConection () throws SQLException {
        UsuariosDAO ud = new UsuariosDAO();
        return ud;
    }

    /**
     * Autentica un usuario por su nombre de usuario y contraseña.
     * Realiza una consulta a la base de datos para comprobar las credenciales del usuario.
     * La contraseña almacenada en la base de datos se compara con la proporcionada utilizando BCrypt.
     * 
     * @param username el nombre de usuario
     * @param password la contraseña del usuario
     * @return un objeto Usuario si las credenciales son válidas, null si no lo son
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    public static Usuario obtenerUsername(String username, String password) throws SQLException {
        // Consulta SQL para obtener un usuario por nombre de usuario
        String sql = "SELECT * FROM usuarios WHERE username = ?";

        // Obtiene la conexión desde la clase DatabaseConnection
        con = DatabaseConnection.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username); // Establece el nombre de usuario en la consulta
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Recupera el hash de la contraseña almacenada en la base de datos
                    String storedHash = rs.getString("password");

                    // Compara la contraseña proporcionada con el hash usando BCrypt
                    if (BCrypt.checkpw(password, storedHash)) {
                        // Si la contraseña es válida, crea el objeto Usuario
                        Usuario us = new Usuario();
                        us.setUsername(rs.getString("username"));
                        us.setEmail(rs.getString("email"));
                        us.setRol(rs.getString("rol"));
                        return us;
                    } else {
                        // Contraseña incorrecta
                        return null;
                    }
                }
            }
        }
        // Usuario no encontrado o error en la autenticación
        return null;
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     * La contraseña proporcionada es encriptada utilizando BCrypt antes de ser insertada.
     * 
     * @param u el objeto Usuario a insertar
     */
    public void insertarUsuario(Usuario u) {
        // Se obtiene la conexión
        con = DatabaseConnection.getConnection();

        // Sentencia SQL para insertar un nuevo usuario
        String sql = "INSERT INTO usuarios (username,password,rol,email) VALUES(?,?,?,?)";

        // Se encripta la contraseña antes de insertarla
        String hashedPassword = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getUsername());
            ps.setString(2, hashedPassword); // Se guarda el hash, no la contraseña en texto plano
            ps.setString(3, u.getRol());
            ps.setString(4, u.getEmail());
            ps.executeUpdate(); // Ejecuta la inserción
            ps.close(); // Cierra el PreparedStatement
        } catch (SQLException c) {
            // Manejo de errores al insertar usuario
            c.printStackTrace();
        }
    }
}

