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
		
			String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String email = request.getParameter("email");
	        String rol = request.getParameter("rol");
	        String accion = request.getParameter("accion");

	        Usuario u = new Usuario(username, password, rol, email);
	      

	        try  {
	            UsuariosDAO dao = new UsuariosDAO();
	            
	            if ("crearUsuario".equals(accion)) {
	            	dao.insertarUsuario(u);

	            }
	            
	            else {
	            	response.sendRedirect("gestor/panelPrincipal.html");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            response.sendRedirect("gestor/altaUsuarios.html?error=bd");
	        }
	    }
	}
	
