<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:postresAdmin>
	<jsp:attribute name="headHTML">
		<title>Modificar persona | La Chocolateria Admin</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="large-12 columns">
				<div class="panel">	
					<h3>Modificar persona</h3>
					<form action="/Postres/admin/persona/modificar" method="post" id="formPersona">
						<input type="text" name="id" style="display:none;" value="${persona.id_cliente}" />
						<div class="row">
							<div class="medium-12 columns">
								<label>Nombre y apellido
									<input type="text" name="nom_ap" value="${persona.nombre_ap}" />
								</label>
								<label>Nombre de usuario
									<input type="text" name="nom_usr" value="${persona.nombre_usuario}"/>
								</label>
								<label>Clave
									<input type="text" name="clave" value="${persona.clave}" />
								</label>
								<label>E-mail
									<input type="text" name="mail" value="${persona.mail}" />
								</label>
								<label>DNI
									<input type="text" name="dni" value="${persona.dni}" />
								</label>
								<label>Teléfono
									<input type="text" name="telefono" value="${persona.telefono}" />
								</label>
								<label>Es administrador
									<input type="checkbox" name="esAdmin" value="" <c:if test="${persona.esAdmin == true}">checked</c:if> />
								</label>
							</div>
						</div>
						<input type="button" class="button" value="Guardar">
					</form>
				</div>
			</div>
		</div>
	</jsp:body>
</t:postresAdmin>