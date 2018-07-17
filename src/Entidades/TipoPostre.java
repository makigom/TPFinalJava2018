package Entidades;

public class TipoPostre {
	
	int id_tipo;
	String descripcion_tipo;
	boolean habilitado;
	
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	public int getId_tipo() {
		return id_tipo;
	}
	public void setId_tipo(int idTipo) {
		id_tipo = idTipo;
	}
	
	public String getDescripcion_tipo() {
		return descripcion_tipo;
	}
	public void setDescripcion_tipo(String descripcionTipo) {
		descripcion_tipo = descripcionTipo;
	}
}
