package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import controladores.ControladorTablaPrincipal;
import controladores.ControladorToolbar;
import imagenes.Imagenes;
import modelos.ModeloColumnasTablaProductos;
import modelos.ModeloTablaProductos;
import mysql.ConexionJDBC;
import objetos.BarcodeReader;
import objetos.BarcodeReader.BarcodeListener;
import objetos.Pedido;
import objetos.Producto;
import renderer.TrazadorTablaProductos;
import vistas.Principal;

public class PanelPrincipal {

	int productoLeidoID;
	JLabel labelHora;
	JLabel imagenProducto;
	JLabel nombreProducto;
	JLabel descProducto;
	JLabel precioProducto;
	JLabel importeTotal;
	Principal principal;
	JTable vTabla;
	ModeloColumnasTablaProductos columnas;
	ControladorToolbar controladorToolbar;
	ControladorTablaPrincipal controladorTablaPrincipal;
	TrazadorTablaProductos trazador;
	JScrollPane panelScrollTabla;
	ModeloTablaProductos tabla;
	Imagenes imagenes;
	ConexionJDBC conexionBD;
	PanelFondo panelFondo;
	

	public PanelPrincipal(Principal principal, ControladorToolbar controladorToolbar, Imagenes imagenes,
			JLabel labelHora, ConexionJDBC conexionBD) {
		this.principal = principal;
		this.controladorToolbar = controladorToolbar;
		this.imagenes = imagenes;
		this.labelHora = labelHora;
		this.conexionBD = conexionBD;
		
		trazador = new TrazadorTablaProductos();
		columnas = new ModeloColumnasTablaProductos(trazador);
		tabla = new ModeloTablaProductos(columnas);
		
		
		
		tabla.addTableModelListener(controladorTablaPrincipal);
		
		imagenProducto = new JLabel();
		imagenProducto.setVerticalAlignment(SwingConstants.CENTER);
		imagenProducto.setHorizontalAlignment(SwingConstants.CENTER);

		nombreProducto = new JLabel();
		nombreProducto.setFont(new Font("Courier New", Font.BOLD, 40));
		nombreProducto.setHorizontalAlignment(SwingConstants.CENTER);

		descProducto = new JLabel();
		descProducto.setFont(new Font("Courier New", Font.ITALIC, 20));
		descProducto.setHorizontalAlignment(SwingConstants.CENTER);

		precioProducto = new JLabel();
		precioProducto.setFont(new Font("Courier New", Font.BOLD, 40));
		precioProducto.setHorizontalAlignment(SwingConstants.CENTER);

		BarcodeReader reader = new BarcodeReader();
		reader.addBarcodeListener(new BarcodeListener() {
			public void onBarcodeRead(String barcode) {
				productoLeidoID = Integer.valueOf(barcode);
				cambiarProducto();
			}
		});
	}
	
	public void almacenarProductos() throws ClassNotFoundException, SQLException {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		int usuarioId = principal.getConexionBD().getIdUsuario(principal.getUsuarioActual().getUsuario());
		Pedido p = new Pedido(conexionBD.getIdUltimoPedido()+1, usuarioId, date);
		conexionBD.registrarPedido(p);
		p.setListaProductos(tabla.getListaProductos());
		
		for(Producto pro : p.getListaProductos()) {
			conexionBD.registrarLinea(p.getId(), pro.getID(), pro.getPrecio(), pro.getCantidad());
		}
		
		



	}

	public Component crearToolbar() {
		JToolBar toolBar = new JToolBar();

		toolBar.add(labelHora);
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

	public Component crearSplitPrincipal() {
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, crearPanelInfoUsuario(),
				crearSplitPaneSecundario());
		panel.setDividerLocation(500);
		panel.setEnabled(false);
		return panel;
	}

