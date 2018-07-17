<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:postres>
	<jsp:attribute name="headHTML">
		<title>Contacto | La Chocolateria</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="large-12 columns">
				<div class="panel callout">
					<h3>Para contactarnos...</h3>
					<h4>Puede llamarnos al télefono:</h4>
					<ul>
						<li><i class="fi-telephone"></i> +54 341 4123456</li>						
					</ul>
					<h4>O enviarnos un correo electrónico a:</h4>
					<p><i class="fi-mail"></i>contacto@lachocolateria.com</p>
				</div>
			</div>
		</div>
	</jsp:body>
</t:postres>