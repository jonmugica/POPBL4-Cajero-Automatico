package lineaserie;

import java.util.Scanner;

import lineaserie.SerialComm;
import vistas.Principal;
import lineaserie.Escritor;
import lineaserie.Lector;
import gnu.io.CommPortIdentifier;

public class Inicializador extends Thread{
	SerialComm lineaSerie;
	Lector hiloLectura;
	Escritor hiloEscritura;
	CommPortIdentifier puerto;
	Principal principal;


	public Inicializador(Principal principal) {
		this.principal = principal;
		lineaSerie = new SerialComm(this.principal);
		puerto = lineaSerie.encontrarPuerto();		
		
		
		try {
			lineaSerie.conectar(puerto);
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (puerto == null) {
			System.out.println("No se ha encontrado una linea serie");
			System.exit(0);
		} else {
			System.out.println("Linea serie encontrada en: " + puerto.getName());
			hiloLectura = new Lector(lineaSerie, puerto);
			hiloEscritura = new Escritor(lineaSerie, puerto);
		}
	}
	@Override
	public void run() {
		hiloLectura.start();
		System.out.println("Hilo Lectura empezado");
		hiloEscritura.start();
		System.out.println("Hilo Escritura empezado");
	}
}
