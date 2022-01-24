package rhuancarlos.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rhuancarlos.cursomc.domain.Categoria;
import rhuancarlos.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria Buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new rhuancarlos.cursomc.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado : Id: "+ id +" Tipo: "+ Categoria.class.getName()));
	}

}
