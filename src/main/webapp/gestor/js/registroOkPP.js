// Crea un objeto URLSearchParams a partir de los parámetros de la URL.
const urlParams = new URLSearchParams(window.location.search);

// Comprueba si el parámetro 'registro' tiene el valor 'ok'.
if (urlParams.get('registro') === 'ok') {
    // Si el valor de 'registro' es 'ok', muestra un mensaje de alerta indicando que el usuario se registró correctamente.
    alert("✅ Usuario registrado correctamente");

    // También puedes usar un <div> en vez de un alert para mostrar el mensaje en la página.
}