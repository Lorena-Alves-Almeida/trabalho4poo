package trabalho4poo.ui;

import trabalho4poo.ui.*;
import trabalho4poo.negocio.*;
import trabalho4poo.dados.*;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class UIProjeto {

	static Scanner scn;

	public UIProjeto() {
		scn = new Scanner(System.in);
	}

	public void add() {
		System.out.println("================================================================");
		System.out.print("\n--- Criando um novo projeto: ---");
		System.out.print("\nNome do projeto: ");
		scn.nextLine();
		String nmProjeto = scn.nextLine();

		System.out.print("\nNome do proprietario: ");
		String proprietario = scn.nextLine();

		System.out.print("\nO projeto e Publico ou Privado? ");
		System.out.println("OBS.: Escreva exatamente 'Publico'/'Privado'");
		String privacidade = scn.next();

		String criaProjeto = Projeto.getInstance(nmProjeto, proprietario, privacidade, null, null);
		System.out.println(criaProjeto);
	}

	public void addColaborador() {
		System.out.println();
		System.out.println("----- Adicionar colaborador: -----");
		System.out.println("\n ---- Usuarios: ----");
		System.out.println("|COD.\t |NOME\t");

		for (int i = 0; i < Sistema.getUsuarios().size(); i++) {
			if (sistema.getUsuarios().get(i) != null) {
				System.out.printf("%-8s %-20s%n", sistema.getUsuarios().get(i).getCdUsuario(),
						sistema.getUsuarios().get(i).getNmUsuario());
			}
		}

		System.out.print("\nNome do colaborador: ");
		scn.nextLine();
		String colaborador = scn.nextLine();

		String adicionaColaborador = sistema.addColaborador(colaborador);
		System.out.println(adicionaColaborador);
	}

	public void listar(List <Projeto> listaCopia) {
		System.out.println("\n--- Lista de produtos: ---");
		System.out.println("|COD.\t |NOME\t             |PROPRIETARIO\t      ");
		for (int i = 0; i < listaCopia.size(); i++) {
			if (listaCopia.get(i) != null) {
				System.out.printf("%-8s %-20s %-16s%n", listaCopia.get(i).getCdProjeto(),
						listaCopia.get(i).getNmProjeto(), listaCopia.get(i).getProprietario());
			}
		}
	}
	
	public void projetoPorCodigo() {
		System.out.println();
		System.out.println("================================================================");
		System.out.println("--- Procurar projeto por codigo: ---");
		System.out.println("OBS.: o projeto so sera mostrado se for publico.");
		System.out.print("\nInsira o codigo da venda que voce esta procurando: ");
		int codProjeto = scn.nextInt();

		Projeto projetoCod = sistema.projetoPorCod(codProjeto);

		System.out.println("\n--- Projeto do codigo " + codProjeto + " : ---");
		System.out.println("|COD.\t |NOME\t      |PROPRIETARIO(A)\t      ");

		if (projetoCod != null) {
			System.out.printf("%-8s %-20s %-16s%n", codProjeto, projetoCod.getNmProjeto(),
					projetoCod.getProprietario());

		} else {
			System.out.println("Codigo nao encontrado ou projeto Privado.");
		}
	}
	
	public void excluir() {
		System.out.println("===============================================================");
		System.out.println("\nAVISO: Apenas o proprietario do projeto pode excui-lo.");
		System.out.print("\nCodigo do projeto: ");
		int codigoProjeto = scn.nextInt();
		System.out.print("\nNome do proprietario: ");
		String nomeProprietario = scn.next();
		System.out.print("\nSenha do proprietario: ");
		String senhaProprietario = scn.next();

		String excluiProjeto = Projeto.excluir(codigoProjeto, nomeProprietario, senhaProprietario);
		System.out.println(excluiProjeto);
	}
//	public void add() {
//		System.out.print("Nome: ");
//		String nome = scn.next();
//		if (Sistema.getInstance().existeNomeProduto(nome)) {
//			System.out.println("Nome já cadastrado!");
//			return;
//		}
//
//		System.out.print("Quantidade: ");
//		int quantidade = scn.nextInt();
//		System.out.print("Preço: ");
//		double preco = scn.nextDouble();
//
//		largura.listarM(Sistema.getInstance().listarMarcas());
//		System.out.print("Marca do produto (código): ");
//		int codMarca = scn.nextInt();
//		Marca marca = Sistema.getInstance().buscarMarcaPorCodigo(codMarca);
//		if (marca == null) {
//			System.out.println("Marca não encontrada.");
//			return;
//		}
//
//		Produto novoProduto = Produto.getInstance(nome, marca, quantidade,
//				preco);
//		if (novoProduto != null
//				&& Sistema.getInstance().addProduto(novoProduto))
//			System.out.println("Produto inserido com sucesso!");
//		else
//			System.out.println("Falha em inserir produto.");
//	}

	

}
