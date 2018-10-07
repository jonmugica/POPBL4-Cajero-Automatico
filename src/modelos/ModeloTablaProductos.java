package modelos;


import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import objetos.Pedido;
import objetos.Producto;

public class ModeloTablaProductos extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ModeloColumnasTablaProductos columnas;
	List<Producto> listaProductos;
	double sumaTotal = 0;
	
	
	public ModeloTablaProductos(ModeloColumnasTablaProductos columnas) {
		super();
		this.columnas = columnas;
		listaProductos = new ArrayList<>();

	}
	
	public void añadir(Producto p){
		listaProductos.add(p);
		this.fireTableDataChanged();	
	}

	public void eliminar(int indice){
		listaProductos.remove(indice);
		this.fireTableDataChanged();
	}
	

	public void calcularTotal() {
		int index=0;
		sumaTotal = 0;
		for(index =0; index <listaProductos.size(); index++) {
			sumaTotal = sumaTotal + listaProductos.get(index).getTotal();
		}
	}
	
	public double getSumaTotal() {
		return sumaTotal;
	}
	
	@Override
	public int getRowCount() {
		return listaProductos.size();
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		Producto a = listaProductos.get(fila);
		return a.getFieldAt(columna);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public int getColumnCount() {
		return columnas.getColumnCount();
	}

	public void setListaProductos(Pedido p) {
		this.listaProductos = p.getListaProductos();
		this.fireTableDataChanged();
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}	
	
	
	
}

	