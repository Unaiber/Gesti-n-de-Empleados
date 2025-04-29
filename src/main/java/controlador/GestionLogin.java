package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import DAO.UsuariosDAO;

/**
 * Servlet implementation class GestionLogin
 */
@WebServlet("/GestionLogin")
public class GestionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    // Comprobar si la petición es para cerrar sesión (logout)
	    if (request.getParameter("logout") != null) {
	        HttpSession session = request.getSession(false); // Obtener sesión actual si existe
	        if (session != null) {
	            session.invalidate(); // Invalida (cierra) la sesión del usuario
	        }
	        response.sendRedirect("gestor/index.html"); // Redirige al login tras cerrar sesión
	        return; // Salir del método
	    }

	    // Obtener los parámetros del formulario de login
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    try {
	        // Verificar las credenciales del usuario con la base de datos
	        Usuario us = UsuariosDAO.obtenerUsername(username, password);

	        if (us != null) {
	            // Si el usuario es válido, crear o recuperar la sesión
	            HttpSession sesion = request.getSession();
	            sesion.setAttribute("usuario", us); // Guardar el usuario en la sesión

	            // Redirigir al panel principal si el login fue exitoso
	            response.sendRedirect("gestor/panelPrincipal.html");

	            // Mensajes en consola para depuración
	            System.out.println("Usuario autenticado: " + us.getUsername());
	            System.out.println("Rol del usuario: " + us.getRol());

	        } else {
	            // Si las credenciales no son válidas, redirige al login con error
	            response.sendRedirect("gestor/index.html?error=true");
	        }

	    } catch (SQLException e) {
	        // Capturar posibles errores de conexión o consulta
	        e.printStackTrace();
	        response.sendRedirect("gestor/index.html?error=db"); // Redirige con error de base de datos
	    }
	}
	
}
