<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.util.ArrayList" %>
<%@page import="Entidades.Producto" %>
<t:postres>
	<jsp:attribute name="headHTML">
		<title>Agregar producto al carro | Postres</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="large-12 columns">
				<div class="panel callout">
					
					<h3>Carrito</h3>
					<table>
						<thead>
						<tr>
							<th>Producto</th>
							<th>Precio unitario</th>
							<th>Cantidad</th>
							<th>Subtotal</th>
						</tr>
						</thead>
						<tbody>
							<c:set var="sum" value="${0}" />
							<c:set var="i" value="${1}" />
							<c:forEach items="${sessionScope.carro}" var="pedDetalle" >
								<tr>
									<td>${pedDetalle.producto.nombre_producto}</td>
									<td>${pedDetalle.producto.precio}</td>
									<td>${pedDetalle.cantidad}</td>
									<td>${pedDetalle.producto.precio*pedDetalle.cantidad}</td>
									<td><a class="button tiny alert" href="/Postres/carro/quitar?i=${i}">Eliminar</a></td>
								</tr>
								<c:set var="sum" value="${sum + pedDetalle.producto.precio*pedDetalle.cantidad}" />
								<c:set var="i" value="${i+1}" />
							</c:forEach>
						</tbody>
					</table>
					<h4>Total: ${sum}</h4>
					<a href="#" class="button" data-reveal-id="myModal">Confirmar pedido</a>
				</div>
			</div>
		</div>
		<div id="myModal" class="reveal-modal" data-reveal aria-labelledby="modalTitle" aria-hidden="true" role="dialog">
			<h2 id="modalTitle">Ingresá la dirección para poder registrar el pedido.</h2>
			<a class="close-reveal-modal" aria-label="Close">&#215;</a>
			<form action="/Postres/carro/confirmar" method="post" id="formConfirmarPedido" >
				<div class="row">
					<div class="medium-12 columns">
						<label>Dirección
							<input type="text" name="direccion" placeholder="Dirección" />
						</label>
					</div>
				</div>
				<input type="button" class="button" value="Registrar pedido">
			</form>
		</div>
	</jsp:body>
</t:postres>