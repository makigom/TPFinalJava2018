package Admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Datos.ProductoDatos;
import Datos.TipoPostreDatos;
import Entidades.Producto;

public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Map<String,String> acciones;
	
    public ProductoController() {
        super();
        acciones = new HashMap<String,String>();
        acciones.put("/", "listar");
        acciones.put("/nuevo", "nuevo");
        acciones.put("/eliminar", "eliminar");
        acciones.put("/modificar", "modificar");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(acciones.containsKey(pathInfo)) {
			if(acciones.get(pathInfo) == "listar") {
				listar(request,response);
			} else if(acciones.get(pathInfo) == "nuevo") {
				TipoPostreDatos tbd = new TipoPostreDatos();
				request.setAttribute("tipos", tbd.getAllTipoPostre());
				request.getRequestDispatcher("/WEB-INF/admin/producto/nuevo.jsp").forward(request, response);
			} else if(acciones.get(pathInfo) == "modificar") {
				int idProducto = Integer.parseInt(request.getParameter("id"));
				ProductoDatos pd = new ProductoDatos();
				TipoPostreDatos tbd = new TipoPostreDatos();
				request.setAttribute("tipos", tbd.getAllTipoPostre());
				request.setAttribute("producto", pd.getProductoHabilitado(idProducto));
				request.getRequestDispatcher("/WEB-INF/admin/producto/modificar.jsp").forward(request, response);
			} else if(acciones.get(pathInfo) == "eliminar")
			{
				int idProducto = Integer.parseInt(request.getParameter("id"));
				ProductoDatos pd = new ProductoDatos();
				request.setAttribute("producto", pd.getProductoHabilitado(idProducto));
				request.getRequestDispatcher("/WEB-INF/admin/producto/eliminar.jsp").forward(request, response);
			}
			
			}
		}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(acciones.containsKey(pathInfo)) {
			if(acciones.get(pathInfo) == "nuevo") {
				nuevo(request,response);
			} else if(acciones.get(pathInfo) == "eliminar") {
				eliminar(request,response);
			} else if(acciones.get(pathInfo) == "modificar") {
				modificar(request,response);
			}
		}
	}
	
	protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductoDatos pd = new ProductoDatos();
		request.setAttribute("productos", pd.getAllProductosHabilitados());
		request.getRequestDispatcher("/WEB-INF/admin/producto/listado.jsp").forward(request, response);
	}
	
	protected void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Producto prod = new Producto();
		
		prod.setNombre_producto(request.getParameter("nombre"));
		prod.setImagen(request.getParameter("imagen"));
		TipoPostreDatos tbd = new TipoPostreDatos();
		prod.setTipo(tbd.getTipoPostre(Integer.parseInt(request.getParameter("tipo"))));
		prod.setPrecio(Float.parseFloat(request.getParameter("precio")));
		prod.setStock(Integer.parseInt(request.getParameter("stock")));		
		prod.setHabilitado(true);
		
		if(this.validar(request, response, true))
		{
			ProductoDatos pd = new ProductoDatos();
			pd.crearProducto(prod);
			response.sendRedirect("/Postres/admin/producto/");
		}
		
		else 
		{
			request.setAttribute("mensaje", "El producto ingresado ya existe.");
			request.getRequestDispatcher("/WEB-INF/admin/existe.jsp").forward(request, response);
		}
		
	}
	
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductoDatos pd = new ProductoDatos();
		pd.deshabilitarProducto(Integer.parseInt(request.getParameter("id")));
		
		response.sendRedirect("/Postres/admin/producto/");
	}
	protected void modificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Producto producto = new Producto();
		
		producto.setId_producto(Integer.parseInt(request.getParameter("id")));
		producto.setImagen(request.getParameter("imagen"));
		producto.setNombre_producto(request.getParameter("nombre"));
		producto.setPrecio(Float.parseFloat(request.getParameter("precio")));
		producto.setStock(Integer.parseInt(request.getParameter("stock")));
		TipoPostreDatos tbd = new TipoPostreDatos();
		producto.setTipo(tbd.getTipoPostre(Integer.parseInt(request.getParameter("tipo"))));
		
		if(this.validar(request, response,false))
		{	ProductoDatos pd = new ProductoDatos();
			pd.editarProducto(producto);
			response.sendRedirect("/Postres/admin/producto/");		
		}
		else 
		{
			request.setAttribute("mensaje", "El producto ingresado ya existe.");
			request.getRequestDispatcher("/WEB-INF/admin/existe.jsp").forward(request, response);
		}
	}
	
	protected boolean validar(HttpServletRequest request, HttpServletResponse response, boolean nuevo) throws ServletException, IOException {
	
		ProductoDatos pd = new ProductoDatos();
		if(!nuevo) {
			if(pd.getProductoHabilitado(Integer.parseInt(request.getParameter("id"))).getNombre_producto().equals(request.getParameter("nombre"))) {
				return true;
			}
		}
		Producto prod = null;
		prod = pd.getProductoHabilitado(request.getParameter("nombre"));
		if(prod == null)
			return true;
		else
			return false;		
	}
}