	private Component crearSplitPaneSecundario() {
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, crearPanelInfoProductos(),
				crearPanelInfoTabla());
		panel.setDividerLocation(1000);
		panel.setEnabled(false);
		return panel;
	}

	private Component crearPanelInfoTabla() {
		JPanel panel = new JPanel(new BorderLayout());
		panelScrollTabla = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		crearTabla();
		
		panel.add(panelScrollTabla, BorderLayout.CENTER);
		panel.add(panelTablaSur(), BorderLayout.SOUTH);
		return panel;
	}
	
	private Component panelTablaSur() {
		JPanel panelSur = new JPanel(new GridLayout(1, 1, 0, 0));
		JButton bEliminar = new JButton("Eliminar");
		bEliminar.addActionListener(controladorTablaPrincipal);
		panelSur.add(bEliminar);
		tabla.calcularTotal();
		importeTotal.setText(String.valueOf(tabla.getSumaTotal()));
		return panelSur;
	}

	private void crearTabla() {
		vTabla = new JTable(tabla, columnas);
		vTabla.setFillsViewportHeight(true);
		panelScrollTabla.setViewportView(vTabla);
		vTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vTabla.setRowSelectionAllowed(true);
		controladorTablaPrincipal = new ControladorTablaPrincipal(principal, tabla, vTabla, this);
		tabla.addTableModelListener(controladorTablaPrincipal);

		
	}

	public void cambiarProducto() {

		ResultSet rs;
		String nP = null;
		String dP = null;
		double pP = 0;

		rs = conexionBD.select("SELECT * FROM PRODUCTOS WHERE id='" + productoLeidoID + "';");

		try {
			if (rs.next()) {
				nP = rs.getString("nombre");
				dP = rs.getString("descripcion");
				pP = rs.getDouble("precio");
				
				
	

				imagenProducto.setIcon(new ImageIcon(new ImageIcon("productos/" + productoLeidoID + ".jpg").getImage()
						.getScaledInstance(600, 600, Image.SCALE_DEFAULT)));
				
				Producto p = new Producto(pP, nP, dP, productoLeidoID, 1);
				
				if(!tabla.getListaProductos().contains(p)) {
					tabla.añadir(p);
				}
				else {
					for(int i = 0; tabla.getListaProductos().size() > i; i++) {
						if(productoLeidoID == tabla.getListaProductos().get(i).getID()) {
							tabla.getListaProductos().get(i).setCantidad(tabla.getListaProductos().get(i).getCantidad() + 1);
						tabla.fireTableDataChanged();
						}
						
					}
					
				}
			
				
			
				
			} else {
				imagenProducto.setIcon(new ImageIcon("productos/Oops.png"));
				

				nP = "Producto Inexistente";
				dP = "";
				pP = 0;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		nombreProducto.setText(nP);
		descProducto.setText("<html><body style='text-align: center'> " + dP);
		precioProducto.setText((String.valueOf(pP) + " €"));

	}

	private Component crearPanelInfoProductos() {

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);

		panel.add(imagenProducto, BorderLayout.CENTER);
		panel.add(panelDatosProducto(), BorderLayout.SOUTH);
		return panel;
	}

	private Component panelDatosProducto() {
		JPanel panel = new JPanel(new GridLayout(3, 1, 0, 20));
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

		panel.add(nombreProducto);
		panel.add(descProducto);
		panel.add(precioProducto);
		return panel;
	}

	private Component crearPanelInfoUsuario() {
		panelFondo = new PanelFondo();
		panelFondo.setBackground("logos/fondo.png");
		JLabel labelImagen = new JLabel(new ImageIcon("logos/logomusports.png"));
		panelFondo.add(labelImagen, BorderLayout.NORTH);
		panelFondo.add(panelGridInfoUsuario(), BorderLayout.CENTER);
		return panelFondo;
	}

	private Component panelGridInfoUsuario() {
		JPanel panel = new JPanel(new GridLayout(3, 1, 20, 20));
		panel.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 0));
		panel.setBackground(new Color(0, 0, 0, 0));
		String nomApeAux = null;
		ResultSet rs;
		JLabel nombreApellidos = new JLabel();
		JLabel infoImporte = new JLabel("Importe:");
		importeTotal = new JLabel();
		importeTotal.setBackground(new Color(0, 0, 0, 0));

		rs = conexionBD.select("SELECT * FROM USUARIOS WHERE usuario='" + principal.getUsuarioActual().getUsuario() + "';");

		try {
			while (rs.next()) {
				nomApeAux = String.valueOf(rs.getString("nombre").concat(" ").concat(rs.getString("apellido1")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		nombreApellidos.setText(nomApeAux);
		nombreApellidos.setFont(new Font("Courier New", Font.BOLD, 40));
		infoImporte.setFont(new Font("Courier New", Font.BOLD, 40));
		importeTotal.setFont(new Font("Courier New", Font.BOLD, 40));
		importeTotal.setForeground(Color.GRAY);
		panel.add(nombreApellidos);
		panel.add(infoImporte);
		panel.add(importeTotal);

		return panel;
	}

	
	public PanelFondo getPanelFondo() {
		return panelFondo;
	}

	public JLabel getImporteTotal() {
		return importeTotal;
	}

}
