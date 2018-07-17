<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:postresAdmin>
	<jsp:attribute name="headHTML">
		<title>Nuevo producto | La Chocolateria Admin</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="large-12 columns">
				<div class="panel">	
					<h3>Nuevo producto</h3>
					<form action="/Postres/admin/producto/nuevo" method="post" id="formProducto">
						<div class="row">
							<div class="medium-12 columns">
								<label>Nombre del producto
									<input type="text" placeholder="Nombre del producto" name="nombre" />
								</label>
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Imagen
									<input type="text"  name="imagen" />
								</label>
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Tipo de producto
									<select name="tipo">
										<c:forEach items="${tipos}" var="tipo">
											<option value="${tipo.id_tipo}">${tipo.descripcion_tipo}</option>
										</c:forEach>
									</select>
								</label>
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Precio
									<input type="text" placeholder="Precio" name="precio" />
								</label>
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Stock
									<input type="text" placeholder="Stock" name="stock" />
								</label>
							</div>
						</div>
						<input type="button" class="button" value="Guardar producto">
					</form>
				</div>
			</div>
		</div>
	</jsp:body>
</t:postresAdmin>