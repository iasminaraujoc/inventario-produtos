package inventario;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestarProduto {

	public static void main(String[] args) {
		//cria um objeto Scanner para leitura do teclado
		Scanner leitor = new Scanner(System.in);
		int tamanhoMax = obterNumProdutos(leitor);

		//exibe uma mensagem caso o usuário forneça 0
		if (tamanhoMax == 0) {
			System.out.println("Não há produtos para cadastrar!");
		} 
		else {
			Produto[] produtos = new Produto[tamanhoMax];
			
			leitor.nextLine();
			
			TestarProduto.adicionarAoInventario(produtos, leitor);
			do {
				TestarProduto.executarEscolhaMenu(produtos, leitor);
			}while(obterOpcaoMenu(leitor)!= 0);
				
		}
		//fecha o objeto Scanner e encerra o serviço
		leitor.close();
		System.out.print("Serviço finalizado! Obrigado!");
		
	}
	
	//solicita ao usuário um número de produtos para adicionar
	public static int obterNumProdutos(Scanner leitor) {
		int tamanhoMax=-1;
				do {
					try {
						System.out.print("Insira o número de produtos que gostaria de adicionar "
								+ "\nInsira 0 (zero) se não quiser adicionar produtos: ");
						tamanhoMax = leitor.nextInt();
						if (tamanhoMax < 0) {
							System.out.println("Valor negativo inserido.");
							continue;
						}
					} catch(InputMismatchException e) {
						System.out.println("Tipo incorreto de dados inserido!");
						leitor.nextLine();
					}
				} while(tamanhoMax != 0);
				return tamanhoMax;
	}
	
	//obtém as informações para cadastro dos produtos na lista
	public static void adicionarAoInventario(Produto[] produtos, Scanner leitor) {
		for ( int i = 0; i <produtos.length; i++ ) {
			System.out.print("Digite o número do produto:");
			int numTemp = leitor.nextInt();
			
			System.out.print("Digite o nome do produto:");
			String nomeTemp = leitor.next();
			leitor.nextLine();
			
			System.out.print("Digite a quantidade em estoque do produto:");
			int quantidadeTemp = leitor.nextInt();
			leitor.nextLine();
			
			System.out.print("Digite o preço do produto:");
			String precoTemp = leitor.next();
			double precoTempConv = Double.parseDouble(precoTemp);
			
			produtos[i] = new Produto(numTemp, nomeTemp, quantidadeTemp, precoTempConv);
		}
	}
	
	//mostra atividades que podem ser realizadas e exige uma escolha
	public static int obterOpcaoMenu(Scanner leitor){
		System.out.println("\tOPÇÕES");
		System.out.println("1 - Exibir Inventário"
				+ "\n2 - Adicionar Estoque"
				+ "\n3 - Deduzir Estoque"
				+ "\n4 - Descontinuar Produto"
				+ "\n0 - Sair" 
				+ "\nInsira uma opção de menu: " );
		int opcao  = leitor.nextInt();
		return opcao;
	}
	
	//direciona a escolha do menu para determinada ação
	public static void executarEscolhaMenu(Produto[] produtos, Scanner leitor) {
		int menuChoice = TestarProduto.obterOpcaoMenu(leitor);
		switch(menuChoice) {
		case 1:
			mostrarInventario(produtos);
			break;
		case 2:
			adicionarEstoque(produtos, leitor);
			break;
		case 3:
			deduzirEstoque(produtos, leitor);
			break;
		case 4:
			descontinuarInventario(produtos, leitor);
			break;
		default:
			System.out.println("Opção inválida.");
		}
	}
	
	//exibe a lista de produtos na tela
	public static void mostrarInventario(Produto[] produtos){
		  for (Produto item: produtos) {
				 String sobreItens = item.showString();
				 System.out.print(sobreItens);
			    }
	}
	
	//solicita ao usuário que forneça o numero do produto a ser modificado, baseado no array criado
	public static int obterIndiceProduto(Produto[] produtos, Scanner leitor) {
		int escolhaIndice = -1;
			try {
				for (int i = 0; i<produtos.length;i++ ) {
					System.out.println("Índice de "  + produtos[i].getNome()+ " - " + i);
				}
				System.out.print("Digite o índice do produto que quer atualizar: ");
				escolhaIndice = leitor.nextInt();
			}
			catch(ArrayIndexOutOfBoundsException e){
				System.out.print("Opção não encontrada no menu. Digite outro número.");
				leitor.next();
			}
		return escolhaIndice;
	}
	
	//adiciona ao estoque a quantidade dada pelo usuário
	public static void adicionarEstoque(Produto[] produtos, Scanner leitor) {
		int escolhaIndice;
		int atualizarValor = -1;
		escolhaIndice = obterIndiceProduto(produtos, leitor);
		System.out.print("Quantos produtos quer adicionar? ");
		atualizarValor = leitor.nextInt();
		produtos[escolhaIndice].adicionarEstoque(atualizarValor);
		System.out.println("Adicionado com sucesso.");
	}
	
	//deduz (remove) do estoque a quantidade dada pelo usuário
	public static void deduzirEstoque(Produto[] produtos, Scanner leitor) {
		int escolhaIndice;
		int atualizarValor= -1;
		escolhaIndice = obterIndiceProduto(produtos, leitor);
		System.out.print("Quantos produtos quer deduzir? ");
		atualizarValor = leitor.nextInt();
		produtos[escolhaIndice].removerEstoque(atualizarValor);
		System.out.println("Deduzido com sucesso.");
	}
	
	//descontinua (reduz a 1) o estoque
	public static void descontinuarInventario(Produto[] produtos, Scanner leitor) {
		int escolhaIndice = obterIndiceProduto(produtos, leitor);
		produtos[escolhaIndice].setAtivo();
	}
	
	

}
