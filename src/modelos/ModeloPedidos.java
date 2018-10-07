package modelos;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.AbstractListModel;

import objetos.Pedido;
import objetos.Usuario;

public class ModeloPedidos extends AbstractListModel<Pedido>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Pedido> listaPedidos;
	ArrayList<Pedido> listaPedidosFiltrada;
	
	public ModeloPedidos() {
		listaPedidos = new ArrayList<>();
		listaPedidosFiltrada = new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	public void setListaPedidos(Usuario u) {
		listaPedidos = (ArrayList<Pedido>) u.getListaPedidos();
		listaPedidosFiltrada = (ArrayList<Pedido>) listaPedidos.clone();
		this.fireContentsChanged(listaPedidosFiltrada, 0, listaPedidosFiltrada.size());
	}

	@Override
	public int getSize() {
		 return listaPedidosFiltrada.size();
	}

	@Override
	public Pedido getElementAt(int index) {
		return listaPedidosFiltrada.get(index);
	}

	public void limpiar() {
		listaPedidosFiltrada.clear();
		this.fireContentsChanged(listaPedidosFiltrada, 0, listaPedidosFiltrada.size());
	}
	
	//LIMPIAR FILTROS
		public void mostrarTodos() {
			listaPedidosFiltrada.clear();
			listaPedidosFiltrada = (ArrayList<Pedido>) listaPedidos.stream()
					.collect(Collectors.toList());
			this.fireContentsChanged(listaPedidosFiltrada, 0, listaPedidosFiltrada.size());

		}

	public void filtrarPorAño(String filtro) {
		listaPedidosFiltrada.clear();
		listaPedidosFiltrada = (ArrayList<Pedido>) listaPedidos.stream().filter(a -> a.getFecha()[2].equals(filtro))
				.collect(Collectors.toList());
		System.out.println(filtro);
		System.out.println(listaPedidosFiltrada.size());
		this.fireContentsChanged(listaPedidosFiltrada, 0, listaPedidosFiltrada.size());
	}
	public void filtrarPorMes(String filtro) {
		listaPedidosFiltrada.clear();
		listaPedidosFiltrada = (ArrayList<Pedido>) listaPedidos.stream().filter(a -> a.getFecha()[1].equals(filtro))
				.collect(Collectors.toList());
		this.fireContentsChanged(listaPedidosFiltrada, 0, listaPedidosFiltrada.size());
		
	}
	public void filtrarPorMesYAño(String filtro1, String filtro2) {
		listaPedidosFiltrada.clear();
		
		//MES
		ArrayList<Pedido> aux = new ArrayList<>();
		aux = (ArrayList<Pedido>) listaPedidos.stream().filter(a -> a.getFecha()[1].equals(filtro1))
				.collect(Collectors.toList());
		//AÃ‘O
		listaPedidosFiltrada = (ArrayList<Pedido>) aux.stream().filter(a -> a.getFecha()[2].equals(filtro2))
				.collect(Collectors.toList());
		this.fireContentsChanged(listaPedidosFiltrada, 0, listaPedidosFiltrada.size());
		this.fireContentsChanged(listaPedidosFiltrada, 0, listaPedidosFiltrada.size());
	}
}
