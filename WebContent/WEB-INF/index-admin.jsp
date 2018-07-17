<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:postresAdmin>
	<jsp:attribute name="headHTML">
		<title>Inicio | Admin La Chocolateria</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
		<div class="row Postres-titulo tit-adm">
			<div class="large-12 columns">
				<h1>Bienvenidos al panel de administrador de La Chocolateria</h1>
				<h2><small>Maneja tu negocio desde esta aplicación.</small></h2>
			</div>
		</div>
	</jsp:attribute>
	

	<jsp:body>
		<div class="row text-center">
			<div class="large-12 columns">
				<h2><small>Con esta aplicación podrás...</small></h2>
			</div>
		</div>
		<div class="row Postres-funciones">
			<div class="medium-4 columns">
				<i class="fi-burst-sale"></i>
				<h3>Manejar productos</h3>
			</div>
			<div class="medium-4 columns">
				<i class="fi-torsos"></i>
				<h3>Gestionar clientes</h3>
			</div>
			<div class="medium-4 columns">
				<i class="fi-shopping-cart"></i>
				<h3>Confirmar pedidos</h3>
			</div>
		</div>
	</jsp:body>
</t:postresAdmin>