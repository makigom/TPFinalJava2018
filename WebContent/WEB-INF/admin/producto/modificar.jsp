<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:postresAdmin>
	<jsp:attribute name="headHTML">
		<title>Modificar producto | La Chocolateria Admin</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="large-12 columns">
				<div class="panel">	
					<h3>Modificar producto</h3>
					<form action="/Postres/admin/producto/modificar" method="post" id="formProducto">
						<input type="text" name="id" style="display:none;" value="${producto.id_producto}" />
						<div class="row">
							<div class="medium-12 columns">
								<label>Nombre del producto
									<input type="text" name="nombre" value="${producto.nombre_producto}" />
								</label>
								<label>Imagen
									<input type="text" name="imagen" value="${producto.imagen}"/>
								</label>
								<label>Tipo de producto
									<select name="tipo"/>
										<c:forEach items="${tipos}" var="tipo">
											<option value="${tipo.id_tipo}" <c:if test="${tipo.id_tipo == producto.tipo.id_tipo}">selected</c:if>>${tipo.descripcion_tipo}</option>
										</c:forEach>
									</select>
								</label>
								<label>Precio
									<input type="text" name="precio" value="${producto.precio}" />
								</label>
								<label>Stock
									<input type="text" name="stock" value="${producto.stock}" />
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