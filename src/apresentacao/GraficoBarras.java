package apresentacao;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import dados.Categoria;
import negocio.SistemaFinancas;

public class GraficoBarras extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public GraficoBarras(SistemaFinancas sistema) {
		
		
		JFreeChart barChart = ChartFactory.createBarChart("Grafico", "Categoria", "Gasto", 
				createDataset(sistema), PlotOrientation.VERTICAL, true, true, false);
		
		
		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500,270));
		setContentPane(chartPanel);
		
	}
	
	private CategoryDataset createDataset(SistemaFinancas sistema) {
		//row
		final String edu = "Educacao";
		final String lazer = "Lazer";
		final String comida = "Comida";
		final String saude = "Saude";
		final String transporte = "Transporte";
		final String outros = "Outros";
		
		//column
		final String gasto = "Gasto";
		
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		
		double numEdu = 0 +sistema.getTotal(sistema.filtrarCategoria(Categoria.EDUCACAO));
		double numLazer = 0 +sistema.getTotal(sistema.filtrarCategoria(Categoria.LAZER));
		double numComida = 0 + sistema.getTotal(sistema.filtrarCategoria(Categoria.COMIDA));
		double numSaude = 0 + sistema.getTotal(sistema.filtrarCategoria(Categoria.SAUDE));
		double numTransporte = 0 + sistema.getTotal(sistema.filtrarCategoria(Categoria.TRANSPORTE));
		double numOutros = 0 + sistema.getTotal(sistema.filtrarCategoria(Categoria.OUTROS));
		
		dataset.addValue(numEdu, edu, gasto);
		dataset.addValue(numLazer, lazer, gasto);
		dataset.addValue(numComida, comida, gasto);
		dataset.addValue(numSaude, saude, gasto);
		dataset.addValue(numTransporte, transporte, gasto);
		dataset.addValue(numOutros, outros, gasto);
		
		return dataset;
		
		
		
	}
	
	
}
