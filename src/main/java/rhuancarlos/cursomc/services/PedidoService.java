package rhuancarlos.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rhuancarlos.cursomc.domain.Pedido;
import rhuancarlos.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido Buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new rhuancarlos.cursomc.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado : Id: "+ id +" Tipo: "+ Pedido.class.getName()));
	}

}
