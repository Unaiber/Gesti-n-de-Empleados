function llamada(id){
	fetch('../GestionEmpleados?id='+id+'&option=2')
	.then(response =>  response.json())
	.then(data => {
		console.log(data);
		llenarFormulario(data);
	})
}


function getQueryVariable(variable) {
 var query = window.location.search.substring(1);
 var vars = query.split("&");
 for (var i=0; i < vars.length; i++) {
     var pair = vars[i].split("=");
     if(pair[0] == variable) {
         return pair[1];
     }
 }
 return false;
}


function  llenarFormulario(datos) {
	document.getElementById('id').value = datos.id;
	document.getElementById('nombre').value = datos.nombre;
	document.getElementById('email').value = datos.email;
	document.getElementById('salario').value = datos.salario;
	document.getElementById('departamento_id').value = datos.departamento_id;
	console.log(datos.id);

}

window.onload = function(){
	
	llamada(getQueryVariable("id"));
}