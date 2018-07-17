<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:postresAdmin>
	<jsp:attribute name="headHTML">
		<title>Nueva persona | La Chocolateria Admin</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="large-12 columns">
				<div class="panel">	
					<h3>Nueva persona</h3>
					<form action="/Postres/admin/persona/nuevo" method="post" id="formPersona">
						<div class="row">
							<div class="medium-12 columns">
								<label>Nombre y apellido
									<input type="text" placeholder="Descripción" name="nom_ap" />
								</label>
								<label>Nombre de usuario
									<input type="text" placeholder="Descripción" name="nom_usr" />
								</label>
								<label>Clave
									<input type="text" placeholder="Descripción" name="clave" />
								</label>
								<label>E-mail
									<input type="text" placeholder="Descripción" name="mail" />
								</label>
								<label>DNI
									<input type="text" placeholder="Descripción" name="dni" />
								</label>
								<label>Teléfono
									<input type="text" placeholder="Descripción" name="telefono" />
								</label>
								<label>Es administrador
									<input type="checkbox" placeholder="Descripción" name="esAdmin" />
								</label>
							</div>
						</div>
						<input type="button" class="button" value="Guardar persona">
					</form>
				</div>
			</div>
		</div>
	</jsp:body>
</t:postresAdmin>