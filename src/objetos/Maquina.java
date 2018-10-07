package objetos;


public class Maquina {
	
	boolean estado;
	int id;
	
	public Maquina(boolean estado, int id) {
		this.estado = estado;
		System.out.println(this.estado);
		this.id = id;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getId() {
		return id;
	}
	
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
