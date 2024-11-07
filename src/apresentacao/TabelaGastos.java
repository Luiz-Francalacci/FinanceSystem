package apresentacao;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dados.Gasto;

public class TabelaGastos extends AbstractTableModel{

	private String[] colunas = {"Nome", "Data", "Valor", "Categoria", "Descricao"};
	private List<Gasto> lista;
	
	public void setGasto(List<Gasto> g) {
		this.lista = g;
	}
	
	public String getColumnName(int column) {
		return colunas[column];
	}
	
	
	@Override
	public int getRowCount() {
		if(this.lista == null) {
			return 1;
		}else {
			return this.lista.size();
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(this.lista == null) {
			return "---";
		}else {
			Gasto g = this.lista.get(rowIndex);
			
			if(this.lista != null) {
				switch(columnIndex) {
				case 0:
					return g.getNome();
				case 1:
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					return g.getData().format(formato);
				case 2:
					return g.getValor();
				case 3:
					return g.getCategoria();
				case 4:
					return g.getDescricao();
				}
			}else {
				return "---";
			}
		}
		
		return null;
	}

	public void atualiza() {
		fireTableStructureChanged();
	}
	
	
	
	
	
	
	
}
