package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import mysql.ConexionJDBC;
import objetos.Usuario;
import paneles.PanelAcceso;
import paneles.PanelMenuAdmin;
import vistas.Principal;

public class ControladorAcceso implements ActionListener{

	ConexionJDBC conexionBD;
	PanelAcceso panelAcceso;
	PreparedStatement pst = null;
	ResultSet result = null;
	Principal principal;

	
	public ControladorAcceso(PanelAcceso panelAcceso, ConexionJDBC conexionBD, Principal principal) {
		this.principal = principal;
		this.conexionBD = conexionBD;
		this.panelAcceso = panelAcceso;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "aceptar":
			try {
				pst = conexionBD.preparedStatement("select esAdmin from usuarios where usuario=? and password=?");

			pst.setString(1, panelAcceso.getTfUsuario().getText());
			pst.setString(2,md5(String.valueOf(panelAcceso.getTfPassword().getPassword())));
			try {
				result = pst.executeQuery();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(result.next()) {
				principal.setUsuarioActual(new Usuario(panelAcceso.getTfUsuario().getText(), result.getBoolean("esAdmin")));
				System.out.println(principal.getUsuarioActual().getEsAdmin());
				
				if(principal.getUsuarioActual().getEsAdmin()) {
					PanelMenuAdmin panel = new PanelMenuAdmin(principal);
					principal.cambiarPanel(panel.getPanel());

				}
				else {
					principal.cambiarPanelCompra();
				}

				
			}
			else{
				
				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
			}
			}
		 catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
			break;
		}

	}

	public String md5(String pass) {
		
		
        String md5hash = null;
		  try {
	            MessageDigest md = MessageDigest.getInstance("MD5");          
	            md.update(pass.getBytes());	   
	            byte[] bytes = md.digest();
	            StringBuilder sb = new StringBuilder();
	            
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            md5hash = sb.toString();
	        }
	        catch (NoSuchAlgorithmException e)
	        {
	            e.printStackTrace();
	        }
		return md5hash;
	      
	    }
}
