function llamada(){
	fetch('../GestionEmpleados?id=0&option=0')
	.then(response => response.json())
	.then(data => pintarDatos(data))
}

function borrar(id){

	if(confirm("seguro que quieres borrar")){
		
    	fetch('../GestionEmpleados?id='+id+'&option=3')
    	.then(response =>  response.json())
    	.then(data => console.log(data))
    	
    	location.reload();

	}
	
	
}

function pintarDatos(datos){
	
	let table = "";
	
	for (let i=0; i<datos.length; i++){
		
		table +=`<tr><td>
		 ${datos[i].id}</td><td>
		 ${datos[i].nombre}</td><td>
		 ${datos[i].email}</td><td>
		 ${datos[i].salario}</td><td>
		 ${datos[i].departamento}</td><td>
		 <a href='editorEmpleados.html?id=${datos[i].id}'>Editar</td><td>
		 <a href="#" onclick="borrar(${datos[i].id})">Borrar</td></tr>`
		
	}
	
	table += "</table>";
	
	document.getElementById("tablaEmpleados").innerHTML = table;
	
}

window.onload = llamada;