package Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

public class PedidoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Map<String,String> acciones;
	
    public PedidoController() {
        super();
        acciones = new HashMap<String,String>();
        acciones.put("/", "listar");
        acciones.put("/verDetalles", "verDetalles");
        acciones.put("/confirmar", "confirmar");
        acciones.put("/cancelar", "cancelar");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(acciones.containsKey(pathInfo)) {
			if(acciones.get(pathInfo) == "listar") {
				listar(request,response);
			} else if(acciones.get(pathInfo) == "verDetalles") {
				verDetalles(request,response);
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(acciones.containsKey(pathInfo)) {
			if(acciones.get(pathInfo) == "cancelar") {
				cancelar(request,response);
			} else if(acciones.get(pathInfo) == "confirmar") {
				confirmar(request,response);
			}
		}
	}
	
	protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PedidoDatos pd = new PedidoDatos();
		ArrayList<Pedido> pedidos = pd.getAllPedidos();
		Collections.sort(pedidos, Collections.reverseOrder(new Comparator<Pedido>() {
			public int compare(Pedido p1, Pedido p2) {
				return p1.getFechaHrPedido().compareTo(p2.getFechaHrPedido());
			}
		}));
		
		request.setAttribute("pedidos", pedidos);
		
		request.getRequestDispatcher("/WEB-INF/admin/pedido/listado.jsp").forward(request, response);
	}
	protected void verDetalles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idPedido = Integer.parseInt(request.getParameter("id"));
		PedidoDatos pd = new PedidoDatos();
		request.setAttribute("pedido", pd.getPedido(idPedido));
		request.setAttribute("detalle", pd.getPedidoDetalle(idPedido));
		
		request.getRequestDispatcher("/WEB-INF/admin/pedido/detallePedido.jsp").forward(request, response);
	}
	protected void confirmar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idPedido = Integer.parseInt(request.getParameter("id"));
		PedidoDatos pd = new PedidoDatos();
		ArrayList<PedidoDetalle > pdet = pd.getPedidoDetalle(idPedido);
		pd.cambiarEstado(idPedido, "Entregado");
		response.sendRedirect("/Postres/admin/pedido/");
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
		response.sendRedirect("/Postres/admin/pedido/");
	}
}