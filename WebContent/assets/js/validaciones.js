$('#formRegistro #btnRegistrar').click(function() {
	validarRegistro();
});

$('#formEditarPerfil input[type=button]').click(function() {
	validarRegistro();
});

$('#formConfirmarPedido input[type=button]').click(function() {
	validarDireccion();
});

$('#formLogin input[type=button]').click(function() {
	validarLogin();
});

$('#formAgregarProducto input[type=button]').click(function() {
	validarCantidad();
});

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
var erNombreUsr = /^[a-z]([A-Za-z0-9]{5,14})+$/;
var erClave = /^\w{6,12}$/;
var erMail = /^[\w-\.]{6,15}@[a-z]{2,15}\.[a-z]{2,3}$/;
var erDni = /^[1-9][0-9]{6,7}$/;
var erTelefono = /^[0-9]{7,10}/;
var erDireccion = /^\w*$/;
var erCantidad = /^[1-9][0-9]*$/;

function validarRegistro() {
	var form = $('#formRegistro');
	
	var inputNyA = $('#formRegistro input[name=nombreAp]');
	var inputUsuario = $('#formRegistro input[name=nombreUsr]');
	var inputClave = $('#formRegistro input[name=clave]');
	var inputMail = $('#formRegistro input[name=mail]');
	var inputDni = $('#formRegistro input[name=dni]');
	var inputTelefono = $('#formRegistro input[name=telefono]');

	var bandera = 1;
	borrarErrores();

	var errores=[];

	if(!$(inputNyA).val().match(erNombreAp)) {
		bandera=0;
		errores[errores.length] = "Nombre y apellido incorrecto. Deben ser con la primera letra en mayúsculas y las siguientes en minúsculas. Como mínimo debe haber un Nombre y un Apellido.";
	}
	if(!$(inputUsuario).val().match(erNombreUsr)) {
		bandera=0;
		errores[errores.length] = "Nombre de usuario incorrecto. Deben ser con la primera letra en minúsculas. Debe tener una longitud entre 6 y 15 caracteres y no puede tener espacios en blanco.";
	}
	if(!$(inputClave).val().match(erClave)) {
		bandera=0;
		errores[errores.length] = "Clave incorrecta. Debe contener cualquier caracter alfanumérico y también una longitudo entre 6 y 12.";
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

function validarLogin() {
	var form = $('#formLogin');	
	
	var inputUsuario = $('#formLogin input[name=nombre_usuario]');
	var inputClave = $('#formLogin input[name=clave]');

	var bandera = 1;
	borrarErrores();

	var erroresLogin=[];

	if(!$(inputUsuario).val().match(erNombreUsr)) {
		bandera=0;
		erroresLogin[erroresLogin.length] = "Nombre de usuario incorrecto. Deben ser con la primera letra en minúsculas. Debe tener una longitud entre 8 y 15 caracteres y no puede tener espacios en blanco.";
	}
	if(!$(inputClave).val().match(erClave)) {
		bandera=0;
		erroresLogin[erroresLogin.length] = "Clave incorrecta. Debe contener cualquier caracter alfanumérico y también una longitudo entre 6 y 12.";
	}

	if(bandera==0) {
		mostrarErrores(form,erroresLogin);
	} else {
		form.submit();
	}
}

function validarDireccion() {
	var form = $('#formConfirmarPedido');
	var inputDireccion = $('#formConfirmarPedido input[name=direccion]');
	
	var bandera = 1;
	borrarErrores();

	var erroresLogin=[];
	if(!$(inputDireccion).val().match(erDireccion)) {
		bandera = 0;
		erroresLogin[erroresLogin.length] = "Dirección inválida.";
	}
	
	if(bandera==0) {
		mostrarErrores(form,erroresLogin);
	} else {
		form.submit();
	}
}

function validarCantidad() {
	var form = $('#formAgregarProducto');
	var inputCantidad = $('#formAgregarProducto input[name=cantidad]');

	var bandera=1;
	borrarErrores();

	var erroresLogin=[];
	if(!$(inputCantidad).val().match(erCantidad)) {
		bandera = 0;
		erroresLogin[erroresLogin.length] = "Cantidad inválida. Debe ser un número entero";
	}
	
	if(bandera==0) {
		mostrarErrores(form,erroresLogin);
	} else {
		form.submit();
	}
}