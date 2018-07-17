<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:postres>
	<jsp:attribute name="headHTML">
		<title>Error | La Chocolateria</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="large-12 columns">
				<div class="panel callout">
					<h3>Disculpe. Pero no se pudo confirmar su pedido.</h3>
					<pre>
						${mensaje}
					</pre>
				</div>
			</div>
		</div>
	</jsp:body>
</t:postres>