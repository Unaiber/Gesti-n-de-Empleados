package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Empleado;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DAO.DatabaseConnection;
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
		try {
			
			PrintWriter out = response.getWriter();
			
			String res = EmpleadosDAO.getConection().toJson();
			//System.out.println(res);
			out.print(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		Empleado e = new Empleado (nombre, email,salario);
		
			
		Connection con = DatabaseConnection.getConnection();
		

		try {
			e.insertar();
			
		} catch (SQLException c) {
			c.printStackTrace();
		}
	
	}
		
		
		
		}


