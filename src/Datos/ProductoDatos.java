package Datos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Entidades.Producto;

import com.mysql.jdbc.PreparedStatement;

public class ProductoDatos {

	public void crearProducto(Producto producto) throws IOException {
		Connection miConexion;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psCrear = null;
				psCrear = (PreparedStatement)miConexion.prepareStatement("INSERT INTO productos (nombre_producto,precio," + 
				"img,stock,id_tipo,habilitado) VALUES(?,?,?,?,?,?)");				
				
								
				psCrear.setString(1,producto.getNombre_producto());
				psCrear.setFloat(2, producto.getPrecio());			
				psCrear.setString(3, producto.getImagen());				
				psCrear.setInt(4, producto.getStock());
				psCrear.setInt(5, producto.getTipo().getId_tipo());
				psCrear.setBoolean(6, producto.isHabilitado());

				psCrear.execute();
				psCrear.close();
				miConexion.close();				
			}
		} catch(SQLException Ex) {
			Ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deshabilitarProducto(int id_producto) {
		Connection miConexion;

		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null)
			{
				PreparedStatement psEliminar = null;
				psEliminar = (PreparedStatement)miConexion.prepareStatement("UPDATE productos" + 
				" SET habilitado=? WHERE id_producto=?");

				psEliminar.setBoolean(1, false);
				psEliminar.setInt(2, id_producto);

				psEliminar.execute();
				psEliminar.close();
				miConexion.close();
			}
		}

		catch(SQLException Ex)
		{
			JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos ");
			Ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editarProducto(Producto producto) throws FileNotFoundException {
		Connection miConexion;

		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psEditar = null;
				psEditar = (PreparedStatement)miConexion.prepareStatement("UPDATE productos" + 
						" SET nombre_producto=?,precio=?,img=?,stock=?,id_tipo=? WHERE" + 
				" id_producto=?");

				psEditar.setString(1, producto.getNombre_producto());
				psEditar.setFloat(2, producto.getPrecio());				
				psEditar.setString(3, producto.getImagen());
				psEditar.setInt(4, producto.getStock());
				psEditar.setInt(5, producto.getTipo().getId_tipo());			
				psEditar.setInt(6, producto.getId_producto());

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

	public Producto getProductoHabilitado(int id_producto) {
		Connection miConexion;
		Producto producto = null;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psGet = null;
				psGet = (PreparedStatement)miConexion.prepareStatement("SELECT id_producto, nombre_producto," + 
				"precio,img,stock,id_tipo FROM productos WHERE id_producto=? AND habilitado=?"); 

				psGet.setInt(1, id_producto);
				psGet.setBoolean(2, true);

				ResultSet rsGet = psGet.executeQuery();				
				while(rsGet.next()) {
					producto = new Producto();
					producto.setId_producto(rsGet.getInt(1));
					producto.setNombre_producto(rsGet.getString(2));
					producto.setPrecio(rsGet.getFloat(3));
					producto.setImagen(rsGet.getString(4));
					producto.setStock(rsGet.getInt(5));
					producto.setHabilitado(true);

					TipoPostreDatos tbd = new TipoPostreDatos();
					producto.setTipo(tbd.getTipoPostre(rsGet.getInt(6)));
				}

				psGet.close();

			}
			miConexion.close();
		} catch(SQLException Ex) {
			Ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return producto;
	}	
	
	public Producto getProductoHabilitado(String nombre_producto) {
		Connection miConexion;
		Producto producto = null;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psGet = null;
				psGet = (PreparedStatement)miConexion.prepareStatement("SELECT id_producto, nombre_producto," + 
				"precio,img,stock,id_tipo FROM productos WHERE nombre_producto=? AND habilitado=?"); 

				psGet.setString(1, nombre_producto);
				psGet.setBoolean(2, true);
				ResultSet rsGet = psGet.executeQuery();				
				while(rsGet.next()) {
					producto = new Producto();
					producto.setId_producto(rsGet.getInt(1));
					producto.setNombre_producto(rsGet.getString(2));
					producto.setPrecio(rsGet.getFloat(3));
					producto.setImagen(rsGet.getString(4));
					producto.setStock(rsGet.getInt(5));
					producto.setHabilitado(true);

					TipoPostreDatos tbd = new TipoPostreDatos();
					producto.setTipo(tbd.getTipoPostre(rsGet.getInt(6)));
				}
				psGet.close();
			}
			miConexion.close();
		} catch(SQLException Ex) {
			Ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return producto;
	}	

	public Producto getProducto(int id_producto) {
		Connection miConexion;
		Producto producto = null;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psGet = null;
				psGet = (PreparedStatement)miConexion.prepareStatement("SELECT id_producto, nombre_producto," + 
				"precio,img,stock,id_tipo,habilitado FROM productos WHERE id_producto=? ;"); 

				psGet.setInt(1, id_producto);

				ResultSet rsGet = psGet.executeQuery();				
				while(rsGet.next()) {
					producto = new Producto();
					producto.setId_producto(rsGet.getInt(1));
					producto.setNombre_producto(rsGet.getString(2));
					producto.setPrecio(rsGet.getFloat(3));
					producto.setImagen(rsGet.getString(4));
					producto.setStock(rsGet.getInt(5));
					TipoPostreDatos tbd = new TipoPostreDatos();
					producto.setTipo(tbd.getTipoPostre(rsGet.getInt(6)));
					producto.setHabilitado(rsGet.getBoolean(7));
				}
				psGet.close();
			}
			miConexion.close();
		} catch(SQLException Ex) {
			Ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return producto;
	}
	
	public Producto getProducto(String nombre_producto) {
		Connection miConexion;
		Producto producto = null;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psGet = null;
				psGet = (PreparedStatement)miConexion.prepareStatement("SELECT id_producto, nombre_producto," + 
				"precio,img,stock,id_tipo,habilitado FROM productos WHERE nombre_producto=? ;"); 

				psGet.setString(1, nombre_producto);
				
				ResultSet rsGet = psGet.executeQuery();				
				while(rsGet.next()) {
					producto = new Producto();
					producto.setId_producto(rsGet.getInt(1));
					producto.setNombre_producto(rsGet.getString(2));
					producto.setPrecio(rsGet.getFloat(3));
					producto.setImagen(rsGet.getString(4));
					producto.setStock(rsGet.getInt(5));
					producto.setHabilitado(rsGet.getBoolean(6));
					TipoPostreDatos tbd = new TipoPostreDatos();
					producto.setTipo(tbd.getTipoPostre(rsGet.getInt(6)));
				}
				psGet.close();
			}
			miConexion.close();
		} catch(SQLException Ex) {
			Ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return producto;
	}
	
	public ArrayList<Producto> getAllProductosHabilitados() {
		Connection miConexion;
		ArrayList<Producto> productos = new ArrayList<Producto>();
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psGetAll = null;
				psGetAll = (PreparedStatement)miConexion.prepareStatement("SELECT id_producto," + 
				"nombre_producto,precio,img,stock,id_tipo,habilitado FROM productos WHERE habilitado=?");
				
				psGetAll.setBoolean(1, true);
				ResultSet rsGetAll = psGetAll.executeQuery();
				while(rsGetAll.next()) {
					Producto producto = new Producto();
					producto.setId_producto(rsGetAll.getInt(1));
					producto.setNombre_producto(rsGetAll.getString(2));
					producto.setPrecio(rsGetAll.getFloat(3));
					producto.setImagen(rsGetAll.getString(4));
					producto.setStock(rsGetAll.getInt(5));
					TipoPostreDatos tbd = new TipoPostreDatos();
					producto.setTipo(tbd.getTipoPostre(rsGetAll.getInt(6)));
					producto.setHabilitado(rsGetAll.getBoolean(7));

					productos.add(producto);

					producto = null;
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
		return productos;
	}
	
	public ArrayList<Producto> getAllProductos(int idTipo) {
		Connection miConexion;
		ArrayList<Producto> productos = new ArrayList<Producto>();

		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null)
			{
				PreparedStatement psGetAll = null;
				psGetAll = (PreparedStatement)miConexion.prepareStatement("SELECT id_producto," + 
				"nombre_producto,precio,img,stock,id_tipo,habilitado FROM productos WHERE id_tipo=? ;");
				psGetAll.setInt(1, idTipo);
				
				ResultSet rsGetAll = psGetAll.executeQuery();
				while(rsGetAll.next())
				{
					Producto producto = new Producto();
					producto.setId_producto(rsGetAll.getInt(1));
					producto.setNombre_producto(rsGetAll.getString(2));
					producto.setPrecio(rsGetAll.getFloat(3));
					producto.setImagen(rsGetAll.getString(4));
					producto.setStock(rsGetAll.getInt(5));
					TipoPostreDatos tbd = new TipoPostreDatos();
					producto.setTipo(tbd.getTipoPostre(rsGetAll.getInt(6)));
					producto.setHabilitado(rsGetAll.getBoolean(7));

					productos.add(producto);

					producto = null;
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
		return productos;	
	}
	
	public void actualizarStock(int idProd, int cantidad ) {
		Connection miConexion;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psEditar = null;
				psEditar = (PreparedStatement)miConexion.prepareStatement("UPDATE productos" + 
						" SET stock=? WHERE id_producto=?");
				
				psEditar.setInt(1, this.getProducto(idProd).getStock() - cantidad);
				psEditar.setInt(2, idProd);				
				

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
	
	public boolean hayStock(int idProd, int cantidad ) {	
		Connection miConexion;
		
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psSelect = null;
				psSelect = (PreparedStatement)miConexion.prepareStatement("SELECT stock FROM productos " + 
						"WHERE id_producto=?");				
			
				psSelect.setInt(1, idProd);	
				
				ResultSet rsSelect = psSelect.executeQuery();
				while(rsSelect.next()) {
					if(rsSelect.getInt(1) >= cantidad)
						return true;
					else
						return false;
				}
				psSelect.close();
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
		return false;
	}	
}