package objetos;



public class Producto {
	
	double precio;
	String nombre;
	String descripcion;
	int ID;
	int cantidad;
	double total;

	public Producto(double precio, String nombre, String descripcion, int id, int cantidad) {
		this.precio = precio;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.ID = id;
		this.calcularTotal();
	}
	
	public void calcularTotal() {
		this.total = cantidad * precio;
	}
	
	public void incrementarCantidad() {
		this.setCantidad(getCantidad()+1);
	}

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
