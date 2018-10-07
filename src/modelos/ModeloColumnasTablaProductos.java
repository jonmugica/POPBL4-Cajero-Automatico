package modelos;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import renderer.TrazadorTablaProductos;

public class ModeloColumnasTablaProductos extends DefaultTableColumnModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TrazadorTablaProductos trazador;

	public ModeloColumnasTablaProductos(TrazadorTablaProductos trazador) {
		super();
		this.trazador = trazador;
		this.addColumn(crearColumna("Nombre", 0, 200));
		this.addColumn(crearColumna("Precio", 1, 100));
		this.addColumn(crearColumna("Cantidad", 2, 100));
		this.addColumn(crearColumna("Total", 3, 100));
	}

	private TableColumn crearColumna(String texto, int indice, int ancho) {
		TableColumn columna = new TableColumn(indice, ancho);

		columna.setHeaderValue(texto);
		columna.setPreferredWidth(ancho);
		columna.setCellRenderer(trazador);



		return columna;
	}
}
