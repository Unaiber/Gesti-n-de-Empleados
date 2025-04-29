// Obtener los parámetros de la URL
const params = new URLSearchParams(window.location.search);

// Comprobar si existe el parámetro 'error' en la URL
if (params.has('error')) {
    // Si el parámetro 'error' está presente, mostrar un mensaje de alerta
    alert('Usuario o contraseña incorrectos. Intenta de nuevo.');
}