package apresentacao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dados.Categoria;
import dados.Gasto;
import dados.Usuario;
import negocio.SistemaFinancas;

public class Window extends JFrame{
	
	private SistemaFinancas sistema = new SistemaFinancas();
	
	public SistemaFinancas getSistema() {
		return this.sistema;
	}
	
	public Window() {
		this.setTitle("Sistema de Financa Pessoais");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1200,800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		JPanel gasto = new JPanel();
		gasto.setBackground(Color.gray);
		this.add(gasto);
		
		JTextField nome = new JTextField();
		nome.setText("nome");
		
		JTextField data = new JTextField();
		data.setText("data");
		
		JTextField valor = new JTextField();
		valor.setText("valor");
		
		JTextField categoria = new JTextField();
		categoria.setText("Categoria");
		
		JTextArea descricao = new JTextArea();
		descricao.setText("descricao");
		
		JButton addGasto = new JButton("Adicionar");
		addGasto.addActionListener(new ActionListener() {
			@Override
			 
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				sistema.adicionarGasto(new Gasto(nome.getText(), LocalDate.parse(data.getText(), formato),
						descricao.getText(), Float.parseFloat(valor.getText()),
						Categoria.valueOf(categoria.getText().toUpperCase())));
				nome.setText("nome");
				data.setText("data");
				valor.setText("valor");
				categoria.setText("Categoria");
				descricao.setText("descricao");
				
				
			}
		});
		
		gasto.add(nome);
		gasto.add(data);
		gasto.add(valor);
		gasto.add(categoria);
		gasto.add(descricao);
		gasto.add(addGasto);
		
		
		
		
		
		
		
		
	}
	
	public static void main(String[] args) {
		Window s = new Window();
		s.getSistema().cadastrarUsuario(new Usuario("1234", "1234"));
		s.getSistema().logarUsuario("1234", "1234");
		
		s.setVisible(true);
	}
	
	

}
