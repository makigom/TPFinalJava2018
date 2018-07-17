<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.util.ArrayList" %>
<%@page import="Entidades.Producto" %>
<t:postres>
	<jsp:attribute name="headHTML">
		<title>Agregar producto al carro | La Chocolateria</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="large-12 columns">
				<div class="panel callout">
					<h3>${producto.nombre_producto}</h3>
					<h4>${producto.precio}</h4>
					<form action="/Postres/carro/agregar" method="post" id="formAgregarProducto">
						<input type="text" name="id_producto" value="${producto.id_producto}" style="display:none;" />
						<div class="row">
							<div class="medium-12 columns">
								<label>Cantidad
									<input type="text" name="cantidad" placeholder="Cantidad" />
								</label>
							</div>
						</div>
						<input type="button" class="button" value="Agregar a carrito">
					</form>
				</div>
			</div>
		</div>
	</jsp:body>
</t:postres>