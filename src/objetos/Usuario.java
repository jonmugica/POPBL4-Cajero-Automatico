package objetos;

import java.util.List;

public class Usuario {
	boolean esAdmin;
	String usuario;
	String password;
	String nombre;
	String apellido1;
	String apellido2;
	String email;
	String fecha_nacimiento;
	String poblacion;
	String codigo_postal;
	String direccion;
	String dni;
	String iban;
	String genero;
	String telefono;

	List<Pedido> listaPedidos;

	public Usuario(String usuario, String password, String nombre, String apellido1, String apellido2, String email,
			String fecha_nacimiento, String poblacion, String codigo_postal, String direccion, String iban,
			String genero, String telefono, String dni) {

		this.usuario = usuario;
		this.password = password;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.fecha_nacimiento = fecha_nacimiento;
		this.poblacion = poblacion;
		this.codigo_postal = codigo_postal;
		this.direccion = direccion;
		this.iban = iban;
		this.genero = genero;
		this.telefono = telefono;
		this.esAdmin = false;
		this.dni = dni;
	}

	public Usuario(String usuario, boolean esAdmin) {
		this.usuario = usuario;
		this.esAdmin = esAdmin;
	}

	public void agregarPedido(Pedido p) {
		listaPedidos.add(p);
	}

	public List<Pedido> getListaPedidos() {

		return listaPedidos;

	}

	public void setListaPedidos(List<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getPassword() {
		return password;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public String getEmail() {
		return email;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getIban() {
		return iban;
	}

	public String getGenero() {
		return genero;
	}

	public String getTelefono() {
		return telefono;
	}

	public boolean getEsAdmin() {
		return esAdmin;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public String getCodigo_postal() {
		return codigo_postal;
	}

	public String getDni() {
		return dni;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getUsuario();
	}
	

}
