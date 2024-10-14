package dados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Gasto {

	private String nome;
	private LocalDate data;
	private float valor;
	private String descricao;
	private Categoria categoria;
	
	public Gasto(String nome, LocalDate data, String descricao, float valor, Categoria categoria) {
		setCategoria(categoria);
		setData(data);
		setDescricao(descricao);
		setNome(nome);
		setValor(valor);
	}
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return String.format("Nome: %s \nData: %s \nDescrição: %s \nValor: R$%.2f \nCategoria: %s",this.nome, this.data.format(formato), this.descricao,
							this.valor, this.categoria);
	}
	
	
	
	
	
	
	
}
