package dados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String login;
	private String senha;
	List<Gasto> gastos = new ArrayList<>();
	
	
	public Usuario(String login, String senha) {
		this.senha = senha;
		this.login = login;
	}
	
	public boolean validarLogin(String login, String senha) {
		if(this.login.equals(login) && this.senha.equals(senha)) {
			return true;
		}
		return false;
	}
	
	public void adicionarGasto(Gasto gasto) {
		this.gastos.add(gasto);
	}
	
	public void removerGasto(String nome) {
		for(int i =0; i < this.gastos.size(); i++) {
			if(this.gastos.get(i).getNome().equals(nome)) {
				gastos.remove(i);
			}
		}
	}
	
	public void alterarGasto(String nome, String opcao, String resposta) {
		for(Gasto x : gastos) {
			if(x.getNome().equals(nome)) {
				if(opcao.toLowerCase().equals("nome")) {
					x.setNome(resposta);
				}
				if(opcao.toLowerCase().equals("valor")) {
					x.setValor(Float.parseFloat(resposta));
				}
				if(opcao.toLowerCase().equals("data")) {
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					x.setData(LocalDate.parse(resposta, formato));
				}
				if(opcao.toLowerCase().equals("categoria")) {
					x.setCategoria(Categoria.valueOf(resposta.toUpperCase()));
				}
				if(opcao.toLowerCase().equals("descricao")) {
					x.setDescricao(resposta);
				}
			}
		}
	}
	
	
	
	public List<Gasto> getGastos(){
		return gastos;
	}
	
	
	
	
	
	
}
