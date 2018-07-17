<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:postres>
	<jsp:attribute name="headHTML">
		<title>Inicio | La Chocolateria</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
		<div class="row Postres-titulo tit-cliente">
			<div class="large-12 columns">
				<h1>La Chocolateria</h1>
				<h2><small>Regístrate y no te pierdas las promociones exclusivas</small></h2>
			</div>
		</div>
	</jsp:attribute>
	

	<jsp:body>
		<div class="row">
			<div class="medium-6 columns">
				<div class="panel callout">
					<h3>¿Todavía no te has registrado? Hazlo acá</h3>
					<form action="/Postres/usuario/registro" method="post" id="formRegistro">
						<div class="row">
							<div class="medium-12 columns">
								<label>Nombre y apellido
									<input type="text" name="nombreAp" />
								</label>
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Nombre de Usuario
									<input type="text" name="nombreUsr"/>
								</label>
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Clave (mínimo 6 caracteres)
									<input type="password" name="clave"/>
								</label>
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Correo electrónico
									<input type="text" name="mail" />
								</label>
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>DNI
									<input type="text" name="dni" />
								</label>
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Teléfono
									<input type="text" name="telefono" />
								</label>
							</div>
						</div>
						<input type="button" class="button success" id="btnRegistrar" value="Registrarse" />
					</form>
				</div>
			</div>
			<div class="medium-6 columns">
				<div class="panel">
					<h3>Si ya sos cliente, accede aquí.</h3>
					<form action="/Postres/usuario/login" method="post" id="formLogin">
						<div class="row">
							<div class="medium-12 columns">
								<label>Nombre de usuario
									<input type="text" name="nombre_usuario"/>
								</label>
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Clave
									<input type="password" name="clave"/>
								</label>
							</div>
						</div>
						<input type="button" class="button" value="Iniciar sesión">
					</form>
				</div>
			</div>
		</div>
	</jsp:body>
</t:postres>