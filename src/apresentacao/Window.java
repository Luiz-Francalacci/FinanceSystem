package apresentacao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private JButton filtrarCategoria = new JButton("Filtrar Data");
	private JTextField filtro = new JTextField();
	private JLabel infoFiltro = new JLabel("Filtros:");
	
	
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
		
		
		
		
		
		
		
	}
	
	public static void main(String[] args) {
		Window s = new Window();
		s.getSistema().cadastrarUsuario(new Usuario("1234", "1234"));
		s.getSistema().logarUsuario("1234", "1234");
		
		s.setVisible(true);
	}
	
	

}
