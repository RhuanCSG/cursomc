package rhuancarlos.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import rhuancarlos.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto (PagamentoComBoleto pagto, Date instanteDePedido) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDePedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
	

}
