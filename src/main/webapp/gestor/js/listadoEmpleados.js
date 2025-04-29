// Función que realiza una llamada fetch para obtener la lista de empleados.
function llamada() {
    // Realiza una solicitud GET al servidor para obtener todos los empleados (opción 0).
    fetch('../GestionEmpleados?id=0&option=0')
    // Cuando se recibe la respuesta, la convierte en formato JSON.
    .then(response => response.json())
    // Cuando los datos están listos, pasa los datos a la función 'pintarDatos' para mostrar en la tabla.
    .then(data => pintarDatos(data))
}

// Función que borra un empleado de la base de datos después de confirmar con el usuario.
function borrar(id) {
    // Muestra una ventana de confirmación para asegurar que el usuario quiere borrar al empleado.
    if(confirm("seguro que quieres borrar")) {
        // Si el usuario confirma, se hace una solicitud para borrar al empleado con el 'id'.
        fetch('../GestionEmpleados?id=' + id + '&option=3')
        // Después de realizar la solicitud, recibe la respuesta en formato JSON.
        .then(response => response.json())
        .then(data => console.log(data)) // Imprime los datos en la consola (para depuración).
        
        // Recarga la página para reflejar los cambios en la interfaz de usuario.
        location.reload();
    }
}

// Función que pinta (muestra) los datos de los empleados en una tabla HTML.
function pintarDatos(datos) {
    let table = ""; // Variable que almacenará el contenido HTML de la tabla.

    // Recorre el array 'datos' que contiene los empleados.
    for (let i = 0; i < datos.length; i++) {
        // Crea una fila en la tabla con la información del empleado (ID, nombre, email, salario, departamento).
        table += `<tr>
                    <td>${datos[i].id}</td>
                    <td>${datos[i].nombre}</td>
                    <td>${datos[i].email}</td>
                    <td>${datos[i].salario}</td>
                    <td>${datos[i].departamento}</td>
                    <td><a href='editorEmpleados.html?id=${datos[i].id}'>Editar</a></td>
                    <td><a href="#" onclick="borrar(${datos[i].id})">Borrar</a></td>
                  </tr>`
    }
    
    // Cierra la tabla y asigna el HTML generado a la tabla de empleados en el documento.
    table += "</table>";
    
    // Asigna el contenido HTML generado a la tabla en el DOM.
    document.getElementById("tablaEmpleados").innerHTML = table;
}

// Se ejecuta cuando la página se carga completamente, haciendo una llamada para obtener los empleados.
window.onload = llamada;