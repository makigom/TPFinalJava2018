package Datos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import Entidades.Persona;
import Datos.Adapter;

public class PersonaDatos {
	
	public boolean login(String usr, String password) {
		Connection miConexion;
		boolean loginValido = false;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion != null) {
				PreparedStatement psLogin = null;  	
				psLogin = (PreparedStatement) miConexion.prepareStatement("select * from " + 
				"personas where nombre_usuario=? and clave=?");		
				psLogin.setString(1,usr);
				psLogin.setString(2, password);
				ResultSet rsLogin = psLogin.executeQuery();
				if(rsLogin.next()) {
					loginValido = true;
				}
			}
			miConexion.close();
		} catch(SQLException Ex) {
			Ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return loginValido;
	}

	public void crearPersona(Persona persona) {
		Connection miConexion;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psCrear = null;
				psCrear = (PreparedStatement)miConexion.prepareStatement("INSERT INTO personas (nombre_ap,nombre_usuario," + 
				"clave,mail,dni,telefono,esAdmin,habilitado) VALUES(?,?,?,?,?,?,?,?)");
				psCrear.setString(1,persona.getNombre_ap());
				psCrear.setString(2, persona.getNombre_usuario());
				psCrear.setString(3, persona.getClave());
				psCrear.setString(4, persona.getMail());
				psCrear.setString(5, persona.getDni());
				psCrear.setString(6, persona.getTelefono());
				psCrear.setBoolean(7, persona.getEsAdmin());
				psCrear.setBoolean(8, persona.isHabilitado());
				psCrear.execute();
				psCrear.close();
				miConexion.close();
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void eliminarPersona(int id_cliente) {
		Connection miConexion;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psEliminar = null;
				psEliminar = (PreparedStatement)miConexion.prepareStatement("UPDATE personas" + 
				" SET habilitado=? WHERE id_cliente=?");

				psEliminar.setBoolean(1, false);
				psEliminar.setInt(2, id_cliente);

				psEliminar.execute();
				psEliminar.close();
				miConexion.close();
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editarPerfil(Persona persona) {
		Connection miConexion;
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psEditar = null;
				psEditar = (PreparedStatement)miConexion.prepareStatement("UPDATE personas" + 
						" SET nombre_ap=?,clave=?,mail=?,telefono=? WHERE" + 
				" id_cliente=?");
				psEditar.setString(1, persona.getNombre_ap());              
				psEditar.setString(2, persona.getClave());
				psEditar.setString(3,persona.getMail());                
				psEditar.setString(4, persona.getTelefono());
				psEditar.setInt(5, persona.getId_cliente());
				psEditar.execute();
				psEditar.close();
				miConexion.close();
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editarPersona(Persona persona)
	{
		Connection miConexion;

		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null)
			{
				PreparedStatement psEditar = null;
				psEditar = (PreparedStatement)miConexion.prepareStatement("UPDATE personas" + 
						" SET nombre_ap=?,clave=?,mail=?,telefono=?,esAdmin=?,nombre_usuario=?,dni=? WHERE" + 
				" id_cliente=?");

				psEditar.setString(1, persona.getNombre_ap());              
				psEditar.setString(2, persona.getClave());
				psEditar.setString(3,persona.getMail());                
				psEditar.setString(4, persona.getTelefono());
				psEditar.setInt(8, persona.getId_cliente());
				psEditar.setBoolean(5, persona.getEsAdmin());
				psEditar.setString(6, persona.getNombre_usuario());
				psEditar.setString(7, persona.getDni());
				
				psEditar.execute();
				psEditar.close();

				miConexion.close();
			}
		}

		catch(SQLException ex)
		{
			ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Persona getPersonaHabilitada(int id_cliente) {
		Connection miConexion;
		Persona persona = new Persona();
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psGet = null;
				psGet = (PreparedStatement)miConexion.prepareStatement("SELECT nombre_ap," + 
				"nombre_usuario,clave,mail,dni,telefono,esAdmin,habilitado FROM personas WHERE id_cliente=? AND habilitado=?"); 
				psGet.setInt(1, id_cliente);
				psGet.setBoolean(2, true);
				ResultSet rsGet = psGet.executeQuery();
				while(rsGet.next()) {
					persona.setNombre_ap(rsGet.getString(1));
					persona.setNombre_usuario(rsGet.getString(2));
					persona.setClave(rsGet.getString(3));
					persona.setMail(rsGet.getString(4));
					persona.setDni(rsGet.getString(5));
					persona.setTelefono(rsGet.getString(6));
					persona.setEsAdmin(rsGet.getBoolean(7));
					persona.setId_cliente(id_cliente);
					persona.setHabilitado(rsGet.getBoolean(8));
				}
				psGet.close();
			}
			miConexion.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return persona;
	}

	public Persona getPersonaHabilitada(String nomUs) {
		Connection miConexion;
		Persona per = null;
		try {
			miConexion = Adapter.GetConnection();
			if(miConexion != null) {
				PreparedStatement ps = null;  	
				ps = (PreparedStatement) miConexion.prepareStatement("select * from " + 
				"personas where nombre_usuario=? and habilitado=?;");
				ps.setString(1,nomUs);
				ps.setBoolean(2, true);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					per = new Persona();
					per.setId_cliente(rs.getInt(1));
					per.setNombre_ap(rs.getString(2));
					per.setNombre_usuario(rs.getString(3));
					per.setClave(rs.getString(4));
					per.setMail(rs.getString(5));
					per.setDni(rs.getString(6));
					per.setTelefono(rs.getString(7));
					per.setEsAdmin(rs.getBoolean(8));
					per.setHabilitado(rs.getBoolean(9));
				}
			}
			miConexion.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return per;
	}

	public Persona getPersona(int id_cliente) {
		Connection miConexion;
		Persona persona = new Persona();
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psGet = null;
				psGet = (PreparedStatement)miConexion.prepareStatement("SELECT nombre_ap," + 
				"nombre_usuario,clave,mail,dni,telefono,esAdmin,habilitado FROM personas WHERE id_cliente=? ;"); 
				psGet.setInt(1, id_cliente);
				ResultSet rsGet = psGet.executeQuery();
				while(rsGet.next()) {
					persona.setNombre_ap(rsGet.getString(1));
					persona.setNombre_usuario(rsGet.getString(2));
					persona.setClave(rsGet.getString(3));
					persona.setMail(rsGet.getString(4));
					persona.setDni(rsGet.getString(5));
					persona.setTelefono(rsGet.getString(6));
					persona.setEsAdmin(rsGet.getBoolean(7));
					persona.setId_cliente(id_cliente);
					persona.setHabilitado(rsGet.getBoolean(8));
				}
				psGet.close();
			}
			miConexion.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return persona;
	}
	
	public Persona getPersona(String nomUs) {
		Connection miConexion;
		Persona per = null;
		try {
			miConexion = Adapter.GetConnection();
			if(miConexion != null) {
				PreparedStatement ps = null;  	
				ps = (PreparedStatement) miConexion.prepareStatement("select * from " + 
				"personas where nombre_usuario=? ;");
				ps.setString(1,nomUs);
				
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					per = new Persona();
					per.setId_cliente(rs.getInt(1));
					per.setNombre_ap(rs.getString(2));
					per.setNombre_usuario(rs.getString(3));
					per.setClave(rs.getString(4));
					per.setMail(rs.getString(5));
					per.setDni(rs.getString(6));
					per.setTelefono(rs.getString(7));
					per.setEsAdmin(rs.getBoolean(8));
					per.setHabilitado(rs.getBoolean(9));
				}
			}
			miConexion.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return per;	
	}
	
	public ArrayList<Persona> getAllPersonas() {
		Connection miConexion;
		ArrayList<Persona> personas = new ArrayList<Persona>();
		try
		{
			miConexion = Adapter.GetConnection();
			if(miConexion!=null) {
				PreparedStatement psGetAll = null;
				psGetAll = (PreparedStatement)miConexion.prepareStatement("SELECT id_cliente," + 
				"nombre_ap,nombre_usuario,clave,mail,dni,telefono,esAdmin,habilitado FROM personas WHERE habilitado=?;");
				psGetAll.setBoolean(1, true);
				ResultSet rsGetAll = psGetAll.executeQuery();
				while(rsGetAll.next()) {
					Persona persona = new Persona();
					persona.setId_cliente(rsGetAll.getInt(1));
					persona.setNombre_ap(rsGetAll.getString(2));
					persona.setNombre_usuario(rsGetAll.getString(3));
					persona.setClave(rsGetAll.getString(4));
					persona.setMail(rsGetAll.getString(5));
					persona.setDni(rsGetAll.getString(6));
					persona.setTelefono(rsGetAll.getString(7));
					persona.setEsAdmin(rsGetAll.getBoolean(8));
					persona.setHabilitado(rsGetAll.getBoolean(9));
					personas.add(persona);
					persona = null;
				}
				psGetAll.close();
				miConexion.close();
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return personas;
	}
}