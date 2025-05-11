
package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Empleado;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import DAO.EmpleadosDAO;

/**
 * Servlet implementation class GestionEmpleados
 */
@WebServlet("/GestionEmpleados")
public class GestionEmpleados extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase {@link GestionEmpleados}. Este constructor invoca el
	 * constructor de la clase base {@link HttpServlet}.
	 */
	public GestionEmpleados() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Maneja las solicitudes GET para la gestión de empleados. Dependiendo del
	 * parámetro 'option', realiza diferentes operaciones sobre los empleados, como
	 * obtener todos los empleados, obtener un empleado por su ID o eliminar un
	 * empleado.
	 * 
	 * @param request  La solicitud HTTP del cliente.
	 * @param response La respuesta HTTP que se enviará al cliente.
	 * @throws ServletException Si ocurre un error al procesar la solicitud.
	 * @throws IOException      Si ocurre un error de entrada/salida al manejar la
	 *                          solicitud.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Obtener parámetros 'id' y 'option' de la URL
		String idStr = request.getParameter("id");
		String optionStr = request.getParameter("option");

		// Preparar salida para escribir la respuesta
		PrintWriter out = response.getWriter();

		// Verificar que los parámetros no estén vacíos ni nulos
		if (idStr != null && optionStr != null && !idStr.isEmpty() && !optionStr.isEmpty()) {
			try {
				// Convertir parámetros a enteros
				int id = Integer.parseInt(idStr);
				int option = Integer.parseInt(optionStr);

				switch (option) {
				case 0:
					// Opción 0: devolver todos los empleados en formato JSON
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					String rs = EmpleadosDAO.getConection().toJson();
					out.print(rs);
					break;

				case 2:
					// Opción 2: obtener un solo empleado por ID
					Empleado e = new Empleado();
					String r = e.recibirEmpleados(id);
					out.print(r);
					break;

				case 3:
					// Opción 3: borrar un empleado por ID
					Empleado ex = new Empleado();
					ex.borrar(id);
					out.print("Empleado borrado con éxito.");
					break;

				default:
					// Opción no válida
					out.print("Opción no válida.");
					break;
				}

			} catch (NumberFormatException e) {
				// Si los parámetros no son numéricos
				out.print("Parámetros inválidos.");
			} catch (SQLException e1) {
				// Errores de base de datos
				e1.printStackTrace();
			}

		} else {
			// Si faltan los parámetros
			out.print("Parámetros 'id' y 'option' son necesarios.");
		}
	}

	/**
	 * Maneja las solicitudes POST para la creación o actualización de empleados.
	 * Dependiendo de si se proporciona un ID, el método actualizará un empleado
	 * existente o insertará uno nuevo.
	 * 
	 * @param request  La solicitud HTTP del cliente.
	 * @param response La respuesta HTTP que se enviará al cliente.
	 * @throws ServletException Si ocurre un error al procesar la solicitud.
	 * @throws IOException      Si ocurre un error de entrada/salida al manejar la
	 *                          solicitud.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recoger parámetros del formulario
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		int salario = Integer.parseInt(request.getParameter("salario"));
		int departamento_id = Integer.parseInt(request.getParameter("departamento_id"));
		String idStr = request.getParameter("id");

		// Crear objeto Empleado con los datos recibidos
		Empleado e = new Empleado(nombre, email, salario, departamento_id);

		try {
			// Si hay un ID, se actualiza el empleado existente
			if (idStr != null && !idStr.isEmpty()) {
				System.out.println("Quiero actualizar");
				int id = Integer.parseInt(request.getParameter("id"));
				e.setId(id);
				e.actualizar();
				response.sendRedirect("gestor/panelPrincipal.html?registro=ok");
			} else {
				// Si no hay ID, se inserta un nuevo empleado
				System.out.println("Quiero insertar");
				e.insertar();
				response.sendRedirect("gestor/listadoEmpleados.html?registro=ok");
			}

		} catch (SQLException ex) {
			// Captura errores SQL y los muestra en consola
			ex.printStackTrace();
		}

		// Mostrar datos del empleado en consola para depuración
		System.out.println(e.toString());
	}

}
