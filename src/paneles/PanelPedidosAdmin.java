package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import controladores.ControladorToolbar;
import imagenes.Imagenes;
import modelos.ModeloColumnasTablaProductos;
import modelos.ModeloPedidos;
import modelos.ModeloTablaProductos;
import modelos.ModeloUsuarios;
import mysql.ConexionJDBC;
import objetos.Pedido;
import objetos.Producto;
import objetos.Usuario;
import renderer.TrazadorTablaProductos;
import vistas.Principal;

public class PanelPedidosAdmin implements ActionListener, TableModelListener, ItemListener {

	JPanel panel;
	JComboBox<String> cAño, cMes;
	ConexionJDBC conexionBD;
	JTable vTabla;

	TrazadorTablaProductos trazador;
	ModeloColumnasTablaProductos columnas;
	ModeloTablaProductos tabla;
	JScrollPane panelScrollTabla;
	ControladorToolbar controladorToolbar;
	JTextField cantidad;

	JPanel panelPrincipal;

	JPanel panelTabla;
	JPanel panelSur;

	ButtonGroup filtrosUsuario;
	JRadioButton rDNI, rUsuario, rCP, rPoblacion;
	JButton bBuscar;
	JTextField tBuscar;

	List<Usuario> listaUsuariosFull;
	List<Pedido> listaPedidosFull;
	List<Producto> listaProductosFull;

	ModeloUsuarios modeloUsuarios;
	ModeloPedidos modeloPedidos;
	JList<Usuario> listaUsuarios;
	JList<Pedido> listaPedidos;
	Imagenes imagenes;
	Principal principal;

	public PanelPedidosAdmin(ConexionJDBC conexionBD, Principal principal)
			throws ClassNotFoundException, SQLException, IOException {

		this.conexionBD = conexionBD;
		this.principal = principal;
		imagenes = new Imagenes();
		panel = new JPanel(new BorderLayout());
		
		modeloUsuarios = new ModeloUsuarios((ArrayList<Usuario>) crearListaCompleta());

		modeloPedidos = new ModeloPedidos();
		controladorToolbar = new ControladorToolbar(principal);
		trazador = new TrazadorTablaProductos();
		columnas = new ModeloColumnasTablaProductos(trazador);
		tabla = new ModeloTablaProductos(columnas);
		tabla.addTableModelListener(this);
		panel.add(crearToolbar(), BorderLayout.NORTH);
		panel.add(crearSplitPrincipal(), BorderLayout.CENTER);

	}

	public Component crearSplitPrincipal() {
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, crearPanelInfoUsuario(),
				crearSplitPaneSecundario());
		panel.setDividerLocation(500);
		panel.setBackground(new Color(0, 0, 0, 0));

		panel.setEnabled(false);
		return panel;
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

