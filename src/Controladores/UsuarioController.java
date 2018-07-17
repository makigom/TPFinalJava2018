package Controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Datos.PersonaDatos;
import Entidades.PedidoDetalle;
import Entidades.Persona;
import Entidades.Producto;

public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Map<String,String> acciones;
	
    public UsuarioController() {
        super();
        acciones = new HashMap<String, String>();
        acciones.put("/login", "login");
        acciones.put("/logout", "logout");
        acciones.put("/registro", "registro");
        acciones.put("/editarPerfil", "editarPerfil");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(acciones.containsKey(pathInfo)) {
			if(acciones.get(pathInfo) == "logout") {
				logout(request,response);
			} else if(acciones.get(pathInfo) == "editarPerfil") {
				request.getRequestDispatcher("/WEB-INF/editarPerfil.jsp").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(acciones.containsKey(pathInfo)) {
			String ac = acciones.get(pathInfo); 
			if(ac == "login") {
				login(request,response);
			} else if(ac == "registro") {
				registro(request,response);
			}			
			 else if(acciones.get(pathInfo) == "editarPerfil") {
					editarPerfil(request,response);		
	}
		}
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usr = request.getParameter("nombre_usuario");
		String psw = request.getParameter("clave");
		String mensaje = "";
		
		if(!(usr.equals("") || psw.equals(""))) {
			PersonaDatos cd = new PersonaDatos();
			Persona us = cd.getPersonaHabilitada(usr);
			if(us!=null) {
				if(us.getClave().equals(psw) && us.isHabilitado()) {
					HttpSession sesion = request.getSession();
					ArrayList<PedidoDetalle> carro = new ArrayList<PedidoDetalle>();
					
					sesion.setAttribute("usuario", us);
					sesion.setAttribute("carro", carro);
					request.getRequestDispatcher("/WEB-INF/loginOk.jsp").forward(request, response);
					return;
				} else {
					mensaje = "Contraseña incorrecta";
				}
			} else {
				mensaje = "El usuario no existe";
			}
		} else {
			mensaje = "Datos incompletos";
		}
		request.setAttribute("errores", mensaje);
		request.getRequestDispatcher("/WEB-INF/errorLogin.jsp").forward(request, response);
	}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("usuario", null);
		request.getSession().setAttribute("carro", null);
		request.getSession().invalidate();
		
		response.sendRedirect("/Postres/");
	}

	protected void registro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Persona persona = new Persona();
			persona.setNombre_ap(request.getParameter("nombreAp"));
			persona.setNombre_usuario(request.getParameter("nombreUsr"));
			persona.setClave(request.getParameter("clave"));
			persona.setMail(request.getParameter("mail"));
			persona.setTelefono(request.getParameter("telefono"));
			persona.setDni(request.getParameter("dni"));
			persona.setEsAdmin(false);
			
			if(this.validar(request, response))
			{PersonaDatos pd = new PersonaDatos();
			 pd.crearPersona(persona);
			 request.getRequestDispatcher("/WEB-INF/registroOk.jsp").forward(request, response);
			 }
			else{request.getRequestDispatcher("/WEB-INF/existeUsr.jsp").forward(request, response);}
			
			
			
	}
	
	protected void editarPerfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Persona persona = new Persona();
		persona.setNombre_ap(request.getParameter("nombreAp"));
		persona.setNombre_usuario(request.getParameter("nombreUsr"));
		persona.setClave(request.getParameter("clave"));
		persona.setMail(request.getParameter("mail"));
		persona.setTelefono(request.getParameter("telefono"));
		persona.setDni(request.getParameter("dni"));
		Persona p = (Persona) request.getSession().getAttribute("usuario");
		persona.setId_cliente(p.getId_cliente());
				
		PersonaDatos pd = new PersonaDatos();
		pd.editarPerfil(persona);
		
		request.getSession().setAttribute("nombreAp",persona.getNombre_ap());
		request.getSession().setAttribute("clave",persona.getClave());
		request.getSession().setAttribute("mail",persona.getMail());
		request.getSession().setAttribute("telefono",persona.getTelefono());
		
		request.getRequestDispatcher("/WEB-INF/editarOk.jsp").forward(request, response);		
	}
	
	protected boolean validar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PersonaDatos pd = new PersonaDatos();
		String nombre_usr = request.getParameter("nombreUsr");
		Persona persona = null;
		persona = pd.getPersonaHabilitada(nombre_usr);
		if(persona == null)
			{return true;}
		else return false;
	}
}