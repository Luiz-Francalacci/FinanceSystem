package dados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import negocio.ControleGastos;

public class testes {

	public static void main(String[] args) {
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Usuario usuario = new Usuario("Luiz", "1234");
		
		ControleGastos sistema = new ControleGastos();
		sistema.cadastrarUsuario(usuario);
		sistema.logarUsuario("Luiz", "1234");
		
		Gasto gasto1 = new Gasto("A",LocalDate.parse("10/04/2003", formato),"bla", 1900, Categoria.valueOf("EDUCACAO"));
		Gasto gasto2 = new Gasto("B",LocalDate.parse("10/04/2004", formato),"bla", 1900, Categoria.valueOf("LAZER"));
		Gasto gasto3 = new Gasto("C",LocalDate.parse("10/04/2004", formato),"bla", 1900, Categoria.valueOf("LAZER"));
		
		sistema.adicionarGasto(gasto1);
		sistema.adicionarGasto(gasto2);
		sistema.adicionarGasto(gasto3);
		
		List<Gasto> lista = sistema.filtrarCategoria(Categoria.valueOf("LAZER"));
		
		for(Gasto x : lista) {
			System.out.println(x.toString());
		}
		
		
		
		
		
		
		

	}

}
