package Controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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
import Entidades.Producto;

public class CarritoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Map<String,String> acciones;

	public CarritoController() {
        super();
        acciones = new HashMap<String,String>();
        acciones.put("/agregar", "agregar");
        acciones.put("/ver", "ver");
        acciones.put("/quitar", "quitarProducto");
        acciones.put("/confirmar", "confirmarCompra");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(acciones.containsKey(pathInfo)) {
			if(acciones.get(pathInfo) == "agregar") {
				agregar(request,response);
			} else if(acciones.get(pathInfo) == "ver") {
				ver(request,response);
			} else if(acciones.get(pathInfo) == "quitarProducto") {
				quitarProducto(request,response);
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(acciones.containsKey(pathInfo)) {
			if(acciones.get(pathInfo) == "agregar") {
				int idProducto = Integer.parseInt(request.getParameter("id_producto"));
				int cantidad = Integer.parseInt(request.getParameter("cantidad"));
				PedidoDetalle pedDet = new PedidoDetalle();
				ProductoDatos pd = new ProductoDatos();
				pedDet.setProducto(pd.getProductoHabilitado(idProducto));
				pedDet.setCantidad(cantidad);
				ArrayList<PedidoDetalle> carro = (ArrayList<PedidoDetalle>)request.getSession().getAttribute("carro");
				carro.add(pedDet);
				request.getSession().setAttribute("carro", carro);
				
				response.sendRedirect("/Postres/carro/ver");
			} else if(acciones.get(pathInfo) == "confirmarCompra") {
				confirmarCompra(request,response);
			}
		}
	}
	
	protected void agregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idProd = request.getParameter("id");
		int id = Integer.parseInt(idProd);
		ProductoDatos pd = new ProductoDatos();
		Producto prod = pd.getProductoHabilitado(id);
		request.setAttribute("producto", prod);
		
		request.getRequestDispatcher("/WEB-INF/agregarProducto.jsp").forward(request, response);
	}
	protected void ver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/verCarro.jsp").forward(request, response);
	}
	protected void quitarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int lugar = Integer.parseInt(request.getParameter("i"));
		ArrayList<PedidoDetalle> carro = (ArrayList<PedidoDetalle>)request.getSession().getAttribute("carro");
		carro.remove(lugar-1);
		request.getSession().setAttribute("carro", carro);
		
		response.sendRedirect("/Postres/carro/ver");
	}
	
	protected void confirmarCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		float total = 0;
		ArrayList<PedidoDetalle> productos = (ArrayList<PedidoDetalle>) request.getSession().getAttribute("carro");
		int bandera = 1;
		
		String mensaje = "Los productos:</br>";
		
		ProductoDatos pd = new ProductoDatos();
		for(PedidoDetalle pdet : productos) {
			if(!pd.hayStock(pdet.getProducto().getId_producto(), pdet.getCantidad())) {
				bandera = 0;
				mensaje += pdet.getProducto().getId_producto()+"- "+pdet.getProducto().getNombre_producto()+"</br>";
			}
			total = total + ((pdet.getProducto().getPrecio()) * pdet.getCantidad());
		}
		if(bandera == 1) {
			Pedido pedido = new Pedido();
			for(PedidoDetalle pdet : productos) {
				pd.actualizarStock(pdet.getProducto().getId_producto(), pdet.getCantidad());
			}
			Timestamp fecha = new Timestamp(new java.util.Date().getTime());		
			pedido.setFechaHrPedido(fecha);
			pedido.setPersona((Persona)request.getSession().getAttribute("usuario"));
			pedido.setDireccion(request.getParameter("direccion"));
			pedido.setTotal(total);
			
			PedidoDatos peDatos = new PedidoDatos();
			peDatos.crearPedido(pedido, productos);
			request.getSession().setAttribute("carro", new ArrayList<PedidoDetalle>());
			
			request.getRequestDispatcher("/WEB-INF/pedidoRegistrado.jsp").forward(request, response);
		} else {
			mensaje += "No poseen el stock suficiente.";
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/WEB-INF/errorCarro.jsp").forward(request, response);
		}
	}
}