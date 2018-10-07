package modelos;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import objetos.Maquina;

public class ModeloTablaMaquina extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ModeloColumnasTabla columnas;
	List<Maquina> listaMaquinas;
	
	public ModeloTablaMaquina(ModeloColumnasTabla columnas, List<Maquina> lista) {
		super();
		this.columnas = columnas;
		listaMaquinas = lista;
	}


	public void delete(int index) {
		listaMaquinas.remove(index);
		this.fireTableDataChanged();
	}

	public void add(Maquina e) {
		listaMaquinas.add(e);
		this.fireTableDataChanged();
	}

	public List<Maquina> getlistaMaquinas() {
		return listaMaquinas;
	}

	public void setlistaMaquinas(List<Maquina> listaMaquinas) {
		this.listaMaquinas = listaMaquinas;
	}

	public void cambiarEstado(int indice) {
		System.out.println(indice);
		if (listaMaquinas.get(indice).isEstado())
			listaMaquinas.get(indice).setEstado(false);
		else
			listaMaquinas.get(indice).setEstado(true);
		this.fireTableDataChanged();

	}
	public void refrescarTabla() {
		this.fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return listaMaquinas.size();
	}

	public Boolean getValueAt2(int fila, int columna) {
		Maquina a = listaMaquinas.get(fila);
		if (a.getFieldAt(columna) != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Object getValueAt(int fila, int columna) {
		Maquina a = listaMaquinas.get(fila);
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
	
}


