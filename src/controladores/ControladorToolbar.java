package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vistas.Principal;

public class ControladorToolbar implements ActionListener{

	Principal principal;
	public ControladorToolbar(Principal principal) {
		this.principal = principal;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "c_sesion":
			principal.getVentanaPrincipal().getContentPane().removeAll();
			principal.getVentanaPrincipal().getContentPane().repaint();
			//principal.setUsuarioActual("");
			principal.cambiarPanelAcceso();
			
		}
	}

}
