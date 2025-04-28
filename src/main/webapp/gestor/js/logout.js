function logout() {
            sessionStorage.removeItem('usuarioLogueado');
            window.location.href = '/EMPLEADOS/gestor/index.html'; // Redirige al login
        }