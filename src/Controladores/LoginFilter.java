package Controladores;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

    public LoginFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//Convertir los tipos de request y response para usar sesion y poder hacer dispatch
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		//Obtener sesion desde el request
		HttpSession sesion = req.getSession(false);
		//Validar y redirigir
		if(sesion == null || sesion.getAttribute("usuario") == null) {
			String mensaje = "Disculpe. No tiene permiso para acceder a esta página. Inicie sesión.";
			req.setAttribute("errores", mensaje);
			RequestDispatcher rDis = req.getRequestDispatcher("/WEB-INF/errorLogin.jsp");
			rDis.forward(req, res);
		} else {
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}