package rhuancarlos.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rhuancarlos.cursomc.domain.Cliente;
import rhuancarlos.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository  repo;
	
	public Cliente Buscar ( Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(()-> new rhuancarlos.cursomc.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado : Id: "+ id +" Tipo: "+ Cliente.class.getName()));
	}

}
