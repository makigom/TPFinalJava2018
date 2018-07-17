package Entidades;


public class Persona {
	
	protected int id_cliente;
	protected String nombre_ap, nombre_usuario, clave, mail, telefono, dni;
	boolean esAdmin, habilitado;
	
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int idCliente) {
		id_cliente = idCliente;
	}
	public String getDni() {
		return dni;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre_ap() {
		return nombre_ap;
	}
	public void setNombre_ap(String nombreAp) {
		nombre_ap = nombreAp;
	}
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombreUsuario) {
		nombre_usuario = nombreUsuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Boolean getEsAdmin() {
		return esAdmin;
	}
	public void setEsAdmin(Boolean esAdmin) {
		this.esAdmin = esAdmin;
	}
	
}
