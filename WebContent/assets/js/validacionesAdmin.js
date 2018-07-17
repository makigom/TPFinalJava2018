//TIPO BEBIDA
$('#formTipo input[type=button]').click(function() {
	validarDescripcionTipo();
});

//PRODUCTO
$('#formProducto input[type=button]').click(function() {
	validarProducto();
});

//PERSONA
$('#formPersona input[type=button]').click(function() {
	validarPersona();
});

//FUNCIONES
function borrarErrores() {
	$('div.alert-box').remove();
}

function mostrarErrores(selector, errores) {
	var divError = document.createElement("div");
	$(divError).attr("data-alert","");
	$(divError).addClass('alert-box alert');
	divError.id = "errores";
	//var ulError = document.createElement('ul');
	for (var i = 0; i < errores.length; i++) {
		//$(ulError).append("<li>"+errores[i]+"</li>");
		$(divError).append('<p>'+errores[i]+'</p>');
	}
	//$(divError).append(ulError);
	$(divError).append('<a href="#" class="close">&times;</a>');
	selector.after(divError);
}

var erNombreAp = /^[\p{L}\s'.-]+$/;
var erNombreUsr = /^[a-z]([A-Za-z0-9]{7,14})+$/;
var erClave = /^\w{6,12}$/;
var erMail = /^[\w-\.]{6,15}@[a-z]{2,15}\.[a-z]{2,3}$/;
var erDni = /^[1-9][0-9]{6,7}$/;
var erTelefono = /^[0-9]{7,10}/;
var erDireccion = /^\w*$/;
var erCantidad = /^[1-9][0-9]*$/;
var erCadena = /^[\p{L}\s'.-]+$/;
var erPrecio = /^[1-9][0-9]*$/; 
var erStock = /^[1-9][0-9]*$/;

function validarDescripcionTipo() {
	var form = $('#formTipo');
	var inputDescripcion = $('#formTipo input[name=descripcion]');
	
	var bandera = 1;
	borrarErrores();

	var erroresLogin=[];
	if(!$(inputDescripcion).val().match(erCadena)) {
		bandera = 0;
		erroresLogin[erroresLogin.length] = "Descripción inválida. Debe tener al menos 1 caracter alfanumérico";
	}
	
	if(bandera==0) {
		mostrarErrores(form,erroresLogin);
	} else {
		form.submit();
	}
}

function validarProducto() {
	var form = $('#formProducto');
	var inputNombre = $('#formProducto input[name=nombre]');
	var inputImagen = $('#formProducto input[name=imagen]');
	var inputPrecio = $('#formProducto input[name=precio]');
	var inputStock = $('#formProducto input[name=stock]');

	var bandera = 1;
	borrarErrores();

	var erroresLogin=[];
	//if(!$(inputNombre).val().match(erCadena)) {
	//	bandera = 0;
	//	erroresLogin[erroresLogin.length] = "Nombre inválido. Debe tener al menos 1 caracter alfanumérico";
	//}
	//if(!$(inputImagen).val().match(erCadena)) {
	//	bandera = 0;
	//	erroresLogin[erroresLogin.length] = "URL de imágen inválida. Debe tener al menos 1 caracter alfanumérico";
	//}
	if(!$(inputPrecio).val().match(erPrecio)) {
		bandera = 0;
		erroresLogin[erroresLogin.length] = "Precio inválido. Debe ser un número entero";
	}
	if(!$(inputStock).val().match(erStock)) {
		bandera = 0;
		erroresLogin[erroresLogin.length] = "Stock inválido. Debe ser un número entero";
	}

	if(bandera==0) {
		mostrarErrores(form,erroresLogin);
	} else {
		form.submit();
	}
}

function validarPersona() {
	var form = $('#formPersona');
	var inputNyA = $('#formPersona input[name=nom_ap]');
	var inputUsuario = $('#formPersona input[name=nom_usr]');
	var inputClave = $('#formPersona input[name=clave]');
	var inputMail = $('#formPersona input[name=mail]');
	var inputDni = $('#formPersona input[name=dni]');
	var inputTelefono = $('#formPersona input[name=telefono]');

	var bandera = 1;
	borrarErrores();

	var errores=[];

	if(!$(inputNyA).val().match(erNombreAp)) {
		bandera=0;
		errores[errores.length] = "Nombre y apellido incorrecto. Deben ser con la primera letra en mayúsculas y las siguientes en minúsculas. Como mínimo debe haber un Nombre y un Apellido.";
	}
	if(!$(inputUsuario).val().match(erNombreUsr)) {
		bandera=0;
		errores[errores.length] = "Nombre de usuario incorrecto. Deben ser con la primera letra en minúsculas. Debe tener una longitud entre 8 y 15 caracteres y no puede tener espacios en blanco.";
	}
	if(!$(inputClave).val().match(erClave)) {
		bandera=0;
		errores[errores.length] = "Clave incorrecta. Debe contener cualquier caracter alfanumérico y tambíen una longitudo entre 6 y 12.";
	}
	if(!$(inputMail).val().match(erMail)) {
		bandera=0;
		errores[errores.length] = "E-Mail inválido. Ingrese una dirección de e-mail correcta.";
	}
	if(!$(inputDni).val().match(erDni)) {
		bandera=0;
		errores[errores.length] = "DNI incorrecto. Deben ser solo números entre 7 y 8 dígitos.";
	}
	if(!$(inputTelefono).val().match(erTelefono)) {
		bandera=0;
		errores[errores.length] = "Número de teléfono incorrecto. Deben ser solo números entre 7 y 10 dígitos.";
	}
	
	if(bandera==0) {
		mostrarErrores(form,errores);
	} else {
		form.submit();
	}
}