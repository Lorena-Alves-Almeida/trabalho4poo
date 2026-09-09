package trabalho4poo;

import java.util.List;
import java.util.Scanner;

import trabalho4poo.negocio.Usuario;
import trabalho4poo.ui.*;
import trabalho4poo.negocio.*;
import trabalho4poo.dados.*;

public class Main {

	static Scanner scn = new Scanner(System.in);

	static Sistema sistema = Sistema.getInstance();
	
	private static UIUsuario uiusuario = new UIUsuario();
	private static UIProjeto uiprojeto = new UIProjeto();

	public static void main(String[] args) {

		int logar;
		do {

			int escolha = 0;
			do {
				System.out.println("\n================================================================");
				System.out.println("======== BEM VINDO AO SISTEMA! ========");
				System.out.println("\n===== ENTRAR: =====");
				System.out.println();
				System.out.println("0-> SAIR.");
				System.out.println("1-> Login.");
				System.out.println("2-> Cadastrar.");

				logar = scn.nextInt();

				switch (logar) {
				case 0:
					System.out.println("saindo do sistema...");
					escolha = 0;
					break;

				case 1:
					System.out.println("\n================================================================");
					System.out.println("----- LOGIN: ----");

					System.out.print("\nNome do usuário: ");
					scn.nextLine();
					String nomeUsuario1 = scn.nextLine();

					System.out.print("\nSenha: ");
					String senha1 = scn.nextLine();

					Usuario usuarioLogado = sistema.autenticarLogin(nomeUsuario1, senha1);
					if (usuarioLogado != null) {
						System.out.println("Perfil encontrado!");
					} else {
						System.out.println("Perfil nao encontrado ou inexistente");
					}
					menuPrincipal();
					break;

				case 2:
					cadastrarUsuario();
					
					break;
				}

				switch (escolha) {
				case 0:
					System.out.println("saindo do sistema...");
					break;

				case 1:

					int operacao;
					do {
						System.out.println("");
						System.out.println("================================================================");
						System.out.println("   ----- Menu de operações: -----");
						System.out.println("0-> SAIR.");
						System.out.println("1-> Listar usuarios cadastrados.");
						System.out.println("2-> Criar um projeto.");
						System.out.println("3-> Listar projetos.");
						System.out.println("4-> Pesquisar um projeto por meio de seu código.");
						System.out.println("5-> Excluir projeto.");

						operacao = scn.nextInt();
						switch (operacao) {

						case 0:
							System.out.println("saindo do sistema...");
							break;

						case 1:
							listarUsuarios();
							
							break;

						case 2:
//							System.out.println();
//							System.out.println("================================================================");
//							System.out.print("\n--- Criando um novo projeto: ---");
//							System.out.print("\nNome do projeto: ");
//							scn.nextLine();
//							String nmProjeto = scn.nextLine();
//
//							System.out.print("\nNome do proprietario: ");
//							String proprietario = scn.nextLine();
//							
//							System.out.print("\nO projeto e Publico ou Privado? ");
//							System.out.println("OBS.: Escreva exatamente 'Publico'/'Privado'");
//							String privacidade = scn.next();
//
//							String criaProjeto = sistema.criarProjeto(nmProjeto, proprietario, privacidade);
//							System.out.println(criaProjeto);
							uiprojeto.add();
							int resposta;
							do {
								System.out.println("\n");
								System.out.println("================================================================");
								System.out.println("---------- Fazendo o projeto: ----------");
								System.out.println("0-> SAIR");
								System.out.println("1-> Adicionar colaborador.");
								System.out.println("2-> Fazer o projeto.");

								resposta = scn.nextInt();
								switch (resposta) {

								case 0:
									break;

								case 1:
//									System.out.println();
//									System.out.println("----- Adicionar colaborador: -----");
//									System.out.println("\n ---- Usuarios: ----");
//									System.out.println("|COD.\t |NOME\t");
//									for (int i = 0; i < sistema.getUsuarios().size(); i++) {
//										if (sistema.getUsuarios().get(i) != null) {
//											System.out.printf("%-8s %-20s%n", sistema.getUsuarios().get(i).getCdUsuario(),
//													sistema.getUsuarios().get(i).getNmUsuario());
//										}
//									}
//                                    
//									System.out.print("\nNome do colaborador: ");
//									scn.nextLine();
//									String colaborador = scn.nextLine();
//
//									String adicionaColaborador = sistema.addColaborador(colaborador);
//									System.out.println(adicionaColaborador);
									uiprojeto.addColaborador();
									
									break;

								case 2:
									System.out.println("=== CONFIGURADOR DE LAYOUT DINÂMICO ===");

									// 1. Definição da quantidade de Colunas Principais
									System.out.print("\nEm quantas colunas deseja dividir o container principal? ");
									int qtdColunas = scn.nextInt();

									// 2. Definição da quantidade de Divs
									System.out.print("Quantas Divs deseja criar empilhadas na coluna de Divs? ");
									int qtdDivs = scn.nextInt();

									// 3. Definição dos Formulários e seus Inputs
									System.out.print("Quantos Formulários deseja criar na coluna de Formulários? ");
									int qtdForms = scn.nextInt();
									scn.nextLine(); // Consome a quebra de linha do scanner

									// 4. Posicionamento e Altura
									System.out.println("\nQual a disposição no container principal?");
									System.out.println("1 - Coluna de Divs na Esquerda | Formulários na Direita");
									System.out.println("2 - Formulários na Esquerda | Coluna de Divs na Direita");
									System.out.print("Opção: ");
									int posicao = scn.nextInt();

									System.out.print("\nQual a altura total do container (em px)? ");
									int alturaPx = scn.nextInt();
									scn.nextLine();

									// --- CONSTRUÇÃO DA ESTRUTURA HTML ---

									// Container Principal
									Div containerPrincipal = new Div("main-container", "flex-container");
									containerPrincipal.adicionarEstilo("display", "flex");
									containerPrincipal.adicionarEstilo("gap", "20px");
									containerPrincipal.adicionarEstilo("width", "100%");
									containerPrincipal.adicionarEstilo("height", alturaPx + "px");

									// Cálculo dinâmico da largura de cada coluna com base na escolha do usuário
									int larguraPorcentagemColuna = qtdColunas > 0 ? 100 / qtdColunas : 100;

									// Coluna de Divs
									Div colunaDivs = new Div("coluna-divs", "coluna");
									colunaDivs.adicionarEstilo("display", "flex");
									colunaDivs.adicionarEstilo("flex-direction", "column");
									colunaDivs.adicionarEstilo("width", larguraPorcentagemColuna + "%");
									colunaDivs.adicionarEstilo("height", "100%");
									colunaDivs.adicionarEstilo("gap", "10px");

									// Calcula a porcentagem de altura proporcional para cada Div criada
									int alturaPorcentagemDiv = qtdDivs > 0 ? 100 / qtdDivs : 100;

									for (int i = 1; i <= qtdDivs; i++) {
										Div divFilha = new Div("div-filha-" + i, "caixa-filha");
										divFilha.adicionarEstilo("height", alturaPorcentagemDiv + "%");
										divFilha.adicionarEstilo("background-color", "#e0e0e0");
										divFilha.adicionarFilho(
												new Paragrafo("p-" + i, "texto", "Conteúdo da Div " + i));
										colunaDivs.adicionarFilho(divFilha);
									}

									// Coluna de Formulários
									Div colunaForms = new Div("coluna-forms", "coluna");
									colunaForms.adicionarEstilo("display", "flex");
									colunaForms.adicionarEstilo("flex-direction", "column");
									colunaForms.adicionarEstilo("width", larguraPorcentagemColuna + "%");
									colunaForms.adicionarEstilo("height", "100%");
									colunaForms.adicionarEstilo("gap", "15px");

									for (int f = 1; f <= qtdForms; f++) {
										System.out.println("\n--- CONFIGURAÇÃO DO FORMULÁRIO " + f + " ---");
										System.out.print("Quantos inputs este formulário terá? ");
										int qtdInputs = scn.nextInt();
										scn.nextLine();

										Formulario form = new Formulario("form-" + f, "formulario-estilizado",
												"/enviar", "POST");
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

									// Montagem do Layout Principal de acordo com a opção escolhida
									if (posicao == 1) {
										containerPrincipal.adicionarFilho(colunaDivs);
										containerPrincipal.adicionarFilho(colunaForms);
									} else {
										containerPrincipal.adicionarFilho(colunaForms);
										containerPrincipal.adicionarFilho(colunaDivs);
									}

									// --- GERAÇÃO E ARMAZENAMENTO NA STRING ---

									StringBuilder sb = new StringBuilder();
									sb.append("<!-- HTML Gerado Dinamicamente -->\n");
									sb.append(containerPrincipal.renderizar(0));

									// Variável contendo o código final como String
									String htmlGerado = sb.toString();

									// Exemplo de uso da String criada:
									System.out.println("\n=== HTML GERADO EM STRING COM SUCESSO ===");
									System.out.println(htmlGerado);
									sistema.projetos.get(Projeto.quantProjetos).setProjetoCodigo(htmlGerado);

									break;

								}

							} while (resposta != 0);

							break;


						case 3:
//							System.out.println("\n--- Lista de projetos: ---");
//							System.out.println("OBS.: apenas os projetos publicos serao mostrados.");
//							System.out.println("|COD.\t |NOME\t      |PROPRIETARIO(A)\t      ");
//							for (int i = 0; i < sistema.projetos.size(); i++) {
//								if (sistema.projetos.get(i) != null && sistema.projetos.get(i).getPrivacidade() == "Publico") {
//									System.out.printf("%-8s %-20s %-16s%n", sistema.projetos.get(i).getCdProjeto(), 
//											sistema.projetos.get(i).getNmProjeto(), sistema.projetos.get(i).getProprietario());
//								}
//							}
							uiprojeto.listar();
							break;
							
						case 4:
//							System.out.println();
//							System.out.println("================================================================");
//							System.out.println("--- Procurar projeto por codigo: ---");
//							System.out.println("OBS.: o projeto so sera mostrado se for publico.");
//							System.out.print("\nInsira o codigo da venda que voce esta procurando: ");
//							int codProjeto = scn.nextInt();
//
//							Projeto projetoCod = sistema.projetoPorCod(codProjeto);
//
//							System.out.println("\n--- Projeto do codigo " + codProjeto + " : ---");
//							System.out.println("|COD.\t |NOME\t      |PROPRIETARIO(A)\t      ");
//
//							if (projetoCod != null) {
//								System.out.printf("%-8s %-20s %-16s%n", codProjeto, projetoCod.getNmProjeto(),
//										projetoCod.getProprietario());
//
//							} else {
//								System.out.println("Codigo nao encontrado ou projeto Privado.");
//							}
							uiprojeto.projetoPorCodigo();
							break;
							
						case 5:
//							System.out.println("===============================================================");
//							System.out.println("\nAVISO: Apenas o proprietario do projeto pode excui-lo.");
//							System.out.print("\nCodigo do projeto: ");
//							int codigoProjeto = scn.nextInt();
//							System.out.print("\nNome do proprietario: ");
//							String nomeProprietario = scn.next();
//							System.out.print("\nSenha do proprietario: ");
//							String senhaProprietario = scn.next();
//
//							String excluiProjeto = sistema.excluirProjeto(codigoProjeto, nomeProprietario, senhaProprietario);
//							System.out.println(excluiProjeto);
							uiprojeto.excluir();
							break;
						}

					} while (operacao != 0);

					break;

					
				}

			} while (escolha != 0);

		} while (logar != 0);

//    	//EXEMPLO 1
//       Div containerPrincipal = new Div("main-container", "box-container");
//        containerPrincipal.adicionarEstilo("border", "2px solid black");
//        containerPrincipal.adicionarEstilo("padding", "20px");
//
//        Div card = new Div("card-1", "card");
//        card.adicionarEstilo("background-color", "#f0f0f0");
//
//        Paragrafo p1 = new Paragrafo("p1", "texto-destaque", "Olá, mundo da POO!");
//        card.adicionarFilho(p1);
//
//        containerPrincipal.adicionarFilho(card);
//
//        // Imprime a estrutura HTML gerada no console
//        System.out.println(containerPrincipal.renderizar(0));

		// 1. Container Principal (Flexbox em linha)

//===================================================================================================

//    	//EXEMPLO 2 
//        Div containerPrincipal = new Div("main-container", "flex-container");
//        containerPrincipal.adicionarEstilo("display", "flex");
//        containerPrincipal.adicionarEstilo("gap", "20px");
//        containerPrincipal.adicionarEstilo("width", "100%");
//        containerPrincipal.adicionarEstilo("height", "400px");
//
//        // 2. Coluna da Esquerda: Contêiner para as duas DIVs empilhadas (Flexbox em coluna)
//        Div colunaEsquerda = new Div("coluna-esquerda", "coluna");
//        colunaEsquerda.adicionarEstilo("display", "flex");
//        colunaEsquerda.adicionarEstilo("flex-direction", "column");
//        colunaEsquerda.adicionarEstilo("width", "50%");
//        colunaEsquerda.adicionarEstilo("height", "100%");
//        colunaEsquerda.adicionarEstilo("gap", "10px");
//
//        // DIV Superior (50% da altura)
//        Div divSuperior = new Div("div-sup", "caixa-filha");
//        divSuperior.adicionarEstilo("height", "50%");
//        divSuperior.adicionarEstilo("background-color", "#e0e0e0");
//        divSuperior.adicionarFilho(new Paragrafo("p-sup", "texto", "Div Superior (50% de altura)"));
//
//        // DIV Inferior (50% da altura)
//        Div divInferior = new Div("div-inf", "caixa-filha");
//        divInferior.adicionarEstilo("height", "50%");
//        divInferior.adicionarEstilo("background-color", "#d0d0d0");
//        divInferior.adicionarFilho(new Paragrafo("p-inf", "texto", "Div Inferior (50% de altura)"));
//
//        // Adiciona as duas DIVs na coluna da esquerda
//        colunaEsquerda.adicionarFilho(divSuperior);
//        colunaEsquerda.adicionarFilho(divInferior);
//
//        // 3. Coluna da Direita: Formulário com 3 Inputs (50% da largura)
//        Formulario form = new Formulario("form-cadastro", "formulario-estilizado", "/enviar", "POST");
//        form.adicionarEstilo("width", "50%");
//        form.adicionarEstilo("height", "100%");
//        form.adicionarEstilo("display", "flex");
//        form.adicionarEstilo("flex-direction", "column");
//        form.adicionarEstilo("gap", "15px");
//        form.adicionarEstilo("background-color", "#f9f9f9");
//        form.adicionarEstilo("padding", "20px");
//
//        Input inputNome = new Input("input-nome", "campo", "text", "nome", "Digite seu nome");
//        Input inputEmail = new Input("input-email", "campo", "email", "email", "Digite seu e-mail");
//        Input inputSenha = new Input("input-senha", "campo", "password", "senha", "Digite sua senha");
//
//        form.adicionarFilho(inputNome);
//        form.adicionarFilho(inputEmail);
//        form.adicionarFilho(inputSenha);
//        // 4. Monta a estrutura final
//        containerPrincipal.adicionarFilho(colunaEsquerda);
//        containerPrincipal.adicionarFilho(form);
//
//        // Renderiza no console
//        System.out.println(containerPrincipal.renderizar(0));

		// EXEMPLO DE ENTRADA QUE O USUÁRIO DEVERÁ FAZER NO CONSOLE:
		// 2 -> Quantidade de Divs que deseja criar na coluna de Divs
		// 1 -> Quantidade de Formulários a criar
		// 3 -> Quantidade de inputs no Formulário 1
		// Digite seu nome -> Placeholder do Input 1
		// Digite seu e-mail -> Placeholder do Input 2
		// Digite sua senha -> Placeholder do Input 3
		// 1 -> Posicionamento: 1 (Divs na Esquerda) ou 2 (Divs na Direita)
		// 400 -> Altura do container principal em px
		// =========================================================================

//		System.out.println("=== CONFIGURADOR DE LAYOUT DINÂMICO ===");
//
//		// 1. Definição da quantidade de Divs
//		System.out.print("\nQuantas Divs deseja criar empilhadas na coluna? ");
//		int qtdDivs = scn.nextInt();
//
//		// 2. Definição dos Formulários e seus Inputs
//		System.out.print("Quantos Formulários deseja criar? ");
//		int qtdForms = scn.nextInt();
//		scn.nextLine(); // Consome a quebra de linha do scanner
//
//		// 3. Posicionamento e Altura
//		System.out.println("\nQual a disposição no container principal?");
//		System.out.println("1 - Coluna de Divs na Esquerda | Formulários na Direita");
//		System.out.println("2 - Formulários na Esquerda | Coluna de Divs na Direita");
//		System.out.print("Opção: ");
//		int posicao = scn.nextInt();
//
//		System.out.print("\nQual a altura total do container (em px)? ");
//		int alturaPx = scn.nextInt();
//		scn.nextLine();
//
//		// --- CONSTRUÇÃO DA ESTRUTURA HTML ---
//
//		// Container Principal
//		Div containerPrincipal = new Div("main-container", "flex-container");
//		containerPrincipal.adicionarEstilo("display", "flex");
//		containerPrincipal.adicionarEstilo("gap", "20px");
//		containerPrincipal.adicionarEstilo("width", "100%");
//		containerPrincipal.adicionarEstilo("height", alturaPx + "px");
//
//		// Coluna de Divs
//		Div colunaDivs = new Div("coluna-divs", "coluna");
//		colunaDivs.adicionarEstilo("display", "flex");
//		colunaDivs.adicionarEstilo("flex-direction", "column");
//		colunaDivs.adicionarEstilo("width", "50%");
//		colunaDivs.adicionarEstilo("height", "100%");
//		colunaDivs.adicionarEstilo("gap", "10px");
//
//		// Calcula a porcentagem de altura proporcional para cada Div criada
//		int alturaPorcentagemDiv = qtdDivs > 0 ? 100 / qtdDivs : 100;
//
//		for (int i = 1; i <= qtdDivs; i++) {
//			Div divFilha = new Div("div-filha-" + i, "caixa-filha");
//			divFilha.adicionarEstilo("height", alturaPorcentagemDiv + "%");
//			divFilha.adicionarEstilo("background-color", "#e0e0e0");
//			divFilha.adicionarFilho(new Paragrafo("p-" + i, "texto", "Conteúdo da Div " + i));
//			colunaDivs.adicionarFilho(divFilha);
//		}
//
//		// Coluna de Formulários
//		Div colunaForms = new Div("coluna-forms", "coluna");
//		colunaForms.adicionarEstilo("display", "flex");
//		colunaForms.adicionarEstilo("flex-direction", "column");
//		colunaForms.adicionarEstilo("width", "50%");
//		colunaForms.adicionarEstilo("height", "100%");
//		colunaForms.adicionarEstilo("gap", "15px");
//
//		for (int f = 1; f <= qtdForms; f++) {
//			System.out.println("\n--- CONFIGURAÇÃO DO FORMULÁRIO " + f + " ---");
//			System.out.print("Quantos inputs este formulário terá? ");
//			int qtdInputs = scn.nextInt();
//			scn.nextLine();
//
//			Formulario form = new Formulario("form-" + f, "formulario-estilizado", "/enviar", "POST");
//			form.adicionarEstilo("display", "flex");
//			form.adicionarEstilo("flex-direction", "column");
//			form.adicionarEstilo("gap", "10px");
//			form.adicionarEstilo("background-color", "#f9f9f9");
//			form.adicionarEstilo("padding", "15px");
//
//			for (int inp = 1; inp <= qtdInputs; inp++) {
//				System.out.print("Texto do placeholder para o Input " + inp + ": ");
//				String placeholder = scn.nextLine();
//
//				Input input = new Input("input-" + f + "-" + inp, "campo", "text", "campo_" + f + "_" + inp,
//						placeholder);
//				form.adicionarFilho(input);
//			}
//			colunaForms.adicionarFilho(form);
//		}
//
//		// Montagem do Layout Principal de acordo com a opção escolhida
//		if (posicao == 1) {
//			containerPrincipal.adicionarFilho(colunaDivs);
//			containerPrincipal.adicionarFilho(colunaForms);
//		} else {
//			containerPrincipal.adicionarFilho(colunaForms);
//			containerPrincipal.adicionarFilho(colunaDivs);
//		}
//
//		// Renderização do HTML gerado
//		System.out.println("\n=== HTML GERADO COM SUCESSO ===");
//		System.out.println(containerPrincipal.renderizar(0));

	}
	
	static void cadastrarUsuario() {
		System.out.println("\n================================================================");
		System.out.println("----- CADASTRO: ----");
		
		
		System.out.print("Nome: ");
		String nome = scn.next();
		System.out.print("Nome de usuário (login): ");
		String nomeUsuario = scn.next();
		if (sistema.existeNomeUsuario(nomeUsuario)) {
			System.out.println("Login já cadastrado!");
			return;
		}
		System.out.print("Senha: ");
		String senha = scn.next();

		Usuario novoUsuario = Usuario.getInstance(nome, senha);
		if (novoUsuario != null && sistema.addUsuario(novoUsuario))
			System.out.println("Usuário cadastrado com sucesso!");
		else
			System.out.println("Falha em cadastrar usuário.");
		
	} 
	
	static void menuPrincipal() {
		
	}
	
	static void listarUsuarios() {
		System.out.println("\n--- Lista de usuarios cadastrados: ---");
		System.out.println("|COD.\t |NOME\t");
		for (int i = 0; i < sistema.getUsuarios().size(); i++) {
			if (sistema.getUsuarios().get(i) != null) {
				System.out.printf("%-8s %-20s%n", sistema.getUsuarios().get(i).getCdUsuario(),
						sistema.getUsuarios().get(i).getNmUsuario());
			}
		}
	}
}
