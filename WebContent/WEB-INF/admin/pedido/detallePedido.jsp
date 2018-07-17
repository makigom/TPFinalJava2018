<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:postresAdmin>
	<jsp:attribute name="headHTML">
		<title>Detalle del pedido | La Chocolateria Admin</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="large-12 columns">
				<div class="panel callout">
					<h3>Pedido <small>ID: ${pedido.id_pedido}</small></h3>
					<ul>
						<li>Nombre y apellido: <strong>${pedido.persona.nombre_ap}</strong></li>
						<li>Usuario: <strong>${pedido.persona.nombre_usuario}</strong></li>
						<li>Direccion: <strong>${pedido.direccion}</strong></li>
						<li>Fecha y hora: <strong>${pedido.fechaHrPedido}</strong></li>
						<li>Estado: <strong>${pedido.estado}</strong></li>
					</ul>
					<h4>Detalles del pedido</h4>
					<table>
						<thead>
						<tr>
							<th>Producto</th>
							<th>Precio unitario</th>
							<th>Cantidad</th>
						</tr>
						</thead>
						<tbody>
							<c:set var="sum" value="${0}" />
							<c:set var="i" value="${1}" />
							<c:forEach items="${detalle}" var="pedDetalle" >
								<tr>
									<td>${pedDetalle.producto.nombre_producto} <c:if test="${pedDetalle.producto.habilitado == false}"><span class="alert round label">Deshabilitado</span></c:if></td>
									<td>${pedDetalle.producto.precio}</td>
									<td>${pedDetalle.cantidad}</td>
								</tr>
								
							</c:forEach>
						</tbody>
					</table>
					<h4>Total: ${pedido.total}</h4>
					<c:if test="${pedido.estado == 'Pendiente'}">
						<form method="post" action="/Postres/admin/pedido/confirmar">
							<input type="text" style="display:none;" name="id" value="${pedido.id_pedido}" />
							<input type="submit" class="button" value="Confirmar entrega" />
						</form>
						<form method="post" action="/Postres/admin/pedido/cancelar">
							<input type="text" style="display:none;" name="id" value="${pedido.id_pedido}" />
							<input type="submit" class="button alert" value="Cancelar pedido" />
						</form>
					</c:if>
				</div>
			</div>
		</div>
	</jsp:body>
</t:postresAdmin>