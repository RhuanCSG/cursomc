package rhuancarlos.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import rhuancarlos.cursomc.domain.Cliente;
import rhuancarlos.cursomc.domain.enums.TipoCliente;
import rhuancarlos.cursomc.dto.ClienteNewDTO;
import rhuancarlos.cursomc.repositories.ClienteRepository;
import rhuancarlos.cursomc.resources.exceptions.FieldMessage;
import rhuancarlos.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}
	
	@Autowired
	ClienteRepository repo;

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage( "cpfOuCnpj" , "CPF inválido"));
		}
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage( "cpfOuCnpj" , "CNPJ inválido"));
		}
		
		Cliente aux = repo.findByCpfOuCnpj(objDto.getCpfOuCnpj());
		if (aux != null) {
			if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod())){
			list.add(new FieldMessage("cpfOuCnpj", "CPF já existente"));
			}
			if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod())){
				list.add(new FieldMessage("cpfOuCnpj", " CNPJ já existente"));
			}
		}
		
		aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}


		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
			
		
		}
		return list.isEmpty();
	}
}
