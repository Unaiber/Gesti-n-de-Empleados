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

//Anotación que indica que este filtro se aplicará a las siguientes URL 
@WebFilter(urlPatterns = {
		"/gestor/panelPrincipal.html",
	    "/gestor/listadoEmpleados.html"
})
public class FiltroAutenticacion implements Filter {

		// Método que intercepta las peticiones sin loguear
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {

			 // Cast a HttpServletRequest y HttpServletResponse para usar métodos HTTP específicos (necesarios para getSession() y sendRedirect())
	        HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse res = (HttpServletResponse) response;
	        // Se obtiene la sesión actual del usuario, sin crear una nueva si no existe
	        HttpSession sesion = req.getSession(false);
	        
	        // Si no hay sesión o no hay atributo "usuario", se redirige al login (index.html)
	        if (sesion == null || sesion.getAttribute("usuario") == null) {
	            res.sendRedirect(req.getContextPath() + "/gestor/index.html");
	            return;
	        }

	        chain.doFilter(request, response);
	    }
	}
	    




	 	
