package Datos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Entidades.Pedido;
import Entidades.PedidoDetalle;
import Entidades.Persona;
import appExceptions.ApplicationException;

import com.mysql.jdbc.PreparedStatement;

public class PedidoDatos {

	public void crearPedido(Pedido pedido, ArrayList<PedidoDetalle> productos) throws ApplicationException, IOException, ClassNotFoundException {
		Connection miConexion = null;
		PreparedStatement psCrear = null;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				
				psCrear = (PreparedStatement)miConexion.prepareStatement("INSERT INTO pedidos (fecha_pedido" + 
						",id_cliente,direccion_envio,estado,total) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
				psCrear.setTimestamp(1, pedido.getFechaHrPedido());
				psCrear.setInt(2, pedido.getPersona().getId_cliente());
				psCrear.setString(3, pedido.getDireccion());				
				psCrear.setString(4, "Pendiente");
				psCrear.setFloat(5, pedido.getTotal());
				psCrear.executeUpdate();
				ResultSet id = psCrear.getGeneratedKeys();
				id.next();
				int idPedido = id.getInt(1);
				for(PedidoDetalle pedidoDet: productos) {
					psCrear = (PreparedStatement)miConexion.prepareStatement("INSERT INTO productos_pedidos " + 
					"(id_pedido,id_producto,cantidad) VALUES(?,?,?)");
					psCrear.setInt(1, idPedido);
					psCrear.setInt(2, pedidoDet.getProducto().getId_producto());
					psCrear.setInt(3, pedidoDet.getCantidad());

					psCrear.execute();
				}

			}
		}catch(SQLException e){
			
			throw new ApplicationException("Error al recuperar estado en la base de datos", e);	
		}
		finally{
			
			try {
						
				if(psCrear != null) psCrear.close();
				if(miConexion != null) miConexion.close();				
				
			} catch(SQLException e){
				
				throw new ApplicationException("Error al recuperar estado en la base de datos", e);	
				
			}
			finally{
				
				Adapter.getInstancia().releaseConn();
				
			}
			
		}
	}

	public void cambiarEstado(int id_pedido, String estado) throws ApplicationException, IOException, ClassNotFoundException {
		Connection miConexion = null;
		PreparedStatement psEditar = null;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				
				psEditar = (PreparedStatement)miConexion.prepareStatement("UPDATE pedidos" + 
				" SET estado=? WHERE id_pedido=? ;");
				psEditar.setString(1, estado);
				psEditar.setInt(2, id_pedido);
				psEditar.execute();
			}
		}catch(SQLException e){
			
			throw new ApplicationException("Error al recuperar estado en la base de datos", e);	
		}
		finally{
			
			try {
						
				if(psEditar != null) psEditar.close();
				if(miConexion != null) miConexion.close();				
				
			} catch(SQLException e){
				
				throw new ApplicationException("Error al recuperar estado en la base de datos", e);	
				
			}
			finally{
				
				Adapter.getInstancia().releaseConn();
				
			}
			
		}
	} 

	public ArrayList<Pedido> getAllPedidos() throws ApplicationException, IOException, ClassNotFoundException
	{
		Connection miConexion = null;
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		PreparedStatement psGetAll = null;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				
				psGetAll = (PreparedStatement)miConexion.prepareStatement("SELECT id_pedido," + 
				"fecha_pedido,id_cliente,direccion_envio,estado,total FROM pedidos;");
				ResultSet rsGetAll = psGetAll.executeQuery();
				while(rsGetAll.next()) {
					Pedido pedido = new Pedido();
					pedido.setId_pedido(rsGetAll.getInt(1));
					pedido.setFechaHrPedido(rsGetAll.getTimestamp(2));
					PersonaDatos pd = new PersonaDatos();
					pedido.setPersona(pd.getPersona(rsGetAll.getInt(3)));
					pedido.setDireccion(rsGetAll.getString(4));
					pedido.setEstado((rsGetAll.getString(5)));
					pedido.setTotal(rsGetAll.getFloat(6));
					pedidos.add(pedido);
					pedido = null;
				}

			}
		}catch(SQLException e){
			
			throw new ApplicationException("Error al recuperar estado en la base de datos", e);	
		}
		finally{
			
			try {
						
				if(psGetAll != null) psGetAll.close();
				if(miConexion != null) miConexion.close();				
				
			} catch(SQLException e){
				
				throw new ApplicationException("Error al recuperar estado en la base de datos", e);	
				
			}
			finally{
				
				Adapter.getInstancia().releaseConn();
				
			}
			
		}
		return pedidos;
	}
	
	public ArrayList<Pedido> getAllPedidos(Persona per) throws IOException, ClassNotFoundException, ApplicationException {
		Connection miConexion = null;
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		PreparedStatement psGetAll = null;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				
				psGetAll = (PreparedStatement)miConexion.prepareStatement("SELECT id_pedido," + 
				"fecha_pedido,id_cliente,direccion_envio,estado,total FROM pedidos WHERE id_cliente=? ;");
				psGetAll.setInt(1, per.getId_cliente());
				ResultSet rsGetAll = psGetAll.executeQuery();
				while(rsGetAll.next()) {
					Pedido pedido = new Pedido();
					pedido.setId_pedido(rsGetAll.getInt(1));
					pedido.setFechaHrPedido(rsGetAll.getTimestamp(2));
					PersonaDatos pd = new PersonaDatos();
					pedido.setPersona(pd.getPersonaHabilitada(rsGetAll.getInt(3)));
					pedido.setDireccion(rsGetAll.getString(4));
					pedido.setEstado((rsGetAll.getString(5)));
					pedido.setTotal(rsGetAll.getFloat(6));
					pedidos.add(pedido);
					pedido = null;
				}

			}
		}catch(SQLException e){
			
			throw new ApplicationException("Error al recuperar estado en la base de datos", e);	
		}
		finally{
			
			try {
						
				if(psGetAll != null) psGetAll.close();
				if(miConexion != null) miConexion.close();				
				
			} catch(SQLException e){
				
				throw new ApplicationException("Error al recuperar estado en la base de datos", e);	
				
			}
			finally{
				
				Adapter.getInstancia().releaseConn();
				
			}
			
		}
		return pedidos;
	}
	
	public Pedido getPedido(int id) throws IOException, ClassNotFoundException, ApplicationException {
		Connection miConexion = null;
		Pedido pedido = null;
		PreparedStatement psGetAll = null;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				
				psGetAll = (PreparedStatement)miConexion.prepareStatement("SELECT id_pedido," + 
				"fecha_pedido,id_cliente,direccion_envio,estado,total FROM pedidos WHERE id_pedido=? ;");
				psGetAll.setInt(1, id);
				ResultSet rsGetAll = psGetAll.executeQuery();
				while(rsGetAll.next()) {
					pedido = new Pedido();
					pedido.setId_pedido(rsGetAll.getInt(1));
					pedido.setFechaHrPedido(rsGetAll.getTimestamp(2));
					PersonaDatos pd = new PersonaDatos();
					pedido.setPersona(pd.getPersonaHabilitada(rsGetAll.getInt(3)));
					pedido.setDireccion(rsGetAll.getString(4));
					pedido.setEstado((rsGetAll.getString(5)));
					pedido.setTotal(rsGetAll.getFloat(6));
				}
				psGetAll.close();
				miConexion.close();
			}
		}catch(SQLException e){
			
			throw new ApplicationException("Error al recuperar estado en la base de datos", e);	
		}
		finally{
			
			try {
						
				if(psGetAll != null) psGetAll.close();
				if(miConexion != null) miConexion.close();				
				
			} catch(SQLException e){
				
				throw new ApplicationException("Error al recuperar estado en la base de datos", e);	
				
			}
			finally{
				
				Adapter.getInstancia().releaseConn();
				
			}
			
		}
		return pedido;
	}
	
	public ArrayList<PedidoDetalle> getPedidoDetalle(int id) throws ApplicationException, IOException, ClassNotFoundException {
		Connection miConexion = null;
		ArrayList<PedidoDetalle> pedidoDetalle = new ArrayList<PedidoDetalle>();
		PreparedStatement psGetAll = null;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				
				psGetAll = (PreparedStatement)miConexion.prepareStatement("SELECT " + 
				"id_producto,cantidad FROM productos_pedidos WHERE id_pedido=? ;");
				psGetAll.setInt(1,id);
				ResultSet rsGetAll = psGetAll.executeQuery();
				ProductoDatos proDat = new ProductoDatos();
				while(rsGetAll.next()) {
					PedidoDetalle pd = new PedidoDetalle();
					pd.setProducto(proDat.getProducto(rsGetAll.getInt(1)));
					pd.setCantidad(rsGetAll.getInt(2));
					pedidoDetalle.add(pd);
					pd = null;
				}

			}
		}catch(SQLException e){
			
			throw new ApplicationException("Error al recuperar estado en la base de datos", e);	
		}
		finally{
			
			try {
						
				if(psGetAll != null) psGetAll.close();
				if(miConexion != null) miConexion.close();				
				
			} catch(SQLException e){
				
				throw new ApplicationException("Error al recuperar estado en la base de datos", e);	
				
			}
			finally{
				
				Adapter.getInstancia().releaseConn();
				
			}
			
		}
		return pedidoDetalle;
	}
}