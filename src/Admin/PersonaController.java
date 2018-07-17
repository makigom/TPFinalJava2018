package Admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Datos.PersonaDatos;
import Entidades.Persona;

public class PersonaController extends HttpServlet {

	Map<String,String> acciones;
	
	public PersonaController() {
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
				request.getRequestDispatcher("/WEB-INF/admin/persona/nuevo.jsp").forward(request, response);
			}
			else if(acciones.get(pathInfo) == "modificar") {
				int idPersona = Integer.parseInt(request.getParameter("id"));
				PersonaDatos pd = new PersonaDatos();				
				request.setAttribute("persona", pd.getPersonaHabilitada(idPersona));
				
				request.getRequestDispatcher("/WEB-INF/admin/persona/modificar.jsp").forward(request, response);
			}
			else if(acciones.get(pathInfo) == "eliminar")
			{
				int idPersona = Integer.parseInt(request.getParameter("id"));
				PersonaDatos pd = new PersonaDatos();
				request.setAttribute("persona", pd.getPersonaHabilitada(idPersona));
				
				request.getRequestDispatcher("/WEB-INF/admin/persona/eliminar.jsp").forward(request, response);
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
		PersonaDatos pd = new PersonaDatos();
		request.setAttribute("personas", pd.getAllPersonas());
		request.getRequestDispatcher("/WEB-INF/admin/persona/listado.jsp").forward(request, response);
	}
	
	protected void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PersonaDatos pd = new PersonaDatos();
		Persona persona = new Persona();
		
		persona.setNombre_ap(request.getParameter("nom_ap"));
		persona.setNombre_usuario(request.getParameter("nom_usr"));
		persona.setClave(request.getParameter("clave"));
		persona.setMail(request.getParameter("mail"));
		persona.setDni(request.getParameter("dni"));
		persona.setTelefono(request.getParameter("telefono"));
		persona.setHabilitado(true);
		if(request.getParameter("esAdmin") == null)
		{ persona.setEsAdmin(false);}
		else {persona.setEsAdmin(true);}
		
		if(this.validar(request, response,true))
			{
				pd.crearPersona(persona);
				response.sendRedirect("/Postres/admin/persona/");
			}
		else {
			request.setAttribute("mensaje", "El nombre de usuario ingresado ya existe.");
			request.getRequestDispatcher("/WEB-INF/admin/existe.jsp").forward(request, response);
		}
	}
	
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idPersona = Integer.parseInt(request.getParameter("id"));
		int idSesion = ((Persona)request.getSession(false).getAttribute("usuario")).getId_cliente();
		if(idPersona == idSesion) {
			String mensaje = "No se pudo completar esta acción. No puede eliminarse a usted mismo.";
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/WEB-INF/admin/existe.jsp").forward(request, response);
		} else {
			PersonaDatos pd = new PersonaDatos();
			pd.eliminarPersona(idPersona);
			response.sendRedirect("/Postres/admin/persona/");
		}
	}
	protected void modificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersonaDatos pd = new PersonaDatos();		
		Persona persona = new Persona();		
		
		persona.setNombre_ap(request.getParameter("nom_ap"));
		persona.setNombre_usuario(request.getParameter("nom_usr"));
		persona.setClave(request.getParameter("clave"));
		persona.setMail(request.getParameter("mail"));
		persona.setDni(request.getParameter("dni"));
		persona.setTelefono(request.getParameter("telefono"));
		if(request.getParameter("esAdmin") == null)
		{ persona.setEsAdmin(false);}
		else {persona.setEsAdmin(true);}
		persona.setId_cliente(Integer.parseInt(request.getParameter("id")));
		
		if(this.validar(request, response,false))
			{
				pd.editarPersona(persona);
				response.sendRedirect("/Postres/admin/persona/");
			}	
		else {
			request.setAttribute("mensaje", "El nombre de usuario ingresado ya existe.");
			request.getRequestDispatcher("/WEB-INF/admin/existe.jsp").forward(request, response);
		}
		
		
	}
	
	protected boolean validar(HttpServletRequest request, HttpServletResponse response, boolean nuevo) throws ServletException, IOException {
	
		PersonaDatos pd = new PersonaDatos();
		if(!nuevo) {
			if(pd.getPersonaHabilitada(Integer.parseInt(request.getParameter("id"))).getNombre_usuario().equals(request.getParameter("nom_usr"))) {
				return true;
			}
		}	
		String nombre_usr = request.getParameter("nom_usr");
		Persona persona = null;
		persona = pd.getPersonaHabilitada(nombre_usr);
		if(persona == null) {
			return true;
		} else
			return false;
	}	
}