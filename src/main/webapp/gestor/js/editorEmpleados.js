// Función que realiza una llamada fetch a un servidor para obtener datos de un empleado.
// 'id' es el identificador del empleado que se va a obtener.
function llamada(id){
    // Realiza una solicitud GET al servidor con el 'id' del empleado y una opción (2) para obtener datos.
    fetch('../GestionEmpleados?id=' + id + '&option=2')
    // Cuando se recibe la respuesta, la convierte en formato JSON
    .then(response => response.json())
    // Cuando los datos están listos, se procesa la respuesta y se pasa a la función 'llenarFormulario'
    .then(data => {
        console.log(data); // Imprime los datos en la consola para fines de depuración.
        llenarFormulario(data); // Llama a la función para llenar el formulario con los datos recibidos.
    })
}

// Función para obtener el valor de un parámetro de la URL mediante su nombre.
// 'variable' es el nombre del parámetro que se desea obtener.
function getQueryVariable(variable) {
    // Obtiene la parte de la URL después del signo de interrogación (query string).
    var query = window.location.search.substring(1);
    // Divide la cadena query string en pares clave-valor usando '&' como separador.
    var vars = query.split("&");
    // Recorre todos los pares clave-valor para encontrar el que coincide con 'variable'.
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("="); // Separa la clave del valor.
        if(pair[0] == variable) { // Si la clave coincide con la variable solicitada.
            return pair[1]; // Devuelve el valor correspondiente.
        }
    }
    // Si no se encuentra la variable, devuelve 'false'.
    return false;
}

// Función para llenar el formulario con los datos obtenidos de la solicitud fetch.
// 'datos' contiene la información del empleado que se quiere mostrar en el formulario.
function llenarFormulario(datos) {
    // Rellena los campos del formulario con los datos del empleado.
    document.getElementById('id').value = datos.id;
    document.getElementById('nombre').value = datos.nombre;
    document.getElementById('email').value = datos.email;
    document.getElementById('salario').value = datos.salario;
    document.getElementById('departamento_id').value = datos.departamento_id;
    console.log(datos.id); // Imprime el 'id' del empleado en la consola para fines de depuración.
}

// Función que se ejecuta cuando la página se carga completamente.
window.onload = function() {
    // Llama a la función 'llamada' pasando el valor del parámetro 'id' de la URL como argumento.
    llamada(getQueryVariable("id"));
}