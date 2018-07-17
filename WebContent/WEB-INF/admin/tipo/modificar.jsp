<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:postresAdmin>
	<jsp:attribute name="headHTML">
		<title>Modificar tipo de producto | La Chocolateria Admin</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="large-12 columns">
				<div class="panel">	
					<h3>Modificar tipo de producto</h3>
					<form action="/Postres/admin/tipo/modificar" method="post" id="formTipo">
						<input type="text" name="id" style="display:none;" value="${tipo.id_tipo}" />
						<div class="row">
							<div class="medium-12 columns">
								<label>Descripción
									<input type="text" placeholder="Descripción" name="descripcion" value="${tipo.descripcion_tipo}" />
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