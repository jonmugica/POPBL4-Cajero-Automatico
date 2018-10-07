package modelos;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.AbstractListModel;

import objetos.Usuario;

public class ModeloUsuarios extends AbstractListModel<Usuario>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Usuario> listaUsuarios;
	ArrayList<Usuario> listaUsuariosFiltrada;
	Usuario u;
	
	@SuppressWarnings("unchecked")
	public ModeloUsuarios (ArrayList<Usuario> listaUsuarios){
		this.listaUsuarios = listaUsuarios;
		
		listaUsuariosFiltrada = new ArrayList<>();
		listaUsuariosFiltrada = (ArrayList<Usuario>) listaUsuarios.clone();
	}

	
	//USUARIO
	public void filtrarPorUsuario(String filtro) {
		
		listaUsuariosFiltrada.clear();
		listaUsuariosFiltrada = (ArrayList<Usuario>) listaUsuarios.stream().filter(a -> a.getUsuario().equals(filtro))
				.collect(Collectors.toList());
		this.fireContentsChanged(listaUsuariosFiltrada, 0, listaUsuariosFiltrada.size());
	}
	
	 //CP
	public void filtrarPorCodigoPostal(String filtro) {
		listaUsuariosFiltrada.clear();
		listaUsuariosFiltrada = (ArrayList<Usuario>) listaUsuarios.stream().filter(a -> a.getCodigo_postal().equals(filtro))
				.collect(Collectors.toList());
		this.fireContentsChanged(listaUsuariosFiltrada, 0, listaUsuariosFiltrada.size());
	}
	
	//POBLACIÓN
	public void filtrarPorPoblacion(String filtro) {
		listaUsuariosFiltrada.clear();
		listaUsuariosFiltrada = (ArrayList<Usuario>) listaUsuarios.stream().filter(a -> a.getPoblacion().equals(filtro))
				.collect(Collectors.toList());
		this.fireContentsChanged(listaUsuariosFiltrada, 0, listaUsuariosFiltrada.size());
	} 

	//LIMPIAR FILTROS
	public void mostrarTodos() {
		listaUsuariosFiltrada.clear();
		listaUsuariosFiltrada = (ArrayList<Usuario>) listaUsuarios.stream()
				.collect(Collectors.toList());
		this.fireContentsChanged(listaUsuariosFiltrada, 0, listaUsuariosFiltrada.size());

	}
	
	//DNI
	public void filtrarPorDni(String filtro) {
		listaUsuariosFiltrada.clear();
		listaUsuariosFiltrada = (ArrayList<Usuario>) listaUsuarios.stream().filter(a -> a.getDni().equals(filtro))
				.collect(Collectors.toList());
		this.fireContentsChanged(listaUsuariosFiltrada, 0, listaUsuariosFiltrada.size());
	} 

	
	@Override
	public int getSize() {
		return listaUsuariosFiltrada.size();
	}

	@Override
	public Usuario getElementAt(int index) {
		return listaUsuariosFiltrada.get(index);
	}
	
}