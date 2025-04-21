
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
     * @see HttpServlet#HttpServlet()
     */
    public GestionEmpleados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String idStr = request.getParameter("id");
		String optionStr = request.getParameter("option");
		
		PrintWriter out = response.getWriter();
		
		
		if (idStr != null && optionStr != null && !idStr.isEmpty() && !optionStr.isEmpty()) {
		    try {
		        int id = Integer.parseInt(idStr);
		        int option = Integer.parseInt(optionStr);

		        switch (option) {
		            case 0:
		                response.setContentType("application/json");
		                response.setCharacterEncoding("UTF-8");
		                String rs = EmpleadosDAO.getConection().toJson();
		                out.print(rs);
		                break;
		            case 2:
		                Empleado e = new Empleado();
		                String r = e.recibirEmpleados(id);
		                out.print(r);
		                break;
		            case 3:
		                Empleado ex = new Empleado();
		                ex.borrar(id);
		                out.print("Empleado borrado con éxito.");
		                break;
		            default:
		                out.print("Opción no válida.");
		                break;
		        }
		    } catch (NumberFormatException e) {
		        out.print("Parámetros inválidos.");
		    } catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
		    out.print("Parámetros 'id' y 'option' son necesarios.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		int salario = Integer.parseInt(request.getParameter("salario"));
		int departamento_id = Integer.parseInt(request.getParameter("departamento_id"));
		String idStr = request.getParameter("id");
		
	

		
		Empleado e = new Empleado (nombre,email,salario,departamento_id);
		
try {
			
			if (idStr != null && !idStr.isEmpty()) {
				System.out.println("Quiero actualizar");
				int id = Integer.parseInt(request.getParameter("id"));
				
				e.setId(id);
				e.actualizar();
				response.sendRedirect("gestor/panelPrincipal.html?registro=ok");
				
				
			}else {
				System.out.println("Quiero insertar");
				e.insertar();
				response.sendRedirect("gestor/listadoEmpleados.html?registro=ok");
			}
			
			
		
		} catch (SQLException ex) {
			ex.printStackTrace();
		
		}
System.out.println(e.toString());
	    }

	}		
	


