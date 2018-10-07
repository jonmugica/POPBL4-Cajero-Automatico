package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objetos.Pedido;
import objetos.Producto;
import objetos.Usuario;

public class ConexionJDBC {
	String db = "popbl4tienda";
	String usuario = "root";
	String contrasena = "";
	String url = "jdbc:mysql://localhost/" + db
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	Connection conexion;
	Statement statement = null;
	ResultSet result;

	public ConexionJDBC() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, usuario, contrasena);
			if (conexion != null) {
				statement = conexion.createStatement();
				System.out.println(" Conexion a base de datos " + db + " correcta.");
			} else
				System.out.println("Conexion fallida.");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PreparedStatement preparedStatement(String sql) {
		try {
			return conexion.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ResultSet select(String query) {
		try {
			statement = conexion.createStatement();
			result = statement.executeQuery(query);
			return result;
		} catch (SQLException e) {
			System.out.println("Error al ejecutar el comando select!");
			System.out.println(e.toString());
			return null;
		}
	}

	public int update(String query) {
		try {
			statement = conexion.createStatement();
			return statement.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Error al ejecutar el comando query");
			System.out.println(e.toString());
			return -1;
		}
	}

	public int delete(String query) {
		try {
			statement = conexion.createStatement();
			return statement.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Error al ejecutar el comando delete");
			System.out.println(e.toString());
			return -1;
		}
	}

	public void close() {
		try {
			statement.close();
			result.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar!");
			System.out.println(e.toString());
		}
	}
	public void registrarPedido(Pedido p) {
		PreparedStatement pst = null;

		try {
			pst = conexion.prepareStatement("INSERT INTO pedidos VALUES (?, ?, ?)");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		try {
	
		pst.setInt(1, p.getId());
		pst.setInt(2, p.getUsuarioID());
		pst.setString(3, p.getStringFecha());
	
		pst .executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	public void registrarLinea(int pedidoId, int productoID, double precioProducto, int cantidadProductos) {
		PreparedStatement pst = null;

		try {
			pst = conexion.prepareStatement("INSERT INTO lineas VALUES (?, ?, ?, ?)");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		try {
	
		pst.setInt(1, pedidoId);
		pst.setInt(2,productoID);
		pst.setDouble(3, precioProducto);
		pst.setInt(4, cantidadProductos);
	
		pst .executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	public int getIdUltimoPedido() throws ClassNotFoundException, SQLException {

		int idUltimoPedido = 0;
		statement = conexion.createStatement();
		String sql = "select max(id) as id from pedidos";

		result = statement.executeQuery(sql);
		
		while (result.next()) {
			idUltimoPedido = result.getInt("id");
		}
		return idUltimoPedido;
	}
	
	public int getIdUsuario(String usuario) throws ClassNotFoundException, SQLException {

		int idUsuario = 0;
		statement = conexion.createStatement();
		String sql = "select id from usuarios where usuario='" + usuario + "'";

		result = statement.executeQuery(sql);
		
		while (result.next()) {
			idUsuario = result.getInt("id");
		}
		return idUsuario;
	}

	public ArrayList<Usuario> getListaUsuarios() throws ClassNotFoundException, SQLException {

		statement = conexion.createStatement();
		String sql = "Select * From usuarios";

		result = statement.executeQuery(sql);
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();
		while (result.next()) {
			Usuario usuario = new Usuario(result.getString("usuario"), result.getString("password"),
					result.getString("nombre"), result.getString("apellido1"), result.getString("apellido2"),
					result.getString("email"), result.getString("fecha_nacimiento"), result.getString("poblacion"),
					result.getString("cod_postal"), result.getString("direccion"), result.getString("iban"),
					result.getString("genero"), result.getString("telefono"), result.getString("dni"));
			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}

	public ArrayList<Pedido> getListaPedidos(String usuario) throws ClassNotFoundException, SQLException {

		statement = conexion.createStatement();
		String sql = "select u.id as usuarioId, p.fecha, p.id from ( usuarios u JOIN pedidos p ON u.id = p.usuarioID ) JOIN lineas l ON l.pedidoID = p.id where usuario = '"
				+ usuario + "' group by p.id";

		result = statement.executeQuery(sql);
		ArrayList<Pedido> listaPedidos = new ArrayList<>();
		while (result.next()) {
			Pedido pedido = new Pedido(result.getInt("id"), result.getInt("usuarioId"), result.getDate("fecha").toString());
			listaPedidos.add(pedido);
		}
		return listaPedidos;
	}

	public ArrayList<Producto> getListaProductos(int pedidoID) throws ClassNotFoundException, SQLException {

		statement = conexion.createStatement();
		String sql = "select p.id, p.nombre, p.descripcion, p.precio, l.cantidad "
				+ "from ( productos p JOIN lineas l ON p.id = l.productoID ) "
				+ "JOIN pedidos pe ON pe.id = l.pedidoID "
				+ "where pe.id = '" + pedidoID + "'";

		result = statement.executeQuery(sql);
		ArrayList<Producto> listaProductos = new ArrayList<>();
		while (result.next()) {
			Producto producto = new Producto(result.getDouble("precio"), result.getString("nombre"),
					result.getString("descripcion"), result.getInt("id"), result.getInt("cantidad"));
			listaProductos.add(producto);
		}
		return listaProductos;
	}


	public void guardarPedidosBD(Usuario u) {

	}
}
