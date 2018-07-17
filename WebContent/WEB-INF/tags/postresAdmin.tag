<%@tag description="Template de Postres" pageEncoding="UTF-8" %>
<%@attribute name="headHTML" fragment="true" %>
<%@attribute name="imagenTop" fragment="true" %>
<%@attribute name="menu" fragment="true" %>
<!doctype html>
<html lang="es">
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="/Postres/assets/css/foundation.css" />
	<script src="/Postres/assets/js/vendor/modernizr.js"></script>
	<link rel="stylesheet" type="text/css" href="/Postres/assets/css/estilo.css">
	<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
	<jsp:invoke fragment="headHTML"/>
</head>
<body>
	<nav class="top-bar" data-topbar role="navigation">
		<ul class="title-area">
			<li class="name">
				<h1><a href="/Postres/admin">Admin <strong>La Chocolateria</strong></a></h1>
			</li>
			<li class="toggle-topbar menu-icon"><a href="#"><span>Menú</span></a></li>
		</ul>

		<section class="top-bar-section">
			<!-- Right Nav Section -->
			<ul class="right">
				<li><a href="/Postres/admin/producto/">Productos</a></li>
				<li><a href="/Postres/admin/tipo/">Tipo Producto</a></li>
				<li><a href="/Postres/admin/persona/">Clientes</a></li>
				<li><a href="/Postres/admin/pedido/">Pedidos</a></li>
			</ul>
			<ul class="left">
				<li class="active"><a href="/Postres/">Sección<strong>Clientes</strong></a></li>
			</ul>
		</section>
	</nav>
	
	<jsp:invoke fragment="imagenTop"/>
	<jsp:doBody/>

	<footer>
		<div class="Postres-sub-footer">
			Si querés acceder a la parte de clientes, <a href="/Postres/usuario/logout">deslogueate</a> y accedé con tu usuario cliente.
		</div>
		<div class="Postres-adm-footer">
			Copyright &copy; 2018
			<hr>
			Trabajo Práctico Final Java
		</div>
	</footer>

	<script src="/Postres/assets/js/vendor/jquery.js"></script>
	<script src="/Postres/assets/js/foundation.min.js"></script>
	<script src="/Postres/assets/js/validacionesAdmin.js"></script>
	<script>
		$(document).foundation();
	</script>
</body>
</html>