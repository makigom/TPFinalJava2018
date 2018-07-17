<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${sessionScope.usuario != null}">
	<li>
		<a href="/Postres/carro/ver">Carrito <span class="alert round label">${fn:length(sessionScope.carro)}</span></a>
	</li>
	<li class="has-dropdown">
		<a href="#"><i class="fi-torso"></i> ${sessionScope.usuario.nombre_usuario}</a>
		<ul class="dropdown">
			<c:if test="${sessionScope.usuario.esAdmin == true}">
				<li><a href="/Postres/admin">Panel de Administrador</a></li>
			</c:if>
			<li><a href="/Postres/usuario/editarPerfil">Editar perfil</a></li>
			<li><a href="/Postres/misPedidos/">Mis Pedidos</a></li>
			<li><a href="/Postres/usuario/logout">Cerrar sesión</a></li>
		</ul>
	</li>
</c:if>