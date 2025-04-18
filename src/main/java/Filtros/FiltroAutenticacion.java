  package Filtros;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebFilter(urlPatterns = {"/gestor/panelPrincipal.html",
	    "/gestor/empleados.html",
	    "/gestor/listadoEmpleados.html",
	    "/gestor/editorEmpleados.html",
	    "/gestor/altaEmpleados.html"})
public class FiltroAutenticacion implements Filter {

	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {

	        HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse res = (HttpServletResponse) response;

	        HttpSession session = req.getSession(false); // No crear una nueva sesión si no hay

	        // Validación: si hay sesión y el atributo "usuarioLogueado" está presente, dejamos pasar
	        if (session != null && session.getAttribute("usuarioLogueado") != null) {
	            chain.doFilter(request, response); // sigue hacia la página solicitada
	        } else {
	            res.sendRedirect("login.html"); // redirige al login si no está autenticado
	        }
	    }
}