	private Component crearPanelInfoUsuario() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 0));
		JLabel labelImagen = new JLabel(new ImageIcon("logos/logomusports.png"));
		panel.add(labelImagen, BorderLayout.NORTH);
		panel.add(crearPanelFiltros(), BorderLayout.CENTER);
		return panel;
	}

	private Component crearSplitPaneSecundario() {
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, crearPanelListas(), crearPanel());
		panel.setBackground(new Color(0, 0, 0, 0));
		panel.setDividerLocation(710);
		panel.setEnabled(false);
		return panel;
	}

	private Component crearPanelListas() {
		JPanel panel = new JPanel(new GridLayout(1, 2));
		panel.setBackground(new Color(0, 0, 0, 0));
		panel.add(panelListaUsuarios());
		panel.add(panelListaFechas());
		return panel;
	}

	private Container crearPanel() {
		panelTabla = new JPanel(new BorderLayout());

		panelScrollTabla = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		crearTabla();

		panelTabla.add(panelScrollTabla, BorderLayout.CENTER);

		return panelTabla;
	}

	private Component crearPanelFiltros() {
		JPanel panelFiltros = new JPanel(new GridLayout(2, 1, 0, 50));
		panelFiltros.setSize(new Dimension(400, 200));
		panelFiltros.add(panelFiltrosPersona());
		panelFiltros.add(panelFiltrosFecha());
		panelFiltros.setBackground(new Color(0, 0, 0, 0));
		return panelFiltros;
	}

	private Component panelListaFechas() {
		JScrollPane panelListaFechas = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		listaPedidos = new JList<>(modeloPedidos);

		listaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaPedidos.setSelectedIndex(0);
		listaPedidos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {

					if (listaUsuarios.getSelectedIndex() == -1) {

					} else {
						Pedido p = modeloPedidos.getElementAt(listaPedidos.getSelectedIndex());
						tabla.setListaProductos(p);
					}

				}
			}
		});
		panelListaFechas.setBackground(new Color(0, 0, 0, 0));
		panelListaFechas.setViewportView(listaPedidos);
		return panelListaFechas;
	}

	private Component panelListaUsuarios() {
		JScrollPane panelListaUsuarios = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		listaUsuarios = new JList<>(modeloUsuarios);
		listaUsuarios.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {

					if (listaUsuarios.getSelectedIndex() == -1) {

					}
					else {
						Usuario u = modeloUsuarios.getElementAt(listaUsuarios.getSelectedIndex());
					modeloPedidos.setListaPedidos(u);
					}
					

				}
			}
		});
		listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelListaUsuarios.setViewportView(listaUsuarios);
		return panelListaUsuarios;
	}

	private Component panelFiltrosFecha() {
		JPanel panelFiltrosFecha = new JPanel(new GridLayout(1, 2));

		String opAño[] = { "AÑO", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009",
				"2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000" };
		String opMes[] = { "MES", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };

		cAño = new JComboBox<>(opAño);
		cAño.addItemListener(this);
		cMes = new JComboBox<>(opMes);
		cMes.addItemListener(this);
		panelFiltrosFecha.add(cAño);
		panelFiltrosFecha.add(cMes);
		panelFiltrosFecha.setBackground(new Color(0, 0, 0, 0));
		return panelFiltrosFecha;
	}

	private Component panelFiltrosPersona() {
		JPanel panelFiltrosPersona = new JPanel(new GridLayout(0, 2));
		panelFiltrosPersona.setBackground(new Color(0, 0, 0, 0));
		rDNI = new JRadioButton("DNI");
		rDNI.setSelected(false);

		rUsuario = new JRadioButton("Usuario");
		rUsuario.setSelected(false);

		rCP = new JRadioButton("C.P.");
		rCP.setSelected(false);

		rPoblacion = new JRadioButton("Población");
		rPoblacion.setSelected(false);

		tBuscar = new JTextField();
		tBuscar.setEditable(true);

		filtrosUsuario = new ButtonGroup();
		filtrosUsuario.add(rDNI);
		filtrosUsuario.add(rUsuario);
		filtrosUsuario.add(rCP);
		filtrosUsuario.add(rPoblacion);

		bBuscar = new JButton("Buscar");
		bBuscar.setActionCommand("Buscar");
		bBuscar.addActionListener(this);

		panelFiltrosPersona.add(rDNI);
		panelFiltrosPersona.add(rUsuario);
		panelFiltrosPersona.add(rCP);
		panelFiltrosPersona.add(rPoblacion);
		panelFiltrosPersona.add(tBuscar);
		panelFiltrosPersona.add(bBuscar);

		return panelFiltrosPersona;
	}

	private void crearTabla() {
		vTabla = new JTable(tabla, columnas);
		vTabla.setFillsViewportHeight(true);
		panelScrollTabla.setViewportView(vTabla);
		vTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vTabla.setRowSelectionAllowed(true);
	}

	public List<Usuario> crearListaCompleta() throws ClassNotFoundException, SQLException {
		listaUsuariosFull = conexionBD.getListaUsuarios();

		for (Usuario u : listaUsuariosFull) {
			listaPedidosFull = conexionBD.getListaPedidos(u.getUsuario());
			u.setListaPedidos(listaPedidosFull);

			for (Pedido p : listaPedidosFull) {
				listaProductosFull = conexionBD.getListaProductos(p.getId());
				p.setListaProductos(listaProductosFull);
			}
		}
		return listaUsuariosFull;
	}

	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Buscar")) {

			if (tBuscar.getText().equals("") || tBuscar.getText().equals(" ")) {
				modeloUsuarios.mostrarTodos();
				modeloPedidos.limpiar();
			} else if (rDNI.isSelected()) {
				modeloUsuarios.filtrarPorDni(tBuscar.getText());
				modeloPedidos.limpiar();
			} else if (rCP.isSelected()) {
				modeloUsuarios.filtrarPorCodigoPostal(tBuscar.getText());
				modeloPedidos.limpiar();
			} else if (rPoblacion.isSelected()) {
				modeloUsuarios.filtrarPorPoblacion(tBuscar.getText());
				modeloPedidos.limpiar();
			} else if (rUsuario.isSelected()) {
				modeloUsuarios.filtrarPorUsuario(tBuscar.getText());
				modeloPedidos.limpiar();
			}
			listaUsuarios.clearSelection();
			listaPedidos.clearSelection();
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String año = String.valueOf(cAño.getSelectedItem());
			String mes = String.valueOf(cMes.getSelectedItem());
			System.out.println("año" + String.valueOf(cAño.getSelectedItem()));

			if (!año.equals("AÑO") && !mes.equals("MES")) {
				modeloPedidos.filtrarPorAño(año);
				modeloPedidos.filtrarPorMes(mes);
			} else if (!año.equals("AÑO") && mes.equals("MES")) {
				System.out.println("entra");
				modeloPedidos.filtrarPorAño(año);
			} else if (año.equals("AÑO") && !mes.equals("MES")) {
				modeloPedidos.filtrarPorMes(mes);
			} else if (año.equals("AÑO") && mes.equals("MES")) {
				modeloPedidos.mostrarTodos();
			}
		}
	}
}