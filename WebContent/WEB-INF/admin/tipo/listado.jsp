<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:postresAdmin>
	<jsp:attribute name="headHTML">
		<title>Tipo de Producto | La Chocolateria Admin</title>
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
							<th>Descripción</th>
							<th>Acciones</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${tipos}" var="tb" >
								<tr>
									<td>${tb.id_tipo}</td>
									<td>${tb.descripcion_tipo}</td>
									<td>
									<a class="button tiny info" href="/Postres/admin/tipo/modificar?id=${tb.id_tipo}">Modificar</a>
									<a class="button tiny alert" href="/Postres/admin/tipo/eliminar?id=${tb.id_tipo}">Eliminar</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<a class="button success" href="/Postres/admin/tipo/nuevo">Crear nuevo tipo de producto</a>
				</div>
			</div>
		</div>
	</jsp:body>
</t:postresAdmin>