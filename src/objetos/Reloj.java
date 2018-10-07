package objetos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Timer;

import vistas.Principal;

public class Reloj {
	int timeDelay = 500;
	ActionListener time;
	Principal principal;
	
	public Reloj(Principal principal) {
		this.principal = principal;
	}
	
	public void empezar() {
		time = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				principal.getLabelHora().setText(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
				
			}
		};
		new Timer(timeDelay, time).start();
		
	}
}

