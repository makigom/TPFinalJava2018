<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.util.ArrayList" %>
<%@page import="Entidades.Producto" %>
<t:postres>
	<jsp:attribute name="headHTML">
		<title>Mis Pedidos | La Chocolateria</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="large-12 columns">
				<div class="panel">	
					<h3>Mis Pedidos</h3>
					<h3><small>Pendientes</small></h3>
					<table>
						<thead>
						<tr>
							<th>ID</th>
							<th>Dirección</th>
							<th>Fecha y hora</th>
							<th>Estado</th>
							<th>Acciones</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${pedidos}" var="ped" >
							<c:if test="${ped.estado == 'Pendiente'}">
								<tr>
									<td>${ped.id_pedido}</td>
									<td>${ped.direccion}</td>
									<td>${ped.fechaHrPedido}</td>
									<td>${ped.estado}</td>
									<td>
									<a class="button tiny info" href="/Postres/misPedidos/verDetalles?id=${ped.id_pedido}">Ver detalles</a>
									<a class="button tiny alert" href="/Postres/misPedidos/cancelar?id=${ped.id_pedido}">Cancelar pedido</a>
									</td>
								</tr>
							</c:if>
							</c:forEach>
						</tbody>
					</table>
					<h3><small>Entregados</small></h3>
					<table>
						<thead>
						<tr>
							<th>ID</th>
							<th>Dirección</th>
							<th>Fecha y hora</th>
							<th>Estado</th>
							<th>Acciones</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${pedidos}" var="ped" >
							<c:if test="${ped.estado == 'Entregado'}">
								<tr>
									<td>${ped.id_pedido}</td>
									<td>${ped.direccion}</td>
									<td>${ped.fechaHrPedido}</td>
									<td>${ped.estado}</td>
									<td>
									<a class="button tiny info" href="/Postres/misPedidos/verDetalles?id=${ped.id_pedido}">Ver detalles</a>
									</td>
								</tr>
							</c:if>
							</c:forEach>
						</tbody>
					</table>
					<h3><small>Cancelados</small></h3>
					<table>
						<thead>
						<tr>
							<th>ID</th>
							<th>Dirección</th>
							<th>Fecha y hora</th>
							<th>Estado</th>
							<th>Acciones</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${pedidos}" var="ped" >
							<c:if test="${ped.estado == 'Cancelado'}">
								<tr>
									<td>${ped.id_pedido}</td>
									<td>${ped.direccion}</td>
									<td>${ped.fechaHrPedido}</td>
									<td>${ped.estado}</td>
									<td>
									<a class="button tiny info" href="/Postres/misPedidos/verDetalles?id=${ped.id_pedido}">Ver detalles</a>
									</td>
								</tr>
							</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</jsp:body>
</t:postres>