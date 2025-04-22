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
import modelo.Usuario;

import java.io.IOException;
@WebFilter(urlPatterns = {
		"/gestor/panelPrincipal.html",
	    "/gestor/listadoEmpleados.html"})
public class FiltroAutenticacion implements Filter {

	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {

	        HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse res = (HttpServletResponse) response;

	        HttpSession session = req.getSession(false); // No crear una nueva sesión si no hay

	        Usuario u = (Usuario) session.getAttribute("usuario");
	        boolean logueado = (session != null && u != null);

	        if (!logueado) {
	            res.sendRedirect(req.getContextPath() + "/gestor/index.html");
	            return;
	        }

	        chain.doFilter(request, response);
	    }
	}
	    




	 	
