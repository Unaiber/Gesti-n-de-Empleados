package test;

import DAO.EmpleadosDAO;
import modelo.Empleado;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestEmpleadosDAO_SQLite {

    private Connection conn;
    private EmpleadosDAO empleadosDAO;

    @BeforeEach
    public void setUp() throws Exception {
        // Carga explícita del driver JDBC de SQLite
        Class.forName("org.sqlite.JDBC");

        // Crea una base de datos SQLite en memoria
        conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        empleadosDAO = new EmpleadosDAO(conn);

        // Crear tabla usando PreparedStatement
        String sqlCrearTabla = """
            CREATE TABLE empleados (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT,
                email TEXT,
                salario INTEGER,
                departamento_id INTEGER
            );
        """;

        try (PreparedStatement ps = conn.prepareStatement(sqlCrearTabla)) {
            ps.execute();
        }

        // Usar EmpleadosDAO con conexión SQLite
        empleadosDAO = new EmpleadosDAO(conn);
    }

    @AfterEach
    public void tearDown() throws Exception {
        conn.close(); // SQLite en memoria se elimina automáticamente
    }

    @Test
    public void testInsertarYLeerEmpleado() throws SQLException {
        Empleado e = new Empleado("Test", "test@test.com", 30000, 1);
        empleadosDAO.insertar(e);

        List<Empleado> empleados = empleadosDAO.obtenerEmpleados();

        assertFalse(empleados.isEmpty(), "La lista no debería estar vacía");
        assertTrue(empleados.stream().anyMatch(emp -> emp.getNombre().equals("Test")),
                "Debe contener al empleado insertado");
    }

    @Test
    public void testActualizarEmpleado() throws SQLException {
        Empleado e = new Empleado("Test", "test@test.com", 25000, 2);
        empleadosDAO.insertar(e);

        Empleado insertado = empleadosDAO.obtenerEmpleados().get(0);
        insertado.setNombre("Actualizado");
        empleadosDAO.actualizar(insertado);

        Empleado actualizado = empleadosDAO.obtenerEmpleado(insertado.getId());
        assertEquals("Actualizado", actualizado.getNombre(), "Nombre actualizado correctamente");
    }

    @Test
    public void testBorrarEmpleado() throws SQLException {
        Empleado e = new Empleado("test", "test@test.com", 15000, 3);
        empleadosDAO.insertar(e);

        Empleado insertado = empleadosDAO.obtenerEmpleados().get(0);
        int borradas = empleadosDAO.borrar(insertado.getId());

        assertEquals(1, borradas, "Debe borrar un empleado");
    }
}

