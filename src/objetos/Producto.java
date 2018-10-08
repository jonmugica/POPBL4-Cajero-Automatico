
/*******************************************************************************
 * Copyright (C) 2018, Elena Larreategui, Jon Mugica, Anton Alberdi
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Elena Larreategi
 *
 ******************************************************************************/

package objetos;

/**
 * This is the object Maquina that has the details of the status and identifier of the machine
 *
 * @author elena.larreategui
 */


public class Producto {

	double precio;
	String nombre;
	String descripcion;
	int ID;
	int cantidad;
	double total;

	/**
	* Parameterized constructor.
	*
	* @param precio Prize of the product
	* @param nombre Name of the product
	* @param descripcion Description of the product
	* @param id Identifier of the product
	* @param cantidad Cuantity of the product
	*/

	public Producto(double precio, String nombre, String descripcion, int id, int cantidad) {
		this.precio = precio;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.ID = id;
		this.calcularTotal();
	}

	/**
	* calculates the total amount of money
	*
	*/
	public void calcularTotal() {
		this.total = cantidad * precio;
	}

	public void incrementarCantidad() {
		this.setCantidad(getCantidad()+1);
	}

	/**
	* Returns the type of variable
	*
	* @return the needed type of variable.
	*/
	public Class<?> getFieldClass(int indice) {
		switch (indice) {
		case 0:
			return String.class;
		case 1:
			return Integer.class;
		case 2:
			return Integer.class;
		case 3:
			return Integer.class;
		default:
			return String.class;
		}
	}


	public Object getFieldAt(int columna) {

		switch (columna) {
		case 0:
			return nombre;
		case 1:
			return precio;
		case 2:
			return cantidad;
		case 3:
			return total;

		default:
			return null;
		}
	}
	/**
	* Returns the cuantity
	*
	* @return actual's product quantity.
	*/
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
		this.calcularTotal();
	}

	public double getPrecio() {
		return precio;
	}

	public double getTotal() {
		return total;
	}
	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getID() {
		return ID;
	}



	@Override
	public boolean equals(Object obj) {
		Producto other = (Producto) obj;
		if (ID == other.ID)
			return true;
		return false;

	}
}
