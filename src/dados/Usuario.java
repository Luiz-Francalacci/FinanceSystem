package dados;

import java.time.LocalDate;
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
		if(this.login == login && this.senha == senha) {
			return true;
		}
		return false;
	}
	
	public void adicionarGasto(Gasto gasto) {
		this.gastos.add(gasto);
	}
	
	public void removerGasto(String nome) {
		for(Gasto x : gastos) {
			if(x.getNome() == nome) {
				gastos.remove(x);
			}
		}
	}
	
	public void alterarGasto(String nome, String opcao, String resposta) {
		for(Gasto x : gastos) {
			if(x.getNome() == nome) {
				if(opcao.toLowerCase() == "nome") {
					x.setNome(resposta);
				}
				if(opcao.toLowerCase() == "valor") {
					x.setValor(Float.parseFloat(resposta));
				}
				if(opcao.toLowerCase() == "data") {
					x.setData(LocalDate.parse(resposta));
				}
				if(opcao.toLowerCase() == "categoria") {
					x.setCategoria(Categoria.valueOf(resposta.toUpperCase()));
				}
				if(opcao.toLowerCase() == "descricao") {
					x.setDescricao(resposta);
				}
			}
		}
	}
	
	
	
	public List<Gasto> getGastos(){
		return gastos;
	}
	
	
	
	
	
}
