package trabalho4poo.ui;

import trabalho4poo.ui.*;
import trabalho4poo.negocio.*;
import trabalho4poo.dados.*;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class UIProjeto {

	private static Scanner scn;

	public UIProjeto() {
		scn = new Scanner(System.in);
	}

	
	public void add() {
		System.out.println("================================================================");
		System.out.print("\n--- Criando um novo projeto: ---");
		System.out.print("\nNome do projeto: ");
		scn.nextLine();
		String nmProjeto = scn.nextLine();
		
		String proprietario;
		do {
			System.out.print("\nNome do proprietario: ");
			proprietario = scn.nextLine();
		}while(!Sistema.getInstance().existeNomeUsuario(proprietario));
		
		
		System.out.print("\nO projeto é Publico ou Privado? ");
		System.out.println("OBS.: Escreva exatamente 'Publico'/'Privado'");
		String privacidade = scn.next();

		Usuario usuarioProprietario = Sistema.getInstance().buscarUsuarioPorLogin(proprietario);
		
	    Projeto.getInstance(nmProjeto, usuarioProprietario, privacidade);
	}

	public void addColaborador() {
		System.out.println();
		System.out.println("----- Adicionar colaborador: -----");
		System.out.println("\n ---- Usuarios: ----");
		System.out.println("|COD.\t |NOME\t");

		for (int i = 0; i < Sistema.getInstance().getUsuarios().size(); i++) {
			if (Sistema.getInstance().getUsuarios().get(i) != null) {
				System.out.printf("%-8s %-20s%n", Sistema.getInstance().getUsuarios().get(i).getCdUsuario(),
						Sistema.getInstance().getUsuarios().get(i).getNmUsuario());
			}
		}

		System.out.print("\nNome do colaborador: ");
		scn.nextLine();
		String colaborador = scn.nextLine();
		do {
			System.out.print("\nNome do colaborador: ");
			colaborador = scn.nextLine();
		}while(!Sistema.getInstance().existeNomeUsuario(colaborador));
		
		Usuario usuarioColaborador = Sistema.getInstance().buscarUsuarioPorLogin(colaborador);

		
		boolean adicionaColaborador = Sistema.getInstance().adicionarColaborador(usuarioColaborador);
		    if (adicionaColaborador != false) {
		    	System.out.println("Colaborador adicionado com sucesso.");
		    }else {
		    	System.out.println("Falha ao adicionar colaborador.");
		    }
	}

	public void fazer() {
		System.out.println("=========================================================================");
		System.out.println("=== CONFIGURADOR DE LAYOUT DINÂMICO ===");

		// 1. Definição da quantidade de Divs empilhadas
		System.out.print("\nQuantas Divs deseja criar na coluna de Divs? ");
		int qtdDivs = scn.nextInt();

		// 2. Pergunta se o usuário deseja incluir formulários
		System.out.print("\nDeseja incluir coluna de Formulários no layout? (1 - Sim | 2 - Não): ");
		int incluirForms = scn.nextInt();

		int qtdForms = 0;
		int posicao = 1; // Padrão: Divs na esquerda

		if (incluirForms == 1) {
		    System.out.print("Quantos Formulários deseja criar na coluna de Formulários? ");
		    qtdForms = scn.nextInt();
		    
		    System.out.println("\nQual a disposição no container principal?");
		    System.out.println("1 - Coluna de Divs na Esquerda | Formulários na Direita");
		    System.out.println("2 - Formulários na Esquerda | Coluna de Divs na Direita");
		    System.out.print("Opção: ");
		    posicao = scn.nextInt();
		}

		// 3. Definição da quantidade de Colunas Principais no Flexbox
		// Se houver formulários, são 2 colunas ativas no container; caso contrário, 1 coluna.
		int colunasAtivas = (incluirForms == 1 && qtdForms > 0) ? 2 : 1;

		System.out.print("\nQual a altura total do container (em px)? ");
		int alturaPx = scn.nextInt();
		scn.nextLine(); // Consome a quebra de linha pendente

		// --- CONSTRUÇÃO DA ESTRUTURA HTML ---

		// Container Principal
		Div containerPrincipal = new Div("main-container", "flex-container");
		containerPrincipal.adicionarEstilo("display", "flex");
		containerPrincipal.adicionarEstilo("gap", "20px");
		containerPrincipal.adicionarEstilo("width", "100%");
		containerPrincipal.adicionarEstilo("height", alturaPx + "px");

		// Cálculo dinâmico da largura de cada coluna com base no número de colunas ativas
		int larguraPorcentagemColuna = 100 / colunasAtivas;

		// --- COLUNA DE DIVS ---
		Div colunaDivs = new Div("coluna-divs", "coluna");
		colunaDivs.adicionarEstilo("display", "flex");
		colunaDivs.adicionarEstilo("flex-direction", "column");
		colunaDivs.adicionarEstilo("width", larguraPorcentagemColuna + "%");
		colunaDivs.adicionarEstilo("height", "100%");
		colunaDivs.adicionarEstilo("gap", "10px");

		// Calcula a altura proporcional para cada Div criada
		int alturaPorcentagemDiv = qtdDivs > 0 ? 100 / qtdDivs : 100;

		for (int i = 1; i <= qtdDivs; i++) {
		    Div divFilha = new Div("div-filha-" + i, "caixa-filha");
		    divFilha.adicionarEstilo("height", alturaPorcentagemDiv + "%");
		    divFilha.adicionarEstilo("background-color", "#e0e0e0");
		    divFilha.adicionarFilho(
		            new Paragrafo("p-" + i, "texto", "Conteúdo da Div " + i)
		    );
		    colunaDivs.adicionarFilho(divFilha);
		}

		// --- COLUNA DE FORMULÁRIOS (Se ativada) ---
		Div colunaForms = null;

		if (incluirForms == 1 && qtdForms > 0) {
		    colunaForms = new Div("coluna-forms", "coluna");
		    colunaForms.adicionarEstilo("display", "flex");
		    colunaForms.adicionarEstilo("flex-direction", "column");
		    colunaForms.adicionarEstilo("width", larguraPorcentagemColuna + "%");
		    colunaForms.adicionarEstilo("height", "100%");
		    colunaForms.adicionarEstilo("gap", "15px");

		    for (int f = 1; f <= qtdForms; f++) {
		        System.out.println("\n--- CONFIGURAÇÃO DO FORMULÁRIO " + f + " ---");
		        System.out.print("Quantos inputs este formulário terá? ");
		        int qtdInputs = scn.nextInt();
		        scn.nextLine(); // Consome o \n

		        Formulario form = new Formulario("form-" + f, "formulario-estilizado", "/enviar", "POST");
		        form.adicionarEstilo("display", "flex");
		        form.adicionarEstilo("flex-direction", "column");
		        form.adicionarEstilo("gap", "10px");
		        form.adicionarEstilo("background-color", "#f9f9f9");
		        form.adicionarEstilo("padding", "15px");

		        for (int inp = 1; inp <= qtdInputs; inp++) {
		            System.out.print("Texto do placeholder para o Input " + inp + ": ");
		            String placeholder = scn.nextLine();

		            Input input = new Input("input-" + f + "-" + inp, "campo", "text",
		                    "campo_" + f + "_" + inp, placeholder);
		            form.adicionarFilho(input);
		        }
		        colunaForms.adicionarFilho(form);
		    }
		}

		// --- MONTAGEM DO LAYOUT PRINCIPAL ---
		if (colunaForms != null) {
		    if (posicao == 1) {
		        containerPrincipal.adicionarFilho(colunaDivs);
		        containerPrincipal.adicionarFilho(colunaForms);
		    } else {
		        containerPrincipal.adicionarFilho(colunaForms);
		        containerPrincipal.adicionarFilho(colunaDivs);
		    }
		} else {
		    // Apenas a coluna de Divs ocupando 100% da largura
		    containerPrincipal.adicionarFilho(colunaDivs);
		}

		// --- GERAÇÃO E ARMAZENAMENTO DA STRING ---
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- HTML Gerado Dinamicamente -->\n");
		sb.append(containerPrincipal.renderizar(0));

		String htmlGerado = sb.toString();

		System.out.println("\n=== HTML GERADO EM STRING COM SUCESSO ===");
		System.out.println(htmlGerado);

		// Ajuste no índice para pegar o último elemento válido da lista (index = tamanho - 1)
		

		// Exemplo de uso da String criada:
		System.out.println("\n=== HTML GERADO EM STRING COM SUCESSO ===");
		System.out.println(htmlGerado);
		Sistema.getInstance().getProjetos().get(Projeto.quantProjetos).setProjetoCodigo(htmlGerado);
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
		int codigo = scn.nextInt();

		Projeto projetoCod = Sistema.getInstance().buscarProjetoPorCodigo(codigo);

		System.out.println("\n--- Projeto do codigo " + codigo + " : ---");
		System.out.println("|COD.\t |NOME\t      |PROPRIETARIO(A)\t      ");

		if (projetoCod != null) {
			System.out.printf("%-8s %-20s %-16s%n", codigo, projetoCod.getNmProjeto(),
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
		
		String proprietario;
		do {
			System.out.print("\nNome do proprietario: ");
			proprietario = scn.nextLine();
		}while(!Sistema.getInstance().existeNomeUsuario(proprietario));
		
		Usuario usuarioProprietario = Sistema.getInstance().buscarUsuarioPorLogin(proprietario);

		boolean excluiProjeto = Sistema.getInstance().excluirProjeto(codigoProjeto, usuarioProprietario);
		if (excluiProjeto != false) {
			System.out.println("Projeto excluido com sucesso!");
		}else {
			System.out.println("Falha ao excluir projeto.");
		}
	}


}
