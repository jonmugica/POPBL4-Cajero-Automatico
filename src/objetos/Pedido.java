package objetos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pedido {
	
	int id;
	String[] fecha;
	String stringFecha;
	List<Producto> listaProductos;
	int usuarioID;
	
	public Pedido(int id, int usuarioID, String fecha) {
		this.id = id;
		this.usuarioID = usuarioID;
		this.fecha = fecha.split("-");
		this.stringFecha = fecha;
		listaProductos = new ArrayList<>();
		
	}
	public void agregarProducto(Producto p) {
		
		listaProductos.add(p);
	}
	
	 public String[] getFecha() {
		return fecha;
	}
	public List<Producto> getListaProductos(){		 
		 
		return listaProductos;
		 
	 }
	
	
	public String getStringFecha() {
		return stringFecha;
	}
	public int getUsuarioID() {
		return usuarioID;
	}
	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	public int getId() {
		return id;
	}
	@Override

public String toString() { 
    return this.stringFecha;
} 
}