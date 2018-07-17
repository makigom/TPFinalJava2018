package Controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Datos.ProductoDatos;
import Datos.TipoPostreDatos;
import Entidades.Producto;

public class PostresController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Map<String,String> acciones;
	
    public PostresController() {
        super();
        acciones = new HashMap<String,String>();
        acciones.put("/productos", "listarProductos");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(acciones.containsKey(pathInfo)) {
			if(acciones.get(pathInfo) == "listarProductos") {
				listarProductos(request,response);
			}
		} else {
			RequestDispatcher rDis = request.getRequestDispatcher("/WEB-INF/index.jsp");
			rDis.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void listarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductoDatos pd = new ProductoDatos();
		TipoPostreDatos tbd = new TipoPostreDatos();
		ArrayList<Producto> prods;
		
		if(request.getParameter("tipo") != null) {
			int idTipo = Integer.parseInt(request.getParameter("tipo"));
			prods = pd.getAllProductos(idTipo);
		} else {
			prods = pd.getAllProductosHabilitados();
		}
		
		request.setAttribute("tipos", tbd.getAllTipoPostre());
		request.setAttribute("productos", prods);
		request.setAttribute("cant", prods.size());
		
		request.getRequestDispatcher("/WEB-INF/productos.jsp").forward(request, response);		
	}
}