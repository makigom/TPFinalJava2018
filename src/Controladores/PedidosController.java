package Controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Datos.PedidoDatos;
import Datos.ProductoDatos;
import Entidades.Pedido;
import Entidades.PedidoDetalle;
import Entidades.Persona;

/**
 * Servlet implementation class PedidosController
 */
public class PedidosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Map<String,String> acciones;
	
    public PedidosController() {
        super();
        acciones = new HashMap<String, String>();
        acciones.put("/", "listado");
        acciones.put("/verDetalles", "verDetalles");
        acciones.put("/cancelar", "cancelar");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(acciones.containsKey(pathInfo)) {
			if(acciones.get(pathInfo) == "listado") {
				listado(request,response);
			} else if(acciones.get(pathInfo) == "verDetalles") {
				verDetalles(request,response);
			} else if(acciones.get(pathInfo) == "cancelar") {
				cancelar(request,response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void listado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Persona us = (Persona)request.getSession().getAttribute("usuario");
		PedidoDatos pd = new PedidoDatos();
		ArrayList<Pedido> pedidos = pd.getAllPedidos(us);
		
		request.setAttribute("pedidos", pedidos);
		
		request.getRequestDispatcher("/WEB-INF/misPedidos.jsp").forward(request, response);
	}
	
	protected void verDetalles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idPedido = Integer.parseInt(request.getParameter("id"));
		PedidoDatos pd = new PedidoDatos();
		
		request.setAttribute("pedido",pd.getPedido(idPedido));
		request.setAttribute("detalle",pd.getPedidoDetalle(idPedido));
		
		request.getRequestDispatcher("/WEB-INF/detallePedido.jsp").forward(request, response);
	}
	
	protected void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idPedido = Integer.parseInt(request.getParameter("id"));
		PedidoDatos pd = new PedidoDatos();
		ArrayList<PedidoDetalle> productos = pd.getPedidoDetalle(idPedido);
		ProductoDatos proDatos = new ProductoDatos();
		for(PedidoDetalle pdet : productos) {
			proDatos.actualizarStock(pdet.getProducto().getId_producto(), -pdet.getCantidad());
		}
		
		pd.cambiarEstado(idPedido, "Cancelado");
		
		request.getRequestDispatcher("/WEB-INF/pedidoCancelado.jsp").forward(request, response);
	}
}