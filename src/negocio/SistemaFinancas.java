package negocio;

import java.util.ArrayList;
import java.util.List;

import dados.Categoria;
import dados.Gasto;
import dados.Usuario;


public class SistemaFinancas {

	private List<Usuario> usuarios = new ArrayList<>();
	
	private int usuarioOn;
	
	public void cadastrarUsuario(Usuario user) {
		this.usuarios.add(user);
	}
	
	public void logarUsuario(String login, String senha) {
		for(int i = 0; i < this.usuarios.size(); i++) {
			if(this.usuarios.get(i).validarLogin(login, senha)) {
				this.usuarioOn = i;
			}
		}
	}
	
	
	public void adicionarGasto(Gasto gasto) {
		this.usuarios.get(usuarioOn).adicionarGasto(gasto);
	}
	
	
	public List<Gasto> filtrarMes(int mes, int ano){
		List<Gasto> gastos = this.usuarios.get(usuarioOn).getGastos();
		
		return gastos.stream().filter(gasto -> gasto.getData().getMonthValue() == mes && gasto.getData().getYear() == ano)
				.toList();
	}
	
	public List<Gasto> filtrarCategoria(Categoria categoria) {
		List<Gasto> gastos = this.usuarios.get(usuarioOn).getGastos();
		return gastos.stream().filter(gasto -> gasto.getCategoria() == categoria).toList();
	}
	
	public List<Gasto> getGastos() {
		return this.usuarios.get(usuarioOn).getGastos();
	}
	
	public void removerGasto(String nome) {
		this.usuarios.get(usuarioOn).removerGasto(nome);
	}
	
	public void alterarGasto(String nome, String opcao, String resposta) {
		this.usuarios.get(usuarioOn).alterarGasto(nome, opcao, resposta);
	}
	
	
	
	
	
}
