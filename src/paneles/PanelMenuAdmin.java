package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controladores.ControladorMenuAdmin;
import mysql.ConexionJDBC;
import vistas.Principal;

public class PanelMenuAdmin {
	PanelFondo panelFondo;
	Principal principal;
	ConexionJDBC conexionBD;
	ControladorMenuAdmin controladorMenuAdmin;
	
	
	public PanelMenuAdmin(Principal principal) {
		this.principal = principal;
		this.conexionBD = principal.getConexionBD();
		controladorMenuAdmin = new ControladorMenuAdmin(principal);
		panelFondo = new PanelFondo();
		panelFondo.setBackground("logos/fondo.png");
		panelFondo.add(crearPanelIcono(), BorderLayout.NORTH);
		panelFondo.add(crearPanelOpciones(), BorderLayout.CENTER);
		
	}

	private Component crearPanelIcono() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel labelImagen = new JLabel(new ImageIcon("logos/logomusports.png"));
		panel.add(labelImagen);
		panel.setBackground(new Color(0,0,0,0));
		return panel;
	}

	private Component crearPanelOpciones() {		
		
		JPanel panel = new JPanel(new GridLayout(4, 1, 0, 35));
		panel.setBackground(new Color(0,0,0,0));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 700, 100, 700));
		panel.add(crearBoton("Ver últimos pedidos"));
		panel.add(crearBoton("Estado de los cajeros"));
		panel.add(crearBoton("Cancelar"));

		return panel;
	}
	private Component crearBoton(String nombre) {
		
		JButton btn = new JButton(nombre);
		btn.setFont(new Font("Arial", Font.PLAIN, 25));
		btn.setActionCommand(nombre);
		btn.addActionListener(controladorMenuAdmin);
		
		
		return btn;
		
	}

	public JPanel getPanel() {
		return panelFondo;
	}



}	
