const params = new URLSearchParams(window.location.search);
        if (params.has('registro')) {
            alert('El registro se ha completado correctamente!');
            window.location.href = '/EMPLEADOS/gestor/panelPrincipal.html';
        }