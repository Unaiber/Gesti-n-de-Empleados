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

import org.mindrot.jbcrypt.BCrypt;

import DAO.UsuariosDAO;

/**
 * Servlet implementation class GestionUsuarios
 */
@WebServlet("/GestionUsuarios")
public class GestionUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionUsuarios() {
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
		// TODO Auto-generated method stub
		
		
		if (request.getParameter("logout") != null) {
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<script>");
	        out.println("sessionStorage.removeItem('usuarioLogueado');");
	        out.println("window.location.href = '/EMPLEADOS/gestor/index.html';"); // Redirigir al login
	        out.println("</script>");
	        
	        // Invalidar la sesión en el servidor
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate(); // Cerrar sesión en el servidor
	        }
	        return; // Salir después de logout
	    }

	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    try {
	        // Obtener el usuario de la base de datos
	        Usuario us = UsuariosDAO.obtenerUsername(username, password);

	        if (us != null) {
	            // Login correcto
	            HttpSession sesion = request.getSession();
	            sesion.setAttribute("usuario", us);
	            sesion.setAttribute("rol", us.getRol());
	            sesion.setAttribute("usuarioLogueado", true);
	            
	            // Enviar el script para establecer sessionStorage y redirigir
	            response.setContentType("text/html");
	            PrintWriter out = response.getWriter();
	            out.println("<script>");
	            out.println("sessionStorage.setItem('usuarioLogueado', 'true');"); // Establecer el valor en sessionStorage
	            out.println("window.location.href = '/EMPLEADOS/gestor/panelPrincipal.html';"); // Redirigir al dashboard
	            out.println("</script>");
	            
	            // Logueo correcto
	            System.out.println("Usuario autenticado: " + us.getUsername());
	            System.out.println("Rol del usuario: " + us.getRol());
	            System.out.println("Usuario logueado: " + sesion.getAttribute("usuarioLogueado"));
	        } else {
	            // Login fallido
	            response.sendRedirect("gestor/index.html?error=true");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        response.sendRedirect("gestor/index.html?error=db");
	    }

	    System.out.println("Username recibido: " + username);
	    System.out.println("Password recibido: " + password);
	}
	
}