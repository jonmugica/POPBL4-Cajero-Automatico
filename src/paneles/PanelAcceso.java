package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controladores.ControladorAcceso;
import mysql.ConexionJDBC;
import vistas.Principal;

public class PanelAcceso {
	
	PanelFondo panelFondo;
	JTextField tfUsuario;
	JPasswordField tfPassword;
	ControladorAcceso controlador;
	ConexionJDBC conexionDB;
	Principal principal;
	
	
	public PanelAcceso(Principal principal, ConexionJDBC conexionDB) {
		this.principal = principal;
		controlador = new ControladorAcceso(this, conexionDB, this.principal);
		panelFondo = new PanelFondo();
		panelFondo.setBackground("logos/fondo.png");
		panelFondo.add(crearPanelIcono(), BorderLayout.NORTH);
		panelFondo.add(crearPanelCredenciales(), BorderLayout.CENTER);
		
	}

	private Component crearPanelIcono() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel labelImagen = new JLabel(new ImageIcon("logos/logomusports.png"));
		panel.add(labelImagen);
		panel.setBackground(new Color(0,0,0,0));
		return panel;
	}

	private Component crearPanelCredenciales() {		

		char c = 0;
		
		tfUsuario = new JTextField();
		tfUsuario.setFont(new Font("Arial", Font.PLAIN, 40));
		tfUsuario.setHorizontalAlignment(JTextField.CENTER);
		tfUsuario.setText("Usuario");
		tfUsuario.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				tfUsuario.setText("");
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		tfPassword = new JPasswordField();	
		tfPassword.setEchoChar(c);
		tfPassword.setText("Contraseña");
		tfPassword.setFont(new Font("Arial", Font.PLAIN, 40));
		tfPassword.setHorizontalAlignment(JTextField.CENTER);
		tfPassword.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				tfPassword.setText("");
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		tfPassword.addKeyListener(new KeyListener() {

			int count = 0;
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				char ch = 0x25cf;
				if(count == 0) {
					tfPassword.setText("");
					tfPassword.setEchoChar(ch);
					count++;
				}
				
				
			}
			
		});
		
	
		
		JPanel panel = new JPanel(new GridLayout(3, 1, 0, 35));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 500, 10, 500));	
		panel.add(tfUsuario);
		panel.add(tfPassword);
		panel.add(crearPanelBoton());
		panel.setBackground(new Color(0,0,0,0));
		return panel;
	}
	private Component crearPanelBoton() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(0,0,0,0));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 250, 50, 250));
		JButton aceptar = new JButton("Aceptar");
		aceptar.setFont(new Font("Arial", Font.PLAIN, 25));
		aceptar.setActionCommand("aceptar");
		aceptar.addActionListener(controlador);
		panel.add(aceptar);
		
		return panel;
		
	}

	public JPanel getPanel() {
		return panelFondo;
	}

	public JTextField getTfUsuario() {
		return tfUsuario;
	}

	public JPasswordField getTfPassword() {
		return tfPassword;
	}
	
	

}	
