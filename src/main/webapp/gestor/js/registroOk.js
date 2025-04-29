// Crea un objeto URLSearchParams a partir de los parámetros de la URL.
const params = new URLSearchParams(window.location.search);

// Comprueba si el parámetro 'registro' existe en la URL.
if (params.has('registro')) {
    // Si el parámetro 'registro' está presente, muestra un mensaje de alerta indicando que el registro se ha completado correctamente.
    alert('El registro se ha completado correctamente!');

    // Redirige al usuario a la página 'panelPrincipal.html' después de mostrar el mensaje.
    window.location.href = '/EMPLEADOS/gestor/panelPrincipal.html';
}