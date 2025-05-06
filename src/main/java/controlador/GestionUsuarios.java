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
     * Constructor de la clase {@link GestionUsuarios}.
     * Este constructor invoca el constructor de la clase base {@link HttpServlet}.
     */
    public GestionUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Maneja las solicitudes GET para la gestión de usuarios.
     * Actualmente no se implementa ninguna lógica para el método GET.
     * @param request La solicitud HTTP del cliente.
     * @param response La respuesta HTTP que se enviará al cliente.
     * @throws ServletException Si ocurre un error al procesar la solicitud.
     * @throws IOException Si ocurre un error de entrada/salida al manejar la solicitud.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implementar lógica si es necesario para solicitudes GET
    }

    /**
     * Maneja las solicitudes POST para la gestión de usuarios.
     * Este método procesa los datos enviados desde un formulario para crear un nuevo usuario.
     * 
     * @param request La solicitud HTTP del cliente.
     * @param response La respuesta HTTP que se enviará al cliente.
     * @throws ServletException Si ocurre un error al procesar la solicitud.
     * @throws IOException Si ocurre un error de entrada/salida al manejar la solicitud.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Obtención de los parámetros enviados desde el formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String rol = request.getParameter("rol");
        String accion = request.getParameter("accion");

        // Creación del objeto Usuario con los datos obtenidos
        Usuario u = new Usuario(username, password, rol, email);

        try {
            // Creación del objeto DAO para gestionar la base de datos
            UsuariosDAO dao = new UsuariosDAO();
            
            // Si la acción es "crearUsuario", se inserta el nuevo usuario en la base de datos
            if ("crearUsuario".equals(accion)) {
                dao.insertarUsuario(u);  // Llamada al método para insertar el usuario
                // Redirección a la página de panel principal con mensaje de éxito en la URL
                response.sendRedirect("gestor/panelPrincipal.html?registro=ok");
            } 
            // Si la acción no es "crearUsuario", solo se redirige al panel principal
            else {
                response.sendRedirect("gestor/panelPrincipal.html");
            }
        } catch (SQLException e) {
            // En caso de error en la base de datos, se imprime el error y se redirige con un mensaje de error
            e.printStackTrace();
            response.sendRedirect("gestor/altaUsuarios.html?error=bd");
        }
    }
}

	
