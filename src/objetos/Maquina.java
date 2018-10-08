/*******************************************************************************
 * Copyright (C) 2018, Elena Larreategui, Jon Mugica, Anton Alberdi 
 * 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0 
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 *     Anton Alberdi
 * 
 ******************************************************************************/
 

package objetos;

/**
 * This is the object Maquina that has the details of the status and identifier of the machine
 * 
 * @author anton.alberdi 
 */ 
 
public class Maquina {
	
	boolean estado;
	int id;
	
/**
* Parameterized constructor.
* 
* @param estado The value with the status
* @param id The identifier of the machine
*/
	public Maquina(boolean estado, int id) {
		this.estado = estado;
		System.out.println(this.estado);
		this.id = id;
	}
	
/**
* Returns the machines status value.
*
* @return Current machine status value.
*/
	public boolean isEstado() {
		return estado;
	}

/**
* Sets the machines status value.
*
*/
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

/**
* Returns the machines id value.
*
* @return Current machine id value.
*/
	public int getId() {
		return id;
	}
	
	
/**
* Returns the Object type
*
* @return Object type.
*/
	public Class<?> getFieldClass(int indice) {
		switch (indice) {
		case 0:
			return Boolean.class;
		case 1:
			return Integer.class;
		default:
			return String.class;
		}
	}
	
/**
* Returns the value of specified field
*
* @return the value of specified field
*/

	public Object getFieldAt(int columna) {
		
		switch (columna) {
		case 0:
			return id;
		case 1:
			return estado;
		default:
			return null;
		}
	}
}
