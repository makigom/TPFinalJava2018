package Datos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Entidades.Producto;
import Entidades.TipoPostre;

import com.mysql.jdbc.PreparedStatement;

public class TipoPostreDatos {

	public void crearTipoPostre(TipoPostre tipo)
	{
		Connection miConexion;

		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null)
			{
				PreparedStatement psCrear = null;
				psCrear = (PreparedStatement)miConexion.prepareStatement("INSERT INTO tipo_postre" + 
				"(descripcion,habilitado) VALUES(?,?)");
				
				psCrear.setString(1,tipo.getDescripcion_tipo());
				psCrear.setBoolean(2, tipo.isHabilitado());

				psCrear.execute();
				psCrear.close();
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

	public void eliminarTipoPostre(int id_tipo)
	{
		Connection miConexion;

		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null)
			{
				PreparedStatement psEliminar = null;
				psEliminar = (PreparedStatement)miConexion.prepareStatement("UPDATE tipo_postre" + 
				" SET habilitado=? WHERE id_tipo=?");

				psEliminar.setBoolean(1, false);
				psEliminar.setInt(2, id_tipo);
				

				psEliminar.execute();
				psEliminar.close();
				miConexion.close();
			}
		}

		catch(SQLException Ex)
		{
			JOptionPane.showMessageDialog(null, "Error en la conexi�n con la base de datos ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editarTipoPostre(TipoPostre tipoPostre)
	{
		Connection miConexion;

		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null)
			{
				PreparedStatement psEditar = null;
				psEditar = (PreparedStatement)miConexion.prepareStatement("UPDATE tipo_postre" + 
				" SET descripcion=? WHERE id_tipo=?");

				psEditar.setString(1, tipoPostre.getDescripcion_tipo());	
				psEditar.setInt(2, tipoPostre.getId_tipo());

				psEditar.execute();
				psEditar.close();

				miConexion.close();
			}
		}

		catch(SQLException Ex)
		{
			JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public TipoPostre getTipoPostre(int id_tipo)
	{
		Connection miConexion;

		TipoPostre tipo = null;

		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null)
			{
				PreparedStatement psGet = null;
				psGet = (PreparedStatement)miConexion.prepareStatement("SELECT descripcion,habilitado " +
				"FROM tipo_postre WHERE id_tipo=? AND habilitado=?"); 


				psGet.setInt(1, id_tipo);
				psGet.setBoolean(2, true);

				ResultSet rsGet = psGet.executeQuery();			


				while(rsGet.next())
				{
					tipo = new TipoPostre();
					tipo.setDescripcion_tipo(rsGet.getString(1));	
					tipo.setId_tipo(id_tipo);
					tipo.setHabilitado(rsGet.getBoolean(2));

				}

				psGet.close();

			}


			miConexion.close();



		}

		catch(SQLException Ex)
		{
			JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tipo;


	}
	
	public TipoPostre getTipoPostre(String desc_tipo)
	{
		Connection miConexion;

		TipoPostre tipo = null;

		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null)
			{
				PreparedStatement psGet = null;
				psGet = (PreparedStatement)miConexion.prepareStatement("SELECT id_tipo,descripcion,habilitado " +
 				"FROM tipo_postre WHERE descripcion=? AND habilitado=?"); 


				psGet.setString(1, desc_tipo);
				psGet.setBoolean(2, true);

				ResultSet rsGet = psGet.executeQuery();			


				while(rsGet.next())
				{
					tipo = new TipoPostre();
					tipo.setDescripcion_tipo(rsGet.getString(2));	
					tipo.setId_tipo(rsGet.getInt(1));
					tipo.setHabilitado(rsGet.getBoolean(3));

				}

				psGet.close();

			}


			miConexion.close();



		}

		catch(SQLException Ex)
		{
			JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tipo;


	}

	public ArrayList<TipoPostre> getAllTipoPostre() {
		Connection miConexion;
		ArrayList<TipoPostre> tipos = new ArrayList<TipoPostre>();

		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null)
			{
				PreparedStatement psGetAll = null;
				psGetAll = (PreparedStatement)miConexion.prepareStatement("SELECT id_tipo," + 
				"descripcion,habilitado FROM tipo_postre WHERE habilitado=?;");
				
				psGetAll.setBoolean(1, true);

				ResultSet rsGetAll = psGetAll.executeQuery();

				while(rsGetAll.next())
				{
					TipoPostre tipo = new TipoPostre();
					tipo.setId_tipo(rsGetAll.getInt(1));
					tipo.setDescripcion_tipo(rsGetAll.getString(2));
					tipo.setHabilitado(rsGetAll.getBoolean(3));
					tipos.add(tipo);

					tipo = null;
				}
				psGetAll.close();
				miConexion.close();
			}
		}
		catch(SQLException Ex)
		{
			JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return tipos;
	}
}
