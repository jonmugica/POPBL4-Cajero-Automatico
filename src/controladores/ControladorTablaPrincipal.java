package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import modelos.ModeloTablaProductos;
import paneles.PanelPrincipal;
import vistas.Principal;

public class ControladorTablaPrincipal  implements ActionListener, TableModelListener {
	
	ModeloTablaProductos tabla;
	JTable vTabla;
	Principal principal;
	PanelPrincipal panelPrincipal;
	
	public ControladorTablaPrincipal(Principal principal, ModeloTablaProductos tabla, JTable vTabla, PanelPrincipal panelPrincipal) {
		this.principal = principal;
		this.tabla = tabla;
		this.vTabla = vTabla;
		this.panelPrincipal = panelPrincipal;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Eliminar")) {
			tabla.eliminar(vTabla.getSelectedRow());
		}
	}


	@Override
	public void tableChanged(TableModelEvent e) {
		tabla.calcularTotal();
		
		panelPrincipal.getImporteTotal().setText(String.format("%.2f €", tabla.getSumaTotal()));
		panelPrincipal.getPanelFondo().repaint();
	}

}
