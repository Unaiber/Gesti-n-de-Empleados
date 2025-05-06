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

/**
 * Filtro que restringe el acceso a las páginas de alta y edición de empleados a usuarios con roles 'ADMIN' o 'RRHH'.
 * Si el usuario no tiene una sesión activa o no tiene los permisos adecuados, se le redirige al login o al panel principal con un mensaje de error.
 */
@WebFilter(urlPatterns = {
        "/gestor/altaEmpleados.html",
        "/gestor/editorEmpleados.html",
})
public class FiltroRRHH implements Filter {

    /**
     * Método que intercepta las peticiones a las URLs protegidas.
     * Verifica si el usuario tiene sesión activa y si tiene el rol adecuado ('ADMIN' o 'RRHH') para acceder a las páginas.
     * Si el usuario no cumple los requisitos, se le redirige al login o al panel principal.
     *
     * @param request la solicitud HTTP recibida
     * @param response la respuesta HTTP que se enviará
     * @param chain la cadena de filtros que se debe ejecutar si el usuario tiene acceso
     * @throws IOException si ocurre un error al procesar la solicitud o respuesta
     * @throws ServletException si ocurre un error durante el proceso del filtro
     */
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

        // Se recupera el objeto Usuario desde la sesión
        Usuario u = (Usuario) sesion.getAttribute("usuario");

        // Si el rol del usuario es ADMIN o RRHH, se permite el acceso a la web
        if ("ADMIN".equals(u.getRol()) || "RRHH".equals(u.getRol())) {
            chain.doFilter(request, response);
        } else {
            // Si el rol no es ADMIN o RRHH, se redirige al panel principal con un mensaje de error
            res.sendRedirect(req.getContextPath() + "/gestor/panelPrincipal.html?error=accesoDenegado");
        }
    }

}