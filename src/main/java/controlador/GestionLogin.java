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
		 // Comprobar si es un logout
	    if (request.getParameter("logout") != null) {
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate(); // Cerrar sesión en el servidor
	        }
	        response.sendRedirect("gestor/index.html");
	        return;
	    }
		
		String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Usuario us = UsuariosDAO.obtenerUsername(username, password);
            if (us != null) {
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", us);
                //sesion.setAttribute("rol", us.getRol());
                //sesion.setAttribute("usuarioLogueado", true);
                response.sendRedirect("gestor/panelPrincipal.html");
                
                System.out.println("Usuario autenticado: " + us.getUsername());
                System.out.println("Rol del usuario: " + us.getRol());
                
            } else {
                response.sendRedirect("gestor/index.html?error=true");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("gestor/index.html?error=db");
        }
    }
}
