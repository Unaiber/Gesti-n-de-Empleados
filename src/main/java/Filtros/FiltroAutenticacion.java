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
@WebFilter(urlPatterns = {
		"/gestor/panelPrincipal.html",
	    "/gestor/listadoEmpleados.html"})
public class FiltroAutenticacion implements Filter {

	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {

	        HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse res = (HttpServletResponse) response;

	        HttpSession sesion = req.getSession(false); // No crear una nueva sesión si no hay


	        if (sesion == null || sesion.getAttribute("usuario") == null) {
	            res.sendRedirect(req.getContextPath() + "/gestor/index.html");
	            return;
	        }

	        chain.doFilter(request, response);
	    }
	}
	    




	 	
