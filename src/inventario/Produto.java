package inventario;

import java.text.DecimalFormat;

public class Produto {
	
	//declarações de campo de instancia
	private int numItem;
	private String nomeProduto;
	private int unidadesEstoque;
	private double preco;
	private boolean ativo;
	
	
	//declarações de métodos
	
	//construtores
	public Produto() {
	}
	
	public Produto(int numItem, String nomeProduto, int unidadesEstoque, double preco) {
		this.numItem = numItem;
		this.nomeProduto = nomeProduto;
		this.unidadesEstoque = unidadesEstoque;
		this.preco = preco;
	}
	
	//getters e setters de cada campo
	public int getNumeroItem() {
		return this.numItem;
	}
	public void setNumero(int n) {
		this.numItem= n;
	}
	
	public String getNome() {
		return this.nomeProduto;
	}
	public void setNome(String s) {
		this.nomeProduto = s;
	}
	
	public int getUnidadesEstoque() {
		return this.unidadesEstoque;
	}
	public void setUnidades(int u) {
		this.unidadesEstoque = u;
	}
	
	public double getPreco() {
		return this.preco;
	}
	public void setPreco(double p) {
		this.preco = p;
	}
	
	public boolean getAtivo() {
		if (this.getUnidadesEstoque() <= 1 ) {
			this.ativo = false;
		} else {
			this.ativo = true;
		}
		return this.ativo;
	}
	public void setAtivo() {
		this.setUnidades(1);
	}
	
	//troca os valores do campo ativo de true/false para ativo/descontinuado
	public String tipoAtivo() {
		if (this.getAtivo()) {
			return("Ativo");
		}
		else  {
			return("Descontinuado");
		}
	}
	
	//adiciona a quantidade u ao campo unidadesEstoque
	public void adicionarEstoque(int u) {
		this.setUnidades(getUnidadesEstoque()+u);
	}
	
	//remove a quantidade u do campo unidadesEstoque
	public void removerEstoque(int u) {
		this.setUnidades(getUnidadesEstoque()-u);
	}
	
	//calcula o valor do estoque baseado no preco e na quantidade em estoque. 
	public Double valorEstoque(){
		 Double valor = this.getPreco() * this.getUnidadesEstoque();
		 //retorna com apenas duas casas decimais 
		 DecimalFormat df = new DecimalFormat("#,###.00");
		 df.format(valor);
		 return valor ;
	}
	
	//mostra detalhes sobre cada item, em String
	public String showString() {
				return    "Número do item:            " + this.getNumeroItem()+
				 		"\nNome:                      " + this.getNome()+
				 		"\nQuantidade em estoque:     " + this.getUnidadesEstoque()+
				 		"\nPreço:                     " + "R$" + this.getPreco()+
				 		"\nValor total do inventário: " +  this.valorEstoque()+
				 		"\nAtivo:                     " + this.tipoAtivo()+
				 		"\n--------------------------------------------\n";
		
	}
}


