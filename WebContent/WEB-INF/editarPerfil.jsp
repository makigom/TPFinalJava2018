<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:postres>
	<jsp:attribute name="headHTML">
		<title>Bienvenido | La Chocolateria</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="large-12 columns">
				<div class="panel callout">
					<form action="/Postres/usuario/editarPerfil" method="post" id="formEditarPerfil">
						<div class="row">
							<div class="medium-12 columns">
								<label>Nombre y apellido
									<input type="text" placeholder="Nombre y apellido" name="nombreAp" value="${sessionScope.usuario.nombre_ap}"/>
								</label>
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Nombre de Usuario
									<input type="text" placeholder="Usuario" name="nombreUsr" value="${sessionScope.usuario.nombre_usuario}" disabled/>
								</label>
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Clave (mínimo 6 caracteres)
									<input type="password" placeholder="Clave" name="clave" value="${sessionScope.usuario.clave}"/>
								</label>
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Correo electrónico
									<input type="text" placeholder="E-mail" name="mail" value="${sessionScope.usuario.mail}" />
								</label>
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>DNI
									<input type="text" placeholder="DNI" name="dni" value="${sessionScope.usuario.dni}" disabled/>
								</label>
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Teléfono
									<input type="text" placeholder="Teléfono" name="telefono" value="${sessionScope.usuario.telefono}" />
								</label>
							</div>
						</div>
						
						<input type="button" class="button success" id="btnRegistrar" value="Editar Datos">
					</form>
				</div>
			</div>
		</div>
	</jsp:body>
</t:postres>