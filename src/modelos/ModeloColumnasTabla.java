package modelos;



import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;


public class ModeloColumnasTabla extends DefaultTableColumnModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TrazadorTablaMaquina trazador;

	public ModeloColumnasTabla(TrazadorTablaMaquina trazador) {
		super();
		this.trazador = trazador;
		this.addColumn(crearColumna("Id", 0, 200));
		this.addColumn(crearColumna("Estado", 1, 200));
	
	}

	private TableColumn crearColumna(String texto, int indice, int ancho) {
		TableColumn columna = new TableColumn(indice, ancho);

		columna.setHeaderValue(texto);
		columna.setPreferredWidth(ancho);
		columna.setCellRenderer(trazador);

		return columna;
	}
}
