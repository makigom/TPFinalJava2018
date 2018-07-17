package Entidades;

import java.sql.Blob;

public class Producto {
	
	protected int id_producto, stock;
	protected TipoPostre tipo;
	protected String nombre_producto;
	protected float precio;
	protected String imagen;
	protected boolean habilitado;
	
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	public int getId_producto() {
		return id_producto;
	}
	public void setId_producto(int idProducto) {
		id_producto = idProducto;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public TipoPostre getTipo() {
		return tipo;
	}
	public void setTipo(TipoPostre tipo) {
		this.tipo = tipo;
	}
	public String getNombre_producto() {
		return nombre_producto;
	}
	public void setNombre_producto(String nombreProducto) {
		nombre_producto = nombreProducto;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
}
