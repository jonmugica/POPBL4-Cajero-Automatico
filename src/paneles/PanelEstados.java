package paneles;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import controladores.ControladorMenuAdmin;
import controladores.ControladorToolbar;
import imagenes.Imagenes;
import modelos.ModeloColumnasTabla;
import modelos.ModeloTablaMaquina;
import modelos.TrazadorTablaMaquina;
import objetos.Maquina;
import vistas.Principal;

public class PanelEstados implements TableModelListener, MouseListener{
	
	JPanel panel;
	Imagenes imagenes;
	ModeloColumnasTabla columnas;
	ModeloTablaMaquina modelo;
	TrazadorTablaMaquina trazador;
	List<Maquina> listaMaquina;
	ControladorToolbar controladorToolbar;
	Principal principal;
	JTable vTabla;
	JScrollPane panelScrollTabla;
	
	Boolean isAlreadyOneClick = false;
	
	public PanelEstados(Principal principal) {
		this.principal = principal;
		try {
			imagenes = new Imagenes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controladorToolbar = new ControladorToolbar(principal);
		panel = new JPanel(new BorderLayout());
		trazador = new TrazadorTablaMaquina();
		columnas = new ModeloColumnasTabla(trazador);
		listaMaquina = new ArrayList<>();
		listaMaquina.add(new Maquina(true,1));
		listaMaquina.add(new Maquina(true,2));
		listaMaquina.add(new Maquina(true,3));
		listaMaquina.add(new Maquina(false,4));
		listaMaquina.add(new Maquina(false,5));
		modelo = new ModeloTablaMaquina(columnas, listaMaquina);
		modelo.addTableModelListener(this);
		panel.add(crearToolbar(), BorderLayout.NORTH);
		panel.add(crearPanelPrincipal(), BorderLayout.CENTER);

	}

	private Container crearPanelPrincipal() {
		panelScrollTabla = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		crearTabla();
		return panelScrollTabla;
	}
	private void crearTabla() {
		vTabla = new JTable(modelo, columnas);
		vTabla.setFillsViewportHeight(true);
		panelScrollTabla.setViewportView(vTabla);
		vTabla.addMouseListener(this);
		vTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vTabla.setCellSelectionEnabled(true);
	}
	public Component crearToolbar() {
		JToolBar toolBar = new JToolBar();

		toolBar.add(Box.createHorizontalGlue());
		toolBar.add(Box.createHorizontalStrut(50));
		toolBar.add(crearBotonToolbar("c_sesion"));

		toolBar.setFloatable(false);

		return toolBar;
	}

	public Component crearBotonToolbar(String nombreBoton) {
		JButton boton = new JButton();

		if (nombreBoton == "c_sesion") {
			boton.setIcon(imagenes.getiCerrarSesionUno());
		}

		boton.setActionCommand(nombreBoton);
		boton.addActionListener(controladorToolbar);
		boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
		boton.setFocusPainted(false);

		boton.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if (nombreBoton == "c_sesion") {
					boton.setIcon(imagenes.getiCerrarSesionUno());
				}

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (nombreBoton == "c_sesion") {
					boton.setIcon(imagenes.getiCerrarSesionDos());
				}

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});

		return boton;
	}

	

	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		String nombre;
		
		if (isAlreadyOneClick) {
			
			int fila = vTabla.getSelectedRow();
			int columna = vTabla.getSelectedColumn();
			nombre = vTabla.getValueAt(fila, 0).toString();

			switch (columna) {

				case 0:
					break;
				case 1:
						System.out.println("double click");
						modelo.cambiarEstado(fila);
						modelo.refrescarTabla();
					break;
			}
		}

		else {

			isAlreadyOneClick = true;
			Timer t = new Timer("doubleclickTimer", false);
			t.schedule(new TimerTask() {

				@Override
				public void run() {
					isAlreadyOneClick = false;
				}
			}, 200);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
