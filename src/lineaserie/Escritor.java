package lineaserie;

import gnu.io.CommPortIdentifier;

public class Escritor extends Thread {
	SerialComm lineaSerie;
	CommPortIdentifier puerto;
	volatile boolean parar = false;

	public Escritor(SerialComm lineaSerie, CommPortIdentifier puerto) {
		this.lineaSerie = lineaSerie;
		this.puerto = puerto;

	}

	@Override
	public void run() {
		while (!parar) {


				//lineaSerie.escribir("Buzzer$");
				
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

		}
	}

	public void parar() {
		parar = true;
	}
}
