package lineaserie;

import java.io.IOException;
import java.sql.SQLException;

import lineaserie.SerialComm;
import gnu.io.CommPortIdentifier;

public class Lector extends Thread {
	SerialComm lineaSerie;
	CommPortIdentifier puerto;

	volatile boolean parar = false;;
	
	public Lector(SerialComm lineaSerie, CommPortIdentifier puerto) {
		this.lineaSerie = lineaSerie;
		this.puerto = puerto;
		
	}

	@Override
	public void run() {
		try {
			while (!parar) {
				lineaSerie.leer();
			}
		}catch(IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println("fin hilo lector");
	}
	public void parar() {
		parar = true;
	}
	
}
