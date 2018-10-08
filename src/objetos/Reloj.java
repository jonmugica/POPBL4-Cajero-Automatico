/*******************************************************************************
 * Copyright (C) 2018, Elena Larreategui, Jon Mugica, Anton Alberdi 
 * 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0 
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 *     Jon Mugica
 * 
 ******************************************************************************/

package objetos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Timer;

import vistas.Principal;

/**
 * This is the object Reloj that has the details of the Principal and the timeDelay.
 * 
 * @author jon.mugica 
 */ 

public class Reloj {
	int timeDelay = 500;
	ActionListener time;
	Principal principal;
	
	
/**
* Parameterized constructor.
* 
* @param principal with the principal
*/

	public Reloj(Principal principal) {
		this.principal = principal;
	}
	
	
/**
* It started the timer.
*
*/

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

