package Admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Datos.TipoPostreDatos;
import Entidades.TipoPostre;

public class TipoPostreController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Map<String,String> acciones;
	
	public TipoPostreController() {
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
				request.getRequestDispatcher("/WEB-INF/admin/tipo/nuevo.jsp").forward(request, response);
			}
			else if(acciones.get(pathInfo) == "modificar") {
				int idTipo = Integer.parseInt(request.getParameter("id"));
				TipoPostreDatos tbd = new TipoPostreDatos();				
				request.setAttribute("tipo", tbd.getTipoPostre(idTipo));
				
				request.getRequestDispatcher("/WEB-INF/admin/tipo/modificar.jsp").forward(request, response);
			}
			else if(acciones.get(pathInfo) == "eliminar")
			{
				int idTipo = Integer.parseInt(request.getParameter("id"));
				TipoPostreDatos tbd = new TipoPostreDatos();
				request.setAttribute("tipo", tbd.getTipoPostre(idTipo));
				
				request.getRequestDispatcher("/WEB-INF/admin/tipo/eliminar.jsp").forward(request, response);
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
		TipoPostreDatos tbd = new TipoPostreDatos();
		request.setAttribute("tipos", tbd.getAllTipoPostre());
		request.getRequestDispatcher("/WEB-INF/admin/tipo/listado.jsp").forward(request, response);
	}
	
	protected void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TipoPostreDatos tbd = new TipoPostreDatos();
		TipoPostre tipo = new TipoPostre();
		tipo.setDescripcion_tipo(request.getParameter("descripcion"));
		tipo.setHabilitado(true);
		
		if(this.validar(request, response,true)) {	
			tbd.crearTipoPostre(tipo);		
		    response.sendRedirect("/Postres/admin/tipo/");
		} else {
			request.setAttribute("mensaje", "El tipo ingresado ya existe.");
			request.getRequestDispatcher("/WEB-INF/admin/existe.jsp").forward(request, response);
		}
	}
	
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TipoPostreDatos tbd = new TipoPostreDatos();
		int id_tipo = Integer.parseInt(request.getParameter("id"));
		
		tbd.eliminarTipoPostre(id_tipo);
		
		response.sendRedirect("/Postres/admin/tipo/");
	}
	protected void modificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TipoPostreDatos tbd = new TipoPostreDatos();		
		TipoPostre tb = new TipoPostre();			
		tb.setId_tipo(Integer.parseInt(request.getParameter("id")));		
		tb.setDescripcion_tipo(request.getParameter("descripcion"));
		
		if(this.validar(request, response,false)) {
			tbd.editarTipoPostre(tb);
			response.sendRedirect("/Postres/admin/tipo/");
		} else {
			request.setAttribute("mensaje", "El tipo ingresado ya existe.");
			request.getRequestDispatcher("/WEB-INF/admin/existe.jsp").forward(request, response);
		}	
	}
	
	protected boolean validar(HttpServletRequest request, HttpServletResponse response, boolean nuevo) throws ServletException, IOException {
		TipoPostreDatos tbd = new TipoPostreDatos();
		if(!nuevo) {
			if(tbd.getTipoPostre(Integer.parseInt(request.getParameter("id"))).getDescripcion_tipo().equals(request.getParameter("descripcion"))) {
				return true;
			}
		}
		TipoPostre tb = null;
		tb = tbd.getTipoPostre(request.getParameter("descripcion"));
		if(tb == null)
			return true;
		else
			return false;
	}
}