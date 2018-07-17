<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:postresAdmin>
	<jsp:attribute name="headHTML">
		<title>Clientes | La Chocolateria Admin</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="large-12 columns">
				<div class="panel">	
					<h3>Personas</h3>
					<h3><small>Clientes</small></h3>
					<table>
						<thead>
						<tr>
							<th>ID</th>
							<th>Nombre y apellido</th>
							<th>Nombre de usuario</th>
							<th>Contraseña</th>
							<th>E-mail</th>
							<th>DNI</th>
							<th>Teléfono</th>
							<th>Acciones</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${personas}" var="per" >
							<c:if test="${per.esAdmin == false}">
								<tr>
									<td>${per.id_cliente}</td>
									<td>${per.nombre_ap}</td>
									<td>${per.nombre_usuario}</td>
									<td>${per.clave}</td>
									<td>${per.mail}</td>
									<td>${per.dni}</td>
									<td>${per.telefono}</td>
									<td>
									<a class="button tiny info" href="/Postres/admin/persona/modificar?id=${per.id_cliente}">Modificar</a>
									<a class="button tiny alert" href="/Postres/admin/persona/eliminar?id=${per.id_cliente}">Eliminar</a>
									</td>
								</tr>
							</c:if>
							</c:forEach>
						</tbody>
					</table>
					<h3><small>Administradores</small></h3>
					<table>
						<thead>
						<tr>
							<th>ID</th>
							<th>Nombre y apellido</th>
							<th>Nombre de usuario</th>
							<th>Contraseña</th>
							<th>E-mail</th>
							<th>DNI</th>
							<th>Teléfono</th>
							<th>Acciones</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${personas}" var="per" >
							<c:if test="${per.esAdmin == true}">
								<tr>
									<td>${per.id_cliente}</td>
									<td>${per.nombre_ap}</td>
									<td>${per.nombre_usuario}</td>
									<td>${per.clave}</td>
									<td>${per.mail}</td>
									<td>${per.dni}</td>
									<td>${per.telefono}</td>
									<td>
									<a class="button tiny info" href="/Postres/admin/persona/modificar?id=${per.id_cliente}">Modificar</a>
									<a class="button tiny alert" href="/Postres/admin/persona/eliminar?id=${per.id_cliente}">Eliminar</a>
									</td>
								</tr>
							</c:if>
							</c:forEach>
						</tbody>
					</table>
					<a class="button success" href="/Postres/admin/persona/nuevo">Crear nueva persona</a>
				</div>
			</div>
		</div>
	</jsp:body>
</t:postresAdmin>