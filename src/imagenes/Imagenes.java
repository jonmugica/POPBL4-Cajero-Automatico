package imagenes;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Imagenes {
	Dimension dimensionPantalla;
	Image imagen;
	Image nuevaImagen;
	final ImageIcon iLogo;
	final ImageIcon iCerrarSesionUno;
	final ImageIcon iCerrarSesionDos;

	public Imagenes() throws IOException {
		dimensionPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		imagen = ImageIO.read(new File("logos/logomusports.png"));
		nuevaImagen = imagen.getScaledInstance(250, 250,
				java.awt.Image.SCALE_SMOOTH);
		iLogo = new ImageIcon(nuevaImagen);
		iLogo.setDescription("logo");

		imagen = ImageIO.read(new File("logos/c_SesionUno.png"));
		nuevaImagen = imagen.getScaledInstance(dimensionPantalla.width / 32, dimensionPantalla.height / 18,
				java.awt.Image.SCALE_SMOOTH);
		iCerrarSesionUno = new ImageIcon(nuevaImagen);
		iCerrarSesionUno.setDescription("c_sesionUno");
		

		imagen = ImageIO.read(new File("logos/c_SesionDos.png"));
		nuevaImagen = imagen.getScaledInstance(dimensionPantalla.width / 32, dimensionPantalla.height / 18,
				java.awt.Image.SCALE_SMOOTH);
		iCerrarSesionDos = new ImageIcon(nuevaImagen);
		iCerrarSesionDos.setDescription("c_SesionDos");

	}


	public ImageIcon getiDome() {
		return iLogo;
	}


	public ImageIcon getiCerrarSesionUno() {
		return iCerrarSesionUno;
	}


	public ImageIcon getiCerrarSesionDos() {
		return iCerrarSesionDos;
	}

	

}