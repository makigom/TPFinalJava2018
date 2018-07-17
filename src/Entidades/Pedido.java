package Entidades;
import java.sql.Timestamp;

public class Pedido {

	int id_pedido;
	String estado, direccion;
	Timestamp fechaHrPedido; //ver si incluye hora
	Persona persona;
	float total;
	
	public int getId_pedido() {
		return id_pedido;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public void setId_pedido(int idPedido) {
		id_pedido = idPedido;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Timestamp getFechaHrPedido() {
		return fechaHrPedido;
	}
	public void setFechaHrPedido(Timestamp fechaHrPedido) {
		this.fechaHrPedido = fechaHrPedido;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
		
}
