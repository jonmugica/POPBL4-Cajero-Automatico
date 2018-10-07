package vistas;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controladores.ControladorToolbar;
import imagenes.Imagenes;
import lineaserie.Inicializador;
import mysql.ConexionJDBC;
import objetos.Reloj;
import objetos.Usuario;
import paneles.PanelAcceso;
import paneles.PanelPrincipal;

public class Principal {

	JFrame ventanaPrincipal;
	Dimension dimensionPantalla;
	PanelAcceso panelAcceso;
	PanelPrincipal panelPrincipal;
	Imagenes imagenes;
	ConexionJDBC conexionBD;
	JLabel labelHora;
	Reloj reloj;
	Usuario usuarioActual;
	ControladorToolbar controladorToolbar;
	Runnable r;

	public Principal() {
		try {
			imagenes = new Imagenes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controladorToolbar = new ControladorToolbar(this);
		conexionBD = new ConexionJDBC();
		panelAcceso = new PanelAcceso(this, conexionBD);
		dimensionPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		ventanaPrincipal = new JFrame();
		ventanaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ventanaPrincipal.setUndecorated(true);
		ventanaPrincipal.getContentPane().add(panelAcceso.getPanel(), BorderLayout.CENTER);
		ventanaPrincipal.setVisible(true);
		ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		r = new Inicializador(this); 
		new Thread(r).start();

	}

	public void cambiarPanel(JPanel panel) {
		ventanaPrincipal.getContentPane().removeAll();
		ventanaPrincipal.getContentPane().repaint();
		ventanaPrincipal.getContentPane().add(panel, BorderLayout.CENTER);
		ventanaPrincipal.setVisible(true);
	}

	public void cambiarPanelCompra() {

		labelHora = new JLabel(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		labelHora.setFont(new Font("Courier New", Font.PLAIN, 40));
		panelPrincipal = new PanelPrincipal(this, controladorToolbar, imagenes, labelHora, conexionBD);
		ventanaPrincipal.getContentPane().removeAll();
		ventanaPrincipal.getContentPane().repaint();
		ventanaPrincipal.getContentPane().add(panelPrincipal.crearToolbar(), BorderLayout.NORTH);
		ventanaPrincipal.getContentPane().add(panelPrincipal.crearSplitPrincipal(), BorderLayout.CENTER);
		ventanaPrincipal.setVisible(true);
		reloj = new Reloj(this);
		reloj.empezar();
		
	}


	public void cambiarPanelAcceso() {
		panelAcceso = new PanelAcceso(this, conexionBD);
		ventanaPrincipal.getContentPane().removeAll();
		ventanaPrincipal.getContentPane().repaint();
		ventanaPrincipal.getContentPane().add(panelAcceso.getPanel(), BorderLayout.CENTER);
		ventanaPrincipal.setVisible(true);
	}
	


	public PanelPrincipal getPanelPrincipal() {
		return panelPrincipal;
	}

	public JFrame getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public JLabel getLabelHora() {
		return labelHora;
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(Usuario usuarioActual) {
		this.usuarioActual = usuarioActual;
	}

	public ConexionJDBC getConexionBD() {
		return conexionBD;
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No se encuentra el look&Feel");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		Principal principal = new Principal();

	}
}
