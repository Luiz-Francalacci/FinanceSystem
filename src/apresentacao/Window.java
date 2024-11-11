package apresentacao;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
	
	private JPanel panel = new JPanel();
	private JPanel painelAddGasto = new JPanel();
	private JLabel infoNome = new JLabel("Nome:");
	private JTextField nome = new JTextField();
	private JLabel infoData = new JLabel("Data:");
	private JTextField data = new JTextField();
	private JLabel infoValor = new JLabel("Valor:");
	private JTextField valor = new JTextField();
	private JLabel infoCategoria = new JLabel("Categoria:");
	private JTextField categoria = new JTextField();
	private JLabel infoDescricao = new JLabel("Descricao:");
	private JTextField descricao = new JTextField();
	private JButton addGasto = new JButton("Add");
	private JScrollPane painelTabelaValores = new JScrollPane();
	private JTable tabelaGastos;
	private TabelaGastos tabela = new TabelaGastos();
	private JButton filtrarData = new JButton("Filtrar Data");
	private JButton filtrarCategoria = new JButton("Filtrar Ctgr");
	private JTextField filtro = new JTextField();
	private JLabel infoFiltro = new JLabel("Filtros:");
	private JButton limpar = new JButton("Limpar Filtros");
	private JLabel total = new JLabel("Total: 0");
	private JButton mudarDados = new JButton("Mudar Dados");
	private JButton removerGasto = new JButton("Remover Gasto");
	
	
	public Window() {
		
		setTitle("Sistema de Financas Pessoais");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100,100,1000,800);
		setContentPane(panel);
		panel.setLayout(null);
		painelAddGasto.setBounds(10,10, 390, 750);
		painelAddGasto.setBackground(Color.gray);
		painelAddGasto.setLayout(null);
		panel.add(painelAddGasto);
		infoNome.setBounds(30, 30, 50, 15);
		painelAddGasto.add(infoNome);
		nome.setBounds(30, 50, 200, 30);
		painelAddGasto.add(nome);
		
		infoData.setBounds(30, 90, 50, 15); //60
		painelAddGasto.add(infoData);
		data.setBounds(30, 110, 200, 30);
		painelAddGasto.add(data);
		
		infoValor.setBounds(30,150,50,15);
		painelAddGasto.add(infoValor);
		valor.setBounds(30,170, 200,30);
		painelAddGasto.add(valor);
		
		infoCategoria.setBounds(30,210, 70, 15);
		painelAddGasto.add(infoCategoria);
		categoria.setBounds(30, 230, 200, 30);
		painelAddGasto.add(categoria);
		
		infoDescricao.setBounds(30, 270, 70, 15);
		painelAddGasto.add(infoDescricao);
		descricao.setBounds(30, 290, 200, 30);
		painelAddGasto.add(descricao);
		
		addGasto.setBounds(30, 350, 200, 40);
		painelAddGasto.add(addGasto);
		
		painelTabelaValores.setBounds(450,10,500,400);
		panel.add(painelTabelaValores);
		tabelaGastos = new JTable(tabela);
		painelTabelaValores.setViewportView(tabelaGastos);
		addGasto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				sistema.adicionarGasto(new Gasto(nome.getText(), LocalDate.parse(data.getText(), formato),
						descricao.getText(), Float.parseFloat(valor.getText()),
						Categoria.valueOf(categoria.getText().toUpperCase())));
				tabela.setGasto(sistema.getGastos());
				tabela.atualiza();
				total.setText("Total: " + sistema.getTotal(sistema.getGastos()));
				
				nome.setText("");
				data.setText("");
				valor.setText("");
				categoria.setText("");
				descricao.setText("");
				
			}
		});
		
		infoFiltro.setBounds(30,430 , 70, 15);
		painelAddGasto.add(infoFiltro);
		filtro.setBounds(30,450,200,30);
		painelAddGasto.add(filtro);
		filtrarData.setBounds(30, 490,100,40);
		painelAddGasto.add(filtrarData);
		filtrarCategoria.setBounds(130, 490,100,40);
		painelAddGasto.add(filtrarCategoria);
		
		filtrarData.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String data[] = filtro.getText().split("/");
				tabela.setGasto(sistema.filtrarMes(Integer.parseInt(data[0]), 
						Integer.parseInt(data[1])));
				total.setText("Total: " + sistema.getTotal(sistema.filtrarMes(Integer.parseInt(data[0]), 
						Integer.parseInt(data[1]))));
				tabela.atualiza();
				filtro.setText("");
				
			}
		});
		
		filtrarCategoria.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String categoria = filtro.getText();
				tabela.setGasto(sistema.filtrarCategoria(Categoria.valueOf(categoria.toUpperCase())));
				total.setText("Total: " + sistema.getTotal(sistema.filtrarCategoria(Categoria.valueOf(categoria.toUpperCase()))));
				tabela.atualiza();
				filtro.setText("");
				
			}
		});
		
		limpar.setBounds(30, 550, 200, 40);
		painelAddGasto.add(limpar);
		
		limpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tabela.setGasto(sistema.getGastos());
				tabela.atualiza();
				total.setText("Total: " + sistema.getTotal(sistema.getGastos()));
				
			}
		});
		
		total.setBounds(450 ,410 , 100, 15);
		panel.add(total);
		
		mudarDados.setBounds(445,430,200,40);
		panel.add(mudarDados);
		
		mudarDados.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizaDados(sistema);
				
				tabela.atualiza();
				
				
			}
		});
		removerGasto.setBounds(445,500,200,40);
		panel.add(removerGasto);
		removerGasto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removeGasto(sistema);
				tabela.atualiza();
				
			}
		});
		
		
		
		
		
		
	}
	
	private void loginDialog(SistemaFinancas sistemaF) {
		JDialog login = new JDialog(this,"Login",true);
		login.setSize(300,150);
		login.setLayout(new GridLayout(3,2));
		login.setLocationRelativeTo(this);
		JLabel userText = new JLabel("Usuario");
		JTextField userField = new JTextField();
		JLabel senhaText = new JLabel("Senha");
		JTextField senhaField = new JTextField();
		JButton loginButton = new JButton("Entrar");
		JButton cadastroButton = new JButton("Cadastrar");
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(sistemaF.logarUsuario(userField.getText(), senhaField.getText())) {
					JOptionPane.showMessageDialog(login, "Login Aprovado");
					login.dispose();
				}else {
					JOptionPane.showMessageDialog(login, "Usuario ou Senha Errados");
				}
				
				
			
			}
		});
		
		cadastroButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login.dispose();
				cadastroDialog(sistemaF);
				
			}
		});
		
		login.add(userText);
		login.add(userField);
		login.add(senhaText);
		login.add(senhaField);
		login.add(loginButton);
		login.add(cadastroButton);
		login.setVisible(true);
		
	}
	
	private void cadastroDialog(SistemaFinancas sistemaF) {
		JDialog login = new JDialog(this,"Cadastro",true);
		login.setSize(300,150);
		login.setLayout(new GridLayout(3,2));
		login.setLocationRelativeTo(this);
		JLabel userText = new JLabel("Usuario");
		JTextField userField = new JTextField();
		JLabel senhaText = new JLabel("Senha");
		JTextField senhaField = new JTextField();
		JButton loginButton = new JButton("Cadastrar");
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				sistemaF.cadastrarUsuario(new Usuario(userText.getText(),senhaText.getText()));
				JOptionPane.showMessageDialog(login, "Cadastrado com Sucesso");
				login.dispose();
				loginDialog(sistemaF);
				
			
			}
		});
		login.add(userText);
		login.add(userField);
		login.add(senhaText);
		login.add(senhaField);
		login.add(loginButton);
		login.setVisible(true);
		
	}
	
	private void atualizaDados(SistemaFinancas sistemaF) {
		JDialog tela = new JDialog(this,"Atualizar", true);
		tela.setSize(800,400);
		tela.setLayout(new GridLayout(7,3));
		tela.setLocationRelativeTo(this);
		JLabel nomeTxt = new JLabel("Nome: ");
		JLabel valorTxt = new JLabel("Valor: ");
		JLabel dataTxt = new JLabel("Data: ");
		JLabel categoriaTxt = new JLabel("Categoria: ");
		JLabel descricaoTxt = new JLabel("Descricao: ");
		JTextField nomeField = new JTextField();
		JTextField valorField = new JTextField();
		JTextField dataField = new JTextField();
		JTextField categoriaField = new JTextField();
		JTextField descricaoField = new JTextField();
		JLabel gasto = new JLabel("Digite o nome do Gasto a Alterar");
		JTextField gastoField= new JTextField();
		JButton atualiza = new JButton("Atualiza");
		
		
		atualiza.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!nomeField.getText().isEmpty()) {
					sistemaF.alterarGasto(gastoField.getText(), "nome",nomeField.getText());
				}
				
				if(!valorField.getText().isEmpty()) {
					sistemaF.alterarGasto(gastoField.getText(), "valor",valorField.getText());
				}
				
				if(!dataField.getText().isEmpty()) {
					sistemaF.alterarGasto(gastoField.getText(), "data",dataField.getText());
				}
				
				if(!categoriaField.getText().isEmpty()) {
					sistemaF.alterarGasto(gastoField.getText(), "categoria",categoriaField.getText());
				}
				
				if(!descricaoField.getText().isEmpty()) {
					sistemaF.alterarGasto(gastoField.getText(), "descricao",descricaoField.getText());
				}
				
				
				tela.dispose();
			}
		});
		
		tela.add(gasto);
		tela.add(gastoField);
		tela.add(nomeTxt);
		tela.add(nomeField);
		tela.add(valorTxt);
		tela.add(valorField);
		tela.add(dataTxt);
		tela.add(dataField);
		tela.add(categoriaTxt);
		tela.add(categoriaField);
		tela.add(descricaoTxt);
		tela.add(descricaoField);
		tela.add(atualiza);
		tela.setVisible(true);
		
		
		
	}
	
	private void removeGasto(SistemaFinancas sistemaF) {
		JDialog tela = new JDialog(this,"Remover", true);
		tela.setSize(300,150);
		tela.setLayout(new GridLayout(3,2));
		tela.setLocationRelativeTo(this);
		JLabel nomeLabel = new JLabel("Digite o nome do Gasto a Remover");
		JTextField nomeField = new JTextField();
		JButton remover = new JButton("Remover");
		remover.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sistemaF.removerGasto(nomeField.getText());
				tela.dispose();
				
			}
		});
		tela.add(nomeLabel);
		tela.add(nomeField);
		tela.add(remover);
		tela.setVisible(true);
		
		
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		Window s = new Window();
		s.getSistema().cadastrarUsuario(new Usuario("1234", "1234"));
		s.getSistema().logarUsuario("1234", "1234");
		
		
		s.setVisible(true);
	}
	
	

}
