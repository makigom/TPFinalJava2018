<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:postres>
	<jsp:attribute name="headHTML">
		<title>Productos | La Chocolateria</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row text-center">
			<div class="large-12 columns">
				<h1>Productos <small>/ Todos</small></h1>
			</div>
		</div>
		<div class="row text-center">
			<div class="large-12 columns">
				<p>Filtrar por:</p>
				<p>|| <a href="/Postres/p/productos">Todos</a> ||
					<c:forEach items="${tipos}" var="tb">
						<a href="/Postres/p/productos?tipo=${tb.id_tipo}">${tb.descripcion_tipo}</a> ||
					</c:forEach>
				</p>
			</div>
		</div>
		<div class="row text-center">
			<c:forEach items="${productos}" var="prod">
				<div class="medium-3 columns">
					<ul class="pricing-table">
						<li class="title">${prod.nombre_producto}</li>
						<li class="image"><img src="${prod.imagen}" alt="${prod.nombre_producto}" title="${prod.nombre_producto}" /></li>
						<li class="price">$ ${prod.precio}</li>
						<c:if test="${prod.stock > 0}">
							<li class="cta-button">
								<a class="button" href="/Postres/carro/agregar?id=${prod.id_producto}">Agregar al carrito</a>
							</li>
						</c:if>
						<c:if test="${prod.stock == 0}">
							<li class="cta-button">
								<a class="button alert disabled" href="#">No hay en stock</a>
							</li>
						</c:if>
					</ul>
				</div>
			</c:forEach>
		</div>
	</jsp:body>
</t:postres>