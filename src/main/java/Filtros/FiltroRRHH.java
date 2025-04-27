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


@WebFilter(urlPatterns = {
		"/gestor/altaEmpleados.html",
		"/gestor/editorEmpleados.html",
})
public class FiltroRRHH implements Filter{
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
 			throws IOException, ServletException {
            
 		HttpServletRequest req = (HttpServletRequest) request;
 		HttpServletResponse res = (HttpServletResponse) response;

 		HttpSession sesion = req.getSession(false);

 		if (sesion == null || sesion.getAttribute("usuario") == null) {
            res.sendRedirect(req.getContextPath() + "/gestor/index.html");
            return;
        }

        Usuario u = (Usuario) sesion.getAttribute("usuario");

        if ("ADMIN".equals(u.getRol()) || "RRHH".equals(u.getRol())) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/gestor/panelPrincipal.html?error=accesoDenegado");
        }
    }
    
}