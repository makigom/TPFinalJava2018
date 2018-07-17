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

import com.mysql.jdbc.PreparedStatement;

public class PedidoDatos {

	public void crearPedido(Pedido pedido, ArrayList<PedidoDetalle> productos) {
		Connection miConexion;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psCrear = null;
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
				psCrear.close();
				miConexion.close();
			}
		} catch(SQLException Ex) {
			Ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cambiarEstado(int id_pedido, String estado) {
		Connection miConexion;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psEditar = null;
				psEditar = (PreparedStatement)miConexion.prepareStatement("UPDATE pedidos" + 
				" SET estado=? WHERE id_pedido=? ;");
				psEditar.setString(1, estado);
				psEditar.setInt(2, id_pedido);
				psEditar.execute();
				psEditar.close();
				miConexion.close();
			}
		} catch(SQLException Ex) {
			Ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

	public ArrayList<Pedido> getAllPedidos()
	{
		Connection miConexion;
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psGetAll = null;
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
				psGetAll.close();
				miConexion.close();
			}
		} catch(SQLException Ex) {
			Ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pedidos;
	}
	
	public ArrayList<Pedido> getAllPedidos(Persona per) {
		Connection miConexion;
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psGetAll = null;
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
				psGetAll.close();
				miConexion.close();
			}
		} catch(SQLException Ex) {
			Ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pedidos;
	}
	
	public Pedido getPedido(int id) {
		Connection miConexion;
		Pedido pedido = null;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psGetAll = null;
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
		} catch(SQLException Ex) {
			Ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pedido;
	}
	
	public ArrayList<PedidoDetalle> getPedidoDetalle(int id) {
		Connection miConexion;
		ArrayList<PedidoDetalle> pedidoDetalle = new ArrayList<PedidoDetalle>();
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psGetAll = null;
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
				psGetAll.close();
				miConexion.close();
			}
		} catch(SQLException Ex) {
			Ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pedidoDetalle;
	}
}