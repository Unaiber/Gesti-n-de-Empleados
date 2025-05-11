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

/**
 * Filtro que verifica si el usuario está autenticado antes de acceder a ciertas
 * páginas. Si el usuario no tiene sesión activa o no está autenticado, se le
 * redirige al login (index.html).
 */
@WebFilter(urlPatterns = {"/gestor/panelPrincipal.html", "/gestor/listadoEmpleados.html" })
public class FiltroAutenticacion implements Filter {

	/**
	 * Método que intercepta las peticiones a las URLs protegidas. Verifica si el
	 * usuario tiene sesión activa. Si no tiene sesión o no está autenticado, se
	 * redirige al login.
	 *
	 * @param request  la solicitud HTTP recibida
	 * @param response la respuesta HTTP que se enviará
	 * @param chain    la cadena de filtros que se debe ejecutar si el usuario está
	 *                 autenticado
	 * @throws IOException      si ocurre un error al procesar la solicitud o
	 *                          respuesta
	 * @throws ServletException si ocurre un error durante el proceso del filtro
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Cast a HttpServletRequest y HttpServletResponse para usar métodos HTTP
		// específicos (necesarios para getSession() y sendRedirect())
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// Se obtiene la sesión actual del usuario, sin crear una nueva si no existe
		HttpSession sesion = req.getSession(false);

		// Si no hay sesión o no hay atributo "usuario", se redirige al login
		// (index.html)
		if (sesion == null || sesion.getAttribute("usuario") == null) {
			res.sendRedirect(req.getContextPath() + "/gestor/index.html");
			return;
		}

		// Si la sesión está activa y el usuario está autenticado, se permite continuar
		// con la solicitud
		chain.doFilter(request, response);
	}
}
