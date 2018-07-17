<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:postresAdmin>
	<jsp:attribute name="headHTML">
		<title>Eliminar persona | La Chocolateria Admin</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="large-12 columns">
				<div class="panel">	
					<h3>Eliminar persona</h3>
					<h2>¿Está seguro de que desea eliminar esta persona?</h2>
					<form action="/Postres/admin/persona/eliminar" method="post">
						<input type="text" name="id" style="display:none;" value="${persona.id_cliente}" />
						<div class="row">
							<div class="medium-12 columns">
								<label>Nombre y apellido
									<input type="text" placeholder="Nombre y Apellido" name="nom_ap" value="${persona.nombre_ap}" disabled />
								</label>
								
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Nombre de usuario
									<input type="text" placeholder="Nombre de Usuario" name="nom_usr" value="${persona.nombre_usuario}" disabled />
								</label>								
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Clave
									<input type="text" placeholder="Clave" name="clave" value="${persona.clave}" disabled />
								</label>								
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Contraseña
									<input type="text" placeholder="Clave" name="clave" value="${persona.clave}" disabled />
								</label>								
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>E-mail
									<input type="text" placeholder="Mail" name="mail" value="${persona.mail}" disabled />
								</label>								
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>DNI
									<input type="text" placeholder="Dni" name="dni" value="${persona.dni}" disabled />
								</label>								
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Teléfono
									<input type="text" placeholder="Teléfono" name="telefono" value="${persona.telefono}" disabled />
								</label>								
							</div>
						</div>						
						<input type="submit" class="button" value="Eliminar">
					</form>
				</div>
			</div>
		</div>
	</jsp:body>
</t:postresAdmin>