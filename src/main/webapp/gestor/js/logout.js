// Función para cerrar sesión
function logout() {
    // Elimina el elemento 'usuarioLogueado' del almacenamiento de sesión (sessionStorage).
    sessionStorage.removeItem('usuarioLogueado');
    
    // Redirige al usuario a la página de login (index.html).
    window.location.href = '/EMPLEADOS/gestor/index.html'; // Redirige al login
}