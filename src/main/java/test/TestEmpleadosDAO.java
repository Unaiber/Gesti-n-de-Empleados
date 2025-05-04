package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import DAO.EmpleadosDAO;
import modelo.Empleado;

class TestEmpleadosDAO {
	
	private static EmpleadosDAO dao;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		 dao = EmpleadosDAO.getConection();
	}

	@Test
	void testInsertar() throws SQLException {
		//Orden de eliminar empleado con el siguiente email para evitar duplicados
		dao.borrarPorEmail("test1@test.com");
		Empleado e = new Empleado("test1","test1@test.com",3000,1);
		dao.insertar(e);
		// Comprobamos que el email sea el mismo que le hemos asignado
        Empleado ex = dao.obtenerEmpleadoPorEmail("test1@test.com");
		assertNotNull(ex);
		assertEquals("test1", ex.getNombre());
		assertEquals(3000, ex.getSalario());
	}

	@Test
	void testActualizar() throws SQLException {
		//Orden de eliminar empleado con el siguiente email para evitar duplicados
		dao.borrarPorEmail("test2@test.com");
		// Insertamos un empleado
		Empleado e = new Empleado ("Test2","test2@test.com",3000,1);
		dao.insertar(e);
		
		//Recuperamos empleado y modificamos datos
		Empleado ex = dao.obtenerEmpleadoPorEmail("test2@test.com");
		assertNotNull(ex);
		//Actualizamos sus datos
		ex.setNombre("TestActu");
		ex.setEmail("actu@test.com");
		ex.setSalario(3500);
		ex.setDepartamento_id(1);
		//Actualizamos "base de datos"
		int filas = dao.actualizarPorEmail("actu@test.com", ex);
		assertEquals(1,filas);
		
		//Recuperamos los valores del empleado actualizado y verificamos
		Empleado actu = dao.obtenerEmpleadoPorEmail("actu@test.com");
		assertEquals("TestActu",actu.getNombre());
		assertEquals("actu@test.com",actu.getEmail());
		assertEquals(3500,actu.getSalario());
		
	}

	@Test
	void testBorrar() throws SQLException {
		//Orden de eliminar empleado con el siguiente email para evitar duplicados
		dao.borrarPorEmail("test3@test.com");
		//Insertamos nuevo empleado para luego borrarlo
		Empleado e2 = new Empleado("Test3","test3@test.com",1234,2);
		dao.insertar(e2);
		//Ejecutamos y verificamos la eliminación
		Empleado recu = dao.obtenerEmpleadoPorEmail("test3@test.com");
		assertNotNull(recu);

		// Borramos por email en lugar de por su email
		int filas = dao.borrarPorEmail("test3@test.com");
		assertEquals(1, filas);
	
		//Intentamos recuperar el empleado borrado, debería ser null
		Empleado elim = dao.obtenerEmpleadoPorEmail("test3@test.com");
		assertNull(elim);
				
	}

	@Test
	void testObtenerEmpleado() throws SQLException {
		//Orden de eliminar empleado con el siguiente email para evitar duplicados
		dao.borrarPorEmail("test4@test.com");
		// Insertamos un empleado para luego recuperarlo
	    Empleado e3 = new Empleado("test4", "test4@test.com", 2900, 3);
	    dao.insertar(e3); 
	    
	    //Actualizamos "base de datos"	    
	    int filas = dao.actualizarPorEmail("test4@test.com",e3);
		assertEquals(1,filas);

	    // Recuperamos el empleado por su email
	    Empleado ex3 = dao.obtenerEmpleadoPorEmail("test4@test.com");
		assertNotNull(ex3);
	    // Verificamos que los datos del empleado recuperado son correctos
	    assertNotNull(ex3);
	    assertEquals("test4", ex3.getNombre());
	    assertEquals("test4@test.com", ex3.getEmail());
	    assertEquals(2900, ex3.getSalario());
	}

}
