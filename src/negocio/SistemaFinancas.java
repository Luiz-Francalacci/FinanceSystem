package negocio;

import java.util.ArrayList;
import java.util.List;

import dados.Categoria;
import dados.Gasto;
import dados.Usuario;


public class SistemaFinancas {

	private UserDAO  userDAO= new UserDAO();
	private GastoDAO  gastoDAO= new GastoDAO();
	
	private List<Usuario> usuarios = new ArrayList<>();
	
	private Usuario usuarioOn;
	
	public void cadastrarUsuario(Usuario user) {
		this.usuarios.add(user);
		userDAO.adicionarUsuario(user);
	}
	
	public boolean logarUsuario(String login, String senha) {
		Usuario usuario = userDAO.autenticarUsuario(login, senha);
		if(usuario != null) {
			usuarioOn = usuario;
			return true;
		}
		return false;
	}
	
	
	
	
	
	public void adicionarGasto(Gasto gasto) {
		this.usuarioOn.adicionarGasto(gasto);
		gasto.setIdUsuario(usuarioOn.getId());
		gastoDAO.addGasto(gasto);
	}
	
	
	public List<Gasto> filtrarMes(int mes, int ano){
		List<Gasto> gastos = this.usuarioOn.getGastos();
		
		//return gastos.stream().filter(gasto -> gasto.getData().getMonthValue() == mes && gasto.getData().getYear() == ano)
		//		.toList();
		return gastoDAO.filtrarPorMes(usuarioOn.getId(), mes, ano);
	}
	
	public List<Gasto> filtrarCategoria(Categoria categoria) {
		List<Gasto> gastos = this.usuarioOn.getGastos();
		//return gastos.stream().filter(gasto -> gasto.getCategoria() == categoria).toList();
		return gastoDAO.filtrarPorCategoria(usuarioOn.getId(), categoria);
	}
	
	public List<Gasto> getGastos() {
		//return this.usuarioOn.getGastos();
		return gastoDAO.listaPorUser(usuarioOn.getId());
	}
	
	public void removerGasto(String nome) {
		this.usuarioOn.removerGasto(nome);
		gastoDAO.removerGasto(usuarioOn.getId(), nome);
	}
	
	public void alterarGasto(String nome, String opcao, String resposta) {
		this.usuarioOn.alterarGasto(nome, opcao, resposta);
		gastoDAO.alterarGasto(usuarioOn.getId(), nome, opcao, resposta);
	}
	
	public float getTotal(List<Gasto> gastos) {
		float total = 0;
		for(Gasto x : gastos) {
			total += x.getValor();
		}
		return total;
	}
	
	
	
	
}
