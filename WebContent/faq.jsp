<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:postres>
	<jsp:attribute name="headHTML">
		<title>Preguntas frecuentes | La Chocolateria</title>
	</jsp:attribute>
	
	<jsp:attribute name="imagenTop">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="large-12 columns">
				<div class="panel callout">
					<h1>FAQ <small>/ Preguntas frecuentes</small></h1>
					<ol>
						<li><strong>¿Cuál es la forma de pago?</strong>
							<p>El pago se realiza en efectivo al momento de la entrega del pedido, en el domicilio del cliente.</p>
						</li>						
						
						<li><strong>¿Debo ser mayor de 18 años para realizar un pedido?</strong>
							<p>Sí. Al momento de la entrega del pedido deberás presentar tu DNI para corroborar tu nombre y tu edad.</p>
						</li>						
						
						<li><strong>¿Cuáles son los días y horarios de entrega de los pedidos?</strong>
							<p>Las entregas se realizan de Lunes a Viernes de 20 a 23 hs.</p>
						</li>						
						
						<li><strong>¿Qué pasa si realizo mi pedido fuera de los horarios de entrega?</strong>
							<p>Tu pedido quedará registrado y será enviado tan pronto comience el horario de entrega.</p>
						</li>						
						
						<li><strong>¿Puedo realizar pedidos al por mayor?</strong>
							<p>Para realizar pedidos al por mayor con descuento comunicate telefónicamente al 0341-6955800.  </p>
						</li>						
						
						<li><strong>¿Cuál es el costo de envío?</strong>
							<p>El envío es sin cargo.</p>
						</li>
												
					</ol>
				</div>
			</div>
		</div>
	</jsp:body>
</t:postres>