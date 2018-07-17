<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:postresAdmin>
	<jsp:attribute name="headHTML">
		<title>Productos | La Chocolateria Admin</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="large-12 columns">
				<div class="panel">
					<h3>Listado</h3>
					<table>
						<thead>
						<tr>
							<th>ID</th>
							<th>Nombre de producto</th>
							<th>Imagen</th>
							<th>Tipo de producto</th>
							<th>Precio</th>
							<th>Stock</th>
							<th>Acciones</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${productos}" var="p" >
								<tr>
									<td>${p.id_producto}</td>
									<td>${p.nombre_producto}</td>
									<td><img src="${p.imagen}" style="height:100px;" /></td>
									<td>${p.tipo.descripcion_tipo}</td>
									<td>${p.precio}</td>
									<td>${p.stock}</td>
									<td>
									<a class="button tiny info" href="/Postres/admin/producto/modificar?id=${p.id_producto}">Modificar</a>
									<a class="button tiny alert" href="/Postres/admin/producto/eliminar?id=${p.id_producto}">Eliminar</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<a class="button success" href="/Postres/admin/producto/nuevo">Crear nuevo producto</a>
				</div>
			</div>
		</div>
	</jsp:body>
</t:postresAdmin>