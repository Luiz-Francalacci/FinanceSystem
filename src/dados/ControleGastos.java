package dados;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControleGastos {

	
	List<Gasto> gastos = new ArrayList<>();
	
	
	public void adicionarGasto(Gasto gasto) {
		this.gastos.add(gasto);
	}
	
	
	public List<Gasto> filtrarMes(int mes, int ano){
		return gastos.stream().filter(gasto -> gasto.getData().getMonthValue() == mes && gasto.getData().getYear() == ano)
				.toList();
	}
	
	public List<Gasto> filtrarCategoria(Categoria categoria) {
		return gastos.stream().filter(gasto -> gasto.getCategoria() == categoria).toList();
	}
	
	
	
	
	
}
