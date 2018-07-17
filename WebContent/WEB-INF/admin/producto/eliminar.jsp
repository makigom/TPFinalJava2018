<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:postresAdmin>
	<jsp:attribute name="headHTML">
		<title>Eliminar producto | La Chocolateria Admin</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="large-12 columns">
				<div class="panel">	
					<h3>Eliminar producto</h3>
					<h2>¿Está seguro de que desea eliminar este producto?</h2>
					<form action="/Postres/admin/producto/eliminar" method="post">
						<input type="text" name="id" style="display:none;" value="${producto.id_producto}" />
						<div class="row">
							<div class="medium-12 columns">
								<label>Nombre del producto
									<input type="text" name="nombre" value="${producto.nombre_producto}" disabled />
								</label>
								
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Imagen
									<img src="${producto.imagen}" style="height:100px;" name="imagen" />
								</label>								
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Tipo de producto
									<select name="tipo" disabled>
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
									<input type="text" name="precio" value="${producto.precio}" disabled />
								</label>								
							</div>
						</div>
						<div class="row">
							<div class="medium-12 columns">
								<label>Stock
									<input type="text" name="stock" value="${producto.stock}" disabled />
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