package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import DAO.EmpleadosDAO;

/**
 * Clase que representa a un empleado en el sistema.
 * Contiene los atributos necesarios para almacenar información de un empleado
 * y operaciones CRUD asociadas con la base de datos a través de la clase EmpleadosDAO.
 */
public class Empleado {

    // Atributos de la clase Empleado
    private int id; // Identificador único del empleado
    private String nombre; // Nombre del empleado
    private String email; // Correo electrónico del empleado
    private int salario; // Salario del empleado
    private int departamento_id; // Identificador del departamento al que pertenece el empleado
    private String departamento; // Nombre del departamento al que pertenece el empleado
    
    // Constructor por defecto
    public Empleado() {}

    /**
     * Constructor con parámetros para crear un empleado nuevo.
     * Utilizado en las operaciones de insertar y actualizar.
     *
     * @param nombre el nombre del empleado
     * @param email el correo electrónico del empleado
     * @param salario el salario del empleado
     * @param departamento_id el identificador del departamento al que pertenece el empleado
     */
    public Empleado(String nombre, String email, int salario, int departamento_id) {
        this.nombre = nombre;
        this.email = email;
        this.salario = salario;
        this.departamento_id = departamento_id;
    }

    /**
     * Constructor con parámetros para crear un empleado, utilizado para las operaciones
     * de lectura y borrado de datos del empleado.
     *
     * @param id el identificador único del empleado
     * @param nombre el nombre del empleado
     * @param email el correo electrónico del empleado
     * @param salario el salario del empleado
     * @param departamento el nombre del departamento al que pertenece el empleado
     */
    public Empleado(int id, String nombre, String email, int salario, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.salario = salario;
        this.departamento = departamento;
    }

    // Métodos getter y setter para los atributos de la clase Empleado

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * Inserta el empleado en la base de datos utilizando el DAO de Empleados.
     * Llama al método insertar del DAO para realizar la operación de inserción.
     *
     * @throws SQLException si ocurre un error en la base de datos durante la inserción
     */
    public void insertar() throws SQLException {
        EmpleadosDAO.getConection().insertar(this);
    }

    /**
     * Actualiza los datos del empleado en la base de datos utilizando el DAO de Empleados.
     * Llama al método actualizar del DAO para realizar la operación de actualización.
     *
     * @throws SQLException si ocurre un error en la base de datos durante la actualización
     */
    public void actualizar() throws SQLException {
        EmpleadosDAO.getConection().actualizar(this);
    }

    /**
     * Recupera un empleado de la base de datos mediante su ID.
     *
     * @param id el identificador único del empleado a recuperar
     * @return el objeto Empleado con los datos recuperados
     * @throws SQLException si ocurre un error en la base de datos durante la consulta
     */
    public Empleado obtenerEmpleado(int id) throws SQLException {
        Empleado e = EmpleadosDAO.getConection().obtenerEmpleado(id);
        System.out.println(e.toString());
        return e;
    }

    /**
     * Recibe un empleado de la base de datos y lo convierte a formato JSON utilizando Gson.
     *
     * @param id el identificador único del empleado a convertir
     * @return el empleado en formato JSON
     * @throws SQLException si ocurre un error en la base de datos durante la consulta
     */
    public String recibirEmpleados(int id) throws SQLException {
        Empleado e = EmpleadosDAO.getConection().obtenerEmpleado(id);

        // Asigna los valores del empleado obtenido a los atributos de la instancia actual
        this.id = e.getId();
        this.nombre = e.getNombre();
        this.email = e.getEmail();
        this.salario = e.getSalario();
        this.departamento = e.getDepartamento();

        // Convierte el objeto empleado a JSON y lo devuelve
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    /**
     * Devuelve un empleado en formato JSON basado en su ID.
     *
     * @param id el identificador único del empleado a convertir
     * @return el empleado en formato JSON
     * @throws SQLException si ocurre un error en la base de datos durante la consulta
     */
    public String dameJson(int id) throws SQLException {
        Gson gson = new Gson();
        // Convierte el empleado obtenido en formato JSON y lo retorna
        String json = gson.toJson(this.obtenerEmpleado(id));
        return json;
    }

    /**
     * Elimina un empleado de la base de datos utilizando su ID.
     *
     * @param id el identificador único del empleado a eliminar
     * @throws SQLException si ocurre un error en la base de datos durante la eliminación
     */
    public void borrar(int id) throws SQLException {
        int filas = EmpleadosDAO.getConection().borrar(id);
    }
}

