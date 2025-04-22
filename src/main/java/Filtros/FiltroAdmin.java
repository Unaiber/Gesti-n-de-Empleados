package Filtros;

import java.io.IOException;

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


@WebFilter(urlPatterns = {"/gestor/altaUsuarios.html"})
public class FiltroAdmin implements Filter{
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
 			throws IOException, ServletException {
            
 		HttpServletRequest req = (HttpServletRequest) request;
 		HttpServletResponse res = (HttpServletResponse) response;

 		HttpSession sesion = req.getSession(false);

 		Usuario u = (Usuario) sesion.getAttribute("usuario");
 		
 		if (sesion != null && u != null && "ADMIN".equals(u.getRol())) {
 			chain.doFilter(request, response); // Permitir acceso si es admin
 		} else {
 			res.sendRedirect(req.getContextPath() + "/gestor/panelPrincipal.html?error=accesoDenegado");
 		}
 	}
    
}

