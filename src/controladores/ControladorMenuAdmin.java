package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import paneles.PanelEstados;
import paneles.PanelPedidosAdmin;
import vistas.Principal;


public class ControladorMenuAdmin implements ActionListener {

	Principal principal;

	public ControladorMenuAdmin(Principal principal) {
		this.principal = principal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "Ver últimos pedidos":
			PanelPedidosAdmin panelPedidos = null;
			try {
				try {
					panelPedidos = new PanelPedidosAdmin(principal.getConexionBD(), principal);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			principal.cambiarPanel(panelPedidos.getPanel());
	
			break;
		case "Estado de los cajeros":
			PanelEstados panelEstados = new PanelEstados(principal);
			principal.cambiarPanel(panelEstados.getPanel());
		
			break;
		case "Cancelar":
			principal.getVentanaPrincipal().getContentPane().removeAll();
			principal.getVentanaPrincipal().getContentPane().repaint();
			principal.cambiarPanelAcceso();
			break;
	
		}

	}
}