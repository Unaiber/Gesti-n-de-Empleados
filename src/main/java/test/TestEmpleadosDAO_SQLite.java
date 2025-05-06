package test;

import DAO.EmpleadosDAO;
import modelo.Empleado;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba que valida las operaciones CRUD sobre empleados en la base de datos SQLite.
 * Se utiliza una base de datos en memoria para realizar las pruebas sin modificar datos persistentes.
 */
public class TestEmpleadosDAO_SQLite {

    private Connection conn;
    private EmpleadosDAO empleadosDAO;

    /**
     * Configura la base de datos en memoria antes de cada prueba, creando las tablas y vistas necesarias.
     * También se inicializa el DAO para las operaciones de empleados.
     */
    @BeforeEach
    public void setUp() throws Exception {
        // Carga explícita del driver JDBC de SQLite
        Class.forName("org.sqlite.JDBC");

        // Crea una base de datos SQLite en memoria
        conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        empleadosDAO = new EmpleadosDAO(conn);

        // Crear tabla departamentos
        String sqlCrearDepartamentos = """
            CREATE TABLE departamentos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL
            );
        """;
        try (PreparedStatement ps = conn.prepareStatement(sqlCrearDepartamentos)) {
            ps.execute();
        }

        // Insertar departamentos
        String sqlInsertDepartamentos = """
            INSERT INTO departamentos (nombre) VALUES 
            ('RRHH'),
            ('TI'),
            ('Marketing');
        """;
        try (PreparedStatement ps = conn.prepareStatement(sqlInsertDepartamentos)) {
            ps.executeUpdate();
        }

        // Crear tabla empleados
        String sqlCrearEmpleados = """
            CREATE TABLE empleados (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT,
                email TEXT,
                salario INTEGER,
                departamento_id INTEGER,
                FOREIGN KEY (departamento_id) REFERENCES departamentos(id)
            );
        """;
        try (PreparedStatement ps = conn.prepareStatement(sqlCrearEmpleados)) {
            ps.execute();
        }

        // Crear vista vista_empleado
        String vista_empleado = """
            CREATE VIEW vista_empleado AS
            SELECT 
                e.id,
                e.nombre,
                e.email,
                e.salario,
                d.nombre AS departamento
            FROM empleados e
            JOIN departamentos d ON e.departamento_id = d.id;
        """;
        try (PreparedStatement ps = conn.prepareStatement(vista_empleado)) {
            ps.execute();
        }

        // Inicializar DAO con conexión SQLite
        empleadosDAO = new EmpleadosDAO(conn);
    }

    /**
     * Cierra la conexión a la base de datos después de cada prueba.
     * Este método se ejecuta después de cada test.
     */
    @AfterEach
    public void tearDown() throws Exception {
        conn.close(); // SQLite en memoria se elimina automáticamente
    }

    /**
     * Prueba que verifica la inserción de un empleado y su lectura desde la base de datos.
     * Se asegura de que el empleado insertado esté presente en la lista obtenida.
     */
    @Test
    public void testInsertarYLeerEmpleado() throws SQLException {
        Empleado e = new Empleado("Test", "test@test.com", 30000, 1);
        empleadosDAO.insertar(e);

        List<Empleado> empleados = empleadosDAO.obtenerEmpleados();

        // Verifica que la lista no esté vacía
        assertFalse(empleados.isEmpty(), "La lista no debería estar vacía");

        // Verifica que el empleado insertado esté presente
        assertTrue(empleados.stream().anyMatch(emp -> emp.getNombre().equals("Test")),
                "Debe contener al empleado insertado");
    }

    /**
     * Prueba que verifica la actualización de un empleado en la base de datos.
     * Se inserta un empleado, se actualiza su nombre y se comprueba que el cambio fue exitoso.
     */
    @Test
    public void testActualizarEmpleado() throws SQLException {
        Empleado e = new Empleado("Test", "test@test.com", 25000, 2);
        empleadosDAO.insertar(e);

        Empleado insertado = empleadosDAO.obtenerEmpleados().get(0);
        insertado.setNombre("Actualizado");
        empleadosDAO.actualizar(insertado);

        // Verifica que el nombre se haya actualizado correctamente
        Empleado actualizado = empleadosDAO.obtenerEmpleado(insertado.getId());
        assertEquals("Actualizado", actualizado.getNombre(), "Nombre actualizado correctamente");
    }

    /**
     * Prueba que verifica la eliminación de un empleado de la base de datos.
     * Se inserta un empleado, se borra y se comprueba que el número de empleados eliminados sea 1.
     */
    @Test
    public void testBorrarEmpleado() throws SQLException {
        Empleado e = new Empleado("test", "test@test.com", 15000, 3);
        empleadosDAO.insertar(e);

        Empleado insertado = empleadosDAO.obtenerEmpleados().get(0);
        int borradas = empleadosDAO.borrar(insertado.getId());

        // Verifica que se haya borrado exactamente un empleado
        assertEquals(1, borradas, "Debe borrar un empleado");
    }
}

