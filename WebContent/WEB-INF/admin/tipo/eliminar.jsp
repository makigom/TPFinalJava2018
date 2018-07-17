<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:postresAdmin>
	<jsp:attribute name="headHTML">
		<title>Eliminar tipo de producto | La Chocolateria Admin</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="large-12 columns">
				<div class="panel">	
					<h3>Eliminar tipo de producto</h3>
					<h2>¿Está seguro de que desea eliminar el tipo de producto?</h2>
					<form action="/Postres/admin/tipo/eliminar" method="post">
						<input type="text" name="id" style="display:none;" value="${tipo.id_tipo}" />
						<div class="row">
							<div class="medium-12 columns">
								<label>Descripción
									<input type="text" placeholder="Descripción" name="descripcion" value="${tipo.descripcion_tipo}" disabled />
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