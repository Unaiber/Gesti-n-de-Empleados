const params = new URLSearchParams(window.location.search);
        if (params.has('error')) {
            alert('Usuario o contraseña incorrectos. Intenta de nuevo.');
        }